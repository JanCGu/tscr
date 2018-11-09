package de.tarent.challenge.service;

import de.tarent.challenge.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductCatalog productCatalog;

    public ProductService(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public List<Product> retrieveAllProducts() {
        return productCatalog.findAll();
    }

    public Product retrieveProductBySku(String sku) {
        return productCatalog.findBySku(sku);
    }
}
