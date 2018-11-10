package de.tarent.challenge.domain;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;

import java.util.Objects;
import java.util.Set;

/**
 * This is the Domain implementation of a Product. A Product is an enty object
 * representing something which can be sold in a shop.
 *
 * @author Jan
 */
public class Product implements IProduct {

    private Long id;

    /**
     * The stock keeping unit. Basicly an alphanumerical identifier for electronic use.
     * https://de.wikipedia.org/wiki/Artikelnummer
     */
    private String sku;

    /**
     * The name of the product as it is called in the shop.
     */
    private String name;

    /**
     * The European article number for this product.
     * https://de.wikipedia.org/wiki/European_Article_Number
     */
    private Set<String> eans;

    private Product() {
    }
    
     /**
     * Converts a IProduct to a Product and inatialises it.
     *
     * @param in the IProduct to be converted.
     * @return
     */
    public Product(IProduct in) {
        this(in.getSku(), in.getName(), in.getEans());
    }

    public Product(String sku, String name, Set<String> eans) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
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
