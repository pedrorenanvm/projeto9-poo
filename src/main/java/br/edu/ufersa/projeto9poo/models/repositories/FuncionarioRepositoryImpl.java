package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;

public class FuncionarioRepositoryImpl implements FuncionarioRepository {
    private final EntityManager em = JPAUtil.pegarEntityManagerFactory();

    @Override
    public void cadastrar(Funcionario funcionario) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(funcionario);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar funcionario", e);
        }
    }

    @Override
    public void editar(Funcionario funcionario) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(funcionario);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao editar funcionario", e);
        }
    }

    @Override
    public void deletar(Funcionario funcionario) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(funcionario);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao editar funcionario", e);
        }
    }

    @Override
    public Funcionario buscar(int id) {
        try {
            return em.createQuery("SELECT f FROM Funcionario f WHERE f.id = :id", Funcionario.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException ignora) {
            return null;
        }
    }

    @Override
    public Funcionario buscar(String usuario) {
        try {
            return em.createQuery("SELECT f FROM Funcionario f WHERE f.usuario = :usuario", Funcionario.class)
                    .setParameter("usuario", usuario).getSingleResult();
        } catch (NoResultException ignora) {
            return null;
        }
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return em.createQuery("SELECT f FROM Funcionario f", Funcionario.class).getResultList();
    }
}
