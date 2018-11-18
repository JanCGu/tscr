package de.tarent.challenge.domain.General;

import com.google.common.base.MoreObjects;
import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.domain.IProduct;
import java.util.List;
import org.javamoney.moneta.Money;

/**
 * Helper class to create a MockProduct and store the product properties as well
 * as if it should fail.
 *
 * @author Jan
 */
public class AssertCart implements IAssert {

    public ICart mockCart;
    public String id;
    public List<IProduct> products;
    public Money totalPrice;
    public boolean checkedOut;
    public boolean expectedToFail;

    /**
     * Return value of the operations(remove & update of the mockCart.)
     */
    public boolean operationOutcome;

    public AssertCart(String id, List<IProduct> products, Money totalPrice, boolean CheckedOut, boolean operationOutcome, boolean expectedToFail) {
        this.id = id;
        this.products = products;
        this.totalPrice = totalPrice;
        this.checkedOut = CheckedOut;

        this.expectedToFail = expectedToFail;
        mockCart = new MockCart(id, products, totalPrice, CheckedOut, operationOutcome);

    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("products", products)
                .add("totalPrice", totalPrice)
                .add("expectedToFail", expectedToFail)
                .toString();
    }

    @Override
    public boolean expectedToFail() {
        return expectedToFail;
    }
}
