package de.tarent.challenge.persistent;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.javamoney.moneta.Money;

/**
 * This class implements the IProduct from the domain layer. It's main purpase
 * is to be used by the persitance layer. Therefore it doubles as an ACL
 * implementation.
 *
 * @author Jan
 */
@Entity
@Table(name = "Product")
public class ProductDTO implements IProduct {

    /**
     * Only one product with a sku is allowed to exist. Issue #1
     */
    @Id
    protected String sku;

    protected String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Product_Eans", joinColumns = @JoinColumn(name = "product_sku"))
    protected Set<String> eans;

    @Column(length = 65335)//Blob
    @Basic(optional = true)
    protected Money price;

    /**
     * A ProdcutDTO should not be initalised empty as it cirumvents security
     * measures!
     *
     * Needed for JPA.
     */
    protected ProductDTO() {
    }

    public ProductDTO(IProduct product) {
        Product p = new Product(product);//Uses the functionality of the domain model to validate the input and create a dto.
        setWithProduct(p);
    }

    public ProductDTO(String sku, String name, Set<String> eans, Money price) throws IllegalArgumentException {
        Product p = new Product(sku, name, eans, price);//Uses the functionality of the domain model to validate the input and create a dto.
        setWithProduct(p);
    }

    private void setWithProduct(Product p) throws IllegalArgumentException {
        this.sku = p.getSku();
        this.name = p.getName();
        this.eans = p.getEans();
        this.price = p.getPrice();
    }

    @Override
    public String getSku() {
        return sku;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getEans() {
        return Sets.newHashSet(eans);
    }

    /**
     * A ProductDTO object is equal to an other one if it is bitewise the same
     * or if the sku is the same.
     *
     * @param o this object is compared to o.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || sku == null) {
            return false;
        }
        ProductDTO product = (ProductDTO) o;
        return product.sku.equals(sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .add("price", price)
                .toString();
    }

    @Override
    public Money getPrice() {
        return price;
    }
}
