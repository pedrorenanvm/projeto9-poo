package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.Pedido;
import br.edu.ufersa.projeto9poo.models.repositories.PedidoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.PedidoRepositoryImpl;

import java.util.List;

public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository repo = new PedidoRepositoryImpl();

    @Override
    public void cadastrar(Pedido pedido) {
        repo.cadastrar(pedido);
    }

    @Override
    public void editar(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo");
        }

        Pedido pedidoEdita = repo.buscar(pedido.getId());
        if (pedidoEdita == null) {
            throw new IllegalArgumentException("O id do pedido não existe");
        }

        pedidoEdita.setData(pedido.getData());
        pedidoEdita.setCliente(pedido.getCliente());
        pedidoEdita.setItens(pedido.getItens());
        pedidoEdita.setPagamento(pedido.getPagamento());
        pedidoEdita.setEstado(pedido.getEstado());

        repo.editar(pedidoEdita);
    }

    @Override
    public void deletar(int id) {
        Pedido pedido = repo.buscar(id);
        if (pedido == null) {
            throw new IllegalArgumentException("O id não existe");
        }
        repo.deletar(pedido);
    }

    @Override
    public Pedido buscar(int id) {
        return repo.buscar(id);
    }

    @Override
    public List<Pedido> buscarTodos() {
        return repo.buscarTodos();
    }
}
