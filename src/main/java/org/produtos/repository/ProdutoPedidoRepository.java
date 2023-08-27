package org.produtos.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.produtos.entity.ProdutoPedido;

import java.util.List;
@ApplicationScoped
public class ProdutoPedidoRepository {

    @PersistenceContext
    EntityManager em;

    public List<ProdutoPedido> findAll() {
        return em.createQuery("SELECT pp FROM ProdutoPedido pp", ProdutoPedido.class).getResultList();
    }
}
