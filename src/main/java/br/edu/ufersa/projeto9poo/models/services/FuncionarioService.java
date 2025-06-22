package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.Funcionario;

import java.util.List;

public interface FuncionarioService {
    public Funcionario logar(String usuario, String senha);

    public void trocarSenha(int id, String novaSenha);

    public void resetarSenha(int id);

    public void cadastrar(Funcionario funcionario);

    public void deletar(int id);

    public List<Funcionario> buscarTodos();
}
