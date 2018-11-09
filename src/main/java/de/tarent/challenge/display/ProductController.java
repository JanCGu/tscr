package de.tarent.challenge.display;

import de.tarent.challenge.domain.Product;
import de.tarent.challenge.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> retrieveProducts() {
        return productService.retrieveAllProducts();
    }

    @GetMapping("/{sku}")
    public Product retrieveProductBySku(@PathVariable String sku) {
        return productService.retrieveProductBySku(sku);
    }
}
