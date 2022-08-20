package com.vaadin.tutorial.crm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableWebSecurity // turns on Spring Security for the application
@Configuration // tells Spring Boot to use this class for configuring security
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disables cross-site request forgery (CSRF) protection, as Vaadin already has CSRF protection
                .requestCache().requestCache(new CustomRequestCache()) // Uses CustomRequestCache to track unauthorized requests so that users are redirected appropriately after login
                .and().authorizeRequests() // turn on authorization
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll() // Allows all internal traffic from the Vaadin framework.
                .anyRequest().authenticated() // Allows all authenticated traffic.
                .and().formLogin() //  Enables form-based login and permits unauthenticated access to it.
                .loginPage(LOGIN_URL).permitAll() //
                .loginProcessingUrl(LOGIN_PROCESSING_URL) // Configures the login page URLs
                .failureUrl(LOGIN_FAILURE_URL)
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL); // Configures the logout URL.
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().anyMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                "/icons/**",
                "/images/**",
                "/styles/**",
                "/h2-console/**");
    }
}
