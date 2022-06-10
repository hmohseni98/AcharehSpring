package com.spring.achareh.config;

import com.spring.achareh.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/customer/register").permitAll()
                .antMatchers("/api/expert/register").permitAll()
                .antMatchers("/api/admin/register").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/secure/admin/**").hasAuthority("Admin")
                .antMatchers("/secure/customer/**").hasAuthority("Customer")
                .antMatchers("/secure/expert/**").hasAuthority("Expert")
                .antMatchers("/secure/**").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login.html")
                .failureHandler((request, response, exception) -> response.setStatus(404))
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(email ->
                        userRepository
                                .findByEmail(email)
                                .orElseThrow(() -> new UsernameNotFoundException("Username#" + email + " not found!"))
                )
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
