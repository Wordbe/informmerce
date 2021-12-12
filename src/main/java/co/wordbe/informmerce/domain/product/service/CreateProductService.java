package co.wordbe.informmerce.domain.product.service;

import co.wordbe.informmerce.domain.product.ProductEntity;
import co.wordbe.informmerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateProductService {
    private final ProductRepository productRepository;

    public Long createProduct(ProductEntity productEntity) {
        ProductEntity saved = productRepository.save(productEntity);
        return saved.getId();
    }
}
