package br.edu.ufersa.projeto9poo.util;

import br.edu.ufersa.projeto9poo.models.entities.Administrador;
import br.edu.ufersa.projeto9poo.models.entities.Carrinho;
import br.edu.ufersa.projeto9poo.models.entities.Funcionario;

import java.util.Optional;

public class Estado extends SingletonAbstract {
    private Funcionario funcionarioLogado;

    private Carrinho carrinhoNota;

    private Estado() {
        this.funcionarioLogado = null;
        this.carrinhoNota = null;
    }

    public static Estado pegarInstancia() {
        return pegarInstancia(Estado.class, Estado::new);
    }

    public Optional<Funcionario> getFuncionarioLogado() {
        return Optional.ofNullable(funcionarioLogado);
    }

    public boolean isAdmin() {
        return funcionarioLogado != null && funcionarioLogado instanceof Administrador;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public Optional<Carrinho> getCarrinhoNota() {
        return Optional.ofNullable(carrinhoNota);
    }

    public void setCarrinhoNota(Carrinho carrinhoNota) {
        this.carrinhoNota = carrinhoNota;
    }
}
