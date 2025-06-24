package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;

import java.util.List;
import java.util.Optional;

public interface CarrinhoService {
    public void cadastrar(Carrinho carrinho);

    public void editar(Carrinho carrinho);

    public void deletar(Carrinho carrinho);

    public Optional<Carrinho> buscar(Carrinho carrinho);

    public List<Carrinho> buscarTodos();
}
