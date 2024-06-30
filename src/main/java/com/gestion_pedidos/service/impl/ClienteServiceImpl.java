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
}