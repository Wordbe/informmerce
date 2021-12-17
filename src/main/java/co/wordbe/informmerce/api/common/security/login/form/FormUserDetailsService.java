package co.wordbe.informmerce.api.common.security.login.form;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import co.wordbe.informmerce.domain.member.service.FindMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormUserDetailsService implements UserDetailsService {
    private final FindMemberService findMemberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username == email
        MemberEntity member = findMemberService.findByEmailAndProvider(username, MemberAuthProvider.INFORMMERCE);
        return new FormUserDetails(member);
    }
}
