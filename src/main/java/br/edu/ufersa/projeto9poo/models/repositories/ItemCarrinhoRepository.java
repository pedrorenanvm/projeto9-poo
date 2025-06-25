package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.ItemCarrinho;

import java.util.List;
import java.util.Optional;

public interface ItemCarrinhoRepository {

    void cadastrar(ItemCarrinho produto);

    void editar(ItemCarrinho produto);

    void deletar(ItemCarrinho produto);

    Optional<ItemCarrinho> buscarPorId( Long id);

    List<ItemCarrinho> buscarTodos();

}
