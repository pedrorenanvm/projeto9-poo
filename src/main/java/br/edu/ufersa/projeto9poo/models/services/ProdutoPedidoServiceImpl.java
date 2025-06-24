package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.ProdutoPedido;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoPedidoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoPedidoRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ProdutoPedidoServiceImpl implements ProdutoPedidoService {

    private final ProdutoPedidoRepository repository = new ProdutoPedidoRepositoryImpl();

    @Override
    public void cadastrar(ProdutoPedido produtoPedido) {
        validarProdutoPedido(produtoPedido);
        repository.cadastrar(produtoPedido);
    }

    @Override
    public void editar(ProdutoPedido produtoPedido) {
        if (produtoPedido.getId() == null){
            throw new IllegalArgumentException("ID do produtoPedido não pode ser nulo");
        }

        Optional<ProdutoPedido> produtoExistente = repository.buscarPorId(produtoPedido.getId());
        if(produtoExistente.isEmpty()){
            throw new IllegalArgumentException("produtoPedido não encontrado");
        }

        validarProdutoPedido(produtoPedido);
        repository.editar(produtoPedido);
    }

    @Override
    public void deletar(ProdutoPedido produtoPedido) {
        if (produtoPedido.getId() == null){
            throw new IllegalArgumentException("ID do produtoPedido não pode ser nulo");
        }

        Optional<ProdutoPedido> produtoExistente = repository.buscarPorId(produtoPedido.getId());
        if(produtoExistente.isEmpty()){
            throw new IllegalArgumentException("produtoPedido não encontrado");
        }

        validarProdutoPedido(produtoPedido);
        repository.deletar(produtoPedido);
    }

    @Override
    public Optional<ProdutoPedido> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    @Override
    public List<ProdutoPedido> buscarTodos() {
        return repository.buscarTodos();
    }

    private void validarProdutoPedido(ProdutoPedido produtoPedido) {
        if (produtoPedido.getProduto() == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if (produtoPedido.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if (produtoPedido.getPrecoUnidade() <= 0) {
            throw new IllegalArgumentException("Preço unitário deve ser maior que zero.");
        }

        if (produtoPedido.getPedido() == null) {
            throw new IllegalArgumentException("O ProdutoPedido deve estar associado a um Pedido.");
        }
    }
}
