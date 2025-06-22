package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.Pedido;

import java.util.List;

public interface PedidoService {
    public void cadastrar(Pedido pedido);

    public void editar(Pedido pedido);

    public void deletar(int id);

    public Pedido buscar(int id);

    public List<Pedido> buscarTodos();
}
