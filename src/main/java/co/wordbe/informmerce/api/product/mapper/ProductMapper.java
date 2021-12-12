package co.wordbe.informmerce.api.product.mapper;

import co.wordbe.informmerce.api.product.dto.ProductCreateRequestDto;
import co.wordbe.informmerce.api.product.dto.ProductResponseDto;
import co.wordbe.informmerce.domain.product.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto toDto(ProductEntity productEntity);
    ProductEntity toEntity(ProductCreateRequestDto productCreateRequestDto);
}
