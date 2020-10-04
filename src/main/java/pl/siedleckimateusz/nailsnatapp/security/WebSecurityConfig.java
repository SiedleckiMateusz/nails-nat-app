package pl.siedleckimateusz.nailsnatapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SimpleUrlAuthenticationSuccessHandlerImpl successHandler;

    public WebSecurityConfig(SimpleUrlAuthenticationSuccessHandlerImpl successHandler) {
        this.successHandler = successHandler;
        this.successHandler.setUseReferer(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/visit/**")
                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/*")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/login")
                .not().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
//                    .successHandler(successHandler)
                    .and()
                    .logout()
                .and()
                .headers()
                    .frameOptions().disable()
                    .and()
                    .csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
