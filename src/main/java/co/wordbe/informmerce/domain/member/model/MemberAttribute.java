package co.wordbe.informmerce.domain.member.model;

import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberAttribute {
    private String id;
    private String email;
    private MemberAuthProvider provider;
    private List<String> roles;
}
