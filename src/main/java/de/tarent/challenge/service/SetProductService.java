package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import java.util.List;
import javax.naming.ServiceUnavailableException;

/**
 *
 * @author Jan
 */
public class SetProductService implements IProductSetter {

    private final IProductSetter persitanceProvider;

    /**
     * Initalises the SetProductService with a persitanceProvider.
     *
     * This method should only be used by and through a Factory in config!
     *
     * @param perstianceProvider
     */
    public SetProductService(IProductSetter perstianceProvider) {
        this.persitanceProvider = perstianceProvider;
    }

    /**
     * Converts toUpdate to a list of products specified through the domain.
     * Then passes this list to the persitance layer to update the storage.
     *
     * @param toUpdate
     * @return
     * @throws ServiceUnavailableException
     */
    @Override
    public boolean Update(List<IProduct> toUpdate) throws ServiceUnavailableException {
        testProvider();
        return persitanceProvider.Update(DomainProductConverter.Convert(toUpdate));
    }

    /**
     * Converts toDelete to a list of products specified through the domain.
     * Then passes the list to the perstiance layer to delete it in the storage.
     *
     * @param toDelete
     * @return
     * @throws ServiceUnavailableException
     */
    @Override
    public boolean Delete(List<IProduct> toDelete) throws ServiceUnavailableException {
        testProvider();
        return persitanceProvider.Delete(DomainProductConverter.Convert(toDelete));
    }

    private void testProvider() throws ServiceUnavailableException {
        if (persitanceProvider == null) {
            throw new ServiceUnavailableException("The provider service was not set!");
        }
    }

}
