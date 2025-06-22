package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Funcionario {
    public Administrador() {
    }

    public Administrador(int id, String usuario, String senha, boolean admin) {
        super(id, usuario, senha, admin);
    }
}
