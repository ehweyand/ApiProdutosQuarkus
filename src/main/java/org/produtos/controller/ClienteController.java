package org.produtos.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.produtos.entity.Cliente;
import org.produtos.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

@Path("/api/clientes")
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @GET
    public List<Cliente> retrieveClientes() {

        List<Cliente> clientes = new ArrayList<>();

        try {
            clientes = clienteService.findAllClientes();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return clientes;
    }

}
