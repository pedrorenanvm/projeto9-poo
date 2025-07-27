package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;

import java.util.List;

public interface FuncionarioService {
    Funcionario logar(Funcionario funcionario);

    void trocarSenha(Funcionario funcionario);

    void resetarSenha(Funcionario funcionario);

    void cadastrar(Funcionario funcionario);

    void deletar(Funcionario funcionario);

    List<Funcionario> buscarTodos(String likeUsuario);
}
