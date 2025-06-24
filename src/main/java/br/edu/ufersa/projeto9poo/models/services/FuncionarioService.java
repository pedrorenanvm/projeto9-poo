package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;

import java.util.List;

public interface FuncionarioService {
    public Funcionario logar(Funcionario funcionario);

    public void trocarSenha(Funcionario funcionario);

    public void resetarSenha(Funcionario funcionario);

    public void cadastrar(Funcionario funcionario);

    public void deletar(Funcionario funcionario);

    public List<Funcionario> buscarTodos();
}
