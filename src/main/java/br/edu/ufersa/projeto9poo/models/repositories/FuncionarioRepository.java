package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository {
    public void cadastrar(Funcionario funcionario);

    public void editar(Funcionario funcionario);

    public void deletar(Funcionario funcionario);

    public Optional<Funcionario> buscar(int id);

    public Optional<Funcionario> buscar(String usuario);

    public List<Funcionario> buscarTodos();
}
