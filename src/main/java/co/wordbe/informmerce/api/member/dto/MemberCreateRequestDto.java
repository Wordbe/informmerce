package co.wordbe.informmerce.api.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateRequestDto {
    private String name;
    private String email;
    private String password;
}
