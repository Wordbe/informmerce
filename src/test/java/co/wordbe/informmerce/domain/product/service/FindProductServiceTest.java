package co.wordbe.informmerce.domain.product.service;

import co.wordbe.informmerce.domain.product.ProductEntity;
import co.wordbe.informmerce.domain.product.exception.ProductNotFoundException;
import co.wordbe.informmerce.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    FindProductService findProductService;

    @Test
    void 상품이_없으면_예외를_반환한다() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findProductService.findById(productId))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("상품을 찾을 수 없습니다.");
    }

    @Test
    void 상품이_있으면_상품을_반환한다() {
        ProductEntity product = ProductEntity.builder()
                .name("상품")
                .description("상품설명")
                .imageUrl("상품이미지")
                .price(BigDecimal.valueOf(10000))
                .build();

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        assertThat(findProductService.findById(product.getId())).isEqualTo(product);
    }
}