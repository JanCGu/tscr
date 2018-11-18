package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.General.GeneralProductTests;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the DTO Product in the persistence layer.
 *
 * @author Jan
 */
@SpringBootTest
public class ProductDTOTest {

    @Test
    public void createProductWithPropertiesTest() {
        GeneralProductTests.testCreationOfProduct(ta -> new ProductDTO(ta.sku, ta.name, ta.eans, ta.price, ta.available));
    }

    @Test
    public void createProductWithIProductTest() {
        GeneralProductTests.testCreationOfProduct(ta -> new ProductDTO(ta.mockproduct));
    }

    @Test
    public void equalTest() {
        GeneralProductTests.testEqualityOfProduct(ip -> new ProductDTO(ip));
    }
    
    @Test
    public void testAvailablity(){
        GeneralProductTests.testAvailablity(ip -> new ProductDTO(ip));
    }

}
