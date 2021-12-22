package co.wordbe.informmerce.domain.asset.domain.service;

import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModule;
import co.wordbe.informmerce.domain.asset.domain.dto.SendRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IndustryApiModuleService {

    public Integer send(IndustryApiModule industryApiModule, SendRequestDto sendRequestDto) {
        log.info("industryApiModule: {}", industryApiModule.getClass());
        log.info("industryApiModule.getIndustry : {}", industryApiModule.getIndustry());
        log.info("industryApiModule.getSuccessCount : {}", industryApiModule.getSuccessCount());
        return industryApiModule.getSuccessCount();
    }
}
