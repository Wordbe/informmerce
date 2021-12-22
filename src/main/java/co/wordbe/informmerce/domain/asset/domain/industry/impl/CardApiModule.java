package co.wordbe.informmerce.domain.asset.domain.industry.impl;

import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModule;
import org.springframework.stereotype.Service;

@Service
public class CardApiModule implements IndustryApiModule {

    @Override
    public IndustryType getIndustry() {
        return IndustryType.CARD;
    }

    @Override
    public Integer getSuccessCount() {
        return 20;
    }
}
