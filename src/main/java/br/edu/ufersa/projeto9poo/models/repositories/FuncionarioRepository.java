package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository {
    void cadastrar(Funcionario funcionario);

    void editar(Funcionario funcionario);

    void deletar(Funcionario funcionario);

    Optional<Funcionario> buscar(int id);

    Optional<Funcionario> buscar(String usuario);

    List<Funcionario> buscarTodos();
}
