package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Produto;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


public class ProdutoRepositoryImpl implements ProdutoRepository{
    private final EntityManager em = JPAUtil.pegarEntityManagerFactory();

    public void cadastrar(Produto produto){
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(produto);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()){
                tx.rollback();
            }throw new RuntimeException("Erro ao cadastrar produto", e);
        }

    }

    public void editar(Produto produto){
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.merge(produto);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()){
                tx.rollback();
            }throw new RuntimeException("Erro ao editar roduto", e);
        }

    }

    public void deletar(Produto produto){
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.remove(produto);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()){
                tx.rollback();
            }throw new RuntimeException("Erro ao remover produto", e);
        }

    }

    public Optional<Produto> buscarPorNome(Produto produto){
        try{
            Produto p = em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome",Produto.class)
                    .setParameter("nome",produto.getNome()).getSingleResult();
            return Optional.of(p);
        } catch (NoResultException a) {
            return Optional.empty();
        }
    }

    public Optional<Produto> buscarPorId(long id){
        try{
            Produto p = em.createQuery("SELECT p FROM Produto p WHERE p.id = :id",Produto.class)
                    .setParameter("id",id).getSingleResult();
            return Optional.of(p);
        } catch (NoResultException a) {
            return Optional.empty();
        }
    }

    public List<Produto> buscarTodos() {
        return em.createQuery("SELECT p FROM Produto p",Produto.class).getResultList();
    }
    public List<Produto> buscarTodos(String produto){
        return em.createQuery("SELECT a FROM Produto a WHERE LOWER(a.nome) LIKE LOWER(:nome)", Produto.class)
                .setParameter("nome", "%" + produto + "%")
                .getResultList();
    }


}
