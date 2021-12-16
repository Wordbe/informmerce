package co.wordbe.informmerce.api.common.security.config;

import co.wordbe.informmerce.api.common.security.form.FormAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final FormAuthenticationProvider formAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // REST API 이므로 CSRF 보안을 사용하지 않음
                .authorizeRequests()
                .antMatchers("/**").permitAll()
            .and()
                .formLogin()
                .and()
                .authenticationProvider(formAuthenticationProvider);
    }
}
