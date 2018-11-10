package de.tarent.challenge.display;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import de.tarent.challenge.domain.IProduct;

import java.util.Objects;
import java.util.Set;

/**
 * This class implements the IProduct from the domain layer.
 * It's main purpase is to be used by the display layer.
 * Therefore it doubles as an ACL implementation.
 * 
 * As the name suggests its a model in a MVC architecture.
 * @author Jan
 */
public class ProductModel implements IProduct{

    private Long id;

    private String sku;

    private String name;

    private Set<String> eans;

    private ProductModel() {
    }
    
    public ProductModel(IProduct product)
    {
        this(product.getSku(),product.getName(),product.getEans());
    }

    public ProductModel(String sku, String name, Set<String> eans) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductModel product = (ProductModel) o;
        return Objects.equals(id, product.id)
                && Objects.equals(sku, product.sku)
                && Objects.equals(name, product.name)
                && Objects.equals(eans, product.eans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, name, eans);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .toString();
    }
}
