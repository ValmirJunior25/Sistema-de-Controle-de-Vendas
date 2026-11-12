import enums.NivelFuncionario;

public class Main {
    public static void main(String[] args) {

        NivelFuncionario nivel = NivelFuncionario.GERENTE;

        System.out.println(nivel.name());
        System.out.println(nivel.getDescricao());
        for (NivelFuncionario n : NivelFuncionario.values()){
            System.out.println(n.getDescricao());
        }
        System.out.println(NivelFuncionario.valueOf("ADMINISTRADOR"));


        try {
            System.out.println(NivelFuncionario.valueOf("gerente"));
        }
        catch (IllegalArgumentException e){
            System.out.println("Deu erro na execulção do .valueOf(\"gerente\")");
        }

    }
}
