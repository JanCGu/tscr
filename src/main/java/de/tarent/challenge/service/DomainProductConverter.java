package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an adapter to convert a list of iproducts to the domain product.
 * @author Jan
 */
public class DomainProductConverter {
    
    /**
     * Converts the input list of iproducts to the domain product.
     * @param input
     * @return 
     */
    public static List<IProduct> Convert(List<IProduct> input)
    {
        List<IProduct> ret = new ArrayList<>();
        input.forEach(convert -> ret.add(new Product(convert)));
        return ret;
    }
    
}
