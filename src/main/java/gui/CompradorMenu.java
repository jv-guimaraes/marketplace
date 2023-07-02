package gui;

import entities.Loja;
import entities.Produto;
import services.CompradorService;
import services.LojaService;
import services.ProdutoService;
import validation.Validation;

import static gui.GuiUtil.*;

public class CompradorMenu {
    private GuiUtil util;
    private LojaService lojaService;
    private CompradorService compradorService;
    private ProdutoService produtoService;

    public CompradorMenu(GuiUtil util, LojaService lojaService, CompradorService compradorService, ProdutoService produtoService) {
        this.util = util;
        this.lojaService = lojaService;
        this.compradorService = compradorService;
        this.produtoService = produtoService;
    }
    
    public void run(String email) {
        while (true) {
            util.print("1 - Exibir produtos a venda");
            util.print("2 - Adcionar produto ao carrinho");
            util.print("3 - Exibir carrinho");
            util.print("4 - Comprar itens no carrinho");
            util.print("5 - Exibir pontos");
            util.print("6 - Sair");
            switch (util.getNumero()) {
                case 1 -> exibirProdutos();
                case 2 -> adcionarProdutoCarrinho(email);
                case 3 -> exibirCarrinho(email);
                case 4 -> comprarItensCarrinho(email);
                case 5 -> exibirPontos(email);
                case 6 -> {
                    return;
                }
            }
        }
    }

    private void exibirPontos(String email) {
        var comprador = compradorService.getCompradorByEmail(email);
        System.out.printf("Voce tem %d pontos.\n", comprador.getPontos());
        util.print("Preços dos bônus:");
        util.print("   100 pontos - Frete grátis");
        util.print("   150 pontos - 10% de desconto");
        util.print();
    }

    private void exibirCarrinho(String email) {
        var comprador = compradorService.getCompradorByEmail(email);
        if (comprador.getCarrinho().isEmpty()) {
            util.print("O carrinho está vazio!");
            return;
        }
        util.print("CARRINHO:");
        for (var id : comprador.getCarrinho()) {
            util.print(id.toString() + " - " + produtoService.getProdutoById(id).getNome());
        }
        util.print();
    }

    private void adcionarProdutoCarrinho(String email) {
        long id = Integer.parseInt(util.receberString("ID do produto", Validation::idValido));
        if (!produtoService.produtoExiste(id)) {
            util.print("Produto não encontrado.");
            return;
        }
        if (compradorService.getCompradorByEmail(email).carrinhoContem(id)) {
            util.print("Produto ja está no carrinho.");
            return;
        }

        String cpf = compradorService.getCpfFromEmail(email);
        compradorService.addProdutoCarrinho(cpf, id);
    }

    private void exibirProdutos() {
        var lojas = lojaService.getAllLojas();
        for (Loja loja : lojas) {
            System.out.printf("Loja: %s (%s)\n", loja.getNome(), loja.getAvaliacao());
            var produtos = produtoService.getProdutosByLoja(loja.getCnpj());
            for (Produto produto : produtos) {
                System.out.printf("   id:%2d | %-20s | %.2f☆ | R$ %.2f\n",
                        produto.getId(), produto.getNome(), produto.getNota(), produto.getValor());
            }
        }
        util.print();
    }

    private void comprarItensCarrinho(String email) {
        var comprador = compradorService.getCompradorByEmail(email);
        if (comprador.getCarrinho().isEmpty()) {
            util.print("O carrinho está vazio.");
            return;
        }

        util.print("Realizando a compra dos itens no carrinho:");
        for (var id : comprador.getCarrinho()) {
            var produto = produtoService.getProdutoById(id);
            System.out.printf("%s(R$ %.2f):\n", produto.getNome(), produto.getValor());

            // Perguntar se o usuario quer usar seus pontos para ganhar um bônus
            if (comprador.getPontos() >= 100) {
                System.out.printf("Você tem %d pontos. Quer usar 100 pontos para ganhar frete grátis? (s/n) ",
                        comprador.getPontos());
                var simOuNao = scanner.nextLine();
                if (simOuNao.equals("S") || simOuNao.equals("s")) {
                    System.out.printf("%s foi comprado com frete grátis.\n", produto.getNome());
                    comprador.setPontos(comprador.getPontos() - 100);
                    compradorService.updateComprador(comprador.getCpf(), comprador);
                }
            }
            if (comprador.getPontos() >= 150) {
                System.out.printf("Você tem %d pontos. Quer usar 150 pontos para ganhar 10%% de desconto? (s/n) ",
                        comprador.getPontos());
                var simOuNao = scanner.nextLine();
                if (simOuNao.equals("S") || simOuNao.equals("s")) {
                    System.out.printf("%s foi comprado com 10%% de desconto, por R$ %.2f.\n",
                            produto.getNome(), produto.getValor() * 0.9);
                    comprador.setPontos(comprador.getPontos() - 150);
                    compradorService.updateComprador(comprador.getCpf(), comprador);
                }
            }
            updateProduto(id, 1);
            comprador.addPontos(produto.getValor());
            comprador.addToHistorico(id);
            avaliar(produto);
        }
        comprador.clearCarrinho();
        compradorService.updateComprador(comprador.getCpf(), comprador);

        util.print("Compra concluída com sucesso!");
    }

    private void updateProduto(long id, int quantidadeComprada) {
        Produto produto = produtoService.getProdutoById(id);
        int quantidade = produto.getQuantidade();
        quantidade -= quantidadeComprada;

        produto.setQuantidade(quantidade);
        if (produto.getQuantidade() == 0) {
            produtoService.deleteProduto(id);
        } else {
            produtoService.updateProduto(id, produto);
        }
    }

    private void avaliar(Produto produto) {
        util.print("Digite a nota para o produto " + produto.getNome());
        int nota = util.getNumero();
        produtoService.adicionarNota(produto.getId(), nota);

        var cnpj = produtoService.getProdutoById(produto.getId()).getLojaCnpj();
        var loja = lojaService.getLojaByCnpj(cnpj);
        util.print("Digite a nota para a loja " + loja.getNome());
        nota = util.getNumero();
        lojaService.adicionarNota(cnpj, nota);
    }
}