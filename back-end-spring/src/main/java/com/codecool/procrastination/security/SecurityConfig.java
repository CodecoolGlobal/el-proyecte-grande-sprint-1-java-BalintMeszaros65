package com.codecool.procrastination.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(2)
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter;
    private final JwtTokenFilter jwtTokenFilter;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter, JwtTokenFilter jwtTokenFilter, BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.customUsernamePasswordAuthenticationFilter = customUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                // TODO remove login after auth
                .antMatchers("/api/user/registration",
                        "/api/user/registration/check-email/**", "/api/user/login", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(customUsernamePasswordAuthenticationFilter)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // TODO use React endpoint for login
                .formLogin()
                .disable()
                .logout()
                .disable();
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
