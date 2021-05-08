package com.genesiscode.quotation.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static com.genesiscode.quotation.security.RoleResponsible.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*" ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        UserDetails rafael = User.builder()
                .username("rafael")
                .password(passwordEncoder.encode("rafael17"))
//                .roles(HEAD_OF_ADMINISTRATIVE_UNIT.name())
                .authorities(HEAD_OF_ADMINISTRATIVE_UNIT.getGrantedAuthorities())
                .build();

        UserDetails juan = User.builder()
                .username("juan")
                .password(passwordEncoder.encode("juan17"))
                //.roles(HEAD_OF_DEPENDENCY_ADMINISTRATIVE_UNIT.name())
                .authorities(HEAD_OF_DEPENDENCY_ADMINISTRATIVE_UNIT.getGrantedAuthorities())
                .build();

        UserDetails alex = User.builder()
                .username("alex")
                .password(passwordEncoder.encode("alex17"))
                //.roles(HEAD_OF_DIRECTION.name())
                .authorities(HEAD_OF_DIRECTION.getGrantedAuthorities())
                .build();

        UserDetails cynthia = User.builder()
                .username("cynthia")
                .password(passwordEncoder.encode("cynthia17"))
                //.roles(HEAD_OF_EXPENSE_UNIT.name())
                .authorities(HEAD_OF_EXPENSE_UNIT.getGrantedAuthorities())
                .build();

        UserDetails jose = User.builder()
                .username("jose")
                .password(passwordEncoder.encode("jose17"))
                //.roles(HEAD_OF_DEPENDENCY_EXPENSE_UNIT.name())
                .authorities(HEAD_OF_DEPENDENCY_EXPENSE_UNIT.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(rafael, juan, alex, cynthia, jose);
    }
}
