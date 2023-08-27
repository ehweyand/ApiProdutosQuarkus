package org.produtos.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.produtos.service.ProdutoService;
import org.produtos.service.dto.ResultadoConsultaDTO;

import java.util.List;

@Path("/api/produtos")
public class ProdutoController {

    @Inject
    ProdutoService produtoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultadoConsultaDTO> retrieveHistoricoProdutosClientes() {
        return produtoService.buscarHistoricoProdutosClientes();
    }
}

