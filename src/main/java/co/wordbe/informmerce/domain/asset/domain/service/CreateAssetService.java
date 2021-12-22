package co.wordbe.informmerce.domain.asset.domain.service;

import co.wordbe.informmerce.domain.asset.domain.entity.AssetEntity;
import co.wordbe.informmerce.domain.asset.domain.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateAssetService {
    private final AssetRepository assetRepository;

    public Long createAsset(AssetEntity assetEntity) {
        return assetRepository.save(assetEntity).getId();
    }
}
