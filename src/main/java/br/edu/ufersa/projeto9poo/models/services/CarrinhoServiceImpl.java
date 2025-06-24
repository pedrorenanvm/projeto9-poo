package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;
import br.edu.ufersa.projeto9poo.models.repositories.CarrinhoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.CarrinhoRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CarrinhoServiceImpl implements CarrinhoService {
    private final CarrinhoRepository repo = new CarrinhoRepositoryImpl();

    @Override
    public void cadastrar(Carrinho carrinho) {
        repo.cadastrar(carrinho);
    }

    @Override
    public void editar(Carrinho carrinho) {
        if (carrinho == null) {
            throw new IllegalArgumentException("O carrinho não pode ser nulo");
        }

        Optional<Carrinho> carrinhoEdita = repo.buscar(carrinho.getId());
        if (carrinhoEdita.isEmpty()) {
            throw new IllegalArgumentException("O id do carrinho não existe");
        }

        carrinhoEdita.get().setData(carrinho.getData());
        carrinhoEdita.get().setCliente(carrinho.getCliente());
        carrinhoEdita.get().setItensCarrinho(carrinho.getItensCarrinho());
        carrinhoEdita.get().setPagamento(carrinho.getPagamento());
        carrinhoEdita.get().setEstado(carrinho.getEstado());

        repo.editar(carrinhoEdita.get());
    }

    @Override
    public void deletar(Carrinho carrinho) {
        Optional<Carrinho> carrinhoEncontrado = repo.buscar(carrinho.getId());
        if (carrinhoEncontrado.isEmpty()) {
            throw new IllegalArgumentException("O id não existe");
        }
        repo.deletar(carrinhoEncontrado.get());
    }

    @Override
    public Optional<Carrinho> buscar(Carrinho carrinho) {
        return repo.buscar(carrinho.getId());
    }

    @Override
    public List<Carrinho> buscarTodos() {
        return repo.buscarTodos();
    }
}
