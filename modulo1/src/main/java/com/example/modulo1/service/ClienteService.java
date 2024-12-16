package com.example.modulo1.service;


import com.example.modulo1.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.modulo1.repository.ClienteRepository;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;


    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        this.repository.persistir(cliente);

    }

    public void validarCliente(Cliente cliente) {
        this.repository.persistir(cliente);
    }
}
