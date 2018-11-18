package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.General.GeneralCartTests;
import org.junit.Test;

/**
 *
 * @author Jan
 */
public class CartDTOTest {

    @Test
    public void testVariableCreation() {
        GeneralCartTests.testCreationOfCart(ac -> new CartDTO(ac.id, ac.products));
    }

    @Test
    public void testInterfaceCreation() {
        GeneralCartTests.testCreationOfCart(ac -> new CartDTO(ac.mockCart));
    }

    @Test
    public void testOperationOnCart() throws IllegalArgumentException, IllegalAccessException {
        GeneralCartTests.testProductMovementInCart((id, products) -> new CartDTO(id, products));
    }

    @Test
    public void testCheckOut() {
        GeneralCartTests.testCheckedOutBehaviour((id, products) -> new CartDTO(id, products));
    }

    @Test
    public void testNonAvailableProducts() throws IllegalAccessException {
        GeneralCartTests.testProductAvailabilityBehaviour((id, products) -> new CartDTO(id, products));
    }

    @Test
    public void testEquality() {
        GeneralCartTests.testCartEquality((id, products) -> new CartDTO(id, products));
    }
}
