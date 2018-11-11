package de.tarent.challenge.persistent;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan
 */
@Repository
@Transactional
public interface IProductRepository extends JpaRepository<ProductDTO,String>{

    public List<ProductDTO> findByName(String name);
    
    public List<ProductDTO> findBySku(String sku);
}
