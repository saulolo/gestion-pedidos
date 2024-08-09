package com.gestion_pedidos.config;

import com.gestion_pedidos.model.Rol;
import com.gestion_pedidos.model.Usuario;
import com.gestion_pedidos.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carga un usuario por su nombre de usuario
     * @param username Nombre de usuario
     * @return Usuario
     * @throws UsernameNotFoundException Excepción en caso de que el usuario no exista
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // Construir el array de roles
        String[] roles = usuario.getRoles().stream()
                .map(Rol::getAuthority)
                .toArray(String[]::new);

        return User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(roles)
                .disabled(!usuario.isEnabled())
                .build();
    }
}
