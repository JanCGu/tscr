package de.tarent.challenge.domain.General;

import com.google.common.base.MoreObjects;
import de.tarent.challenge.domain.IProduct;
import java.util.Set;
import org.javamoney.moneta.Money;

/**
 * Helper class to create a MockProduct and store the product properties as well
 * as if it should fail.
 *
 * @author Jan
 */
public class AssertProduct implements IAssert {

    public IProduct mockproduct;
    public String sku;
    public String name;
    public Set<String> eans;
    public boolean expectedToFail;
    public Money price;
    public boolean available;

    public AssertProduct(String sku, String name, Set<String> eans, Money price, boolean available, boolean expectedToFail) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
        this.price = price;
        this.expectedToFail = expectedToFail;
        this.available = available;
        mockproduct = new MockProduct(sku, name, eans, price, available);

    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .add("price", price)
                .add("expectedToFail", expectedToFail)
                .toString();
    }

    @Override
    public boolean expectedToFail() {
        return expectedToFail;
    }
}
