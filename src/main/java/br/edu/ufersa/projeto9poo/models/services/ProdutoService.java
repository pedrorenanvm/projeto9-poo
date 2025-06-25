package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    void cadastrar(Produto produto);

    void editar(Produto produto);

    void deletar(Produto produto);

    Optional<Produto> buscar(Produto produto);

    List<Produto> buscarTodos();

}
