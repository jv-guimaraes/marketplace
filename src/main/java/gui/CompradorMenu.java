package gui;

import validation.Validation;

import static gui.GuiUtil.*;

public class CompradorMenu {
    public static void run(String email) {
        while (true) {
            System.out.println("1 - Exibir produtos a venda");
            System.out.println("2 - Adcionar produto ao carrinho");
            System.out.println("3 - Exibir carrinho");
            System.out.println("4 - Comprar itens no carrinho");
            System.out.println("5 - Sair");
            switch (getNumero()) {
                case 1 -> exibirProdutos();
                case 2 -> adcionarProdutoCarrinho(email);
                case 3 -> exibirCarrinho(email);
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void exibirCarrinho(String email) {
        var comprador = compradorService.getCompradorByEmail(email);
        System.out.println("CARRINHO:");
        for (var id : comprador.getCarrinho()) {
            System.out.println(id.toString() + " - " + produtoService.getProdutoById(id).getNome());
        }
        System.out.println();
    }

    private static void adcionarProdutoCarrinho(String email) {
        long id = Integer.parseInt(receberString("ID do produto", Validation::idValido));
        if (!produtoService.produtoExiste(id)) {
            System.out.println("Produto não encontrado.");
            return;
        }
        if (compradorService.getCompradorByEmail(email).carrinhoContem(id)) {
            System.out.println("Produto ja está no carrinho.");
            return;
        }

        String cpf = compradorService.getCpfFromEmail(email);
        compradorService.addProdutoCarrinho(cpf, id);
    }

    private static void exibirProdutos() {
        produtoService.getAllProdutos().forEach(System.out::println);
    }
}
