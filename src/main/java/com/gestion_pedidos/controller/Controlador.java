package com.gestion_pedidos.controller;

import com.gestion_pedidos.model.Cliente;
import com.gestion_pedidos.service.interfaces.IClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class Controlador {


    private final IClienteService iClienteService;

    public Controlador(IClienteService iClienteService) {
        this.iClienteService = iClienteService;
    }


    /**
     * Muestra el formulario para agregar un cliente
     * @param elModelo Modelo de la vista
     * @return Vista con el formulario para agregar un cliente
     */
    @GetMapping("/muestraFormularioAgregar")
    public String muestraFormularioAgregar(Model elModelo) {
        try {
            Cliente elCliente = new Cliente();
            elModelo.addAttribute("cliente", elCliente);
            return "formularioCliente";
        } catch (Exception e) {
            elModelo.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    /**
     * Crea o actualiza un cliente
     * @param elCliente Cliente a crear o actualizar
     * @param bindingResult Resultado de la validación
     * @param redirectAttributes Atributos de redirección
     * @param elModelo Modelo de la vista
     * @return Redirección a la lista de clientes si se crea correctamente, de lo contrario, muestra el formulario de creación
     * @throws Exception Excepción en caso de error al crear o actualizar el cliente
     */
    @PostMapping("/insertarCliente")
    public String crearOActualizarCliente(@Valid @ModelAttribute("cliente") Cliente elCliente,
                                          BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes,
                                          Model elModelo) {
        try {
            if (bindingResult.hasErrors()) {
                return "formularioCliente";
            }
            if (iClienteService.crearOActualizarCliente(elCliente)) {
                redirectAttributes.addFlashAttribute("mensaje", "Cliente creado correctamente");
                return "redirect:/cliente/listar";
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "Error al crear o actualizar el cliente");
                return "redirect:/cliente/muestraFormularioAgregar";
            }
        } catch (Exception e) {
            elModelo.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    /**
     * Obtiene todos los clientes
     * @param elModelo Modelo de la vista
     * @return Vista con la lista de clientes
     * @throws Exception Excepción en caso de error al obtener los clientes
     */
    @GetMapping("/listar")
    public String listaClientes(Model elModelo) throws Exception {
        List<Cliente> losClientes = iClienteService.getClientes();

        elModelo.addAttribute("clientes", losClientes);
        return "lista-clientes";
    }
}
