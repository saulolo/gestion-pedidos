package com.gestion_pedidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        try {
            if (error != null) {
                model.addAttribute("error", "Usuario o contraseña incorrectos");
            }
            return "login";
        } catch (Exception e) {
                model.addAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo.");
            return "login";
        }
    }
}
