package co.wordbe.informmerce.domain.asset.domain.dto;

import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SendRequestDto {
    private final Long memberId;
    private final IndustryType industry;
}
