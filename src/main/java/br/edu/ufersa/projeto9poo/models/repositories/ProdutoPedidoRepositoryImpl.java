package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.ProdutoPedido;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class ProdutoPedidoRepositoryImpl implements ProdutoPedidoRepository {
    private final EntityManager em = new JPAUtil().pegarEntityManagerFactory();

    @Override
    public void cadastrar(ProdutoPedido produtoPedido) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(produtoPedido);
            tx.commit();
        }catch(RuntimeException e){
            if(tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao cadastar ProdutoPedido",e);
        }
    }

    @Override
    public void editar(ProdutoPedido produtoPedido) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(produtoPedido);
            tx.commit();
        }catch(RuntimeException e){
            if(tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao editar ProdutoPedido",e);
        }
    }

    @Override
    public void deletar(ProdutoPedido produtoPedido) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            ProdutoPedido find = em.find(ProdutoPedido.class, produtoPedido.getId());
            if(find != null){
                em.remove(find);
            }

            tx.commit();
        }catch(RuntimeException e){
            if(tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao deletar ProdutoPedido",e);
        }
    }

    @Override
    public Optional<ProdutoPedido> buscarPorId(Long id) {
        try {
            return Optional.ofNullable(em.find(ProdutoPedido.class, id));
        }catch(RuntimeException e){
            return Optional.empty();
        }
    }

    @Override
    public List<ProdutoPedido> buscarTodos() {
        return em.createQuery("SELECT pp FROM ProdutoPedido pp", ProdutoPedido.class).getResultList();
    }
}
