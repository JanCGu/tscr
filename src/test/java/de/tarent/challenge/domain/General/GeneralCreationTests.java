package de.tarent.challenge.domain.General;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Contains generic Tests for the creation of domain entities.
 *
 * @author Jan
 */
public class GeneralCreationTests {

    /**
     * Allows to test the creation of a generic entity T according to the
     * expected behavior any product should display.
     *
     * The creation of the product can only fail with an
     * IllegalArgumentException.
     *
     * @param <Assert> allows a generic impementation of everytype who
     * implements the IAssert interface.
     * @param getAsserts returns the assertcases used as input for the creation
     * in order to test it.
     * @param create creates a domain class with the input from IAssert. The
     * creation may throw an IllegalArgumentException.
     */
    public static <Assert extends IAssert> void testCreationOfProduct(Supplier<ArrayList<Assert>> getAsserts, Consumer<Assert> create) {
        ArrayList<Assert> toAssert = getAsserts.get();
        toAssert.forEach(ta -> {
            try {
                System.out.println(ta);
                create.accept(ta);
                assertFalse(ta.expectedToFail());
                System.out.println("assertFalse evaluated");
            } catch (IllegalArgumentException ex) {
                assertTrue(ta.expectedToFail());
                System.out.println("assertTrue for IllegalArgument evaluated");
            }
        });
    }

}
