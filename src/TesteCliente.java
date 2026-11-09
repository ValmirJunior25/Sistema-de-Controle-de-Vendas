import model.Cliente;

public class TesteCliente {

    // Compara o valor esperado com o obtido e imprime OK ou FALHA
    static void confere(String campo, String esperado, String obtido) {
        if (esperado.equals(obtido)) {
            System.out.println("OK   | " + campo);
        } else {
            System.out.println("FALHA| " + campo + " → esperado: [" + esperado + "] obtido: [" + obtido + "]");
        }
    }

    public static void main(String[] args) {

        // ================================================================
        // TESTES DO CONSTRUTOR (concluídos)
        // ================================================================

        System.out.println("=== Casos válidos ===");

        Cliente c1 = new Cliente("Ana Silva", "ana@email.com", "88912345678");
        confere("nome c1",     "Ana Silva",        c1.getNome());
        confere("email c1",    "ana@email.com",    c1.getEmail());
        confere("telefone c1", "88912345678",      c1.getTelefone());

        Cliente c2 = new Cliente("João", "joao@empresa.com.br", "11987654321");
        confere("nome c2", "João", c2.getNome());

        Cliente c3 = new Cliente("Maria Santos", "maria.santos@dominio.org", "21912345678");
        confere("nome c3", "Maria Santos", c3.getNome());

        Cliente c4 = new Cliente("Pedro", "pedro-oli@sub.dominio.com", "31987654321");
        confere("nome c4", "Pedro", c4.getNome());

        System.out.println("\n=== strip() guarda valores limpos ===");

        Cliente c5 = new Cliente("  Carlos  ", "  carlos@email.com  ", "  88912345679  ");
        confere("strip nome",     "Carlos",          c5.getNome());
        confere("strip email",    "carlos@email.com", c5.getEmail());
        confere("strip telefone", "88912345679",     c5.getTelefone());

        // ----------------------------------------------------------------
        System.out.println("\n=== Nome inválido ===");

        try {
            new Cliente(null, "a@b.com", "88912345678");
            confere("nome nulo", "EXCEÇÃO", "SEM EXCEÇÃO");
        } catch (IllegalArgumentException e) {
            confere("nome nulo", "EXCEÇÃO", "EXCEÇÃO");
        }

        try {
            new Cliente("", "a@b.com", "88912345678");
            confere("nome vazio", "EXCEÇÃO", "SEM EXCEÇÃO");
        } catch (IllegalArgumentException e) {
            confere("nome vazio", "EXCEÇÃO", "EXCEÇÃO");
        }

        try {
            new Cliente("   ", "a@b.com", "88912345678");
            confere("nome em branco", "EXCEÇÃO", "SEM EXCEÇÃO");
        } catch (IllegalArgumentException e) {
            confere("nome em branco", "EXCEÇÃO", "EXCEÇÃO");
        }

        // ----------------------------------------------------------------
        System.out.println("\n=== E-mail inválido ===");

        String tel = "88912345678";

        try { new Cliente("X", null, tel);
              confere("email nulo", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("email nulo", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "", tel);
              confere("email vazio", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("email vazio", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "semat.com", tel);
              confere("sem @", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("sem @", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "a@@b.com", tel);
              confere("dois @", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("dois @", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "a@sempontocom", tel);
              confere("sem ponto no domínio", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("sem ponto no domínio", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "a..b@c.com", tel);
              confere("pontos seguidos no usuário", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("pontos seguidos no usuário", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", ".a@c.com", tel);
              confere("usuário começa com ponto", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("usuário começa com ponto", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "a.@c.com", tel);
              confere("usuário termina com ponto", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("usuário termina com ponto", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "a@b..com", tel);
              confere("pontos seguidos no domínio", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("pontos seguidos no domínio", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "a b@c.com", tel);
              confere("espaço no meio", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("espaço no meio", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", "@c.com", tel);
              confere("sem usuário", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("sem usuário", "EXCEÇÃO", "EXCEÇÃO"); }

        // ----------------------------------------------------------------
        System.out.println("\n=== Telefone inválido ===");

        String em = "a@b.com";

        try { new Cliente("X", em, null);
              confere("telefone nulo", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("telefone nulo", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", em, "");
              confere("telefone vazio", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("telefone vazio", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", em, "8891234567");
              confere("10 dígitos (curto)", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("10 dígitos (curto)", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", em, "889123456789");
              confere("12 dígitos (longo)", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("12 dígitos (longo)", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", em, "8891234567a");
              confere("com letra", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("com letra", "EXCEÇÃO", "EXCEÇÃO"); }

        try { new Cliente("X", em, "(88) 91234-5678");
              confere("formatado com símbolos", "EXCEÇÃO", "SEM EXCEÇÃO"); }
        catch (IllegalArgumentException e) { confere("formatado com símbolos", "EXCEÇÃO", "EXCEÇÃO"); }

        // ================================================================
        // TESTES DOS SETTERS (pendente — próxima tarefa)
        // ================================================================

        System.out.println("\n=== Setters ===");
        // TODO: escrever os testes dos setters aqui

    }
}
