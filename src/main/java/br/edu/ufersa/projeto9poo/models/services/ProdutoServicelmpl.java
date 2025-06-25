package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Produto;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoRepositorylmpl;

import java.util.List;
import java.util.Optional;

public class ProdutoServicelmpl implements ProdutoService{

    private final ProdutoRepository repository = new ProdutoRepositorylmpl();

    public void cadastrar(Produto produto){
        if (repository.buscarPorId(produto.id).isEmpty()) {
            repository.cadastrar(produto);
        }
        throw new IllegalArgumentException("Produto já existente.");
    }
    public void editar(Produto produto){
        if (repository.buscarPorId(produto.id).isEmpty()) {
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
        Optional<Produto> p = repository.buscarPorId(produto.id);
        if (p.isEmpty()){
            throw new IllegalArgumentException("O id não existe");
        } else {
            Produto p2 = p.get();
            repository.deletar(p2);
        }
    }

    @Override
    public Optional<Produto> buscar(Produto produto) {
        Optional<Produto> p = repository.buscarPorId(produto.id);
        if (p.isEmpty()) {
            throw new IllegalArgumentException("O id não existe");
        }
        return repository.buscarPorId(produto.id);
    }
    public List<Produto> buscarTodos(){
        return repository.buscarTodos();
    }
}
