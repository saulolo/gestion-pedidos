package com.gestion_pedidos.controller;

import com.gestion_pedidos.model.Cliente;
import com.gestion_pedidos.service.interfaces.IClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class Controlador {


    private final IClienteService iClienteService;

    public Controlador(IClienteService iClienteService) {
        this.iClienteService = iClienteService;
    }


    /**
     * Crea un cliente
     * @param elModelo Modelo de la vista
     * @return Vista para crear un cliente
     * @throws Exception Excepción en caso de error al crear el cliente en la base de
     *                   datos
     */
    @PostMapping("/crear")
    public String crearCliente(Model elModelo) {
        try {
            Cliente cli = new Cliente();
            elModelo.addAttribute("cliente", cli);
            return "formularioCliente";
        } catch (Exception e) {
            return "error" + e.getMessage();
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
