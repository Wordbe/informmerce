package co.wordbe.informmerce.api.asset.facade;

import co.wordbe.informmerce.api.asset.dto.AssetSendRequestDto;
import co.wordbe.informmerce.api.asset.dto.AssetSendResponseDto;
import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModule;
import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModuleFactory;
import co.wordbe.informmerce.domain.asset.domain.service.IndustryApiModuleService;
import co.wordbe.informmerce.domain.asset.domain.dto.SendRequestDto;
import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AssetFacade {
    private final IndustryApiModuleService industryApiModuleService;
    private final IndustryApiModuleFactory industryApiModuleFactory;

    public AssetSendResponseDto sendAssetApis(AssetSendRequestDto requestDto) {
        List<IndustryType> industries = new ArrayList<>();
        industries.add(IndustryType.CARD);
        industries.add(IndustryType.BANK);
        industries.add(IndustryType.INVEST);

        Integer count = 0;
        for (IndustryType industry : industries) {
            SendRequestDto sendRequestDto = SendRequestDto.builder()
                    .memberId(requestDto.getMemberId())
                    .industry(industry)
                    .build();

            IndustryApiModule industryApiModule = industryApiModuleFactory.getSendModule(industry);
            count += industryApiModuleService.send(industryApiModule, sendRequestDto);
        }

        return new AssetSendResponseDto(count);
    }
}
