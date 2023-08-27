package org.produtos.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.produtos.entity.ProdutoPedido;
import org.produtos.service.ProdutoPedidoService;

import java.util.ArrayList;
import java.util.List;

@Path("/api/produtos-pedidos")
public class ProdutoPedidoController {
    @Inject
    ProdutoPedidoService produtoPedidoService;

    @GET
    public List<ProdutoPedido> retrieveProdutosPedidos() {

        List<ProdutoPedido> produtosPedidos = new ArrayList<>();

        try {
            produtosPedidos = produtoPedidoService.findAllPedidoProduto();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return produtosPedidos;
    }
}
