package co.wordbe.informmerce.domain.asset.domain.industry;

import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;

public interface IndustryApiModule {
    IndustryType getIndustry();
    Integer getSuccessCount();
}
