package com.gestion_pedidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
}
