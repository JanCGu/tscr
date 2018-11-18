package de.tarent.challenge.domain;

import de.tarent.challenge.domain.General.GeneralProductTests;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the Product domain.
 *
 * @author Jan
 */
@SpringBootTest
public class ProductTest {
    
    @Test
    public void ProductCreateBySettings() {
        GeneralProductTests.testCreationOfProduct(ta -> new Product(ta.sku, ta.name, ta.eans, ta.price, ta.available));
        
    }
    
    @Test
    public void ProductCreateByIProduct() {
        GeneralProductTests.testCreationOfProduct(ta -> new Product(ta.mockproduct));
    }
    
    @Test
    public void equalTest() {
        GeneralProductTests.testEqualityOfProduct(ip -> new Product(ip));
    }
    
    @Test
    public void AvailaibiltyTest() {
        GeneralProductTests.testAvailablity(ip -> new Product(ip));
    }
}
