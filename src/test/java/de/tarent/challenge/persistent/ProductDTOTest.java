/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the DTO Product in the persistence layer.
 *
 * @author Jan
 */
@SpringBootTest
public class ProductDTOTest {

    @Test
    public void ProductCreateTest() {
        ProductDTO nullProduct = new ProductDTO(null, null, null);
        assertNull(nullProduct.getSku());
        assertNull(nullProduct.getName());
        assertNull(nullProduct.getSku());
        
        Set<String> emptyEANs = new HashSet<String>();
        testProductCreation("sku","name",emptyEANs);
        testProductCreation("","",emptyEANs);
        
        String keyboard = "qwertzuiopüasdfghjklöäyxcvbnm";
        keyboard += keyboard.toUpperCase();
        keyboard +="1234567890";
        keyboard+="!\"§$%&/()=?´`^°<>|,.-;:_+#*'~";
        testProductCreation(keyboard,keyboard,emptyEANs);
    }

    
    private void testProductCreation(String sku, String name, Set<String> eans) {
        ProductDTO p = new ProductDTO(sku, name, eans);
        assertSame(p.getSku(), sku);
        assertSame(p.getName(), name);
    }
}
