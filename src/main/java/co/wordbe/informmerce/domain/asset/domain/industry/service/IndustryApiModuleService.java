package co.wordbe.informmerce.domain.asset.domain.industry.service;

import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModule;
import co.wordbe.informmerce.domain.asset.domain.industry.dto.ApiModuleInDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IndustryApiModuleService {

    public Integer send(IndustryApiModule industryApiModule, ApiModuleInDto apiModuleInDto) {
        log.info("industryApiModule: {}", industryApiModule.getClass());
        log.info("industryApiModule.getIndustry : {}", industryApiModule.getIndustry());
        log.info("industryApiModule.getSuccessCount : {}", industryApiModule.getSuccessCount());
        log.info("apiModuleInDto.getMemberId : {}", apiModuleInDto.getMemberId());
        log.info("apiModuleInDto.getIndustry : {}", apiModuleInDto.getIndustry());
        return industryApiModule.getSuccessCount();
    }
}
