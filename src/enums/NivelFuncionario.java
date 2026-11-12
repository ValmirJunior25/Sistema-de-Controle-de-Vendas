package enums;

public enum NivelFuncionario {
    FUNCIONARIO("Funcionário"),
    GERENTE("Gerente"),
    ADMINISTRADOR("Administrador");

    private final String descricao;

    NivelFuncionario(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
