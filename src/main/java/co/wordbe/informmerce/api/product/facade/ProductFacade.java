package co.wordbe.informmerce.api.product.facade;

import co.wordbe.informmerce.api.product.dto.ProductCreateRequestDto;
import co.wordbe.informmerce.api.product.dto.ProductResponseDto;
import co.wordbe.informmerce.api.product.mapper.ProductMapper;
import co.wordbe.informmerce.domain.product.service.CreateProductService;
import co.wordbe.informmerce.domain.product.service.FindProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductFacade {
    private final ProductMapper productMapper;
    private final CreateProductService createProductService;
    private final FindProductService productFindService;

    public Long createProduct(ProductCreateRequestDto requestDto) {
        Long productId = createProductService.createProduct(productMapper.toEntity(requestDto));
        return productId;
    }

    public ProductResponseDto getProduct(Long id) {
        return productMapper.toDto(productFindService.findById(id));
    }
}
