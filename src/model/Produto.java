package model;

import java.math.BigDecimal;
import java.util.UUID;

public class Produto {
    private final UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidadeEstoque;
    private boolean ativo;

    public Produto(String nome, String descricao, BigDecimal preco, int quantidadeEstoque, boolean ativo) {
        validaString(nome, "Nome");
        validaString(descricao, "Descrição");
        validarPreco(preco);
        validarQuantidadeEstoque(quantidadeEstoque);

        this.id = UUID.randomUUID();
        this.nome = nome.strip();
        this.descricao = descricao.strip();
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.ativo = ativo;
    }

    public Produto(String nome, String descricao, BigDecimal preco, int quantidadeEstoque) {
        this(nome, descricao, preco, quantidadeEstoque, true);
    }


    public UUID getId(){ return id; }
    public String getNome(){ return nome; }
    public String getDescricao(){ return descricao; }
    public BigDecimal getPreco(){ return preco; }
    public int getQuantidadeEstoque(){ return quantidadeEstoque; }
    public boolean isAtivo(){ return ativo; }


    public void setNome(String nome){
        validaString(nome, "Nome");
        this.nome = nome.strip();
    }

    public void setDescricao(String descricao){
        validaString(descricao, "Descrição");
        this.descricao = descricao.strip();
    }

    public void setPreco(BigDecimal preco){
        validarPreco(preco);
        this.preco = preco;
    }

    public void setQuantidadeEstoque(int quantidade){
        validarQuantidadeEstoque(quantidade);
        this.quantidadeEstoque = quantidade;
    }

    public void ativar(){
        ativo = true;
    }

    public void desativar(){
        ativo = false;
    }


    private void validaString(String valor, String nomeAtributo){
        if (valor == null){
            throw new IllegalArgumentException(nomeAtributo + " não pode ser nulo.");
        }
        if (valor.isBlank()){
            throw new IllegalArgumentException(nomeAtributo + " não pode estar vazio.");
        }
    }

    private void validarPreco(BigDecimal preco) {
        if (preco == null){
            throw new IllegalArgumentException("O preço não pode ser nulo.");
        }
        if (preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
    }

    private void validarQuantidadeEstoque(int quantidade){
        if (quantidade < 0){
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativo.");
        }
    }
}
