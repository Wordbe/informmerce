package co.wordbe.informmerce.domain.asset.domain.dto;

import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class AssetDto {
    private IndustryType industry;
}
