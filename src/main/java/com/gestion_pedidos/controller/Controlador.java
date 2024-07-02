package com.gestion_pedidos.controller;

import com.gestion_pedidos.model.Cliente;
import com.gestion_pedidos.service.interfaces.IClienteService;
import jakarta.validation.Valid;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @throws Exception Excepción en caso de error al obtener el cliente
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
     * Muestra el formulario para modificar un cliente
     * @param elModelo Modelo de la vista
     * @param id Identificador del cliente a modificar
     * @return Vista con el formulario para modificar un cliente
     * @throws Exception Excepción en caso de error al obtener el cliente
     */
    @GetMapping("/muestraFormularioModificar/{id}")
    public String muestraFormularioModificar(Model elModelo, @PathVariable Long id) {
        try {
            Cliente elCliente = iClienteService.getClienteById(id);
            elModelo.addAttribute("cliente", elCliente);
            return "formularioModificarCliente";
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
    public String crearCliente(@Valid @ModelAttribute("cliente") Cliente elCliente,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model elModelo) {
        try {
            if (bindingResult.hasErrors()) {
                return "formularioCliente";
            }
            if (iClienteService.crearOActualizarCliente(elCliente)) {
                redirectAttributes.addFlashAttribute("mensaje", "saveOk");
                return "redirect:/cliente/listar";
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "saveError");
                return "redirect:/cliente/muestraFormularioAgregar";
            }
        } catch (Exception e) {
            elModelo.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    /**
     * Actualiza un cliente
     * @param elCliente Cliente a actualizar
     * @param bindingResult Resultado de la validación
     * @param redirectAttributes Atributos de redirección
     * @param elModelo Modelo de la vista
     * @return Redirección a la lista de clientes si se actualiza correctamente, de lo contrario, muestra el formulario de modificación
     * @throws Exception Excepción en caso de error al actualizar el cliente
     */
    @PostMapping("/modificarCliente")
    public String actualizarCliente(@Valid @ModelAttribute("cliente") Cliente elCliente,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model elModelo) {
        try {
            if (bindingResult.hasErrors()) {
                return "formularioModificarCliente";
            }
            if (iClienteService.crearOActualizarCliente(elCliente)) {
                redirectAttributes.addFlashAttribute("mensaje", "updateOk");
                return "redirect:/cliente/listar";
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "updateError");
                return "redirect:/cliente/formularioModificarCliente";
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
