
package de.tarent.challenge.domain;

import com.google.common.base.MoreObjects;
import java.util.Set;

/**
 * Helper class to create a MockProduct and store the product properties
 * as well as if it should fail.
 *
 * @author Jan
 */
public class AssertProduct {

    public IProduct mockproduct;
    public String sku;
    public String name;
    public Set<String> eans;
    public boolean expectedToFail;
    
    public AssertProduct(String sku, String name, Set<String> eans, boolean expectedToFail) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
        this.expectedToFail=expectedToFail;
        mockproduct = new MockProduct(sku, name, eans);
        
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .add("expectedToFail", expectedToFail)
                .toString();
    }
}
