package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.IProduct;
import java.util.List;
import org.springframework.util.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests the PrdouctRetriver and therefore the retivial from the database.
 *
 * @author Jan
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRetiverTest {

    @Autowired
    private IProductRepository productRepository;

    /**
     * Tests if any Product can be retrived from the database.
     */
    @Test
    public void AllTest() {
        ProductRetiver pr = new ProductRetiver();

        List<IProduct> products = pr.All();

        products.forEach(product -> System.out.println(product));
        //Also check if there are products inserted in the Db through spring if this fails!
        Assert.isTrue(products.size() > 0, "There should exist objects in the database!");
    }
    
}
