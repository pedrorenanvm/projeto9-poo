package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Pedido;
import br.edu.ufersa.projeto9poo.models.repositories.PedidoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.PedidoRepositoryImpl;

import java.util.List;
import java.util.Optional;

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

        Optional<Pedido> pedidoEdita = repo.buscar(pedido.getId());
        if (pedidoEdita.isEmpty()) {
            throw new IllegalArgumentException("O id do pedido não existe");
        }

        pedidoEdita.get().setData(pedido.getData());
        pedidoEdita.get().setCliente(pedido.getCliente());
        pedidoEdita.get().setItens(pedido.getItens());
        pedidoEdita.get().setPagamento(pedido.getPagamento());
        pedidoEdita.get().setEstado(pedido.getEstado());

        repo.editar(pedidoEdita.get());
    }

    @Override
    public void deletar(Pedido pedido) {
        Optional<Pedido> pedidoEncontrado = repo.buscar(pedido.getId());
        if (pedidoEncontrado.isEmpty()) {
            throw new IllegalArgumentException("O id não existe");
        }
        repo.deletar(pedidoEncontrado.get());
    }

    @Override
    public Optional<Pedido> buscar(Pedido pedido) {
        return repo.buscar(pedido.getId());
    }

    @Override
    public List<Pedido> buscarTodos() {
        return repo.buscarTodos();
    }
}
