package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Carrinho;
import br.edu.ufersa.projeto9poo.models.entities.TipoEstado;
import br.edu.ufersa.projeto9poo.models.repositories.CarrinhoRepository;
import br.edu.ufersa.projeto9poo.models.repositories.CarrinhoRepositoryImpl;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Optional;

public class CarrinhoServiceImpl implements CarrinhoService {
    private final CarrinhoRepository repo = new CarrinhoRepositoryImpl();

    @Override
    @Transactional
    public void cadastrar(Carrinho carrinho) {
        repo.cadastrar(carrinho);
    }

    @Override
    @Transactional
    public void editar(Carrinho carrinho) {
        if (carrinho == null) {
            throw new IllegalArgumentException("O carrinho não pode ser nulo");
        }

        Optional<Carrinho> carrinhoExistente = repo.buscar(carrinho.getId());
        if (carrinhoExistente.isEmpty()) {
            throw new IllegalArgumentException("O carrinho com ID " + carrinho.getId() + " não existe");
        }

        repo.editar(carrinho);
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
    public Optional<Carrinho> buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        return repo.buscar(id);
    }

    @Override
    public List<Carrinho> buscarTodos() {
        return repo.buscarTodos();
    }

    @Override
    public List<Carrinho> buscarTodosHoje() {
        LocalDate hoje = LocalDate.now();
        return buscarTodos().stream()
                .filter(c -> c.getData().equals(hoje))
                .toList();
    }

    @Override
    public List<Carrinho> buscarTodosSemana() {
        LocalDate hoje = LocalDate.now();
        return buscarTodos().stream()
                .filter(c -> c.getData().getYear() == hoje.getYear() && c.getData().get(WeekFields.ISO.weekOfYear()) == hoje.get(WeekFields.ISO.weekOfYear()))
                .toList();

    }

    @Override
    public List<Carrinho> buscarTodosMes() {
        LocalDate hoje = LocalDate.now();
        return buscarTodos().stream()
                .filter(c -> c.getData().getYear() == hoje.getYear() && c.getData().getMonth() == hoje.getMonth())
                .toList();
    }

    @Override
    public long quantidadeAberto() {
        return buscarTodos().stream().filter(p -> p.getEstado().equals(TipoEstado.EM_ANDAMENTO)).count();
    }

    @Override
    public long somaPrecoHoje() {
        return buscarTodosHoje().stream().map(Carrinho::precoTotal).reduce(0L, Long::sum);
    }
}
