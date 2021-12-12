package co.wordbe.informmerce.api.product.controller;

import co.wordbe.informmerce.api.product.dto.ProductCreateRequestDto;
import co.wordbe.informmerce.api.product.dto.ProductResponseDto;
import co.wordbe.informmerce.api.product.facade.ProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductFacade productFacade;

    @PostMapping("/v1/products")
    public Long createProduct(@RequestBody ProductCreateRequestDto requestDto) {
        return productFacade.createProduct(requestDto);
    }

    @GetMapping("/v1/products/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productFacade.getProduct(id);
    }
}
