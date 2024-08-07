package com.gestion_pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.ExceptionHandlingDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadAppConfig {


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("saulolo")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN", "USER")
                .build();
        UserDetails user = User.withUsername("felipe")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();
        UserDetails volunteer = User.withUsername("alejandra")
                .password(passwordEncoder().encode("volunteer123"))
                .roles("VOLUNTEER")
                .build();
        return new InMemoryUserDetailsManager(admin, user, volunteer);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //Usuarios
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/static/**", "/css/**").permitAll()
                                //ADMIN
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/administradores").hasRole("ADMIN")
                                //USER
                                .requestMatchers("/user/**").hasRole("USER")
                                .requestMatchers("/assitants/**").hasRole("USER")
                                //VOLUNTEER
                                .requestMatchers("/volunteer/**").hasRole("VOLUNTEER")
                                .anyRequest().authenticated()
                )
                //Hacer login
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/autenticacionUsuario")
                                .failureUrl("/login?error=true")
                                .permitAll()
                )
                //Hacer logut
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout=true")
                                .permitAll()
                )
                //Para denegar accesos
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/acceso-denegado")
                )
                .csrf(AbstractHttpConfigurer::disable);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
