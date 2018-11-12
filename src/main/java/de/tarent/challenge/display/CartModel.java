package de.tarent.challenge.display;

import de.tarent.challenge.domain.Cart;
import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.domain.IProduct;
import java.util.List;

/**
 * CartModel is a Model for the Controller and is an ACL.
 *
 * @author Jan
 */
public class CartModel extends Cart implements ICart {

    public CartModel(String id, List<IProduct> products) {
        super(id, products);
    }

    public CartModel(ICart in) {
        super(in);
    }

}
