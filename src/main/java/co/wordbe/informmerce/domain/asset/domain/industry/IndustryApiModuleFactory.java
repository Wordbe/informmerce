package co.wordbe.informmerce.domain.asset.domain.industry;

import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
public class IndustryApiModuleFactory {
    private final Map<IndustryType, IndustryApiModule> industryApiModules;

    public IndustryApiModuleFactory(List<IndustryApiModule> industryApiModulesByIndustry) {
        this.industryApiModules = industryApiModulesByIndustry.stream()
                .collect(toMap(IndustryApiModule::getIndustry, Function.identity()));
    }

    public IndustryApiModule getSendModule(IndustryType industry) {
        return industryApiModules.get(industry);
    }
}
