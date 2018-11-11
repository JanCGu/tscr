package de.tarent.challenge.display;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.naming.ServiceUnavailableException;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This tests the features of the application. Therefore it is an integration
 * test.
 *
 * Tests the feature specified in Issue #2: CRUD for Products from an Reste
 * interface Tests the feature specified in Issue #1: Unique SKU.
 *
 * @author Jan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerIntegrationTest {

    @Autowired
    private ProductController pc;

    /**
     * Tests if it is possible to get Products from the Display layer, which are
     * stored in the persitance layer.
     *
     * This is an integration test. Tests the feature specified in Issue #2.
     *
     * @throws javax.naming.ServiceUnavailableException
     */
    @Test
    public void displayProducts() throws ServiceUnavailableException {
        List<ProductModel> getAll = Lists.newArrayList(pc.retrieveProducts());

        getAll.forEach(product -> System.out.println(product));
        assertTrue("There should be atleast one product", getAll.size() > 0);
    }

    /**
     * Tests if it is possible to create, update and delete from the Display
     * layer in the persitance layer.
     *
     * This is an integration test.
     *
     * @throws ServiceUnavailableException
     */
    @Test
    public void storeProductTest() throws ServiceUnavailableException {
        ProductModel test = testNonExistens();

        //create
        testUpdate(test);

        //does nothing
        testUpdate(test);

        //update
        Set<String> eans2 = new HashSet<>();
        eans2.add("1234567891234");
        eans2.add("3210987654321");
        ProductModel test2 = new ProductModel(test.getSku(), "TestProduct Namechange", eans2);
        ArrayList<ProductModel> deleteList = testUpdate(test2);

        //delete
        assertTrue("The model could not be deleted!", pc.deleteProducts(deleteList));
        testNonExistens();
    }

    /**
     * Ensures that the test Product does not exist in the DB.
     *
     * @return an instance of a test product.
     * @throws ServiceUnavailableException
     */
    private ProductModel testNonExistens() throws ServiceUnavailableException {
        ProductModel test = ProductControllerTest.getTestProduct();
        ProductModel dbPM = pc.retrieveProductBySku(test.getSku());
        assertNull("A test product still exists in the DB! ", dbPM);
        return test;
    }

    /**
     * Tries to update the model through the ProductController. Checks if the
     * update in the DB worked.
     *
     * @param model
     * @return the list which was send to the perstiance layer.
     * @throws ServiceUnavailableException
     */
    private ArrayList<ProductModel> testUpdate(ProductModel model) throws ServiceUnavailableException {
        ArrayList<ProductModel> list = new ArrayList<>();
        list.add(model);
        assertTrue("The model could not be updated!", pc.updateProducts(list));
        ProductModel dbPM = pc.retrieveProductBySku(model.getSku());
        assertEquals("The perstiance product and the input product are diffrent.", dbPM, model);

        return list;
    }

}
