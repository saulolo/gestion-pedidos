package com.gestion_pedidos.service.interfaces;

import com.gestion_pedidos.model.Cliente;

import java.util.List;

public interface IClienteService {


    /**
     * Crea un cliente
     * @param cliente Cliente a crear
     */
    boolean crearOActualizarCliente(Cliente cliente);

    /**
     * Obtiene todos los clientes
     * @return Lista de clientes
     * @throws Exception Excepción en caso de error al obtener los clientes
     */
    List<Cliente> getClientes() throws Exception;

    /**
     * Obtiene un cliente por su id
     * @param id Id del cliente
     */
    Cliente getClienteById(Long id);

    /**
     * Elimina un cliente por su id
     * @param id Id del cliente
     */
    boolean getDeleteById(Long id);
}
