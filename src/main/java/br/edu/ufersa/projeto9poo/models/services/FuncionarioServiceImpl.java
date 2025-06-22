package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.Funcionario;
import br.edu.ufersa.projeto9poo.models.repositories.FuncionarioRepository;
import br.edu.ufersa.projeto9poo.models.repositories.FuncionarioRepositoryImpl;

import java.util.List;

public class FuncionarioServiceImpl implements FuncionarioService {
    private final String SENHA_PADRAO = "senha123";

    private final FuncionarioRepository repo = new FuncionarioRepositoryImpl();

    @Override
    public Funcionario logar(String usuario, String senha) {
        Funcionario funcionario = repo.buscar(usuario);
        if (funcionario == null) {
            return null;
        }

        // Seria melhor se fosse um hash
        if (funcionario.getSenha().equals(senha)) {
            return funcionario;
        } else {
            return null;
        }
    }

    @Override
    public void trocarSenha(int id, String novaSenha) {
        Funcionario funcionario = repo.buscar(id);
        if (funcionario == null) {
            throw new IllegalArgumentException("O id não existe");
        }
        funcionario.setSenha(novaSenha);
        repo.editar(funcionario);
    }

    @Override
    public void resetarSenha(int id) {
        Funcionario funcionario = repo.buscar(id);
        if (funcionario == null) {
            throw new IllegalArgumentException("O id não existe");
        }
        funcionario.setSenha(SENHA_PADRAO);
        repo.editar(funcionario);
    }

    @Override
    public void cadastrar(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("O funcionario não pode ser nulo");
        }

        if (repo.buscar(funcionario.getUsuario()) != null) {
            throw new IllegalArgumentException("O usuario já está em uso");
        }
        repo.cadastrar(funcionario);
    }

    @Override
    public void deletar(int id) {
        Funcionario funcionario = repo.buscar(id);
        if (funcionario == null) {
            throw new IllegalArgumentException("O id não existe");
        }
        repo.deletar(funcionario);
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return repo.buscarTodos();
    }
}
