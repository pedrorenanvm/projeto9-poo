package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Produto;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java.util.List;


public class ProdutoRepositorylmpl implements ProdutoRepository{
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

    public Produto buscar(String nome){
        try{
            return em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome",Produto.class)
                    .setParameter("nome",nome).getSingleResult();
        } catch (NoResultException a) {
            return null;
        }
    }

    public Produto buscar(int id){
        try{
            return em.createQuery("SELECT p FROM Produto p WHERE p.id = :idd",Produto.class)
                    .setParameter("id",id).getSingleResult();
        } catch (NoResultException a) {
            return null;
        }
    }
    public Produto buscar(long preco){
        try{
            return em.createQuery("SELECT p FROM Produto p WHERE p.preco = :preco",Produto.class)
                    .setParameter("nome",preco).getSingleResult();
        } catch (NoResultException a) {
            return null;
        }
    }
    public List<Produto> buscarTodos() {
        return em.createQuery("SELECT p FROM Produto p",Produto.class).getResultList();
    }



}
