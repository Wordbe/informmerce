package co.wordbe.informmerce.domain.product.service;

import co.wordbe.informmerce.domain.product.ProductEntity;
import co.wordbe.informmerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductEntity getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void createProduct(ProductEntity product) {
        productRepository.save(product);
    }
}
