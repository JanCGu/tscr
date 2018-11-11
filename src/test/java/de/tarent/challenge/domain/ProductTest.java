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
        testCreationOfProduct(ta -> new Product(ta.sku,ta.name,ta.eans));
        
    }
    
    /**
     * Tests the functionality to create a domain Product by an IProduct.
     * 
     * See Issue #1.
     * @param create
     */
    @Test
    public void ProductCreateByIProduct(){
       testCreationOfProduct(ta -> new Product(ta.mockproduct));  
    }
    
    /**
     * Allows to test the creation of a generic product according to the
     * expected behavior any product should display.
     * @param create 
     */
    public static void testCreationOfProduct(Consumer<AssertProduct> create){
        ArrayList<AssertProduct> toAssert = createAsserts();
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
     private static ArrayList<AssertProduct> createAsserts() {
        ArrayList<AssertProduct> toAssert = new ArrayList<>();
        Set<String> oneEan = new HashSet<>();
        oneEan.add("oneEntry");
        Set<String> eansWithEmpty = new HashSet<>();
        eansWithEmpty.add("oneEntry");
        eansWithEmpty.add("");
        
        toAssert.add(new AssertProduct("sku", "name",oneEan,false));
        toAssert.add(new AssertProduct("", "",new HashSet<>(),true));
        toAssert.add(new AssertProduct("sku", "name",new HashSet<>(),true));
        toAssert.add(new AssertProduct("sku", "",oneEan,true));
        toAssert.add(new AssertProduct("sku", "name",eansWithEmpty,true));
        toAssert.add(new AssertProduct("sku", "name",null,true));

        String keyboard = "qwertzuiopüasdfghjklöäyxcvbnm";
        keyboard += keyboard.toUpperCase();
        keyboard += "1234567890";
        keyboard += "!\"§$%&/()=?´`^°<>|,.-;:_+#*'~";
        toAssert.add(new AssertProduct(keyboard, keyboard,oneEan,false));
        return toAssert;
    }
}

