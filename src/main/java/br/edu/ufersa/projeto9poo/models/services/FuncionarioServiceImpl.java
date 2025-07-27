package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Funcionario;
import br.edu.ufersa.projeto9poo.models.repositories.FuncionarioRepository;
import br.edu.ufersa.projeto9poo.models.repositories.FuncionarioRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class FuncionarioServiceImpl implements FuncionarioService {
    private final String SENHA_PADRAO = "senha123";

    private final FuncionarioRepository repo = new FuncionarioRepositoryImpl();

    @Override
    public Funcionario logar(Funcionario funcionario) {
        Optional<Funcionario> funcionarioEncontrado = repo.buscar(funcionario.getUsuario());
        if (funcionarioEncontrado.isEmpty()) {
            throw new IllegalArgumentException("Usuario ou senha incorretos");
        }

        // Seria melhor se fosse um hash
        if (funcionarioEncontrado.get().getSenha().equals(funcionario.getSenha())) {
            return funcionarioEncontrado.get();
        } else {
            throw new IllegalArgumentException("Usuario ou senha incorretos");
        }
    }

    @Override
    public void trocarSenha(Funcionario funcionario) {
        Optional<Funcionario> funcionarioEncontrado = repo.buscar(funcionario.getId());
        if (funcionarioEncontrado.isEmpty()) {
            throw new IllegalArgumentException("O id não existe");
        }
        funcionarioEncontrado.get().setSenha(funcionario.getSenha());
        repo.editar(funcionarioEncontrado.get());
    }

    @Override
    public void resetarSenha(Funcionario funcionario) {
        Optional<Funcionario> funcionarioEncontrado = repo.buscar(funcionario.getId());
        if (funcionarioEncontrado.isEmpty()) {
            throw new IllegalArgumentException("O id não existe");
        }
        funcionarioEncontrado.get().setSenha(SENHA_PADRAO);
        repo.editar(funcionarioEncontrado.get());
    }

    @Override
    public void cadastrar(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("O funcionario não pode ser nulo");
        }

        if (repo.buscar(funcionario.getUsuario()).isPresent()) {
            throw new IllegalArgumentException("O usuario já está em uso");
        }
        funcionario.setSenha(SENHA_PADRAO);
        repo.cadastrar(funcionario);
    }

    @Override
    public void deletar(Funcionario funcionario) {
        Optional<Funcionario> funcionarioEncontrado = repo.buscar(funcionario.getId());
        if (funcionarioEncontrado.isEmpty()) {
            throw new IllegalArgumentException("O id não existe");
        }
        repo.deletar(funcionarioEncontrado.get());
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return repo.buscarTodos();
    }
}
