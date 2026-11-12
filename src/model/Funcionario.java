package model;
import enums.NivelFuncionario;

import java.util.Objects;
import java.util.UUID;

public class Funcionario {
    private final UUID id;
    private String nome;
    private String email;
    private NivelFuncionario nivel;

    public Funcionario(String nome, String email, NivelFuncionario nivel){
        validaString(nome, "Nome");
        validaEmail(email);
        validaNivel(nivel);

        id = UUID.randomUUID();
        this.nome = nome.strip();
        this.email = email.strip().toLowerCase();
        this.nivel = nivel;
    }


    public UUID getId(){return id;}
    public String getNome(){return nome;}
    public String getEmail(){return email;}
    public NivelFuncionario getNivel(){return nivel;}


    public void setNome(String nome){
        validaString(nome, "Nome");
        this.nome = nome.strip();
    }

    public void setEmail(String email) {
        validaEmail(email);
        this.email = email.strip().toLowerCase();
    }

    public void setNivel(NivelFuncionario nivel) {
        validaNivel(nivel);
        this.nivel = nivel;
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

    private void validaNivel(NivelFuncionario nivel){
        if (nivel == null){
            throw new IllegalArgumentException("O nível não pode ser nulo.");
        }
    }


    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Funcionario outro = (Funcionario) obj;
        return Objects.equals(this.id, outro.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.id);
    }

}
