package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.Pedido;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;

public class PedidoRepositoryImpl implements PedidoRepository {
    private final EntityManager em = JPAUtil.pegarEntityManagerFactory();

    @Override
    public void cadastrar(Pedido pedido) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(pedido);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar funcionario", e);
        }
    }

    @Override
    public void editar(Pedido pedido) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(pedido);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao editar funcionario", e);
        }
    }

    @Override
    public void deletar(Pedido pedido) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(pedido);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao deletar funcionario", e);
        }
    }

    @Override
    public Pedido buscar(int id) {
        try {
            return em.createQuery("SELECT f FROM Funcionario f WHERE f.id = :id", Pedido.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException ignora) {
            return null;
        }
    }

    @Override
    public List<Pedido> buscarTodos() {
        return em.createQuery("SELECT f FROM Funcionario f", Pedido.class).getResultList();
    }
}
