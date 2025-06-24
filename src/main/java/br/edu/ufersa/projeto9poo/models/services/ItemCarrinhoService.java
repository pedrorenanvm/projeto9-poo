package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.ItemCarrinho;

import java.util.List;
import java.util.Optional;

public interface ItemCarrinhoService {
    void cadastrar(ItemCarrinho itemCarrinho);

    void editar(ItemCarrinho itemCarrinho);

    void deletar(ItemCarrinho itemCarrinho);

    Optional<ItemCarrinho> buscarPorId(Long id);

    List<ItemCarrinho> buscarTodos();
}