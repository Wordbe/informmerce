package co.wordbe.informmerce.api.common.security.login.oauth2;

import co.wordbe.informmerce.api.common.security.login.oauth2.dto.OAuth2UserAttributes;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import co.wordbe.informmerce.domain.member.service.CreateMemberService;
import co.wordbe.informmerce.domain.member.service.FindMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final FindMemberService findMemberService;
    private final CreateMemberService createMemberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // naver | kakao | facebook
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        MemberAuthProvider provider = MemberAuthProvider.from(registrationId);

        // response | id | id
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // TODO: 익명유저 설정?

        OAuth2UserAttributes oAuth2UserAttributes = provider.getOAuth2UserAttribute(userNameAttributeName, oAuth2User.getAttributes());

        MemberEntity member = findOrSaveMember(oAuth2UserAttributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRole().name())),
                oAuth2UserAttributes.getAttributes(),
                oAuth2UserAttributes.getNameAttributeKey());
    }

    private MemberEntity findOrSaveMember(OAuth2UserAttributes attributes) {
        return findMemberService.findNullableMemberByEmailAndProvider(attributes.getEmail(), attributes.getProvider())
                .orElseGet(() -> createMemberService.createMember(attributes.toEntity()));
    }
}
