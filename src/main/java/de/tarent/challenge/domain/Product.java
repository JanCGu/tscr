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

    private Product() {
    }

    /**
     * Converts a IProduct to a Product and inatialises it.
     *
     * @param in the IProduct to be converted.
     */
    public Product(IProduct in) throws IllegalArgumentException{
        this(in.getSku(), in.getName(), in.getEans());
    }

    /**
     * Initalises the Product with a sku, name and eans.
     * No sku name or eans may be empty. Eans may not contain a empty string.
     * @param sku the stock keeping unit of the product
     * @param name the name of the product
     * @param eans the european article number.
     * @throws IllegalArgumentException if the validity of sku,name or eans is not given.
     */
    public Product(String sku, String name, Set<String> eans) throws IllegalArgumentException{

        this.sku = checkNonEmpty(sku,"SKU may not be empty!"); //
        this.name = checkNonEmpty(name,"Name may not be empty!");
        this.eans = checkNonEmpty(eans,"eans");
    }

    /**
     * Checks if toCheck is non empty and returns it otherwise an IllegalArugmentException is throw.
     * @param toCheck
     * @param errorMsg the message of the Exception.
     * @return returns toCheck.
     * @throws IllegalArgumentException if the check fails this error will be thrown.
     */
    private String checkNonEmpty(String toCheck, String errorMsg) throws IllegalArgumentException {
        if (toCheck == null || toCheck.isEmpty()) {
            throw new IllegalArgumentException(errorMsg);
        }
        return toCheck;
    }
    
    /**
     * Checks that that at least one element exists and that no empty element exists.
     * @param toCheck the Set<string> to check.
     * @param name the name which should appear in the error message.
     * @return Return toCheck if nothing failed.
     * @throws IllegalArgumentException if the check failed this error will be thrown.
     */
    private Set<String> checkNonEmpty(Set<String> toCheck, String name)throws IllegalArgumentException{
        if(toCheck==null  || toCheck.size()==0)
            throw new IllegalArgumentException(name+" has no entries.");
        long nullentries =toCheck.stream().filter(ean -> ean=="").count();
        if(nullentries>0)
            throw new IllegalArgumentException(name+" has empty entries.");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
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
