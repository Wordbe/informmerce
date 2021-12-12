package co.wordbe.informmerce.domain.product.service;

import co.wordbe.informmerce.domain.product.entity.ProductEntity;
import co.wordbe.informmerce.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CreateProductService createProductService;

    @Test
    void 상품이_생성되면_생성된_ID를_반환한다() {
        ProductEntity product = ProductEntity.builder()
                .name("상품")
                .description("상품설명")
                .imageUrl("상품이미지")
                .price(BigDecimal.valueOf(10000))
                .build();

        when(productRepository.save(product)).thenReturn(product);

        assertThat(createProductService.createProduct(product)).isEqualTo(product.getId());
    }
}