package de.tarent.challenge.persistent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan
 */
@Repository
@Transactional
public interface ICartRepository extends JpaRepository<CartDTO, String> {
}
