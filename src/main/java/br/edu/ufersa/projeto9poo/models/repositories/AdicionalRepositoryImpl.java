package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Optional;

public class AdicionalRepositoryImpl implements AdicionalRepository{
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
    public Optional<Adicional> buscarPorId(long id) {
        try {
            Adicional a = em.createQuery("SELECT a FROM Adicional a WHERE a.id = :id", Adicional.class)
                    .setParameter("id", id).getSingleResult();
            return Optional.of(a);
        } catch (NoResultException a) {
            return Optional.empty();
        }
    }
    public Optional<Adicional> buscarPorNome(String adicional){
        try{
            Adicional a = em.createQuery("SELECT a FROM Adicional a WHERE a.nome = :nome",Adicional.class)
                    .setParameter("nome",adicional).getSingleResult();
            return Optional.of(a);
        } catch (NoResultException a) {
            return Optional.empty();
        }
    }

    @Override
    public List<Adicional> buscarTodos() {
        return em.createQuery("SELECT a FROM Adicional a", Adicional.class).getResultList();
    }
    public List<Adicional> buscarTodos(String adicional){
        return em.createQuery("SELECT a FROM Adicional a WHERE LOWER(a.nome) LIKE LOWER(:nome)", Adicional.class)
                .setParameter("nome", "%" + adicional + "%")
                .getResultList();
    }
}
