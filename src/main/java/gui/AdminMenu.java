package gui;

import entities.Comprador;
import entities.Loja;
import entities.Produto;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

import static gui.GuiUtil.*;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        boolean sair = false;
        while (!sair) {
            System.out.println("1 - Gerenciar Lojas");
            System.out.println("2 - Gerenciar Compradores");
            System.out.println("3 - Gerenciar Produtos");
            System.out.println("4 - Sair");
            var escolha = getEscolha();
            switch (escolha) {
                case 1 -> gerenciarLojas();
                case 2 -> gerenciarCompradores();
                case 3 -> gerenciarProdutos();
                case 4 -> sair = true;
                default -> System.out.println("Escolha inválida!");
            }
        }

    }

    public static int getEscolha() {
        var escolha = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                escolha = Integer.parseInt(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                return -1;
            }
        }
        return escolha;
    }

    public static void gerenciarLojas() {
        System.out.println("1 - Cadastrar loja");
        System.out.println("2 - Exibir loja");
        System.out.println("3 - Atualizar loja");
        System.out.println("4 - Deletar loja");
        System.out.println("5 - Exibir todas as lojas");
        System.out.println("6 - Exibir produtos de uma loja");

        switch (getEscolha()) {
            case 1 -> cadastrarLoja();
            case 2 -> exibirLoja();
            case 3 -> atualizarLoja();
            case 4 -> removerLoja();
            case 5 -> lojaService.getAllLojas().forEach(System.out::println);
            case 6 -> exibirProdutosByLoja();
            default -> System.out.println("Escolha inválida!");
        }
    }

    public static void gerenciarCompradores() {
        System.out.println("1 - Cadastrar comprador");
        System.out.println("2 - Exibir comprador");
        System.out.println("3 - Atualizar comprador");
        System.out.println("4 - Deletar comprador");
        System.out.println("5 - Exibir todos os compradores");
        switch (getEscolha()) {
            case 1 -> cadastrarComprador();
            case 2 -> exibirComprador();
            case 3 -> atualizarComprador();
            case 4 -> removerComprador();
            case 5 -> compradorService.getAllCompradores().forEach(System.out::println);
            default -> System.out.println("Escolha inválida!");
        }
    }

    public static void gerenciarProdutos() {
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Exibir produto");
        System.out.println("3 - Atualizar produto");
        System.out.println("4 - Deletar produto");
        System.out.println("5 - Exibir todos os produtos");
        System.out.println("6 - Exibir todos os produtos de uma Loja");
        switch (getEscolha()) {
            case 1 -> cadastrarProduto();
            case 2 -> exibirProduto();
            case 3 -> atualizarProduto();
            case 4 -> removerProduto();
            case 5 -> produtoService.getAllProdutos().forEach(System.out::println);
            case 6 -> exibirProdutosByLoja();
            default -> System.out.println("Escolha inválida!");
        }
    }

    private static void cadastrarLoja() {
        String nome = receberString("Nome", Validation::nomeLojaValido);
        String email = receberString("email", Validation::emailValido);
        String senha = receberString("senha", Validation::senhaValida);
        String cnpj = receberString("CNPJ", Validation::cpfCnpjValido);
        String endereco = receberString("endereço", Validation::enderecoValido);
        Loja loja = new Loja(nome, email, senha, cnpj, endereco);
        lojaService.createLoja(loja);
    }


    private static void exibirLoja() {
        String cnpj = receberString("CNPJ", Validation::cpfCnpjValido);
        Loja retrievedLoja = lojaService.getLojaByCnpj(cnpj);
        System.out.println(retrievedLoja);
    }

    private static void atualizarLoja() {
        String cnpj = receberString("CNPJ", Validation::cpfCnpjValido);
        Loja retrievedLoja = lojaService.getLojaByCnpj(cnpj);

        if (retrievedLoja != null) {
            System.out.println("Digite os novos dados da loja:");

            String nome = receberString("Nome", Validation::nomeProprioValido);
            retrievedLoja.setNome(nome);

            String email = receberString("E-mail", Validation::emailValido);
            retrievedLoja.setEmail(email);

            String senha = receberString("Senha", Validation::senhaValida);
            retrievedLoja.setSenha(senha);

            String endereco = receberString("Endereço", Validation::enderecoValido);
            retrievedLoja.setEndereco(endereco);

            lojaService.updateLoja(cnpj, retrievedLoja);
            System.out.println(retrievedLoja);

            System.out.println("Loja atualizada com sucesso!");

        } else {
            System.out.println("Loja não encontrada.");
        }
    }

    private static void removerLoja() {
        String cnpj = receberString("CNPJ", Validation::cpfCnpjValido);
        lojaService.deleteLoja(cnpj);
    }

    private static void cadastrarComprador() {
        String nome = receberString("Nome", Validation::nomeProprioValido);
        String email = receberString("E-mail", Validation::emailValido);
        String senha = receberString("Senha", Validation::senhaValida);
        String cpf = receberString("CPF", Validation::cpfCnpjValido);
        String endereco = receberString("Endereço", Validation::enderecoValido);
        Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);
        compradorService.createComprador(comprador);
    }

    private static void exibirComprador() {
        System.out.print("CPF do Comprador: ");
        String cpf = receberString("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = compradorService.getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            System.out.println(retrievedComprador);
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void atualizarComprador() {
        String cpf = receberString("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = compradorService.getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            System.out.println("Digite os novos dados do comprador:");

            String nome = receberString("Nome", Validation::nomeProprioValido);
            retrievedComprador.setNome(nome);

            String email = receberString("E-mail", Validation::emailValido);
            retrievedComprador.setEmail(email);

            String endereco = receberString("Endereço", Validation::enderecoValido);
            retrievedComprador.setEndereco(endereco);

            String senha = receberString("Senha", Validation::senhaValida);
            retrievedComprador.setSenha(senha);

            compradorService.updateComprador(cpf, retrievedComprador);
            System.out.println("Comprador atualizado com sucesso!");
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void removerComprador() {
        String cpf = receberString("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = compradorService.getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            compradorService.deleteComprador(cpf);
            System.out.println("Comprador removido com sucesso!");
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void cadastrarProduto() {
        String nome = receberString("Nome", Validation::nomeProprioValido);
        double valor = Double.parseDouble(receberString("Valor", Validation::valorProdutoValido));
        String tipo = receberString("Tipo", Validation::tipoProdutoValido);

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
                    System.out.println("Quantidade de produto inválida! A quantidade não deve ter letras ou símbolos.");
                    System.out.print("Quantidade: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida! Por favor, insira um número inteiro.");
                System.out.print("Quantidade: ");
            }
        }

        String marca = receberString("Marca", Validation::marcaProdutoValido);

        String descricao = receberString("Descrição", Validation::descricaoProdutoValido);

        String lojaCnpj = receberString("CNPJ da Loja", Validation::cpfCnpjValido);

        Produto produto = new Produto(nome, valor, tipo, quantidade, marca, descricao, lojaCnpj);
        produtoService.createProduto(produto);
    }

    private static void exibirProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoService.getProdutoById(id);

        if (retrievedProduto != null) {
            System.out.println(retrievedProduto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void atualizarProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoService.getProdutoById(id);

        if (retrievedProduto != null) {
            System.out.println("Digite os novos dados do produto:");

            String nome = receberString("Nome", Validation::nomeProprioValido);
            retrievedProduto.setNome(nome);

            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine());
            retrievedProduto.setValor(valor);

            String tipo = receberString("Tipo", Validation::tipoProdutoValido);
            retrievedProduto.setTipo(tipo);

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            retrievedProduto.setQuantidade(quantidade);

            String marca = receberString("Marca", Validation::marcaProdutoValido);
            retrievedProduto.setMarca(marca);

            String descricao = receberString("Descrição", Validation::descricaoProdutoValido);
            retrievedProduto.setDescricao(descricao);

            produtoService.updateProduto(id, retrievedProduto);

            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void removerProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoService.getProdutoById(id);

        if (retrievedProduto != null) {
            produtoService.deleteProduto(id);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void exibirProdutosByLoja() {
        String cnpj = receberString("CNPJ", Validation::cpfCnpjValido);

        List<Produto> produtos = produtoService.getProdutosByLoja(cnpj);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado!");
        } else {
            System.out.println(produtos);
        }
    }
}




