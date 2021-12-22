package co.wordbe.informmerce.domain.asset.domain.industry.impl;

import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import co.wordbe.informmerce.domain.asset.domain.industry.IndustryApiModule;
import org.springframework.stereotype.Service;

@Service
public class BankApiModule implements IndustryApiModule {

    @Override
    public IndustryType getIndustry() {
        return IndustryType.BANK;
    }

    @Override
    public Integer getSuccessCount() {
        return 11;
    }
}
