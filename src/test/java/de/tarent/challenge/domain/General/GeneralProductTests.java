package de.tarent.challenge.domain.General;

import de.tarent.challenge.domain.IProduct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import org.javamoney.moneta.Money;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Contains tests for the behaviour which should be shared through all products.
 *
 * This Test ensures the Liskov substitution principle.
 *
 * @author jan
 */
public class GeneralProductTests {

    /**
     * Tests if the iproducts created through create behave as expected from an
     * product in regard to the availabilty.
     *
     * @param create the output of the creation will be tested.
     */
    public static void testAvailablity(Function<IProduct, IProduct> create) {
        Set<String> e1 = new HashSet<>();
        e1.add("e1");
        MockProduct p1 = new MockProduct("sku", "n1", e1, Money.of(1, "EUR"), true);
        IProduct toTest = create.apply(p1);

        assertTrue("A new product should be availble", toTest.getAvailable());
        toTest.setAvailable(false);
        assertFalse("Changed availbility to false", toTest.getAvailable());
        toTest.setAvailable(true);
        assertTrue("Changed availbility to true", toTest.getAvailable());
    }

    /**
     * Tests if the iproducts created through create behave as expected from an
     * product in regard to the equality.
     *
     * @param create the output of the creation will be tested.
     */
    public static void testEqualityOfProduct(Function<IProduct, IProduct> create) {
        Set<String> e1 = new HashSet<>();
        e1.add("e1");
        MockProduct p1 = new MockProduct("sku", "n1", e1, Money.of(1, "EUR"), true);
        Set<String> e2 = new HashSet<>();
        e2.add("e2");
        MockProduct p2 = new MockProduct("sku", "n2", e2, Money.of(2, "EUR"), false);
        MockProduct p3 = new MockProduct("sku2", "n1", e1, Money.of(1, "EUR"), true);
        IProduct c1 = create.apply(p1);
        IProduct c2 = create.apply(p2);
        IProduct c3 = create.apply(p3);

        assertTrue("Products with the same sku should be the same", c1.equals(c2));
        assertFalse("Products with diffrent skus should be diffrent", c1.equals(c3));
    }

    /**
     * Allows to test the creation of a generic product according to the
     * expected behavior any product should display.
     *
     * @param create
     */
    public static void testCreationOfProduct(Consumer<AssertProduct> create) {
        GeneralCreationTests.testCreationOfProduct(() -> createAsserts(), create);
    }

    /**
     * Creates an ArrayList of AssertProduct with all test cases.
     *
     * @return
     */
    private static ArrayList<AssertProduct> createAsserts() {
        ArrayList<AssertProduct> toAssert = new ArrayList<>();
        Set<String> oneEan = new HashSet<>();
        oneEan.add("oneEntry");
        Set<String> eansWithEmpty = new HashSet<>();
        eansWithEmpty.add("oneEntry");
        eansWithEmpty.add("");

        Money oneEur = Money.of(1.0, "EUR");
        Money minusMoney = Money.of(-1.0, "EUR");

        //good cases
        toAssert.add(new AssertProduct("sku", "name", oneEan, null, true, false));
        toAssert.add(new AssertProduct("sku", "name", oneEan, null, false, false));
        toAssert.add(new AssertProduct("sku", "name", oneEan, oneEur, true, false));
        toAssert.add(new AssertProduct("sku", "name", oneEan, oneEur, false, false));

        //bad cases
        toAssert.add(new AssertProduct(null, "name", oneEan, oneEur, true, true));
        toAssert.add(new AssertProduct("sku", null, oneEan, oneEur, true, true));
        toAssert.add(new AssertProduct("sku", "name", null, oneEur, true, true));
        toAssert.add(new AssertProduct(null, null, null, oneEur, true, true));
        toAssert.add(new AssertProduct("", "", new HashSet<>(), oneEur, true, true));
        toAssert.add(new AssertProduct("sku", "name", new HashSet<>(), oneEur, true, true));
        toAssert.add(new AssertProduct("sku", "", oneEan, oneEur, true, true));
        toAssert.add(new AssertProduct("sku", "name", eansWithEmpty, oneEur, true, true));
        toAssert.add(new AssertProduct("sku", "name", oneEan, minusMoney, true, true));

        String keyboard = "qwertzuiopüasdfghjklöäyxcvbnm";
        keyboard += keyboard.toUpperCase();
        keyboard += "1234567890";
        keyboard += "!\"§$%&/()=?´`^°<>|,.-;:_+#*'~";
        toAssert.add(new AssertProduct(keyboard, keyboard, oneEan, oneEur, true, false));
        return toAssert;
    }
}
