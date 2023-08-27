package org.produtos.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.produtos.entity.Produto;
import org.produtos.entity.ProdutoPedido;
import org.produtos.repository.ProdutoPedidoRepository;

import java.util.List;

@ApplicationScoped
public class ProdutoPedidoService {
    @Inject
    ProdutoPedidoRepository produtoPedidoRepository;

    public List<ProdutoPedido> findAllPedidoProduto() {
        return produtoPedidoRepository.findAll();
    }
}
