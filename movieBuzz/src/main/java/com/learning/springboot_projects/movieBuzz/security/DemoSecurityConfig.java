package com.learning.springboot_projects.movieBuzz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    private JWTFilter theJwtFilter;

    @Autowired
    public DemoSecurityConfig(JWTFilter jwtFilter){
        theJwtFilter = jwtFilter;
    }
    // add support for JDBC ..... no more hard coded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select email,password,enabled from user where email=?");

        // define query to retrieve authority by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username,role as authority from roles where username=?");

        return jdbcUserDetailsManager;
    }

    // define BCryptPasswordEncoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        // strength = 10 (default). Can increase if you want stronger hashing but slower performance
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // no HTTP session
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(
                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/v3/api-docs",
                                        "/swagger-resources/**"
                                ).permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/createAdminUser").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyAuthority("CUSTOMER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/ticket/**").hasAnyAuthority("CUSTOMER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/user").hasAuthority("CUSTOMER")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/ticket/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/movie/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/seat").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/movie").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/movie/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(theJwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsManager,PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsManager);  // JdbcUserDetailsManager
        provider.setPasswordEncoder(passwordEncoder);        // bcrypt
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
