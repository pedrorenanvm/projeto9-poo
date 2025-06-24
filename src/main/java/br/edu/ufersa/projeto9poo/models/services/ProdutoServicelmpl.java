package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Produto;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoRepositorylmpl;

import java.util.List;

public class ProdutoServicelmpl implements ProdutoService{

    private final ProdutoRepository repository = new ProdutoRepositorylmpl();

    public void cadastrar(Produto produto){
        if (repository.buscar(produto.nome) != null) {
            throw new IllegalArgumentException("Produto já existente.");
        }
        repository.cadastrar(produto);
    }
    public void editar(Produto produto){
        if (repository.buscar(produto.nome) == null) {
            throw new IllegalArgumentException("Produto inexistente");
        }
        Produto p = repository.buscar(produto.getId());
        p.setNome(produto.getNome());
        p.setId(produto.getId());
        p.setPreco(produto.getPreco());
        p.setEstoque(produto.getEstoque());

        repository.editar(p);
    }

    public void deletar(int id){
        Produto p = repository.buscar(id);
        if (p==null){
            throw new IllegalArgumentException("O id não existe");
        } else {
            repository.deletar(p);
        }
    }

    @Override
    public Produto buscar(int id) {
        return repository.buscar(id);
    }
    public List<Produto> buscarTodos(){
        return repository.buscarTodos();
    }
}
