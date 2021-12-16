package co.wordbe.informmerce.domain.member.service;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberErrorType;
import co.wordbe.informmerce.domain.member.exception.MemberNotFoundException;
import co.wordbe.informmerce.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindMemberService {
    private final MemberRepository memberRepository;

    public MemberEntity findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(MemberErrorType.MEMBER_NOT_FOUND));
    }
}
