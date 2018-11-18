package de.tarent.challenge.domain.General;

import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.domain.IProduct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.javamoney.moneta.Money;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Contains general tests for the behaviour which should be shared for all
 * carts.
 *
 * The behaviour should correspond to the Liskov substitution principle.
 *
 * @author jan
 */
public class GeneralCartTests {

    /**
     * To carts should be equal if their id is equal.
     *
     * @param getCart
     */
    public static void testCartEquality(BiFunction<String, List<IProduct>, ICart> getCart) {
        cartSetup cs = setupCart(getCart);
        ICart c1 = cs.cart;
        ICart c2 = getCart.apply(c1.getId(), cs.p2Andp3);
        ICart c3 = getCart.apply("nope", c1.getProducts());
        assertEquals("The same id means it is the same cart.",c1,c2);
        assertFalse("Diffrent id means diffrent carts",c1.equals(c3));
        
    }

    /**
     * Tests what happens if a product which is available is added and removed
     * from the cart. Also tests what happens if a not available product is
     * added and removed.
     *
     * @param getCart the icart created through this function will be tested.
     */
    public static void testProductAvailabilityBehaviour(BiFunction<String, List<IProduct>, ICart> getCart) throws IllegalAccessException {
        cartSetup cs = setupCart(getCart);
        ICart validCart = cs.cart;

        testAddNotAvailableProduct(validCart, cs.pNotAvailable);

        boolean exception = tryRemoveProduct(validCart, cs.pNotAvailable);
        assertFalse("A not existing product should do nothing, even if it is not valid.", exception);

        ICart oldCart = getCart.apply("old cart", cs.pNotAvailable);
        oldCart.addProducts(cs.p1);//ensure that at least 1 product is allways in the cart.

        testAddNotAvailableProduct(oldCart, cs.pNotAvailable);

        exception = tryRemoveProduct(validCart, cs.pNotAvailable);
        assertFalse("A not available product should still be capable of removing from the oldCart", exception);
    }

    private static boolean tryRemoveProduct(ICart cart, List<IProduct> products) throws IllegalAccessException {

        boolean exception = false;
        try {
            cart.removeProducts(products);
        } catch (IllegalArgumentException ex) {
            exception = true;
        }
        return exception;
    }

    private static void testAddNotAvailableProduct(ICart cart, List<IProduct> emptyProduct) throws IllegalAccessException {
        boolean exception = false;
        try {
            cart.addProducts(emptyProduct);
        } catch (IllegalArgumentException ex) {
            exception = true;
        }
        assertTrue("A not avaialble Product cannot be added to a cart.", exception);
    }

    /**
     * Tests what happens if a product is marked as checked out. A Cart
     * shouldn't be allowed to be modified after checkout.
     *
     * This only tests the behaviour of a checked out cart as the "normal"
     * behaviour should be tested in testProductMovementInCart.
     *
     * @param getCart The created ICart will be tested.
     */
    public static void testCheckedOutBehaviour(BiFunction<String, List<IProduct>, ICart> getCart) {
        cartSetup sc = setupCart(getCart);
        ICart cart = sc.cart;
        boolean threwException = false;

        cart.checkOut();
        assertTrue("Cart is checked out", cart.getCheckedOut());

        try {
            cart.addProducts(sc.p2Andp3);
        } catch (IllegalAccessException ex) {
            threwException = true;
        }
        assertTrue("Adding should throw an IllegalAccess Exception", threwException);
        assertTrue("Cart is checked out (2).", cart.getCheckedOut());

        threwException = false;
        try {
            cart.removeProducts(sc.p2Andp3);
        } catch (IllegalAccessException ex) {
            threwException = true;
        }
        assertTrue("Removing should throw an IllegalAccess Exception", threwException);
        assertTrue("Cart is checked out (3).", cart.getCheckedOut());

    }

    /**
     * Returns an array of mock procuts. All mock Products are valid products,
     * but the 4. has no price.
     *
     * @return
     */
    private static MockProduct[] getMockProducts() {
        Set<String> eans1 = new HashSet<>();
        eans1.add("ean1");
        MockProduct[] ret = {
            new MockProduct("sku1", "name1", eans1, Money.of(1, "EUR"), true),
            new MockProduct("sku1", "name1", eans1, Money.of(3, "EUR"), true),
            new MockProduct("sku1", "name1", eans1, Money.of(2, "EUR"), true),
            new MockProduct("sku1", "name1", eans1, null, true),
            new MockProduct("sku1", "name1", eans1, Money.of(4, "EUR"), false)
        };
        return ret;
    }

