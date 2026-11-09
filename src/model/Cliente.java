package model;

import java.util.UUID;

public class Cliente {
    private final UUID id;
    private String nome;
    private String email;
    private String telefone;

    public Cliente(String nome, String email, String telefone){
        validaString(nome, "O nome");
        validaEmail(email);
        validaTelefone(telefone);

        this.id = UUID.randomUUID();
        this.nome = nome.strip();
        this.email = email.strip();
        this.telefone = telefone.strip();
    }

    public UUID getId(){ return id; }
    public String getNome(){ return nome; }
    public String getEmail(){ return email; }
    public String getTelefone(){ return telefone; }

    public void setNome(String nome){
        validaString(nome, "O nome");
        this.nome = nome.strip();
    }

    public void setEmail(String email){
        validaEmail(email);
        this.email = email.strip();
    }

    public void setTelefone(String telefone){
        validaTelefone(telefone);
        this.telefone = telefone.strip();
    }

    private void validaString(String valor, String nomeValor){
        if (valor == null){
            throw new IllegalArgumentException(nomeValor + " não pode ser nulo.");
        }
        if (valor.isBlank()){
            throw new IllegalArgumentException(nomeValor + " não pode estar vazio.");
        }
    }

    private void validaEmail(String email){
        validaString(email, "O email");
        if (!(email.strip().matches("[A-Za-z0-9]+([._-][A-Za-z0-9]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+"))){
            throw new IllegalArgumentException("O email é invalido.");
        }
    }

    private void validaTelefone(String telefone){
        validaString(telefone, "O numero para contato");
        if (!(telefone.strip().matches("[0-9]{11}"))){
            throw new IllegalArgumentException("O numero para contato deve ter 11 dígitos.");
        }
    }
}
