package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.ItemCarrinho;
import br.edu.ufersa.projeto9poo.models.repositories.ItemCarrinhoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.ItemCarrinhoRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ItemCarrinhoServiceImpl implements ItemCarrinhoService {

    private final ItemCarrinhoRepository repository = new ItemCarrinhoRepositoryImpl();

    @Override
    public void cadastrar(ItemCarrinho itemCarrinho) {
        validarProdutoPedido(itemCarrinho);
        repository.cadastrar(itemCarrinho);
    }

    @Override
    public void editar(ItemCarrinho itemCarrinho) {
        if (itemCarrinho.getId() == null){
            throw new IllegalArgumentException("ID do produtoPedido não pode ser nulo");
        }

        Optional<ItemCarrinho> produtoExistente = repository.buscarPorId(itemCarrinho.getId());
        if(produtoExistente.isEmpty()){
            throw new IllegalArgumentException("produtoPedido não encontrado");
        }

        validarProdutoPedido(itemCarrinho);
        repository.editar(itemCarrinho);
    }

    @Override
    public void deletar(ItemCarrinho itemCarrinho) {
        if (itemCarrinho.getId() == null){
            throw new IllegalArgumentException("ID do produtoPedido não pode ser nulo");
        }

        Optional<ItemCarrinho> produtoExistente = repository.buscarPorId(itemCarrinho.getId());
        if(produtoExistente.isEmpty()){
            throw new IllegalArgumentException("produtoPedido não encontrado");
        }

        validarProdutoPedido(itemCarrinho);
        repository.deletar(itemCarrinho);
    }

    @Override
    public Optional<ItemCarrinho> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    @Override
    public List<ItemCarrinho> buscarTodos() {
        return repository.buscarTodos();
    }

    private void validarProdutoPedido(ItemCarrinho itemCarrinho) {
        if (itemCarrinho.getProduto() == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if (itemCarrinho.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if (itemCarrinho.getPrecoUnidade() <= 0) {
            throw new IllegalArgumentException("Preço unitário deve ser maior que zero.");
        }

        if (itemCarrinho.getCarrinho() == null) {
            throw new IllegalArgumentException("O ProdutoPedido deve estar associado a um Pedido.");
        }
    }
}
