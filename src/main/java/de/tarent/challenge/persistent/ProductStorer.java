package de.tarent.challenge.persistent;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.service.IProductSetter;
import java.util.Set;
import javax.naming.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Allows to create, update and delete IProducts in the database.
 *
 * @author Jan
 */
@Transactional
public class ProductStorer implements IProductSetter {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public boolean Update(Set<IProduct> toUpdate) throws ServiceUnavailableException {
        return doOperation(toUpdate, list -> productRepository.saveAll(list));
    }

    @Override
    public boolean Delete(Set<IProduct> toDelete) throws ServiceUnavailableException {
        return doOperation(toDelete, list -> {
            list.forEach(product -> productRepository.delete(product));
            return list;
        });
    }

    /**
     * Executes the doOperation after checking that the reference has elements.
     *
     * @param reference this list is converted to the list of productdtos and
     * then passted to the function.
     * @param doOperation
     * @return Returns true if the output of doOperation is greater than 0 or if
     * reference has no elements.
     */
    private boolean doOperation(Set<IProduct> reference, Function<Set<ProductDTO>, Iterable<ProductDTO>> doOperation) {
        if (reference.isEmpty()) {
            return true;
        }

        Set<ProductDTO> converted = ProductDTOConverter.convertIProduct(reference);
        Iterable<ProductDTO> ret = doOperation.apply(converted);
        return Lists.newArrayList(ret).size() > 0;
    }

}
