package de.tarent.challenge.domain;

import java.util.List;
import org.javamoney.moneta.Money;

/**
 *
 * @author Jan
 */
public interface ICart {

    /**
     * Gets the Identifier for this card.
     * @return 
     */
    String getId();
        
    /**
     * Gets a list of the products in this card. A Product can be multible times in the cart. 
     * @return an interface list of products. This is import as it is one of the SOLID principales.
     */
    List<IProduct> getProducts();
    
    /**
     * Removes toRemove from the cart. if there a product is twice in a remove it will be removed twice.
     * Therefore toRemove must a list and not a set.
     * @param toRemove
     * @throws  ArrayIndexOutOfBoundsException the error is thrown if the remove operation leads to an underflow, e.g. if there isn't a product remaining.
     * @return Is true if all products could be removed or if toRemove was null.
     */
     boolean removeProducts(List<IProduct> toRemove) throws ArrayIndexOutOfBoundsException;
     
     /**
      * Adds the list of products to the cart.
      * @param toAdd
      * @throws ArrayIndexOutOfBoundsException the error is thrown if the add Operation leads to an overflow.
      * @return 
      */
     boolean addProducts(List<IProduct> toAdd) throws ArrayIndexOutOfBoundsException;

    /**
     * Gets the total price of the products in this cart.
     * @return 
     */
    Money getTotalPrice();
}
