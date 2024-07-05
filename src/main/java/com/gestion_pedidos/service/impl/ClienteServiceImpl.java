package com.gestion_pedidos.service.impl;

import com.gestion_pedidos.model.Cliente;
import com.gestion_pedidos.repository.ClienteRepository;
import com.gestion_pedidos.service.interfaces.IClienteService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    
    /**
     * Crea un cliente
     * @param cliente Cliente a crear
     * @return true si el cliente fue creado correctamente, false en caso contrario
     * @throws RuntimeException Excepción en caso de error al crear el cliente
     */
    @Override
    @Transactional
    public boolean crearOActualizarCliente(Cliente cliente) {
        try {
            Cliente clienteGuardado = clienteRepository.save(cliente);
            return clienteRepository.findById(clienteGuardado.getId()).isPresent();
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cliente", e);
        }
    }

    /**
     * Obtiene todos los clientes
     * @return Lista de clientes
     * @throws Exception Excepción en caso de error al obtener los clientes
     */
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() throws Exception {
        try {
            return clienteRepository.findAll();
        } catch (DataAccessException e) {
            throw new Exception("Error al obtener los clientes", e);
        }
    }

    /**
     * Obtiene un cliente por su identificador
     * @param id Identificador del cliente
     * @return Cliente
     * @throws RuntimeException Excepción en caso de error al obtener el cliente
     */
    @Override
    @Transactional(readOnly = true)
    public Cliente getClienteById(Long id) {
        try {
            return clienteRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente", e);
        }
    }


    /**
     * Elimina un cliente por su identificador
     * @param id Identificador del cliente
     * @return true si el cliente fue eliminado correctamente, false en caso contrario
     * @throws RuntimeException Excepción en caso de error al eliminar el cliente
     */
    @Override
    @Transactional
    public boolean getDeleteById(Long id) {
        try {
            clienteRepository.deleteById(id);
            return !clienteRepository.findById(id).isPresent();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el cliente", e);
        }
    }
}