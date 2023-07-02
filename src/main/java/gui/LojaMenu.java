package gui;

import entities.Produto;
import validation.Validation;

import static gui.GuiUtil.*;

public class LojaMenu {
    private GuiUtil util;

    public LojaMenu(GuiUtil util) {
        this.util = util;
    }
    
    public void run(String email) {
        while (true) {
            util.print("Logado como: " + email);
            util.print("1 - Adcionar produto");
            util.print("2 - Exibir produtos");
            util.print("3 - Remover produto");
            util.print("4 - Sair");
            switch (util.getNumero()) {
                case 1 -> adcionarProduto(email);
                case 2 -> exibirProdutos(email);
                case 3 -> removerProduto(email);
                case 4 -> {
                    return;
                }
            }
        }
    }

    private void removerProduto(String email) {
        util.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        var produtos = produtoService.getProdutosByLoja(lojaService.getCnpjFromEmail(email));
        for (var produto : produtos) {
            if (produto.getId() == id) {
                produtoService.deleteProduto(id);
                return;
            }
        }
        util.print("ID incorreto!");
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
