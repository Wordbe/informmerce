package co.wordbe.informmerce.api.product;

import co.wordbe.informmerce.domain.product.ProductEntity;
import co.wordbe.informmerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/v1/products")
    public void createProduct(ProductEntity product) {
        productService.createProduct(product);
    }

    @GetMapping("/v1/products/{id}")
    public ProductEntity getProducts(@PathVariable Long id) {
        System.out.println("id = " + id);
        return productService.findById(id);
    }
}