    private static cartSetup setupCart(BiFunction<String, List<IProduct>, ICart> getCart) {
        cartSetup ret = new cartSetup();
        ret.mockProducts = getMockProducts();

        ret.pNullPrice = new ArrayList<>();
        ret.pNullPrice.add(ret.mockProducts[3]);
        ret.p1 = new ArrayList<>();
        ret.p1.add(ret.mockProducts[0]);
        ret.p2Andp3 = new ArrayList<>();
        ret.p2Andp3.add(ret.mockProducts[1]);
        ret.p2Andp3.add(ret.mockProducts[2]);
        ret.p3Andp2 = new ArrayList<>();
        ret.p3Andp2.add(ret.mockProducts[2]);
        ret.p3Andp2.add(ret.mockProducts[1]);
        ret.pNotAvailable = new ArrayList<>();
        ret.pNotAvailable.add(ret.mockProducts[4]);

        ret.cart = getCart.apply("test cart", ret.p1);
        return ret;
    }

    /**
     * Tests what happens if products are added and removed. The price should
     * update!
     *
     * @param getCart creates the ICart which will then be testeted.
     */
    public static void testProductMovementInCart(BiFunction<String, List<IProduct>, ICart> getCart)
            throws IllegalArgumentException, IllegalAccessException {

        cartSetup sc = setupCart(getCart);
        ICart cart = sc.cart;
        cart.addProducts(sc.p2Andp3);
        assertEquals("p1+p2+p3", Money.of(6, "EUR"), cart.getTotalPrice());

        //order of removal should not matter:
        cart.removeProducts(sc.p3Andp2);
        assertEquals("order of removal should not matter", Money.of(1, "EUR"), cart.getTotalPrice());

        cart.removeProducts(sc.p3Andp2);
        assertEquals("not contained products should not be removed", Money.of(1, "EUR"), cart.getTotalPrice());
        cart.addProducts(sc.p3Andp2);
        assertEquals("p1+p2+p3", Money.of(6, "EUR"), cart.getTotalPrice());
        cart.addProducts(sc.p1);
        assertEquals("p1 is twice in the cart.", Money.of(7, "EUR"), cart.getTotalPrice());//2*p1+p2+p3
        cart.removeProducts(sc.p2Andp3);
        assertEquals(Money.of(2, "EUR"), cart.getTotalPrice());

        cart.removeProducts(sc.p1);
        assertEquals("Only p1 as a product should be left", Money.of(1, "EUR"), cart.getTotalPrice());

        //Test null operation
        cart.removeProducts(null);
        assertEquals(Money.of(1, "EUR"), cart.getTotalPrice());
        assertTrue("only p1 remains", cart.getProducts().size() == 1);
        cart.addProducts(null);
        assertEquals(Money.of(1, "EUR"), cart.getTotalPrice());
        assertTrue("only p1 remains", cart.getProducts().size() == 1);

        try {
            cart.addProducts(sc.pNullPrice);
            assertTrue("A product without a price cannot be added to a cart! What happens if it is the only one?", false);
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
        assertEquals("products without a price shouldn't influence it", Money.of(1, "EUR"), cart.getTotalPrice());
        assertTrue("only p1 as adding pEmpty failed", cart.getProducts().size() == 1);

        try {
            cart.removeProducts(sc.pNullPrice);
            assertTrue("A product without a price cannot be removed from a cart! They didn't exist in the first place.", false);
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
        assertEquals("products without a price shouldn't influence it", Money.of(1, "EUR"), cart.getTotalPrice());
        assertTrue("only p1 remains", cart.getProducts().size() == 1);

        try {
            cart.removeProducts(sc.pNullPrice);
            assertTrue("zero products in the card threw no error.", false);
        } catch (IllegalArgumentException ex) {
            assertTrue("zero products in the card threw an error.", true);
        }

    }

    public static void testCreationOfCart(Consumer<AssertCart> create) {
        GeneralCreationTests.testCreationOfProduct(() -> createAsserts(), create);
    }

    public static ArrayList<AssertCart> createAsserts() {
        ArrayList<AssertCart> ret = new ArrayList<>();
        Set<String> eans1 = new HashSet<>();
        eans1.add("ean1");
        Money m1 = Money.of(1, "EUR");
        MockProduct p1 = new MockProduct("sku1", "name1", eans1, m1, true);
        MockProduct moneyless = new MockProduct("sku1", "name1", eans1, null, true);
        List<IProduct> p1s = new ArrayList<>();
        p1s.add(p1);
        List<IProduct> mls = new ArrayList<>();
        mls.add(moneyless);
        List<IProduct> mls2 = new ArrayList<>();
        mls2.add(p1);
        mls2.add(moneyless);
        mls2.add(p1);
        ret.add(new AssertCart(null, p1s, m1, false, true, true));
        ret.add(new AssertCart("a1", null, m1, false, true, true));
        ret.add(new AssertCart("a1", mls, m1, false, true, true));
        ret.add(new AssertCart("a1", mls2, m1, false, true, true));
        ret.add(new AssertCart("a1", p1s, m1, false, true, false));

        return ret;
    }
}

/**
 * Container for the creation of a cart and products with which the cart will be
 * tested.
 *
 * @author jan
 */
class cartSetup {

    public ICart cart;
    public MockProduct[] mockProducts;
    public List<IProduct> pNullPrice;
    public List<IProduct> pNotAvailable;
    public List<IProduct> p1;
    public List<IProduct> p2Andp3;
    public List<IProduct> p3Andp2;

}
