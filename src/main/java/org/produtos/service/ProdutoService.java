package org.produtos.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.produtos.repository.ProdutoRepository;
import org.produtos.service.dto.ClienteProdutoDTO;
import org.produtos.service.dto.ResultadoConsultaDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepo;

    public List<ResultadoConsultaDTO> buscarHistoricoProdutosClientes() {
        try {
            return produtoRepo.buscarHistoricoProdutosClientes();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<ClienteProdutoDTO> buscarResumoProdutosClientes() {
        try {
            return produtoRepo.buscarResumoProdutosClientes();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}