package org.produtos.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.produtos.repository.helper.ProdutoRepositoryHelper;
import org.produtos.service.dto.ClienteProdutoDTO;
import org.produtos.service.dto.ResultadoConsultaDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProdutoRepository {

    @PersistenceContext
    EntityManager em;

    public List<ResultadoConsultaDTO> buscarHistoricoProdutosClientes() {
        String sql = ProdutoRepositoryHelper.queryForHistoricoProdutosClientes();

        Query query = em.createNativeQuery(sql);
        List<Object[]> rawResults = query.getResultList();

        List<ResultadoConsultaDTO> results = new ArrayList<>();

        for (Object[] row : rawResults) {
            results.add(new ResultadoConsultaDTO(row));
        }

        return results;
    }

    public List<ClienteProdutoDTO> buscarResumoProdutosClientes() {
        String sql = ProdutoRepositoryHelper.queryForFindResumoProdutosClientes();
        Query query = em.createNativeQuery(sql);
        List<Object[]> rawResults = query.getResultList();

        List<ClienteProdutoDTO> results = new ArrayList<>();

        for (Object[] row : rawResults) {
            results.add(new ClienteProdutoDTO(row));
        }

        return results;
    }
}