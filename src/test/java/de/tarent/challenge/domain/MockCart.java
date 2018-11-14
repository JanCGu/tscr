package de.tarent.challenge.domain;

import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.domain.IProduct;
import java.util.List;
import org.javamoney.moneta.Money;

/**
 * CartModel is a Model for the Controller and is an ACL.
 *
 * @author Jan
 */
public class MockCart implements ICart {

    public String id;
    public List<IProduct> products;
    public Money totalMoney;
    public boolean productOperations;
    
    public MockCart(String id, List<IProduct> products, Money totalMoney, boolean productOperations )
    {
        this.id=id;
        this.products=products;
        this.totalMoney=totalMoney;
        this.productOperations=productOperations;
    }
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<IProduct> getProducts() {
        return products;
    }

    /**
     * Only returns productOperations and does nothing else.
     * @param toRemove
     * @return 
     */
    @Override
    public boolean removeProducts(List<IProduct> toRemove) {
        return productOperations;
    }

    /**
     * Only returns productOperations and does nothing else.
     * @param toAdd
     * @return 
     */
    @Override
    public boolean addProducts(List<IProduct> toAdd) {
        return productOperations;
    }

    @Override
    public Money getTotalPrice() {
        return totalMoney;
    }


}
