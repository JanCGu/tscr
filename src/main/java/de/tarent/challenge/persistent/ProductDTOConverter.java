package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.IProduct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jan
 */
public class ProductDTOConverter {

    /**
     * Converts a list of productdtos to a list of iproducts.
     *
     * @param convert
     * @return
     */
    public static List<IProduct> convertProductDTO(List<ProductDTO> convert) {
        List<IProduct> ret = new ArrayList<>();
        convert.forEach(product -> ret.add(product));
        return ret;
    }
    
    /**
     * Converts a list of iproducts to a list of productdtos.
     * @param convert
     * @return 
     */
    public static List<ProductDTO> convertIProduct(List<IProduct> convert)
    {
        List<ProductDTO> ret = new ArrayList<>();
        convert.forEach(product -> ret.add(new ProductDTO(product)));
        return ret;
    }
    
    /**
     * Converts a list of productdtos to a list of iproducts.
     *
     * @param convert
     * @return
     */
    public static Set<IProduct> convertProductDTO(Set<ProductDTO> convert) {
        Set<IProduct> ret = new HashSet<>();
        convert.forEach(product -> ret.add(product));
        return ret;
    }
    
    /**
     * Converts a list of iproducts to a list of productdtos.
     * @param convert
     * @return 
     */
    public static Set<ProductDTO> convertIProduct(Set<IProduct> convert)
    {
        Set<ProductDTO> ret = new HashSet<>();
        convert.forEach(product -> ret.add(new ProductDTO(product)));
        return ret;
    }
}
