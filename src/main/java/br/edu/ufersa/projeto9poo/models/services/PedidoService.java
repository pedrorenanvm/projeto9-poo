package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    public void cadastrar(Pedido pedido);

    public void editar(Pedido pedido);

    public void deletar(Pedido pedido);

    public Optional<Pedido> buscar(Pedido pedido);

    public List<Pedido> buscarTodos();
}
