package co.wordbe.informmerce.api.asset.facade;

import co.wordbe.informmerce.api.asset.dto.AssetSendRequestDto;
import co.wordbe.informmerce.api.asset.dto.AssetSendResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssetFacade {

    public AssetSendResponseDto sendAndSave(AssetSendRequestDto requestDto) {


        return new AssetSendResponseDto(10);
    }
}
