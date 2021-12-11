package co.wordbe.informmerce.domain.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String image;
    private BigDecimal price;

    @Builder
    public ProductEntity(String name, String description, String image, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
}
