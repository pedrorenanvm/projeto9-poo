package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    public void cadastrar(Pedido pedido);

    public void editar(Pedido pedido);

    public void deletar(Pedido pedido);

    public Optional<Pedido> buscar(int id);

    public List<Pedido> buscarTodos();
}
