package de.tarent.challenge.domain;

import com.google.common.base.MoreObjects;
import de.tarent.challenge.util.Check;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import org.dom4j.IllegalAddException;
import org.javamoney.moneta.Money;

/**
 * A Cart holds multible products and may not be empty.
 *
 * It is a domain object and an entity.
 *
 * @author Jan
 */
public class Cart implements ICart {

    private final String id;

    private List<IProduct> products;

    private Money totalPrice;
    
    private boolean checkedOut;

    public Cart(String id, List<IProduct> products) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        ensureProducts(products);
        this.id = Check.nonEmpty(id, "The Id may not be empty!");
        this.products = new ArrayList<>(Check.atLeastOne(products, p -> (p == null), "products"));
        calculateTotalPrice();
        checkedOut=false;
    }

    public Cart(ICart in) {
        this(in.getId(), in.getProducts());
        checkedOut=in.getCheckedOut();
    }

    @Override
    public List<IProduct> getProducts() {
        return products;
    }

    @Override
    public Money getTotalPrice() {
        return totalPrice;
    }

    /**
     * Removes the list of toRemove from the product list.
     *
     * It might be that the same instance of a product is containt twice in the
     * product list. In that case only one instance of the product is removed,
     * if it only appears once in toRemove.
     *
     * @param toRemove if it is null nothing will be done and the function
     * returns true.
     * @return
     */
    @Override
    public boolean removeProducts(List<IProduct> toRemove) throws IllegalArgumentException {
        if (toRemove == null) {
            return true;
        }

        return changeProducts(toRemove, (ps, mod) -> {
            mod.forEach(m -> ps.remove(m));
            return true;
        });
    }

    /**
     * Adds the products from toAdd to the products of the card.
     *
     * @param toAdd if toAdd is null the function return true and nothing else
     * will happen.
     * @return
     */
    @Override
    public boolean addProducts(List<IProduct> toAdd) throws IllegalArgumentException {
        if (toAdd == null) {
            return true;
        }

        return changeProducts(toAdd, (ps, mod) -> ps.addAll(mod));
    }

    /**
     * Executes the action with this products list and toModify. Then updates
     * the total price based on the products in the list.
     *
     * Allows to apply the open closed principle.
     *
     * @param toModify
     * @param action
     * @return The return value of aciton.
     */
    private boolean changeProducts(List<IProduct> toModify, BiFunction<List<IProduct>, List<IProduct>, Boolean> action)
            throws IllegalArgumentException {
        if(checkedOut)
            throw new IllegalAddException("The cart is checked out and the product list can't be changed.");
        
        ensureProducts(toModify);
        boolean ret = action.apply(products, toModify);
        calculateTotalPrice();
        return ret;
    }

    /**
     * Calculates the totalPrice based on the list of products. The currency of
     * the total price is assumed to be the one of the first product.
     *
     * It is better to calculate the price as a whole than to add or substract
     * individually. This is the case, because in this case discounts can be
     * easily applied.
     */
    private void calculateTotalPrice() {
        totalPrice = Money.of(BigDecimal.ZERO, products.get(0).getPrice().getCurrency());
        products.stream().filter(product -> product.getPrice() != null).forEach(product -> totalPrice = totalPrice.add(product.getPrice()));
    }

    /**
     * Ensures that the list of the products is non empty and only contains
     * products with a price.
     */
    private void ensureProducts(List<IProduct> toEnsure) throws IllegalArgumentException {
        if (toEnsure == null || toEnsure.isEmpty()) {
            throw new IllegalArgumentException("A list of products which should be added or removed from the card has to have at least one product in it.");
        }
        if (toEnsure.stream().filter(p -> p.getPrice() == null).count() > 0) {
            throw new IllegalArgumentException("A product without a price was passed to the cart!");
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Cart) || id == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        Cart c = (Cart) obj;
        return c.id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("products", products)
                .add("totalPrice", totalPrice)
                .toString();
    }

    @Override
    public boolean getCheckedOut() {
        return this.checkedOut;
    }

    @Override
    public void checkOut() {
        checkedOut=true;
    }

}
