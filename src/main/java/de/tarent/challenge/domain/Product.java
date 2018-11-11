package de.tarent.challenge.domain;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;

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
    protected String sku;

    /**
     * The name of the product as it is called in the shop. It is a non empty
     * identifier.
     */
    protected String name;

    /**
     * The European article number for this product. At least one non null entry
     * is required. Any entry may not be emtpy!
     *
     * https://de.wikipedia.org/wiki/European_Article_Number
     */
    protected Set<String> eans;

    /**
     * Stores how much the procut costs. Can only be null or greater than 0.
     */
    protected Money price;

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
        this.sku = checkNonEmpty(sku, "SKU may not be empty!"); //
        this.name = checkNonEmpty(name, "Name may not be empty!");
        this.eans = checkEans(eans);
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
            throw new IllegalArgumentException("The price can ether be null or has to be greater than 0.");
        return toCheck;
    }

    /**
     * Checks if toCheck is non empty and returns it otherwise an
     * IllegalArugmentException is throw.
     *
     * @param toCheck
     * @param errorMsg the message of the Exception.
     * @return returns toCheck.
     * @throws IllegalArgumentException if the check fails this error will be
     * thrown.
     */
    private String checkNonEmpty(String toCheck, String errorMsg) throws IllegalArgumentException {
        if (toCheck == null || toCheck.isEmpty()) {
            throw new IllegalArgumentException(errorMsg);
        }
        return toCheck;
    }

    /**
     * Checks that that at least one element exists and that no empty element
     * exists.
     *
     * @param toCheck the Set<string> to check.
     * @param name the name which should appear in the error message.
     * @return Return toCheck if nothing failed.
     * @throws IllegalArgumentException if the check failed this error will be
     * thrown.
     */
    private Set<String> checkEans(Set<String> toCheck) throws IllegalArgumentException {
        if (toCheck == null || toCheck.size() == 0) {
            throw new IllegalArgumentException("eans has no entries.");
        }
        long nullentries = toCheck.stream().filter(ean -> ean == "").count();
        if (nullentries > 0) {
            throw new IllegalArgumentException("eans has empty entries.");
        }
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

    public boolean equals(Product p) {
        return p == null ? sku == null : p.sku.equals(sku);
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
