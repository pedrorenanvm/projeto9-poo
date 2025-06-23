package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cargo", discriminatorType = DiscriminatorType.STRING, length = 25)
@DiscriminatorValue("funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(nullable = false, length = 50)
    private String senha;

    public Funcionario() {
    }

    public Funcionario(int id, String usuario, String senha, boolean admin) {
        setId(id);
        setUsuario(usuario);
        setSenha(senha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            return;
        }
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if (usuario == null || usuario.isEmpty()) {
            return;
        }
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            return;
        }
        this.senha = senha;

    }
}
