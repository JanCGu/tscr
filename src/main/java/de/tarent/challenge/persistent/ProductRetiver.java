package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.service.IProductGet;
import java.util.List;

/**
 *
 * @author Jan
 */
public class ProductRetiver implements IProductGet{

    @Override
    public List<IProduct> All() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IProduct> ByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IProduct BySku(String sku) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
