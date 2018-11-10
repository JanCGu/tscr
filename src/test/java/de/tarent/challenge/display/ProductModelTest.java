
package de.tarent.challenge.display;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.tarent.challenge.domain.ProductTest;

/**
 * Tests the Product in the display.
 *
 * @author Jan
 */
@SpringBootTest
public class ProductModelTest {

    /**
     * At the moment the ProductModel is just a Product and has no extra functionality!
     */
    @Test
    public void ProductCreateTest() {
        ProductTest pt = new ProductTest();
        pt.ProductCreateByStringTest();
        pt.ProductCreateByIProductTest();
    }
}
