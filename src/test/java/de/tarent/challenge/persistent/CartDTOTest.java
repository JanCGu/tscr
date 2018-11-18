package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.CartTest;
import org.junit.Test;

/**
 *
 * @author Jan
 */
public class CartDTOTest {

    @Test
    public void testVariableCreation() {
        CartTest.testCreationOfCart(ac -> new CartDTO(ac.id, ac.products));
    }

    @Test
    public void testInterfaceCreation() {
        CartTest.testCreationOfCart(ac -> new CartDTO(ac.mockCart));
    }

    @Test
    public void testOperationOnCart() throws IllegalArgumentException, IllegalAccessException {
        CartTest.testProductMovementInCart((id, products) -> new CartDTO(id, products));
    }

    @Test
    public void testCheckOut() {
        CartTest.testCheckedOutBehaviour((id, products) -> new CartDTO(id, products));
    }
}
