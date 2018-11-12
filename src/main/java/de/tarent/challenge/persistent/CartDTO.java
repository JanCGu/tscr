package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.Cart;
import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.domain.IProduct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.javamoney.moneta.Money;

/**
 *
 * @author Jan
 */
@Entity
@Table(name = "Cart")
public class CartDTO implements ICart{
    
    @Id
    private String id;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Cart_Products", joinColumns = @JoinColumn(name = "cart_id"))
    private List<ProductDTO> products;
    
    @Column(length=65335)//Blob
    @Basic(optional = true)
    protected Money totalPrice;
    
    /**
     * This Cart exists to do all the heavy lifting implemented in Cart.
     */
    @Transient 
    private Cart shadowedCart;
    
    public CartDTO(ICart in)
    {
        this(in.getId(),in.getProducts());
    }
    
    public CartDTO(String id, List<IProduct> products){
        this.id=id;
        setProducts(products);
        shadowedCart = new Cart(id,products);
    }
    

    /**
     * gets the unique identifier id.
     * @return 
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * gets all products in the cart.
     * @return 
     */
    @Override
    public List<IProduct> getProducts() {
        return products.stream().map(p->(IProduct)p).collect(Collectors.toList());
    }
    
    private void setProducts(List<IProduct> toSet)
    {
        this.products = toSet.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
    }
    
    private void setThroughShadowedCart()
    {
        setProducts(shadowedCart.getProducts());
        totalPrice = shadowedCart.getTotalPrice();
    }

    /**
     * Removes the products from the cart.
     * @param toRemove
     * @return 
     */
    @Override
    public boolean removeProducts(List<IProduct> toRemove) {
        boolean ret = shadowedCart.removeProducts(toRemove);
        setThroughShadowedCart();
        return ret;
    }

    /**
     * Adds the products to the cart.
     * @param toAdd
     * @return 
     */
    @Override
    public boolean addProducts(List<IProduct> toAdd) {
        boolean ret = shadowedCart.addProducts(toAdd);
        setThroughShadowedCart();
        return ret;
    }

    /**
     * Gets the total price of the products in the cart.
     * @return 
     */
    @Override
    public Money getTotalPrice() {
        return totalPrice;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Cart) || id == null)
            return false;
        if(this == obj)
            return true;
        
        CartDTO c = (CartDTO)obj;
        return c.id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
    
}
