package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    public void cadastrar(Funcionario funcionario);

    public void editar(Funcionario funcionario);

    public void deletar(Funcionario funcionario);

    public Funcionario buscar(int id);

    public Funcionario buscar(String usuario);

    public List<Funcionario> buscarTodos();
}
