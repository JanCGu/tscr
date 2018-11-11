/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.domain;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the Product domain.
 *
 * @author Jan
 */
@SpringBootTest
public class ProductTest {

    /**
     * Tests the functionality to create a domain Product by sku, name and eans.
     * 
     * See Issue #1.
     */
    @Test
    public void ProductCreateBySettings() {
        generalCreationTestProcedure(ta -> new Product(ta.sku,ta.name,ta.eans));
        
    }
    
    /**
     * Tests the functionality to create a domain Product by an IProduct.
     * 
     * See Issue #1.
     * @param create
     */
    @Test
    public void ProductCreateByIProduct(){
       generalCreationTestProcedure(ta -> new Product(ta.mockproduct));  
    }
    
    private void generalCreationTestProcedure(Consumer<assertProduct> create){
        ArrayList<assertProduct> toAssert = createAsserts();
        toAssert.forEach(ta -> {
            try
            {
                System.out.println(ta);
                create.accept(ta);
                assertFalse(ta.expectedToFail);
            }
            catch(IllegalArgumentException ex)
            {
                assertTrue(ta.expectedToFail);
            }
        });
    }
    
    /**
     * Creates an ArrayList of AssertPRoductTo with all test cases.
     * @return 
     */
     private ArrayList<assertProduct> createAsserts() {
        ArrayList<assertProduct> toAssert = new ArrayList<>();
        Set<String> oneEan = new HashSet<>();
        oneEan.add("oneEntry");
        Set<String> eansWithEmpty = new HashSet<>();
        eansWithEmpty.add("oneEntry");
        eansWithEmpty.add("");
        
        toAssert.add(new assertProduct("sku", "name",oneEan,false));
        toAssert.add(new assertProduct("", "",new HashSet<>(),true));
        toAssert.add(new assertProduct("sku", "name",new HashSet<>(),true));
        toAssert.add(new assertProduct("sku", "",oneEan,true));
        toAssert.add(new assertProduct("sku", "name",eansWithEmpty,true));
        toAssert.add(new assertProduct("sku", "name",null,true));

        String keyboard = "qwertzuiopüasdfghjklöäyxcvbnm";
        keyboard += keyboard.toUpperCase();
        keyboard += "1234567890";
        keyboard += "!\"§$%&/()=?´`^°<>|,.-;:_+#*'~";
        toAssert.add(new assertProduct(keyboard, keyboard,oneEan,false));
        return toAssert;
    }
}

/**
 * Helper class to create a Product based on the sku, name and eans.
 *
 * @author Jan
 */
class assertProduct {

    public MockProduct mockproduct;
    public String sku;
    public String name;
    public Set<String> eans;
    public boolean expectedToFail;
    
    public assertProduct(String sku, String name, Set<String> eans, boolean expectedToFail) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
        this.expectedToFail=expectedToFail;
        mockproduct = new MockProduct(sku, name, eans);
        
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .add("expectedToFail", expectedToFail)
                .toString();
    }
}
