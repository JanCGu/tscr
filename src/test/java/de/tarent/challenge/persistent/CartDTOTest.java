package de.tarent.challenge.persistent;

import static de.tarent.challenge.domain.CartTest.testCreationOfCart;
import static de.tarent.challenge.domain.CartTest.testProductMovementInCart;
import org.junit.Test;

/**
 *
 * @author Jan
 */
public class CartDTOTest {
    
    @Test
    public void testVariableCreation() {
        testCreationOfCart(ac -> new CartDTO(ac.id, ac.products));
    }

    @Test
    public void testInterfaceCreation() {
        testCreationOfCart(ac -> new CartDTO(ac.mockCart));
    }

    @Test
    public void testOperationOnCart() throws IllegalArgumentException, IllegalAccessException {
        testProductMovementInCart((id, products) -> new CartDTO(id, products));
    }
}
