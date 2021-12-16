package co.wordbe.informmerce.api.member.controller;

import co.wordbe.informmerce.api.member.dto.MemberCreateRequestDto;
import co.wordbe.informmerce.api.member.dto.MemberCreateResponseDto;
import co.wordbe.informmerce.api.member.facade.MemberFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberFacade memberFacade;

    @PostMapping("/v1/members/signup")
    public MemberCreateResponseDto signup(@RequestBody @Valid MemberCreateRequestDto requestDto) {
        return memberFacade.createMember(requestDto);
    }
}
