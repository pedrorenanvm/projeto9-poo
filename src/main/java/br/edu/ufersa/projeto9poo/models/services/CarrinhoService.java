package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;

import java.util.List;
import java.util.Optional;

public interface CarrinhoService {
    void cadastrar(Carrinho carrinho);

    void editar(Carrinho carrinho);

    void deletar(Carrinho carrinho);

    Optional<Carrinho> buscar(Carrinho carrinho);

    Optional<Carrinho> buscarPorId(Long id);

    List<Carrinho> buscarTodos();

    List<Carrinho> buscarTodosHoje();

    List<Carrinho> buscarTodosSemana();

    List<Carrinho> buscarTodosMes();

    long quantidadeAberto();

    long somaPrecoHoje();
}
