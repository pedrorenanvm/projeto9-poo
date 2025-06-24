package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Cliente;
import br.edu.ufersa.projeto9poo.models.repositories.ClienteRepository;
import br.edu.ufersa.projeto9poo.models.repositories.ClienteRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository repository = new ClienteRepositoryImpl();
    @Override
    public void cadastrar(Cliente cliente) {
        Optional<Cliente> clienteExistente = repository.buscarTodos()
                .stream()
                .filter(c -> c.getTelefone().equals(cliente.getTelefone()))
                .findFirst();

        if(clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com esse telefone, tente outro!");
        }

        repository.cadastrar(cliente);
    }

    @Override
    public void editar(Cliente cliente) {
        Optional<Cliente> clienteExistente = repository.buscarTodos()
                .stream()
                .filter(c -> c.getTelefone().equals(cliente.getTelefone()))
                .findFirst();

        if(clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com esse telefone, tente outro!");
        }

        repository.editar(cliente);
    }

    @Override
    public void deletar(Cliente cliente) {
        repository.deletar(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repository.buscarTodos();
    }
}
