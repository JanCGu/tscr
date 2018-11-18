package de.tarent.challenge.display;

import de.tarent.challenge.domain.IProduct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.naming.ServiceUnavailableException;
import org.javamoney.moneta.Money;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests the creation, update, get and delete of an cart. This is an integration
 * test and therefore tests the user stories. Not all bad cases are testet as
 * they are part of the unit tests.
 *
 * @author jan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartControllerIntegrationTest {

    @Autowired
    private CartController cc;

    @Autowired
    private ProductController pc;

    /**
     * Tests the creat, read, update and delete operations for a card.This is the Integration test to the Issue #4.
     *
     * @throws javax.naming.ServiceUnavailableException
     */
    @Test
    public void testCRUDofCartWithExsitingProduct() throws ServiceUnavailableException, IllegalAccessException {
        //setup
        List<ProductModel> products1 = new ArrayList<>();
        List<ProductModel> p1Andp2 = new ArrayList<>();
        Set<String> eans = new HashSet<>();
        eans.add("ean1");
        ProductModel p1 = new ProductModel("skuTest1", "name1", eans, Money.of(1.0, "EUR"),true);
        ProductModel p2 = new ProductModel("skuTest2", "name2", eans, Money.of(1.0, "EUR"),true);
        products1.add(p1);
        p1Andp2.add(p1);
        p1Andp2.add(p2);
        List<IProduct> iproducts1 = convert(products1);
        List<IProduct> iproducts2 = convert(p1Andp2);

        assertNull("A test product 'skuTest1' may not exit in the storage!", pc.retrieveProductBySku("skuTest1"));
        assertNull("A test product 'skuTest2' may not exit in the storage!", pc.retrieveProductBySku("skuTest2"));
        pc.updateProducts(p1Andp2);
        Set<CartModel> carts = new HashSet<>();
        CartModel cart = new CartModel("testCart", iproducts1);
        carts.add(cart);
        assertNull("A test cart may not exist in the storage!", cc.retriveCartById("testCart"));

        //create
        assertTrue("Cart added to storage", cc.updateCart(carts));

        //read
        assertEquals("Test cart retrived from storage", cart, cc.retriveCartById("testCart"));

        //update - add other products.
        cart.addProducts(iproducts2);
        assertTrue("Carts updated", cc.updateCart(carts));
        List<IProduct>  dbp = cc.retriveCartById("testCart").getProducts();
        assertEquals("Expected three products in card: p1,p1,p2",dbp.size(), 3);
        
        //delete
        assertTrue("Cart deleted", cc.deleteCart(carts));
        assertNull("Cart does not exist in storage", cc.retriveCartById("testCart"));

        //cleanup
        pc.deleteProducts(p1Andp2);
        assertNull("The test product 'skuTest1' was removed from the storage!", pc.retrieveProductBySku("skuTest1"));
        assertNull("The test product 'skuTest2 'was removed from the storage!", pc.retrieveProductBySku("skuTest2"));
    }

    private List<IProduct> convert(List<ProductModel> toConvert) {
        return toConvert.stream().map(p -> (IProduct) p).collect(Collectors.toList());
    }

    /**
     * At the moment JPA should create a new product if it is passed through the
     * cart and the cart is stored, but it is unclear if this behaviour is
     * intended!
     *
     * See Issue #4 and Issue #17.
     */
    @Test
    public void testCRUDofCartWithNewProduct() {
        assertTrue("Unclear if this is an actual requirement or a side effect!", true);
    }
}
