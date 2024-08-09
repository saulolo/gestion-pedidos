package com.gestion_pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadAppConfig {

    private final CustomUserDetailsService userDetailsService;

    public SeguridadAppConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    /**
     * Configuración de la autenticación
     * @param http Objeto HttpSecurity
     * @return SecurityFilterChain la configuración de la seguridad
     * @throws Exception En caso de error al configurar la seguridad
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/static/**", "/css/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/administradores").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("USER")
                                .requestMatchers("/assitants/**").hasRole("USER")
                                .requestMatchers("/volunteer/**").hasRole("VOLUNTEER")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/autenticacionUsuario")
                                .failureUrl("/login?error=true")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout=true")
                                .permitAll()
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/acceso-denegado")
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * Encriptar las contraseñas
     * @return BCryptPasswordEncoder Encriptador de contraseñas
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    // --Metodo para encriptar los password que tengamos de la BD
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("volunteer123"));
    }*/
}