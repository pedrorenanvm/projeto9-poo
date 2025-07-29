package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Produto;

import java.util.List;
import java.util.Optional;


public interface ProdutoRepository {
    void cadastrar(Produto produto);

    void editar(Produto produto);

    void deletar(Produto produto);

    Optional<Produto> buscarPorNome(Produto produto);

    Optional<Produto> buscarPorId(long id);

    List<Produto> buscarTodos();

    List<Produto> buscarTodos(String p);
}
