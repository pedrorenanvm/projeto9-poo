package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.Pedido;

import java.util.List;

public interface PedidoRepository {
    public void cadastrar(Pedido pedido);

    public void editar(Pedido pedido);

    public void deletar(Pedido pedido);

    public Pedido buscar(int id);

    public List<Pedido> buscarTodos();
}
