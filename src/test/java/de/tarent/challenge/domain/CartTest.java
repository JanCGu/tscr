/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.javamoney.moneta.Money;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Jan
 */
public class CartTest {

    @Test
    public void testVariableCreation() {
        testCreationOfCart(ac -> new Cart(ac.id, ac.products));
    }

    @Test
    public void testInterfaceCreation() {
        testCreationOfCart(ac -> new Cart(ac.mockCart));
    }

    @Test
    public void testOperationOnCart() {
        testProductMovementInCart((id, products) -> new Cart(id, products));
    }

    /**
     * Tests what happens if products are added and removed. The price should
     * update!
     *
     * @param getCart creates the ICart which will then be testeted.
     */
    public static void testProductMovementInCart(BiFunction<String, List<IProduct>, ICart> getCart) {

        Set<String> eans1 = new HashSet<>();
        eans1.add("ean1");
        MockProduct p1 = new MockProduct("sku1", "name1", eans1, Money.of(1, "EUR"));
        MockProduct p2 = new MockProduct("sku1", "name1", eans1, Money.of(2, "EUR"));
        MockProduct p3 = new MockProduct("sku1", "name1", eans1, Money.of(3, "EUR"));
        List<IProduct> p1s = new ArrayList<>();
        p1s.add(p1);
        ICart cart = getCart.apply("test cart", p1s);
        List<IProduct> toAdd = new ArrayList<>();
        toAdd.add(p2);
        toAdd.add(p3);
        cart.addProducts(toAdd);
        assertEquals("p1+p2+p3", Money.of(6, "EUR"), cart.getTotalPrice());

        //order of removal should not matter:
        List<IProduct> toRemove = new ArrayList<>();
        toRemove.add(p3);
        toRemove.add(p2);
        cart.removeProducts(toRemove);
        assertEquals("order of removal should not matter", Money.of(1, "EUR"), cart.getTotalPrice());

        cart.removeProducts(toRemove);
        assertEquals("not contained products should not be removed", Money.of(1, "EUR"), cart.getTotalPrice());
        cart.addProducts(toAdd);
        assertEquals("p1+p2+p3", Money.of(6, "EUR"), cart.getTotalPrice());
        cart.addProducts(p1s);
        assertEquals("p1 is twice in the cart.", Money.of(7, "EUR"), cart.getTotalPrice());//2*p1+p2+p3
        cart.removeProducts(toRemove);
        assertEquals(Money.of(2, "EUR"), cart.getTotalPrice());

        cart.removeProducts(p1s);
        assertEquals("Only p1 as a product should be left",Money.of(1, "EUR"), cart.getTotalPrice());

        //Test null operation
        cart.removeProducts(null);
        assertEquals(Money.of(1, "EUR"), cart.getTotalPrice());
        assertTrue("only p1 remains", cart.getProducts().size() == 1);
        cart.addProducts(null);
        assertEquals(Money.of(1, "EUR"), cart.getTotalPrice());
        assertTrue("only p1 remains", cart.getProducts().size() == 1);

        MockProduct pEmpty = new MockProduct("sku1", "name1", eans1, null);
        List<IProduct> pEmtys = new ArrayList<>();
        pEmtys.add(pEmpty);
        cart.addProducts(pEmtys);
        assertEquals("products without a price shouldn't influence it", Money.of(1, "EUR"), cart.getTotalPrice());
        assertTrue("p1+pEmpty", cart.getProducts().size() == 2);
        cart.removeProducts(pEmtys);
        assertEquals("products without a price shouldn't influence it", Money.of(1, "EUR"), cart.getTotalPrice());
        assertTrue("only p1 remains", cart.getProducts().size() == 1);

        try {
            cart.removeProducts(p1s);
            assertTrue("zero products in the card threw no error.", false);
        } catch (IndexOutOfBoundsException ex) {
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
        MockProduct p1 = new MockProduct("sku1", "name1", eans1, m1);
        List<IProduct> p1s = new ArrayList<>();
        p1s.add(p1);
        ret.add(new AssertCart(null, p1s, m1, true, true));
        ret.add(new AssertCart("a1", null, m1, true, true));
        ret.add(new AssertCart("a1", p1s, m1, true, false));

        return ret;
    }
}
