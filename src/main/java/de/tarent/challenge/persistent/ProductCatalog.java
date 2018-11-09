package de.tarent.challenge.persistent;



import de.tarent.challenge.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCatalog extends JpaRepository<Product, Long> {

    Product findBySku(String sku);

}
