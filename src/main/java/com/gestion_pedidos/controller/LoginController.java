package com.gestion_pedidos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    /**
     * Muestra la vista html del login
     * @param error se envía cuando el usuario o la contraseña son incorrectos
     * @param logout  se envía cuando se cierra la sesión
     * @param model se utiliza para enviar mensajes de error o de éxito
     * @return el html del login
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        try {
            if (error != null) {
                model.addAttribute("error", "Usuario o contraseña incorrectos");
            }
            if (logout != null) {
                model.addAttribute("message", "Has cerrado la sesión");
            }
            return "login";
        } catch (Exception e) {
                model.addAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo.");
            return "login";
        }
    }
    
    //todo: 1. Impementacón Rol. Corregir el controlador para que me traiga el rol
  @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");
        model.addAttribute("userRole", userRole);
        return "index";
    }

    /**
     * Muestra la vista html de los  administradores
     * @return el html de los administradores
     */
    @GetMapping("/administradores")
    public String  muestraAdministradores() {
        return "lista-administradores";
    }


    /**
     * Muestra la vista html de los usuarios que intentan acceder a una página sin permisos
     * @return el html de los usuarios que intentan acceder a una página sin permisos
     */
    @GetMapping("/acceso-denegado")
    public String  muestraAccesoDenegado() {
        return "acceso-denegado";
    }

    /**
     * Muestra la vista html de los asistentes
     * @return el html de los asistentes
     */
    @GetMapping("/assitants")
    public String muestraAsitentes() {
        return "lista-asistentes";
    }

}
