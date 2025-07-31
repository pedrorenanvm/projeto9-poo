package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Optional;

public class CarrinhoRepositoryImpl implements CarrinhoRepository {
    private final EntityManager em = JPAUtil.pegarEntityManagerFactory();

    @Override
    public void cadastrar(Carrinho carrinho) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(carrinho);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar carrinho", e);
        }
    }

    @Override
    public void editar(Carrinho carrinho) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.merge(carrinho);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao editar carrinho", e);
        }
    }


    @Override
    public void deletar(Carrinho carrinho) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Carrinho carrinhoGerenciado = em.find(Carrinho.class, carrinho.getId());
            if (carrinhoGerenciado != null) {
                em.remove(carrinhoGerenciado);
            } else {
                throw new RuntimeException("Carrinho com ID " + carrinho.getId() + " não encontrado");
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao deletar carrinho", e);
        }
    }


    @Override
    public Optional<Carrinho> buscar(Long id) {
        try {
            return Optional.of(em.createQuery("SELECT f FROM Carrinho f WHERE f.id = :id", Carrinho.class)
                    .setParameter("id", id).getSingleResult());
        } catch (NoResultException ignora) {
            return Optional.empty();
        }
    }

    @Override
    public List<Carrinho> buscarTodos() {
        return em.createQuery("SELECT p FROM Carrinho p", Carrinho.class).getResultList();
    }
}
