package de.tarent.challenge.display;

import de.tarent.challenge.domain.CartTest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan
 */
public class CartModelTest {
    
    /**
     * Does nothing special, as a CartModel is a 1:1 copy of Cart.
     * 
     * Only for later use and change!
     */
    @Test
    public void testCartModel() {
        CartTest ct = new CartTest();
        ct.testInterfaceCreation();
        ct.testVariableCreation();
        ct.testOperationOnCart();
        
    }
}
