package co.wordbe.informmerce.api.common.security.login.oauth2.dto;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import co.wordbe.informmerce.domain.member.enums.MemberRoleType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2UserAttributes {
    private final String email;
    private final String name;
    private final MemberRoleType role;
    private final MemberAuthProvider provider;
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;

    @Builder
    public OAuth2UserAttributes(String email, String name, MemberRoleType role, MemberAuthProvider provider, Map<String, Object> attributes, String nameAttributeKey) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.provider = provider;
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
    }

    public static OAuth2UserAttributes ofGoogle(MemberAuthProvider provider, String userNameAttributeName, Map<String, Object> attributes) {

        return OAuth2UserAttributes.builder()
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .provider(provider)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    public static OAuth2UserAttributes ofFacebook(MemberAuthProvider provider, String userNameAttributeName, Map<String, Object> attributes) {

        return OAuth2UserAttributes.builder()
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .provider(provider)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuth2UserAttributes ofNaver(MemberAuthProvider provider, String userNameAttributeName, Map<String, Object> attributes) {
        @SuppressWarnings("unchecked")
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2UserAttributes.builder()
                .email((String) response.get("email"))
                .name((String) response.get("name"))
                .provider(provider)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuth2UserAttributes ofKakao(MemberAuthProvider provider, String userNameAttributeName, Map<String, Object> attributes) {
        @SuppressWarnings("unchecked")
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        @SuppressWarnings("unchecked")
        Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");

        return OAuth2UserAttributes.builder()
                .email((String) kakaoAccount.get("email"))
                .name((String) profile.get("nickname"))
                .provider(provider)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .email(email)
                .name(name)
                .role(MemberRoleType.USER)
                .provider(provider)
                .build();
    }
}
