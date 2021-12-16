package co.wordbe.informmerce.api.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberCreateRequestDto {
    @NotBlank private String name;
    @NotBlank private String email;
    @NotBlank private String password;
}
