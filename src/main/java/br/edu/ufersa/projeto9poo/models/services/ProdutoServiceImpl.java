package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Produto;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ProdutoServiceImpl implements ProdutoService{

    private final ProdutoRepository repository = new ProdutoRepositoryImpl();

    public void cadastrar(Produto produto) {
        if (repository.buscarPorNome(produto).isPresent()) {
            throw new IllegalArgumentException("Já existe um adicional com esse nome.");
        }
        repository.cadastrar(produto);
    }
    public void editar(Produto produto){
        if (repository.buscarPorId(produto.getId()).isEmpty()) {
            throw new IllegalArgumentException("Produto inexistente");
        }
        Optional<Produto> p = repository.buscarPorId(produto.getId());
        if (p.isPresent()) {
            Produto p2 = p.get();
            p2.setNome(produto.getNome());
            p2.setId(produto.getId());
            p2.setPreco(produto.getPreco());
            p2.setEstoque(produto.isEstoque());

            repository.editar(p2);
        }
    }

    public void deletar(Produto produto){
        Optional<Produto> p = repository.buscarPorId(produto.getId());
        if (p.isEmpty()){
            throw new IllegalArgumentException("O id não existe");
        } else {
            Produto p2 = p.get();
            repository.deletar(p2);
        }
    }

    @Override
    public List<Produto> buscarTodos(String produto) {
        if (produto==null || produto.trim().isEmpty()) {
            return repository.buscarTodos();
        }
        return repository.buscarTodos(produto);
    }
    public List<Produto> buscarTodos () {
        return repository.buscarTodos();
    }
}
