package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository {
    public void cadastrar(Carrinho carrinho);

    public void editar(Carrinho carrinho);

    public void deletar(Carrinho carrinho);

    public Optional<Carrinho> buscar(int id);

    public List<Carrinho> buscarTodos();
}
