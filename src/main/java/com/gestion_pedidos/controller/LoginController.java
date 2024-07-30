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
    
    //todo: 1. Impementacón Rol. Corregir el controlador para que me traiga 
    //todo: el rol
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

}
