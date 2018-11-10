package de.tarent.challenge.persistent;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan
 */
@Repository
@Transactional
public interface IProductRepository extends JpaRepository<ProductDTO,String>{

    @Query("SELECT product FROM Product WHERE product.name=(:name)")
    public List<ProductDTO> ByName(@Param("name") String name);
    
    @Query("SELECT product FROM Product WHERE product.sku=(:sku)")
    public List<ProductDTO> BySku(@Param("sku") String sku);
}
