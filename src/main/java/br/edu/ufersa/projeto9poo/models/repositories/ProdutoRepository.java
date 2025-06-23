package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Produto;

import java.util.List;


public interface ProdutoRepository {
    public void cadastrar(Produto produto);

    public void editar(Produto produto);

    public void deletar(Produto produto);

    public Produto buscar(int id);

    public Produto buscar(String nome);

    public List<Produto> buscar();
}
