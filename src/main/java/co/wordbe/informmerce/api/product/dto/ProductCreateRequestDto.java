package co.wordbe.informmerce.api.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateRequestDto {
    @NotBlank private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
}
