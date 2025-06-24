package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.ProdutoPedido;

import java.util.List;
import java.util.Optional;

public interface ProdutoPedidoRepository {

    void cadastrar(ProdutoPedido produto);

    void editar(ProdutoPedido produto);

    void deletar(ProdutoPedido produto);

    Optional<ProdutoPedido> buscarPorId(Long id);

    List<ProdutoPedido> buscarTodos();

}
