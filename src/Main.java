
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        // 1. Criação dos produtos
        Produto p1 = new Produto(
                "Bola",
                "Bola de futebol, preta com branco",
                new BigDecimal("110.00"),
                25
        );

        Produto p2 = new Produto(
                "Celular",
                "Celular preto, Samsung, tela AMOLED",
                new BigDecimal("2500.00"),
                12,
                false
        );

        // 2. Teste dos getters
        System.out.println("===== DADOS DOS PRODUTOS =====");

        System.out.println("Nome: " + p1.getNome());
        System.out.println("Descrição: " + p1.getDescricao());
        System.out.println("Preço: " + p1.getPreco());
        System.out.println("Estoque: " + p1.getQuantidadeEstoque());
        System.out.println("Ativo: " + p1.isAtivo());

        System.out.println();

        System.out.println("Nome: " + p2.getNome());
        System.out.println("Descrição: " + p2.getDescricao());
        System.out.println("Preço: " + p2.getPreco());
        System.out.println("Estoque: " + p2.getQuantidadeEstoque());
        System.out.println("Ativo: " + p2.isAtivo());

        // 3. Teste de ativação e desativação
        System.out.println("\n===== TESTE DE STATUS =====");

        p2.ativar();
        System.out.println("Celular após ativar: " + p2.isAtivo());

        p1.desativar();
        System.out.println("Bola após desativar: " + p1.isAtivo());

        // 4. Teste de alteração de atributos
        System.out.println("\n===== TESTE DE ALTERAÇÕES =====");

        p1.setNome("Bola de futebol");
        p1.setDescricao("Bola profissional");
        p1.setPreco(new BigDecimal("150.00"));
        p1.setQuantidadeEstoque(40);

        System.out.println("Nome atualizado: " + p1.getNome());
        System.out.println("Descrição atualizada: " + p1.getDescricao());
        System.out.println("Preço atualizado: " + p1.getPreco());
        System.out.println("Estoque atualizado: " + p1.getQuantidadeEstoque());

        // 5. Teste das validações
        System.out.println("\n===== TESTE DE VALIDAÇÕES =====");

        testarExcecao("Nome nulo", () ->
                p1.setNome(null));

        testarExcecao("Nome vazio", () ->
                p1.setNome("   "));

        testarExcecao("Preço nulo", () ->
                p1.setPreco(null));

        testarExcecao("Preço negativo", () ->
                p1.setPreco(new BigDecimal("-10.00")));

        testarExcecao("Estoque negativo", () ->
                p1.setQuantidadeEstoque(-5));

        // 6. Verificar se os dados permaneceram válidos
        System.out.println("\n===== ESTADO FINAL =====");

        System.out.println("Nome: " + p1.getNome());
        System.out.println("Preço: " + p1.getPreco());
        System.out.println("Estoque: " + p1.getQuantidadeEstoque());
    }

    // Método auxiliar para testar exceções
    private static void testarExcecao(String descricao, Runnable teste) {
        try {
            teste.run();
            System.out.println("[FALHOU] " + descricao);
        } catch (IllegalArgumentException e) {
            System.out.println("[PASSOU] " + descricao + " -> " + e.getMessage());
        }
    }
}
