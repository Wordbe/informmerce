package co.wordbe.informmerce.domain.asset.domain.repository;

import co.wordbe.informmerce.domain.asset.domain.dto.AssetDto;
import co.wordbe.informmerce.domain.asset.domain.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {
    List<AssetDto> findIndustryByMemberId(Long memberId);
}
