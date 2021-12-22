package co.wordbe.informmerce.domain.asset.domain.industry.impl;

import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModule;
import org.springframework.stereotype.Service;

@Service
public class InvestApiModule implements IndustryApiModule {

    @Override
    public IndustryType getIndustry() {
        return IndustryType.INVEST;
    }

    @Override
    public Integer getSuccessCount() {
        return 5;
    }
}
