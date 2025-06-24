package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.ItemCarrinho;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class ItemCarrinhoRepositoryImpl implements ItemCarrinhoRepository {
    private final EntityManager em = new JPAUtil().pegarEntityManagerFactory();

    @Override
    public void cadastrar(ItemCarrinho itemCarrinho) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(itemCarrinho);
            tx.commit();
        }catch(RuntimeException e){
            if(tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao cadastar ItemCarrinho",e);
        }
    }

    @Override
    public void editar(ItemCarrinho itemCarrinho) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(itemCarrinho);
            tx.commit();
        }catch(RuntimeException e){
            if(tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao editar ItemCarrinho",e);
        }
    }

    @Override
    public void deletar(ItemCarrinho itemCarrinho) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            ItemCarrinho find = em.find(ItemCarrinho.class, itemCarrinho.getId());
            if(find != null){
                em.remove(find);
            }

            tx.commit();
        }catch(RuntimeException e){
            if(tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao deletar ItemCarrinho",e);
        }
    }

    @Override
    public Optional<ItemCarrinho> buscarPorId( Long id) {
        try {
            return Optional.ofNullable(em.find(ItemCarrinho.class, id));
        }catch(RuntimeException e){
            return Optional.empty();
        }
    }

    @Override
    public List<ItemCarrinho> buscarTodos() {
        return em.createQuery("SELECT pp FROM ItemCarrinho pp", ItemCarrinho.class).getResultList();
    }
}
