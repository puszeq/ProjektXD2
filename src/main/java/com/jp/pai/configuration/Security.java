package com.jp.pai.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserAuthenticationDetails userAuthenticationDetails;
    
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userAuthenticationDetails);
        builder.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userAuthenticationDetails);
        provider.setPasswordEncoder(passwordEncoder());
        
        return provider;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .usernameParameter("login")
            .passwordParameter("passwd")
            .defaultSuccessUrl("/profile", true)
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true);
    }
}
