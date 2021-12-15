package co.wordbe.informmerce.api.member.facade;

import co.wordbe.informmerce.api.member.dto.MemberCreateRequestDto;
import co.wordbe.informmerce.api.member.dto.MemberCreateResponseDto;
import co.wordbe.informmerce.api.member.mapper.MemberMapper;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.service.CreateMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberFacade {
    private final CreateMemberService createMemberService;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberCreateResponseDto createMember(MemberCreateRequestDto requestDto) {
        MemberEntity memberEntity = memberMapper.toEntity(requestDto);
        memberEntity.updatePassword(passwordEncoder.encode(requestDto.getPassword()));
        memberEntity.init();
        return memberMapper.toDto(createMemberService.createMember(memberEntity));
    }
}
