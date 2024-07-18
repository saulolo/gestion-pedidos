package com.gestion_pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Configuración de seguridad para la aplicación.
 *
 * <p>Esta clase configura la seguridad de la aplicación utilizando usuarios en memoria.
 * Sin embargo, esta implementación es obsoleta y no se recomienda para entornos de producción.</p>
 *
 * <p><b>Nota:</b> La clase {@code WebSecurityConfigurerAdapter} ha sido deprecada en
 * Spring Security 5.7.0-M2 en favor de una configuración basada en componentes.
 * Se recomienda usar la interfaz {@code SecurityConfigurer} y la clase {@code SecurityFilterChain}
 * para configuraciones más modernas y seguras.</p>
 *
 * @deprecated Esta implementación está obsoleta y se recomienda usar la configuración basada en componentes.
 */
@Configuration
@EnableWebSecurity
@Deprecated
public class SeguridadAppConfig implements WebSecurityConfigurer {


    /**
     * Configura un {@link InMemoryUserDetailsManager} con un usuario en memoria.
     *
     * <p>Este método crea un usuario con nombre de usuario "user" y contraseña "123",
     * asignándole el rol "ADMIN".</p>
     *
     * @return Un {@link InMemoryUserDetailsManager} que contiene el usuario configurado.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Método init obsoleto.
     *
     * <p>Este método está obsoleto y no debería ser utilizado. Se recomienda
     * utilizar la configuración basada en componentes.</p>
     *
     * @param builder el constructor de seguridad
     * @throws Exception en caso de que ocurra un error durante la inicialización
     */
    @Override
    @Deprecated
    public void init(SecurityBuilder builder) throws Exception {
        //Método deprecado
    }

    /**
     * Método configure obsoleto.
     *
     * <p>Este método está obsoleto y no debería ser utilizado. Se recomienda
     * utilizar la configuración basada en componentes.</p>
     *
     * @param builder el constructor de seguridad
     * @throws Exception en caso de que ocurra un error durante la configuración
     */
    @Override
    @Deprecated
    public void configure(SecurityBuilder builder) throws Exception {
        //Método deprecado
    }
}
