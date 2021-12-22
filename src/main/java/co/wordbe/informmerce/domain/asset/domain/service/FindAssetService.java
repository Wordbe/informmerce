package co.wordbe.informmerce.domain.asset.domain.service;

import co.wordbe.informmerce.domain.asset.domain.dto.AssetDto;
import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import co.wordbe.informmerce.domain.asset.domain.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FindAssetService {
    private final AssetRepository assetRepository;

    public List<IndustryType> findIndustriesByMemberId(Long memberId) {
        return assetRepository.findIndustryByMemberId(memberId)
                .stream()
                .map(AssetDto::getIndustry)
                .collect(toList());
    }
}
