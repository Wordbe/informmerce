package co.wordbe.informmerce.api.asset.controller;

import co.wordbe.informmerce.api.asset.dto.AssetSendRequestDto;
import co.wordbe.informmerce.api.asset.dto.AssetSendResponseDto;
import co.wordbe.informmerce.api.asset.facade.AssetFacade;
import co.wordbe.informmerce.api.common.annotation.CheckUserLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AssetController {
    private final AssetFacade assetFacade;

    @PostMapping("/v1/assets/send")
    @CheckUserLogin
    public AssetSendResponseDto sendAssetRequests(@RequestBody @Valid AssetSendRequestDto requestDto) {
        return assetFacade.sendAssetApis(requestDto);
    }
}
