package fa.edu.vn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class ConfigrutationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authentication")
                .usernameParameter("userName")
                .passwordParameter("userPassword")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error")
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7))
                .key("ProtectedKey")
                .rememberMeCookieName("remember-me")
                .rememberMeParameter("remember-me")
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .deleteCookies("remember-me", "JSESSIONID")
                .logoutSuccessUrl("/login?action=LOGOUT")
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/register", "/logout", "/js/**", "/css/**", "/init/login", "/init/masterData").permitAll()
                .antMatchers("/CreateNewClass", "/cancelClass", "/importTrainee", "/removerTrainee", "/viewCandidate", "/deleteTraineeProfile").hasAnyRole("FAManager", "DeliveryManager")
                .antMatchers("/updateClass", "/updateTraineeProfile","/submitClass").hasAnyRole("FAManager", "DeliveryManager", "ClassAdmin")
                .antMatchers("/createCandidate", "/updateCandidate", "/deleteCanidate", "/viewCandidate").hasAnyRole("FAManager", "FARec")
                .anyRequest()
                .authenticated();


    }
}
