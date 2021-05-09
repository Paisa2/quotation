package com.genesiscode.quotation.security;
import com.genesiscode.quotation.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ResponsibleService responsibleService;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(responsibleService);
        return provider;
    }


    /*
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
    }*/
}
