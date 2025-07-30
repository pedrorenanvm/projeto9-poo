package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    void cadastrar(Cliente cliente);

    void editar(Cliente cliente);

    void deletar(Cliente cliente);

    Optional<Cliente> buscarPorId(Long id);

    List<Cliente> buscarPorNome(String nome);

    List<Cliente> buscarTodos();
}
