package co.wordbe.informmerce.domain.product.service;

import co.wordbe.informmerce.domain.product.ProductEntity;
import co.wordbe.informmerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FindProductService {
    private final ProductRepository productRepository;

    public ProductEntity findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}