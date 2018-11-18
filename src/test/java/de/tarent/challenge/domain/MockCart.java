package de.tarent.challenge.domain;

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
    public boolean CheckedOut;
    public boolean productOperations;
    public boolean CheckOutSwitch;
    public List<IProduct> OperationInput;

    public MockCart(String id, List<IProduct> products, Money totalMoney, boolean CheckedOut, boolean productOperations) {
        this.id = id;
        this.products = products;
        this.totalMoney = totalMoney;
        this.CheckedOut = CheckedOut;
        this.productOperations = productOperations;
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
     *
     * @param toRemove
     * @return
     */
    @Override
    public boolean removeProducts(List<IProduct> toRemove) {
        OperationInput = toRemove;
        return productOperations;
    }

    /**
     * Only returns productOperations and does nothing else.
     *
     * @param toAdd
     * @return
     */
    @Override
    public boolean addProducts(List<IProduct> toAdd) {
        OperationInput = toAdd;
        return productOperations;
    }

    @Override
    public Money getTotalPrice() {
        return totalMoney;
    }

    @Override
    public boolean getCheckedOut() {
        return this.CheckedOut;
    }

    @Override
    public void checkOut() {
        CheckOutSwitch=true;
    }

}
