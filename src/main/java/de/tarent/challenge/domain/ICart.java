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
     *
     * @return
     */
    String getId();

    /**
     * Gets a list of the products in this card. A Product can be multible times
     * in the cart.
     *
     * @return an interface list of products. This is import as it is one of the
     * SOLID principales.
     */
    List<IProduct> getProducts();

    /**
     * Removes toRemove from the cart.if there a product is twice in a remove it
     * will be removed twice. Therefore toRemove must a list and not a set.
     *
     * @param toRemove
     * @throws java.lang.IllegalAccessException Is thrown if the cart is checked out or if the product can't be accepted.
     * @return Is true if all products could be removed or if toRemove was null.
     */
    boolean removeProducts(List<IProduct> toRemove) throws IllegalAccessException;

    /**
     * Adds the list of products to the cart.
     *
     * @param toAdd
     * @throws java.lang.IllegalAccessException Is thrown if the cart is checked out or if the product can not be accepted.
     * @return
     */
    boolean addProducts(List<IProduct> toAdd) throws IllegalAccessException;

    /**
     * Returns a boolean, which indicateds if the cart is checked out. A Checked
     * out cart can't be changed.
     *
     * @return True if the cart is checked out.
     */
    boolean getCheckedOut();

    /**
     * Marks the cart as checked out. A checked out cart can't be changed.
     */
    void checkOut();

    /**
     * Gets the total price of the products in this cart.
     *
     * @return
     */
    Money getTotalPrice();
}
