package co.wordbe.informmerce.api.common.annotation.aspect;

import co.wordbe.informmerce.domain.member.enums.MemberErrorType;
import co.wordbe.informmerce.domain.member.exception.UnauthorizedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckUserLoginAspect {

    @Before("@annotation(co.wordbe.informmerce.api.common.annotation.CheckUserLogin)")
    public void checkUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if ("anonymousUser".equals(principal)) {
            throw new UnauthorizedException(MemberErrorType.UNAUTHORIZED);
        }
    }
}
