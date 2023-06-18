package gui;

import entities.Produto;
import validation.Validation;

import static gui.GuiUtil.*;

public class LojaMenu {
    public static void run(String email) {
        while (true) {
            System.out.println("Logado como: " + email);
            System.out.println("1 - Adcionar produto");
            System.out.println("2 - Exibir produtos");
            System.out.println("3 - Remover produto");
            System.out.println("4 - Sair");
            switch (getNumero()) {
                case 1 -> adcionarProduto(email);
                case 2 -> exibirProdutos(email);
                case 3 -> removerProduto(email);
                case 4 -> {
                    return;
                }
            }
        }
    }

    private static void removerProduto(String email) {
        System.out.println("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        var produtos = produtoService.getProdutosByLoja(lojaService.getCnpjFromEmail(email));
        for (var produto : produtos) {
            if (produto.getId() == id) {
                produtoService.deleteProduto(id);
                return;
            }
        }
        System.out.println("ID incorreto!");
    }

    private static void exibirProdutos(String email) {
        var cnpj = lojaService.getCnpjFromEmail(email);
        var produtos = produtoService.getProdutosByLoja(cnpj);
        produtos.forEach(System.out::println);
    }

    private static void adcionarProduto(String email) {
        String nome = receberString("Nome", Validation::nomeProdutoValido);
        double valor = Double.parseDouble(receberString("Valor", Validation::valorProdutoValido));
        String tipo = receberString("Tipo", Validation::tipoProdutoValido);
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        String marca = receberString("Marca", Validation::marcaProdutoValido);
        String descricao = receberString("Descrição", Validation::descricaoProdutoValido);
        String lojaCnpj = lojaService.getCnpjFromEmail(email);
        Produto produto = new Produto(nome, valor, tipo, quantidade, marca, descricao, lojaCnpj);
        produtoService.createProduto(produto);
    }
}
