package de.tarent.challenge.service.Asymetric;

import de.tarent.challenge.domain.IProduct;

/**
 * Contains the parameter which limits all products just to the wanted ones.
 *
 * @author Jan
 */
public class ProductRequest {

    private final String productSKU;
    private final String productName;

    public ProductRequest(String sku, String name) {
        productSKU = sku;
        productName = name;
    }

    public boolean Equals(IProduct p) {
        if(p==null)
            return false;
        
        boolean ret = (productSKU != null) ? p.getSku() == productSKU : true;
        ret &= (productName != null) ? p.getName() == productName : true;
        return ret;
    }
}
