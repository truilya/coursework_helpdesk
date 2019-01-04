package coursework_helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("123")
                .roles("USER");
        auth
                .inMemoryAuthentication()
                .withUser("iam_admin")
                .password("123")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/protected/**")
                .access("hasRole('ROLE_USER')")
                .and()
                .formLogin();
        http
                .authorizeRequests()
                .antMatchers("/coursework_helpdesk/user/**")
                .access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin();


        http
                .authorizeRequests()
                .antMatchers("/coursework_helpdesk/issue/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ENGINEER')")
                .and()
                .formLogin();
    }


}
