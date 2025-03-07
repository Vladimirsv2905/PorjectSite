package org.selebros.spring_mvc_demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig   {
    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                 .authorizeHttpRequests(request ->
                    request

                            .requestMatchers("/", "/login","/registration","/users").permitAll()
                            .requestMatchers("/profile").authenticated()
                            .requestMatchers(new AntPathRequestMatcher("/admin/users", "GET")).authenticated()
                            .requestMatchers(new AntPathRequestMatcher("/admin/users", "POST")).hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/admin/users/delete/**").hasRole("ADMIN")
                            .requestMatchers(new AntPathRequestMatcher("/admin/professions", "GET")).authenticated()
                            .requestMatchers(new AntPathRequestMatcher("/admin/professions", "POST")).hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/admin/professions/delete/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/login").permitAll()
                                .defaultSuccessUrl("/")
                )
                .logout(form ->
                        form
                                .logoutUrl("/logout")
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                                .logoutSuccessUrl("/")
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("/styles/*.css");
    }






    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder());
        return provider;
    }

    @Bean
    public UserDetailsManager userDetailsManager(HttpSecurity http) throws Exception {
        AuthenticationManager authManager = http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder())
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .build();
        JdbcUserDetailsManager jdbcManager = new JdbcUserDetailsManager(dataSource);
        jdbcManager.setAuthenticationManager(authManager);
        return jdbcManager;
    }

    @Bean
    protected BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }
}
