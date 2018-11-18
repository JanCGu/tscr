package de.tarent.challenge.domain;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import de.tarent.challenge.util.Check;

import java.util.Objects;
import java.util.Set;
import org.javamoney.moneta.Money;

/**
 * This is the Domain implementation of a Product. A Product is an enty object
 * representing something which can be sold in a shop.
 *
 * @author Jan
 */
public class Product implements IProduct {

    /**
     * The stock keeping unit. Basicly an alphanumerical identifier for
     * electronic use. It is a required non empty identifier.
     * https://de.wikipedia.org/wiki/Artikelnummer
     */
    private String sku;

    /**
     * The name of the product as it is called in the shop. It is a non empty
     * identifier.
     */
    private String name;

    /**
     * The European article number for this product. At least one non null entry
     * is required. Any entry may not be emtpy!
     *
     * https://de.wikipedia.org/wiki/European_Article_Number
     */
    private Set<String> eans;

    /**
     * Stores how much the procut costs. Can only be null or greater than 0.
     */
    private Money price;
    
    

    private Product() {
    }

    /**
     * Converts a IProduct to a Product and inatialises it.
     *
     * @param in the IProduct to be converted.
     */
    public Product(IProduct in) throws IllegalArgumentException {
        this(in.getSku(), in.getName(), in.getEans(),in.getPrice());
    }

    /**
     * Initalises the Product with a sku, name and eans. No sku name or eans may
     * be empty. Eans may not contain a empty string.
     *
     * @param sku the stock keeping unit of the product
     * @param name the name of the product
     * @param eans the european article number.
     * @throws IllegalArgumentException if the validity of sku,name or eans is
     * not given.
     */
    public Product(String sku, String name, Set<String> eans) throws IllegalArgumentException {
        this(sku, name, eans, null);

    }

    public Product(String sku, String name, Set<String> eans, Money price) {
        this.sku = Check.nonEmpty(sku, "SKU may not be empty!"); //
        this.name = Check.nonEmpty(name, "Name may not be empty!");
        this.eans = Check.atLeastOne(eans,ean -> (ean == null || ean==""),"eans");
        this.price = checkAllowdPrice(price);
    }

    /**
     * Checks if toCheck is an allowed Price. A Price can be null or has to be greater than 0.
     * @param toCheck
     * @return returns toCheck if it is valid.
     * @throws IllegalArgumentException throws an error if toCheck is invalid.
     */
    private Money checkAllowdPrice(Money toCheck) throws IllegalArgumentException {
        if (toCheck == null) {
            return null;
        }
        if(toCheck.isNegativeOrZero())
            throw new IllegalArgumentException("The price can ether be zero or has to be greater than 0.");
        return toCheck;
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

    public static String getIdentifierName() {
        return "SKU";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || sku == null) {
            return false;
        }
        Product product = (Product) o;

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

    /**
     * Returns the price of the product.
     * @return 
     */
    @Override
    public Money getPrice() {
        return price;
    }
}
