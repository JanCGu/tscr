package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.service.IProductSetter;
import java.util.List;
import javax.naming.ServiceUnavailableException;

/**
 * Allows to create, update and delete IProducts in the database.
 * @author Jan
 */
public class ProductStorer implements IProductSetter{

    @Override
    public boolean Update(List<IProduct> toUpdate) throws ServiceUnavailableException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(List<IProduct> toDelete) throws ServiceUnavailableException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
