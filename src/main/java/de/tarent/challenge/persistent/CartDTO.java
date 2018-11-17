package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.Cart;
import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.domain.IProduct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.javamoney.moneta.Money;

/**
 * The CartDTO is an ACL Model for the Persitance layer. Therefore it will be
 * retrived or written to the database.
 *
 * In order not to double the domain logic it wraps an Cart object, but hids id.
 * Therefore the Model doubels as a Proxy.
 *
 * @author Jan
 */
@Entity
@Table(name = "Cart")
public class CartDTO implements ICart {

    @Id
    private String id;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @MapsId("Cart_Product_ID")
    private Set<CartItem> items;

    @Column(length = 65335)//Blob
    @Basic(optional = true)
    protected Money totalPrice;
    
    private boolean checkedOut;

    /**
     * This Cart exists to do all the heavy lifting implemented in the domain
     * cart.
     */
    @Transient
    private Cart shadowedCart;

    /**
     * A CartDTO object should not be initalised empty as it cirumvents checks!
     *
     * Needed for JPA.
     */
    protected CartDTO() {
    }

    public CartDTO(ICart in) throws IllegalArgumentException {
        this(in.getId(), in.getProducts());
        if(in.getCheckedOut())
        {
            checkedOut=true;
            shadowedCart.checkOut();
        }
    }

    public CartDTO(String id, List<IProduct> products) throws IllegalArgumentException {
        this.id = id;
        setProducts(products);
        shadowedCart = new Cart(id, products);
        checkedOut=false;
    }

    /**
     * gets the unique identifier id.
     *
     * @return
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * gets all products in the cart.
     *
     * Converts the stored cart item, which is a product with an amount to a
     * list of products where the products is added amount times to the list.
     *
     * @return
     */
    @Override
    public List<IProduct> getProducts() {
        if (items == null) {
            return null;
        }
        List<IProduct> ret = new ArrayList<>();
        items.forEach(item -> {
            for (int i = 0; i < item.getAmount(); i++) {
                ret.add(item.getProduct());
            }
        });

        return ret;
    }

    /**
     * Converts the list of products in toSet to the cart item, where each
     * product is stored only once and the amoutn of times it was referenced in
     * the list.
     *
     * @param toSet
     * @throws IllegalArgumentException If toSet is null, because only non empty
     * products can be added.
     */
    private void setProducts(List<IProduct> toSet) throws IllegalArgumentException {
        if (toSet == null) {
            throw new IllegalArgumentException("The product list in the cart may not be empty!");
        }
        HashMap<IProduct, Integer> itemMap = new HashMap<>();
        toSet.forEach(product -> {
            int value = 1;
            if (itemMap.containsKey(product)) {
                value = itemMap.get(product) + 1;
            }
            itemMap.put(product, value);
        });
        items = new HashSet<>();
        itemMap.forEach((iproduct, amount) -> items.add(new CartItem(new ProductDTO(iproduct), amount)));
    }

    private void setThroughShadowedCart() throws IllegalArgumentException,IllegalAccessException {
        setProducts(shadowedCart.getProducts());
        totalPrice = shadowedCart.getTotalPrice();
    }

    /**
     * Removes the products from the cart.
     *
     * @param toRemove
     * @return
     */
    @Override
    public boolean removeProducts(List<IProduct> toRemove) throws IllegalArgumentException,IllegalAccessException {
        boolean ret = shadowedCart.removeProducts(toRemove);
        setThroughShadowedCart();
        return ret;
    }

    /**
     * Adds the products to the cart.
     *
     * @param toAdd
     * @return
     */
    @Override
    public boolean addProducts(List<IProduct> toAdd) throws IllegalArgumentException,IllegalAccessException {
        boolean ret = shadowedCart.addProducts(toAdd);
        setThroughShadowedCart();
        return ret;
    }

    /**
     * Gets the total price of the products in the cart.
     *
     * @return
     */
    @Override
    public Money getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Cart) || id == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        CartDTO c = (CartDTO) obj;
        return c.id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean getCheckedOut() {
       return checkedOut;
    }

    @Override
    public void checkOut() {
        checkedOut=true;
        shadowedCart.checkOut();
    }

}
