package co.wordbe.informmerce.api.common.security.form;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class FormUserDetails extends User {
    private final MemberEntity member;

    public FormUserDetails(MemberEntity member) {
        super(member.getEmail(),
              member.getPassword(),
              Collections.singleton(new SimpleGrantedAuthority(member.getRole().name())));
        this.member = member;
    }
}
