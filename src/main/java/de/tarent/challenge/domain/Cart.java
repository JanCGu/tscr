package de.tarent.challenge.domain;

import com.google.common.base.MoreObjects;
import de.tarent.challenge.util.Check;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import org.javamoney.moneta.Money;

/**
 * A Cart holds multible products and may not be empty.
 * 
 * It is a domain object and an entity.
 * @author Jan
 */
public class Cart implements ICart {
    
    private final String id;
    
    private List<IProduct> products;
    
    private Money totalPrice;
    
    public Cart(String id, List<IProduct> products)
    {
        this.id = Check.nonEmpty(id, "The Id may not be empty!");
        this.products = Check.atLeastOne(products,p ->(p==null),"products");
        calculateTotalPrice();
    }
    

    @Override
    public List<IProduct> getProducts() {
        return products;
    }

    @Override
    public Money getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean removeProducts(List<IProduct> toRemove) {
       return changeProducts(toRemove,(ps,mod)-> ps.removeAll(mod));
    }

    @Override
    public boolean addProducts(List<IProduct> toAdd) {
        return changeProducts(toAdd,(ps,mod)->ps.addAll(mod));
    }
    
    /**
     * Executes the action with this products list and toModify.
     * Then updates the total price based on the products in the list.
     * 
     * Allows to apply the open closed principle.
     * @param toModify
     * @param action
     * @return The return value of aciton.
     */
    private boolean changeProducts(List<IProduct> toModify ,BiFunction<List<IProduct>,List<IProduct>,Boolean> action)
    {
        boolean ret = action.apply(products,toModify);
        calculateTotalPrice();
        return ret;
    }
    
    /**
     * Calculates the totalPrice based on the list of products.
     * 
     * It is better to calculate the price as a whole than to add or substract individually.
     * This is the case, because in this case discounts can be easily applied. 
     */
    private void calculateTotalPrice(){
        totalPrice = Money.of(0, totalPrice.getCurrency());
        products.forEach(product -> totalPrice=totalPrice.add(product.getPrice()));
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Cart) || id == null)
            return false;
        if(this == obj)
            return true;
        
        Cart c = (Cart)obj;
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
    
    
}
