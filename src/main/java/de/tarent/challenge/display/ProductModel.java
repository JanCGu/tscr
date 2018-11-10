package de.tarent.challenge.display;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;

import java.util.Set;

/**
 * This class implements the IProduct from the domain layer.
 * It's main purpase is to be used by the display layer.
 * Therefore it doubles as an ACL implementation.
 * 
 * As the name suggests its a model in a MVC architecture.
 * @author Jan
 */
public class ProductModel extends Product implements IProduct{
    
    public ProductModel(IProduct product)
    {
        super(product);
    }

    public ProductModel(String sku, String name, Set<String> eans) {
        super(sku,name,eans);
        
    }    
}
