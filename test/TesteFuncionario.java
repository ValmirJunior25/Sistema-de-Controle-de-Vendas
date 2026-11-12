import enums.NivelFuncionario;
import model.Funcionario;

public class TesteFuncionario {

    static void confere(String campo, String esperado, String obtido) {
        if (esperado.equals(obtido)) {
            System.out.println("OK   | " + campo);
        } else {
            System.out.println("FALHA| " + campo + " → esperado: [" + esperado + "] obtido: [" + obtido + "]");
        }
    }

    public static void main(String[] args) {

        // ================================================================
        // 1. CONSTRUTOR - CASOS VÁLIDOS
        // ================================================================
        System.out.println("=== Construtor: Casos válidos ===");

        Funcionario f1 = new Funcionario("Lucas Silva", "lucas@empresa.com", NivelFuncionario.FUNCIONARIO);
        confere("nome f1", "Lucas Silva", f1.getNome());
        confere("email f1", "lucas@empresa.com", f1.getEmail());
        confere("nivel f1", NivelFuncionario.FUNCIONARIO.name(), f1.getNivel().name());
        confere("id nao nulo", "sim", f1.getId() != null ? "sim" : "nao");

        Funcionario f2 = new Funcionario("  Mariana Lima  ", "  MARIANA@EMPRESA.COM  ", NivelFuncionario.GERENTE);
        confere("strip nome", "Mariana Lima", f2.getNome());
        confere("email lowercase e strip", "mariana@empresa.com", f2.getEmail());
        confere("nivel f2", "Gerente", f2.getNivel().getDescricao());

        // ================================================================
        // 2. CONSTRUTOR - CASOS INVÁLIDOS (EXCEÇÕES ESPERADAS)
        // ================================================================
        System.out.println("\n=== Construtor: Nome inválido ===");

        try {
            new Funcionario(null, "f@empresa.com", NivelFuncionario.FUNCIONARIO);
            confere("nome nulo", "EXCEÇÃO", "SEM EXCEÇÃO");
        } catch (IllegalArgumentException e) {
            confere("nome nulo", "EXCEÇÃO", "EXCEÇÃO");
        }

        try {
            new Funcionario("   ", "f@empresa.com", NivelFuncionario.FUNCIONARIO);
            confere("nome em branco", "EXCEÇÃO", "SEM EXCEÇÃO");
        } catch (IllegalArgumentException e) {
            confere("nome em branco", "EXCEÇÃO", "EXCEÇÃO");
        }

        System.out.println("\n=== Construtor: E-mail inválido ===");

        try {
            new Funcionario("Lucas", null, NivelFuncionario.FUNCIONARIO);
            confere("email nulo", "EXCEÇÃO", "SEM EXCEÇÃO");
        } catch (IllegalArgumentException e) {
            confere("email nulo", "EXCEÇÃO", "EXCEÇÃO");
        }

        try {
            new Funcionario("Lucas", "sem-arroba.com", NivelFuncionario.FUNCIONARIO);
            confere("email sem @", "EXCEÇÃO", "SEM EXCEÇÃO");
        } catch (IllegalArgumentException e) {
            confere("email sem @", "EXCEÇÃO", "EXCEÇÃO");
        }

        System.out.println("\n=== Construtor: Nível inválido ===");

        try {
            new Funcionario("Lucas", "lucas@empresa.com", null);
            confere("nivel nulo", "EXCEÇÃO", "SEM EXCEÇÃO");
        } catch (IllegalArgumentException e) {
            confere("nivel nulo", "EXCEÇÃO", "EXCEÇÃO");
        }

        // ================================================================
        // 3. SETTERS VÁLIDOS
        // ================================================================
        System.out.println("\n=== Setters válidos ===");

        Funcionario func = new Funcionario("Roberto", "roberto@empresa.com", NivelFuncionario.FUNCIONARIO);

        func.setNome("  Roberto Carlos  ");
        confere("setNome com strip", "Roberto Carlos", func.getNome());

        func.setEmail("ROBERTO.CARLOS@NOVO.COM");
        confere("setEmail em lowercase", "roberto.carlos@novo.com", func.getEmail());

        func.setNivel(NivelFuncionario.ADMINISTRADOR);
        confere("setNivel", NivelFuncionario.ADMINISTRADOR.name(), func.getNivel().name());

        // ================================================================
        // 4. SETTERS INVÁLIDOS (PROTEÇÃO DE ESTADO)
        // ================================================================
        System.out.println("\n=== Setters inválidos ===");

        try {
            func.setNome("");
            confere("setNome vazio (SEM EXCEÇÃO)", "Roberto Carlos", func.getNome());
        } catch (IllegalArgumentException e) {
            confere("setNome vazio (EXCEÇÃO)", "Roberto Carlos", func.getNome());
        }

        try {
            func.setEmail("email_invalido");
            confere("setEmail invalido (SEM EXCEÇÃO)", "roberto.carlos@novo.com", func.getEmail());
        } catch (IllegalArgumentException e) {
            confere("setEmail invalido (EXCEÇÃO)", "roberto.carlos@novo.com", func.getEmail());
        }

        try {
            func.setNivel(null);
            confere("setNivel nulo (SEM EXCEÇÃO)", NivelFuncionario.ADMINISTRADOR.name(), func.getNivel().name());
        } catch (IllegalArgumentException e) {
            confere("setNivel nulo (EXCEÇÃO)", NivelFuncionario.ADMINISTRADOR.name(), func.getNivel().name());
        }

        // ================================================================
        // 5. TESTES DE EQUALS E HASHCODE
        // ================================================================
        System.out.println("\n=== Testes de equals e hashCode ===");

        Funcionario mesmoFunc = func;
        confere("equals mesmo objeto", "sim", func.equals(mesmoFunc) ? "sim" : "nao");
        confere("hashCode mesmo objeto", "sim", func.hashCode() == mesmoFunc.hashCode() ? "sim" : "nao");

        Funcionario outroFunc = new Funcionario("Roberto", "roberto.carlos@novo.com", NivelFuncionario.ADMINISTRADOR);
        confere("equals funcionarios com IDs diferentes", "nao", func.equals(outroFunc) ? "sim" : "nao");

        confere("equals com null", "nao", func.equals(null) ? "sim" : "nao");
        confere("equals com objeto de outro tipo", "nao", func.equals("texto") ? "sim" : "nao");
    }
}
