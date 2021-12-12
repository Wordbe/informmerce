package co.wordbe.informmerce.domain.product.repository;

import co.wordbe.informmerce.domain.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
