package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Produto;

import java.util.List;

public interface ProdutoService {
    public void cadastrar(Produto produto);

    public void editar(Produto produto);

    public void deletar(int id);

    public Produto buscar(int id);

    public List<Produto> buscarTodos();

}
