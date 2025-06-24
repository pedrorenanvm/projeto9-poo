package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;

public class AdicionalRepositorylmpl implements AdicionalRepository{
    private final EntityManager em = JPAUtil.pegarEntityManagerFactory();

    public void cadastrar(Adicional adicional){
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(adicional);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()){
                tx.rollback();
            }throw new RuntimeException("Erro ao cadastrar adicional", e);
        }

    }
    @Override
    public void editar(Adicional adicional) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(adicional);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao editar adicional", e);
        }
    }

    @Override
    public void deletar(Adicional adicional) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(adicional);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao deletar adicional", e);
        }
    }

    @Override
    public Adicional buscar(int id) {
        try {
            return em.createQuery("SELECT a FROM Adicional a WHERE a.id = :id", Adicional.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException a) {
            return null;
        }
    }
    public Adicional buscar(String nome){
        try{
            return em.createQuery("SELECT a FROM Adicional a WHERE a.nome = :nome",Adicional.class)
                    .setParameter("nome",nome).getSingleResult();
        } catch (NoResultException a) {
            return null;
        }
    }
    public Adicional buscar(long preco){
        try{
            return em.createQuery("SELECT p FROM Produto p WHERE p.preco = :preco",Adicional.class)
                    .setParameter("nome",preco).getSingleResult();
        } catch (NoResultException a) {
            return null;
        }
    }
    @Override
    public List<Adicional> buscarTodos() {
        return em.createQuery("SELECT a FROM Adicional a", Adicional.class).getResultList();
    }
}
