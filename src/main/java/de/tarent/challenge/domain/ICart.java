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
     * @return a list of products.
     */
    List<IProduct> getProducts();
    
    /**
     * Removes toRemove from the cart. if there a product is twice in a remove it will be removed twice.
     * Therefore toRemove must a list and not a set.
     * @param toRemove
     * @return Is true if all products could be removed or if toRemove was null.
     */
     boolean removeProducts(List<IProduct> toRemove);
     
     /**
      * Adds the list of products to the cart.
      * @param toAdd
      * @return 
      */
     boolean addProducts(List<IProduct> toAdd);

    /**
     * Gets the total price of the products in this cart.
     * @return 
     */
    Money getTotalPrice();
}
