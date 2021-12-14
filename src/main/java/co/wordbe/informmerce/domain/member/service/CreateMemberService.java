package co.wordbe.informmerce.domain.member.service;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateMemberService {
    private final MemberRepository memberRepository;

    public MemberEntity createMember(MemberEntity member) {
        return memberRepository.save(member);
    }
}
