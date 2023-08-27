package org.produtos.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.produtos.entity.Cliente;

import java.util.List;

@ApplicationScoped
public class ClienteRepository {

    @PersistenceContext
    EntityManager em;

    public Cliente findById(Long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> findAll() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public void persist(Cliente cliente) {
        em.persist(cliente);
    }
}
