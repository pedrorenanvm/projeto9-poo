package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository {
    void cadastrar(Carrinho carrinho);

    void editar(Carrinho carrinho);

    void deletar(Carrinho carrinho);

    Optional<Carrinho> buscar(Long id);

    List<Carrinho> buscarTodos();
}
