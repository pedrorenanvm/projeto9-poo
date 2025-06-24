package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.ProdutoPedido;

import java.util.List;
import java.util.Optional;

public interface ProdutoPedidoService {
    void cadastrar(ProdutoPedido produtoPedido);

    void editar(ProdutoPedido produtoPedido);

    void deletar(ProdutoPedido produtoPedido);

    Optional<ProdutoPedido> buscarPorId(Long id);

    List<ProdutoPedido> buscarTodos();
}