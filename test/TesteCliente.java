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
        // 1. CONSTRUTOR - CASOS VÁLIDOS
        // ================================================================
        System.out.println("=== Casos válidos ===");

        Cliente c1 = new Cliente("Ana Silva", "ana@email.com", "88912345678");
        confere("nome c1",     "Ana Silva",        c1.getNome());
        confere("email c1",    "ana@email.com",    c1.getEmail());
        confere("telefone c1", "88912345678",      c1.getTelefone());
        confere("id c1 não nulo", "sim", c1.getId() != null ? "sim" : "não");

        Cliente c2 = new Cliente("João", "joao@empresa.com.br", "11987654321");
        confere("nome c2", "João", c2.getNome());

        Cliente c3 = new Cliente("Maria Santos", "maria.santos@dominio.org", "21912345678");
        confere("nome c3", "Maria Santos", c3.getNome());

        Cliente c4 = new Cliente("Pedro", "pedro-oli@sub.dominio.com", "31987654321");
        confere("nome c4", "Pedro", c4.getNome());

        System.out.println("\n=== strip() e toLowerCase() no construtor ===");

        Cliente c5 = new Cliente("  Carlos  ", "  CARLOS@EMAIL.COM  ", "  88912345679  ");
        confere("strip nome",     "Carlos",           c5.getNome());
        confere("email em minúsculas", "carlos@email.com", c5.getEmail());
        confere("strip telefone", "88912345679",      c5.getTelefone());

        // ================================================================
        // 2. CONSTRUTOR - CASOS INVÁLIDOS (EXCEÇÕES ESPERADAS)
        // ================================================================
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
        // 3. SETTERS VÁLIDOS
        // ================================================================
        System.out.println("\n=== Setters válidos ===");

        Cliente cliente = new Cliente("João", "joao@empresa.com.br", "11987654321");

        cliente.setNome("  João Silva  ");
        confere("setNome com strip", "João Silva", cliente.getNome());

        cliente.setEmail("  TESTE@EMAIL.COM  ");
        confere("setEmail em minúsculas e strip", "teste@email.com", cliente.getEmail());

        cliente.setTelefone("  11999998888  ");
        confere("setTelefone com strip", "11999998888", cliente.getTelefone());

        // ================================================================
        // 4. SETTERS INVÁLIDOS (PROTEÇÃO DE ESTADO)
        // ================================================================
        System.out.println("\n=== Setters inválidos ===");

        try {
            cliente.setNome(null);
            confere("setNome nulo (SEM EXCEÇÃO)", "João Silva", cliente.getNome());
        } catch (IllegalArgumentException e) {
            confere("setNome nulo (EXCEÇÃO)", "João Silva", cliente.getNome());
        }

        try {
            cliente.setNome("  ");
            confere("setNome vazio (SEM EXCEÇÃO)", "João Silva", cliente.getNome());
        } catch (IllegalArgumentException e) {
            confere("setNome vazio (EXCEÇÃO)", "João Silva", cliente.getNome());
        }

        try {
            cliente.setEmail(null);
            confere("setEmail nulo (SEM EXCEÇÃO)", "teste@email.com", cliente.getEmail());
        } catch (IllegalArgumentException e) {
            confere("setEmail nulo (EXCEÇÃO)", "teste@email.com", cliente.getEmail());
        }

        try {
            cliente.setEmail("isso-nao-e-email");
            confere("setEmail inválido (SEM EXCEÇÃO)", "teste@email.com", cliente.getEmail());
        } catch (IllegalArgumentException e) {
            confere("setEmail inválido (EXCEÇÃO)", "teste@email.com", cliente.getEmail());
        }

        try {
            cliente.setTelefone(null);
            confere("setTelefone nulo (SEM EXCEÇÃO)", "11999998888", cliente.getTelefone());
        } catch (IllegalArgumentException e) {
            confere("setTelefone nulo (EXCEÇÃO)", "11999998888", cliente.getTelefone());
        }

        try {
            cliente.setTelefone("123123");
            confere("setTelefone curto (SEM EXCEÇÃO)", "11999998888", cliente.getTelefone());
        } catch (IllegalArgumentException e) {
            confere("setTelefone curto (EXCEÇÃO)", "11999998888", cliente.getTelefone());
        }

        // ================================================================
        // 5. TESTES DE EQUALS E HASHCODE
        // ================================================================
        System.out.println("\n=== Testes de equals e hashCode ===");

        Cliente mesmocliente = cliente;
        confere("equals, mesmo objeto", "sim", cliente.equals(mesmocliente) ? "sim" : "não");
        confere("hashCode, mesmo objeto", "sim", cliente.hashCode() == mesmocliente.hashCode() ? "sim" : "não");

        confere("equals, clientes diferentes", "não", cliente.equals(c1) ? "sim" : "não");
        confere("equals com null", "não", cliente.equals(null) ? "sim" : "não");
        confere("equals com outro tipo de objeto", "não", cliente.equals("texto") ? "sim" : "não");
    }
}
