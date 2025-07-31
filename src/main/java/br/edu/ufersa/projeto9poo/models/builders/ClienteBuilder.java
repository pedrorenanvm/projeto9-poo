package br.edu.ufersa.projeto9poo.models.builders;

import br.edu.ufersa.projeto9poo.models.entities.Cliente;

public final class ClienteBuilder {
    private String nome;
    private String endereco;
    private String telefone;

    public static ClienteBuilder builder() {
        return new ClienteBuilder();
    }

    public ClienteBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public ClienteBuilder telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }


    public static ClienteBuilder from(Cliente c) {
        return builder()
                .nome(c.getNome())
                .endereco(c.getEndereco())
                .telefone(c.getTelefone());
    }

    public Cliente build() {
        String n = safe(nome);
        String e = safe(endereco);
        String t = safe(telefone);

        if (n.isEmpty() || e.isEmpty() || t.isEmpty())
            throw new IllegalArgumentException("Nome, endereço e telefone são obrigatórios.");

        if (!t.matches("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$"))
            throw new IllegalArgumentException("Telefone inválido! Use (99) 99999-9999 ou (99) 9999-9999.");

        n = capitalize(n);
        e = e.trim();
        t = t.trim();

        return new Cliente(n, e, t);
    }

    private static String safe(String s) { return s == null ? "" : s.trim(); }

    private static String capitalize(String s) {
        String[] parts = s.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (p.isEmpty()) continue;
            sb.append(Character.toUpperCase(p.charAt(0)))
                    .append(p.substring(1))
                    .append(' ');
        }
        return sb.toString().trim();
    }
}