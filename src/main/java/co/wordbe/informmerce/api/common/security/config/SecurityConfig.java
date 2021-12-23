package co.wordbe.informmerce.api.common.security.config;

import co.wordbe.informmerce.api.common.security.login.form.FormAuthenticationProvider;
import co.wordbe.informmerce.api.common.security.exception.ForwardAuthenticationEntryPoint;
import co.wordbe.informmerce.api.common.security.login.form.FormFailureHandler;
import co.wordbe.informmerce.api.common.security.login.form.FormSuccessHandler;
import co.wordbe.informmerce.api.common.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final FormAuthenticationProvider formAuthenticationProvider;
    private final ForwardAuthenticationEntryPoint forwardAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final FormSuccessHandler formSuccessHandler;
    private final FormFailureHandler formFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // REST API 이므로 CSRF 보안을 사용하지 않음
            .authorizeRequests()
                .antMatchers("/**").permitAll() // 모든 요청은 모두 허용 (인증 필요 요청은 어노테이션으로 blacklist 관리)
                .and()
            .formLogin()
                .loginProcessingUrl("/v1/login")
                .successHandler(formSuccessHandler)
                .failureHandler(formFailureHandler)
                .and()
                .authenticationProvider(formAuthenticationProvider)
            .oauth2Login()
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(forwardAuthenticationEntryPoint)
            .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
