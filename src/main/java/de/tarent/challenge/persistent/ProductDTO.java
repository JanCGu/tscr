package de.tarent.challenge.persistent;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

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
     * Only one product with  a sku is allowed to exist. 
     * Issue #1
     */
    @Id
    protected String sku;

    protected String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Product_Eans", joinColumns = @JoinColumn(name = "product_sku"))
    protected Set<String> eans;
    
    

    protected ProductDTO() {
    }
    
    public ProductDTO(IProduct product)
    {
        Product p = new Product(product);//Uses the functionality of the domain model to validate the input and create a dto.
        setWithProduct(p);
    }

    public ProductDTO(String sku, String name, Set<String> eans) throws IllegalArgumentException{
        Product p = new Product(sku,name,eans);//Uses the functionality of the domain model to validate the input and create a dto.
        setWithProduct(p);
    }

    private void setWithProduct(Product p) throws IllegalArgumentException
    {
        this.sku = p.getSku();
        this.name = p.getName();
        this.eans = p.getEans();
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Set<String> getEans() {
        return Sets.newHashSet(eans);
    }

    /**
     * A ProductDTO object is equal to an other one if it is bitewise the same
     * or if the sku is the same.
     * @param o this object is compared to o.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductDTO product = (ProductDTO) o;
        return Objects.equals(sku, product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, eans);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .toString();
    }
}
