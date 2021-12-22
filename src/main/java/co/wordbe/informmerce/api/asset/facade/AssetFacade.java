package co.wordbe.informmerce.api.asset.facade;

import co.wordbe.informmerce.api.asset.dto.AssetSendRequestDto;
import co.wordbe.informmerce.api.asset.dto.AssetSendResponseDto;
import co.wordbe.informmerce.domain.asset.domain.industry.dto.ApiModuleInDto;
import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModule;
import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModuleFactory;
import co.wordbe.informmerce.domain.asset.domain.service.FindAssetService;
import co.wordbe.informmerce.domain.asset.domain.industry.service.IndustryApiModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssetFacade {
    private final IndustryApiModuleService industryApiModuleService;
    private final IndustryApiModuleFactory industryApiModuleFactory;
    private final FindAssetService findAssetService;

    public AssetSendResponseDto sendAssetApis(AssetSendRequestDto requestDto) {

        List<IndustryType> industries = findAssetService.findIndustriesByMemberId(requestDto.getMemberId());

        Integer count = 0;
        for (IndustryType industry : industries) {
            ApiModuleInDto apiModuleInDto = ApiModuleInDto.builder()
                    .memberId(requestDto.getMemberId())
                    .industry(industry)
                    .build();

            IndustryApiModule industryApiModule = industryApiModuleFactory.getApiModule(industry);
            count += industryApiModuleService.send(industryApiModule, apiModuleInDto);
        }

        return new AssetSendResponseDto(count);
    }
}
