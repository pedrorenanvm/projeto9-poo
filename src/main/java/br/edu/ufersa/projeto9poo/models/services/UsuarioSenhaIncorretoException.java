package br.edu.ufersa.projeto9poo.models.services;

public class UsuarioSenhaIncorretoException extends Exception {
    public UsuarioSenhaIncorretoException() {
        super("Usuario ou senha incorreto");
    }
}
