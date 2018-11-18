package de.tarent.challenge.domain;

import de.tarent.challenge.domain.General.GeneralCartTests;
import org.junit.Test;

/**
 * Unit tests for the domain cart entity.
 *
 * @author Jan
 */
public class CartTest {

    @Test
    public void testVariableCreation() {
        GeneralCartTests.testCreationOfCart(ac -> new Cart(ac.id, ac.products));
    }

    @Test
    public void testInterfaceCreation() {
        GeneralCartTests.testCreationOfCart(ac -> new Cart(ac.mockCart));
    }

    @Test
    public void testOperationOnCart() throws IllegalArgumentException, IllegalAccessException {
        GeneralCartTests.testProductMovementInCart((id, products) -> new Cart(id, products));
    }

    @Test
    public void testCheckOut() {
        GeneralCartTests.testCheckedOutBehaviour((id, products) -> new Cart(id, products));
    }

    @Test
    public void testNonAvailableProducts() throws IllegalAccessException {
        GeneralCartTests.testProductAvailabilityBehaviour((id, products) -> new Cart(id, products));
    }

    @Test
    public void testEquality() {
        GeneralCartTests.testCartEquality((id, products) -> new Cart(id, products));
    }
}
