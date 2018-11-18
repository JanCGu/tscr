package de.tarent.challenge.domain.General;

import com.google.common.base.MoreObjects;
import de.tarent.challenge.domain.IProduct;
import java.util.Set;
import org.javamoney.moneta.Money;

/**
 * Allows to set sku,name and ean directly!
 *
 * @author Jan
 */
public class MockProduct implements IProduct {

    public String sku;
    public String name;
    public Set<String> eans;
    public Money price;
    public boolean available;

    public MockProduct(String sku, String name, Set<String> eans, Money price, boolean available) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
        this.price = price;
        this.available = available;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .add("price", price)
                .add("available", available)
                .toString();
    }

    @Override
    public Set<String> getEans() {
        return eans;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSku() {
        return sku;
    }

    @Override
    public Money getPrice() {
        return price;
    }

    @Override
    public void setAvailable(boolean availability) {
        this.available = availability;
    }

    @Override
    public boolean getAvailable() {
        return available;
    }

}
