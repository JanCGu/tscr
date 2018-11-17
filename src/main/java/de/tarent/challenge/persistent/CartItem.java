package de.tarent.challenge.persistent;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * A Cart Item represents a product and how often it was added to a cart.
 *
 * It is an aggregate, which is only needed for storage. In all other cases a
 * list of products is sufficient to store the amount of products within a cart.
 *
 * @author jan
 */
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="sku", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductDTO product;

    private int amount;

    protected CartItem() {
    }

    public CartItem(ProductDTO product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

}
