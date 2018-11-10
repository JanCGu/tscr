package de.tarent.challenge.store;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import de.tarent.challenge.display.ProductController;
import de.tarent.challenge.display.ProductModel;
import de.tarent.challenge.domain.IProduct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.naming.ServiceUnavailableException;
import static org.junit.Assert.*;

/**
 * This tests the features of the application.
 * Therefore it is an integration test.
 * @author Jan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTests {

    /**
     * Tests if it is possible to get Products from the Display layer,
     * which are stored in the persitance layer.
     * 
     * This is an integration test
     */
    @Test
    public void displayProducts() throws ServiceUnavailableException {
        ProductController pc = new ProductController();
        List<ProductModel> getAll =Lists.newArrayList(pc.retrieveProducts());
        
        getAll.forEach(product -> System.out.println(product));
        assertTrue("There should be atleast one product", getAll.size()>0);
    }
    
    /**
     * Tests if it is possible to create, update and delete from the Display layer
     * in the persitance layer.
     * 
     * This is an integration test.
     * @throws ServiceUnavailableException 
     */
    @Test
    public void storeProductTest() throws ServiceUnavailableException{
        Set<String> eans = new HashSet<>();
        eans.add("1234567890123");
        ProductModel test = new ProductModel("testSKU","TestProduct",eans);
        ProductController pc = new ProductController();
        
        //should not exist
        testNonExistens(test);
        
        //create
        testUpdate(test);
        
        //update
        Set<String> eans2 = eans;
        eans2.add("3210987654321");
        ProductModel test2 = new ProductModel(test.getSku(),"TestProduct Namechange",eans2);
        ArrayList<ProductModel> deleteList = testUpdate(test2);
        
        //delete
        assertTrue("The model could not be deleted!",pc.deleteProducts(deleteList));
        testNonExistens(test);
    }
    
    private void testNonExistens(ProductModel model){
        ProductController pc = new ProductController();
        ProductModel dbPM = pc.retrieveProductBySku(model.getSku());
        assertNull("A test product still exists in the DB! ",dbPM);
    }
    
    /**
     * Tries to update the model through the ProductController.
     * Checks if the update in the DB worked.
     * @param model
     * @return the list which was send to the perstiance layer.
     * @throws ServiceUnavailableException 
     */
    private ArrayList<ProductModel> testUpdate(ProductModel model) throws ServiceUnavailableException
    {
        ArrayList<ProductModel> list = new ArrayList<>();
        list.add(model);
        ProductController pc = new ProductController();
        assertTrue("The model could not be updated!",pc.updateProducts(list));
        ProductModel dbPM = pc.retrieveProductBySku(model.getSku());
        assertEquals("The perstiance product and the input product are diffrent.",dbPM, model);
        
        return list;
    }
    

}
