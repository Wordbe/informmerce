package co.wordbe.informmerce.domain.member.enums;

import co.wordbe.informmerce.api.common.security.login.oauth2.dto.OAuth2UserAttributes;
import co.wordbe.informmerce.domain.common.interfaces.TriFunction;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public enum MemberAuthProvider {
    INFORMMERCE((t, u, v) -> null),
    GOOGLE(OAuth2UserAttributes::ofGoogle),
    FACEBOOK(OAuth2UserAttributes::ofFacebook),
    NAVER(OAuth2UserAttributes::ofNaver),
    KAKAO(OAuth2UserAttributes::ofKakao);

    private final TriFunction<MemberAuthProvider, String, Map<String, Object>, OAuth2UserAttributes> ofProvider;

    public OAuth2UserAttributes getOAuth2UserAttribute(String userNameAttributeName, Map<String, Object> attributes) {
        return ofProvider.apply(this, userNameAttributeName, attributes);
    }

    public static MemberAuthProvider from(final String value) {
        return valueOf(value.toUpperCase());
    }
}
