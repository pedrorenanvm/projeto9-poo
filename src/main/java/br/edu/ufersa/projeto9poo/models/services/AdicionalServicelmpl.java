package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;
import br.edu.ufersa.projeto9poo.models.repositories.AdicionalRepository;
import br.edu.ufersa.projeto9poo.models.repositories.AdicionalRepositorylmpl;

import java.util.List;
import java.util.Optional;

public class AdicionalServicelmpl implements AdicionalServicer {

    private final AdicionalRepository repository = new AdicionalRepositorylmpl();

    public void cadastrar(Adicional adicional) {
        if (repository.buscarPorId(adicional.id).isEmpty()) {
            repository.cadastrar(adicional);
        }
        throw new IllegalArgumentException("Produto já existente.");
    }

    public void editar(Adicional adicional) {
        if (repository.buscarPorId(adicional.id).isEmpty()) {
            throw new IllegalArgumentException("Produto inexistente");
        }
        Optional<Adicional> a = repository.buscarPorId(adicional.id);
        if (a.isPresent()) {
            Adicional a2 = a.get();
            a2.setNome(adicional.getNome());
            a2.setId(adicional.getId());
            a2.setPreco(adicional.getPreco());
            a2.setEstoque(adicional.getEstoque());
            repository.editar(a2);
        }
    }

    public void deletar(Adicional adicional) {
        Optional<Adicional> a = repository.buscarPorId(adicional.id);
        if (a.isEmpty()) {
            throw new IllegalArgumentException("O id não existe");
        }
        Adicional a2 = a.get();
        repository.deletar(a2);
    }

    @Override
    public Optional<Adicional> buscar(Adicional adicional) {
        Optional<Adicional> a = repository.buscarPorId(adicional.id);
        if (a.isEmpty()) {
            throw new IllegalArgumentException("O id não existe");
        }
        Adicional a2 = a.get();
        return repository.buscarPorId(a2.id);
    }
        public List<Adicional> buscarTodos () {
            return repository.buscarTodos();
        }
    }
