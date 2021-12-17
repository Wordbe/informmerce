package co.wordbe.informmerce.api.common.security.jwt.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RefreshTokenPayload {
    private String aud;
    private List<String> roles;
    private String provider;
    private int iat;
    private int exp;
}
