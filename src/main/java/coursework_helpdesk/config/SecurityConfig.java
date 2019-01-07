package coursework_helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login username, password, true enabled " +
                        " from d_users where login=?")
                .authoritiesByUsernameQuery("select du.login, 'ROLE_'||ur.role authority" +
                        " from d_users du" +
                        " join user_roles ur" +
                        " on du.id=ur.user_id" +
                        " where du.login=?")
                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/coursework_helpdesk/index")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ENGINEER') or hasRole('ROLE_ADMIN')")
                .and()
                .formLogin();
        http
                .authorizeRequests()
                .antMatchers("/coursework_helpdesk/user/**")
                .access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/coursework_helpdesk/index")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));


        http
                .authorizeRequests()
                .antMatchers("/coursework_helpdesk/issue/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ENGINEER')")
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/coursework_helpdesk/index")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

    }


}
