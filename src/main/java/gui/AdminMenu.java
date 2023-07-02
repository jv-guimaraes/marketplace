package gui;

import entities.Comprador;
import entities.Loja;
import entities.Produto;
import services.CompradorService;
import services.LojaService;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

import static gui.GuiUtil.*;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private GuiUtil util;
    private LojaService lojaService;
    private CompradorService compradorService;

    public AdminMenu(GuiUtil util, LojaService lojaService, CompradorService compradorService) {
        this.util = util;
        this.lojaService = lojaService;
        this.compradorService = compradorService;
    }

    public void run() {
        boolean sair = false;
        while (!sair) {
            util.print("1 - Gerenciar Lojas");
            util.print("2 - Gerenciar Compradores");
            util.print("3 - Gerenciar Produtos");
            util.print("4 - Sair");
            var escolha = getEscolha();
            switch (escolha) {
                case 1 -> gerenciarLojas();
                case 2 -> gerenciarCompradores();
                case 3 -> gerenciarProdutos();
                case 4 -> sair = true;
                default -> util.print("Escolha inválida!");
            }
        }

    }

    public int getEscolha() {
        var escolha = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                escolha = Integer.parseInt(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                util.print("Entrada inválida! Por favor, insira um número.");
                return -1;
            }
        }
        return escolha;
    }

    public void gerenciarLojas() {
        util.print("1 - Cadastrar loja");
        util.print("2 - Exibir loja");
        util.print("3 - Atualizar loja");
        util.print("4 - Deletar loja");
        util.print("5 - Exibir todas as lojas");
        util.print("6 - Exibir produtos de uma loja");

        switch (getEscolha()) {
            case 1 -> cadastrarLoja();
            case 2 -> exibirLoja();
            case 3 -> atualizarLoja();
            case 4 -> removerLoja();
            case 5 -> lojaService.getAllLojas().forEach(System.out::println);
            case 6 -> exibirProdutosByLoja();
            default -> util.print("Escolha inválida!");
        }
    }

    public void gerenciarCompradores() {
        util.print("1 - Cadastrar comprador");
        util.print("2 - Exibir comprador");
        util.print("3 - Atualizar comprador");
        util.print("4 - Deletar comprador");
        util.print("5 - Exibir todos os compradores");
        switch (getEscolha()) {
            case 1 -> cadastrarComprador();
            case 2 -> exibirComprador();
            case 3 -> atualizarComprador();
            case 4 -> removerComprador();
            case 5 -> compradorService.getAllCompradores().forEach(System.out::println);
            default -> util.print("Escolha inválida!");
        }
    }

    public void gerenciarProdutos() {
        util.print("1 - Cadastrar produto");
        util.print("2 - Exibir produto");
        util.print("3 - Atualizar produto");
        util.print("4 - Deletar produto");
        util.print("5 - Exibir todos os produtos");
        util.print("6 - Exibir todos os produtos de uma Loja");
        switch (getEscolha()) {
            case 1 -> cadastrarProduto();
            case 2 -> exibirProduto();
            case 3 -> atualizarProduto();
            case 4 -> removerProduto();
            case 5 -> produtoService.getAllProdutos().forEach(System.out::println);
            case 6 -> exibirProdutosByLoja();
            default -> util.print("Escolha inválida!");
        }
    }

    private void cadastrarLoja() {
        String nome = util.receberString("Nome", Validation::nomeLojaValido);
        String email = util.receberString("email", Validation::emailValido);
        String senha = util.receberString("senha", Validation::senhaValida);
        String cnpj = util.receberString("CNPJ", Validation::cpfCnpjValido);
        String endereco = util.receberString("endereço", Validation::enderecoValido);
        Loja loja = new Loja(nome, email, senha, cnpj, endereco);
        lojaService.createLoja(loja);
    }


    private void exibirLoja() {
        String cnpj = util.receberString("CNPJ", Validation::cpfCnpjValido);
        Loja retrievedLoja = lojaService.getLojaByCnpj(cnpj);
        util.print(retrievedLoja);
    }

    private void atualizarLoja() {
        String cnpj = util.receberString("CNPJ", Validation::cpfCnpjValido);
        Loja retrievedLoja = lojaService.getLojaByCnpj(cnpj);

        if (retrievedLoja != null) {
            util.print("Digite os novos dados da loja:");

            String nome = util.receberString("Nome", Validation::nomeProprioValido);
            retrievedLoja.setNome(nome);

            String email = util.receberString("E-mail", Validation::emailValido);
            retrievedLoja.setEmail(email);

            String senha = util.receberString("Senha", Validation::senhaValida);
            retrievedLoja.setSenha(senha);

            String endereco = util.receberString("Endereço", Validation::enderecoValido);
            retrievedLoja.setEndereco(endereco);

            lojaService.updateLoja(cnpj, retrievedLoja);
            util.print(retrievedLoja);

            util.print("Loja atualizada com sucesso!");

        } else {
            util.print("Loja não encontrada.");
        }
    }

    private void removerLoja() {
        String cnpj = util.receberString("CNPJ", Validation::cpfCnpjValido);
        lojaService.deleteLoja(cnpj);
    }

    private void cadastrarComprador() {
        String nome = util.receberString("Nome", Validation::nomeProprioValido);
        String email = util.receberString("E-mail", Validation::emailValido);
        String senha = util.receberString("Senha", Validation::senhaValida);
        String cpf = util.receberString("CPF", Validation::cpfCnpjValido);
        String endereco = util.receberString("Endereço", Validation::enderecoValido);
        Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);
        compradorService.createComprador(comprador);
    }

    private void exibirComprador() {
        System.out.print("CPF do Comprador: ");
        String cpf = util.receberString("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = compradorService.getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            util.print(retrievedComprador);
        } else {
            util.print("Comprador não encontrado.");
        }
    }

    private void atualizarComprador() {
        String cpf = util.receberString("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = compradorService.getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            util.print("Digite os novos dados do comprador:");

            String nome = util.receberString("Nome", Validation::nomeProprioValido);
            retrievedComprador.setNome(nome);

            String email = util.receberString("E-mail", Validation::emailValido);
            retrievedComprador.setEmail(email);

            String endereco = util.receberString("Endereço", Validation::enderecoValido);
            retrievedComprador.setEndereco(endereco);

            String senha = util.receberString("Senha", Validation::senhaValida);
            retrievedComprador.setSenha(senha);

            compradorService.updateComprador(cpf, retrievedComprador);
            util.print("Comprador atualizado com sucesso!");
        } else {
            util.print("Comprador não encontrado.");
        }
    }

    private void removerComprador() {
        String cpf = util.receberString("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = compradorService.getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            compradorService.deleteComprador(cpf);
            util.print("Comprador removido com sucesso!");
        } else {
            util.print("Comprador não encontrado.");
        }
    }

    private void cadastrarProduto() {
        String nome = util.receberString("Nome", Validation::nomeProprioValido);
        double valor = Double.parseDouble(util.receberString("Valor", Validation::valorProdutoValido));
        String tipo = util.receberString("Tipo", Validation::tipoProdutoValido);

        System.out.print("Quantidade: ");
        int quantidade = 0;
        boolean quantValida = false;
        while (!quantValida) {
            String input = scanner.nextLine();
            try {
                quantidade = Integer.parseInt(input);
                if (Validation.quantProdutoValido(quantidade)) {
                    quantValida = true;
                } else {
                    util.print("Quantidade de produto inválida! A quantidade não deve ter letras ou símbolos.");
                    System.out.print("Quantidade: ");
                }
            } catch (NumberFormatException e) {
                util.print("Quantidade inválida! Por favor, insira um número inteiro.");
                System.out.print("Quantidade: ");
            }
        }

        String marca = util.receberString("Marca", Validation::marcaProdutoValido);

        String descricao = util.receberString("Descrição", Validation::descricaoProdutoValido);

        String lojaCnpj = util.receberString("CNPJ da Loja", Validation::cpfCnpjValido);

        Produto produto = new Produto(nome, valor, tipo, quantidade, marca, descricao, lojaCnpj);
        produtoService.createProduto(produto);
    }

    private void exibirProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoService.getProdutoById(id);

        if (retrievedProduto != null) {
            util.print(retrievedProduto);
        } else {
            util.print("Produto não encontrado.");
        }
    }

    private void atualizarProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoService.getProdutoById(id);

        if (retrievedProduto != null) {
            util.print("Digite os novos dados do produto:");

            String nome = util.receberString("Nome", Validation::nomeProprioValido);
            retrievedProduto.setNome(nome);

            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine());
            retrievedProduto.setValor(valor);

            String tipo = util.receberString("Tipo", Validation::tipoProdutoValido);
            retrievedProduto.setTipo(tipo);

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            retrievedProduto.setQuantidade(quantidade);

            String marca = util.receberString("Marca", Validation::marcaProdutoValido);
            retrievedProduto.setMarca(marca);

            String descricao = util.receberString("Descrição", Validation::descricaoProdutoValido);
            retrievedProduto.setDescricao(descricao);

            produtoService.updateProduto(id, retrievedProduto);

            util.print("Produto atualizado com sucesso!");
        } else {
            util.print("Produto não encontrado.");
        }
    }

    private void removerProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoService.getProdutoById(id);

        if (retrievedProduto != null) {
            produtoService.deleteProduto(id);
            util.print("Produto removido com sucesso!");
        } else {
            util.print("Produto não encontrado.");
        }
    }

    private void exibirProdutosByLoja() {
        String cnpj = util.receberString("CNPJ", Validation::cpfCnpjValido);

        List<Produto> produtos = produtoService.getProdutosByLoja(cnpj);
        if (produtos.isEmpty()) {
            util.print("Nenhum produto encontrado!");
        } else {
            util.print(produtos);
        }
    }
}




