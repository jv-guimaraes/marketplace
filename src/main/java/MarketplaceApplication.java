import di.DI;
import entities.Comprador;
import entities.Loja;
import entities.Produto;
import validation.Validation;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class MarketplaceApplication {
    private static final DI di = new DI();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Boolean sair = false;
        while (!sair) {
            System.out.println("1 - Gerenciar Lojas");
            System.out.println("2 - Gerenciar Compradores");
            System.out.println("3 - Gerenciar Produtos");
            System.out.println("4 - Sair");
            var escolha = getEscolha();
            switch (escolha) {
                case 1:
                    gerenciarLojas();
                    break;
                case 2:
                    gerenciarCompradores();
                    break;
                case 3:
                    gerenciarProdutos();
                    break;
                case 4:
                    sair = true;
                    break;
                default:
                    System.out.println("Escolha inválida!");
                    break;
            }
        }


        //System.out.println(produtoController.getAllProdutos());
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
            case 1:
                cadastrarLoja();
                break;
            case 2:
                exibirLoja();
                break;
            case 3:
                atualizarLoja();
                break;
            case 4:
                removerLoja();
                break;
            case 5:
                System.out.println(di.getLojaController().getAllLojas());
                break;
            case 6:
                exibirProdutosByLoja();
                break;
            default:
                System.out.println("Escolha inválida!");
                break;
        }
    }

    public static void gerenciarCompradores() {
        System.out.println("1 - Cadastrar comprador");
        System.out.println("2 - Exibir comprador");
        System.out.println("3 - Atualizar comprador");
        System.out.println("4 - Deletar comprador");
        System.out.println("5 - Exibir todos os compradores");
        switch (getEscolha()) {
            case 1:
                cadastrarComprador();
                break;
            case 2:
                exibirComprador();
                break;
            case 3:
                atualizarComprador();
                break;
            case 4:
                removerComprador();
                break;
            case 5:
                System.out.println(di.getCompradorController().getAllCompradores());
                break;
            default:
                System.out.println("Escolha inválida!");
                break;
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
            case 1:
                cadastrarProduto();
                break;
            case 2:
                exibirProduto();
                break;
            case 3:
                atualizarProduto();
                break;
            case 4:
                removerProduto();
                break;
            case 5:
                System.out.println(di.getProdutoController().getAllProdutos());
                break;
            case 6:
                exibirProdutosByLoja();
                break;
            default:
                System.out.println("Escolha inválida!");
                break;
        }
    }

    private static void cadastrarLoja() {
        String nome = receberInput("Nome", Validation::nomeLojaValido);
        String email = receberInput("email", Validation::emailValido);
        String senha = receberInput("senha", Validation::senhaValida);
        String cnpj = receberInput("CNPJ", Validation::cpfCnpjValido);
        String endereco = receberInput("endereço", Validation::enderecoValido);
        Loja loja = new Loja(nome, email, senha, cnpj, endereco);

        di.getLojaController().createLoja(loja);
    }


    private static void exibirLoja() {
        String cnpj = receberInput("CNPJ", Validation::cpfCnpjValido);
        Loja retrievedLoja = di.getLojaController().getLojaByCnpj(cnpj);
        System.out.println(retrievedLoja);
    }

    private static void atualizarLoja() {
        String cnpj = receberInput("CNPJ", Validation::cpfCnpjValido);
        Loja retrievedLoja = di.getLojaController().getLojaByCnpj(cnpj);

        if (retrievedLoja != null) {
            System.out.println("Digite os novos dados da loja:");

            String nome = receberInput("Nome", Validation::nomeProprioValido);
            retrievedLoja.setNome(nome);

            String email = receberInput("E-mail", Validation::emailValido);
            retrievedLoja.setEmail(email);

            String senha = receberInput("Senha", Validation::senhaValida);
            retrievedLoja.setSenha(senha);

            String endereco = receberInput("Endereço", Validation::enderecoValido);
            retrievedLoja.setEndereco(endereco);

            di.getLojaController().updateLoja(cnpj, retrievedLoja);
            System.out.println(retrievedLoja);

            System.out.println("Loja atualizada com sucesso!");

        } else {
            System.out.println("Loja não encontrada.");
        }
    }

    private static void removerLoja() {
        String cnpj = receberInput("CNPJ", Validation::cpfCnpjValido);
        di.getLojaController().deleteLoja(cnpj);
    }

    private static void cadastrarComprador() {
        String nome = receberInput("Nome", Validation::nomeProprioValido);
        String email = receberInput("E-mail", Validation::emailValido);
        String senha = receberInput("Senha", Validation::senhaValida);
        String cpf = receberInput("CPF", Validation::cpfCnpjValido);
        String endereco = receberInput("Endereço", Validation::enderecoValido);
        Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);
        di.getCompradorController().createComprador(comprador);
    }

    private static void exibirComprador() {
        System.out.print("CPF do Comprador: ");
        String cpf = receberInput("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = di.getCompradorController().getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            System.out.println(retrievedComprador);
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void atualizarComprador() {
        String cpf = receberInput("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = di.getCompradorController().getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            System.out.println("Digite os novos dados do comprador:");

            String nome = receberInput("Nome", Validation::nomeProprioValido);
            retrievedComprador.setNome(nome);

            String email = receberInput("E-mail", Validation::emailValido);
            retrievedComprador.setEmail(email);

            String endereco = receberInput("Endereço", Validation::enderecoValido);
            retrievedComprador.setEndereco(endereco);

            String senha = receberInput("Senha", Validation::senhaValida);;
            retrievedComprador.setSenha(senha);

            di.getCompradorController().updateComprador(cpf, retrievedComprador);
            System.out.println("Comprador atualizado com sucesso!");
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void removerComprador() {
        String cpf = receberInput("CPF", Validation::cpfCnpjValido);
        Comprador retrievedComprador = di.getCompradorController().getCompradorByCpf(cpf);
        if (retrievedComprador != null) {
            di.getCompradorController().deleteComprador(cpf);
            System.out.println("Comprador removido com sucesso!");
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void cadastrarProduto() {
        String nome = receberInput("Nome", Validation::nomeProprioValido);

        System.out.print("Valor: ");
        double valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            String input = scanner.nextLine();
            try {
                valor = Double.parseDouble(input);
                if (Validation.valorProdutoValido(valor)) {
                    entradaValida = true;
                } else {
                    System.out.println("Valor inválido! O valor não deve ter letras ou símbolos.");
                    System.out.print("Valor: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Por favor, insira um número.");
                System.out.print("Valor: ");
            }
        }

        String tipo = receberInput("Tipo", Validation::tipoProdutoValido);

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

        String marca = receberInput("Marca", Validation::marcaProdutoValido);

        String descricao = receberInput("Descrição", Validation::descricaoProdutoValido);

        String lojaCnpj = receberInput("CNPJ da Loja", Validation::cpfCnpjValido);

        Produto produto = new Produto(nome, valor, tipo, quantidade, marca, descricao, lojaCnpj);
        di.getProdutoController().createProduto(produto);
    }

    private static void exibirProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = di.getProdutoController().getProdutoById(id);

        if (retrievedProduto != null) {
            System.out.println(retrievedProduto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void atualizarProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = di.getProdutoController().getProdutoById(id);

        if (retrievedProduto != null) {
            System.out.println("Digite os novos dados do produto:");

            String nome = receberInput("Nome", Validation::nomeProprioValido);
            retrievedProduto.setNome(nome);

            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine());
            retrievedProduto.setValor(valor);

            String tipo = receberInput("Tipo", Validation::tipoProdutoValido);
            retrievedProduto.setTipo(tipo);

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            retrievedProduto.setQuantidade(quantidade);

            String marca = receberInput("Marca", Validation::marcaProdutoValido);
            retrievedProduto.setMarca(marca);

            String descricao = receberInput("Descrição", Validation::descricaoProdutoValido);
            retrievedProduto.setDescricao(descricao);

            di.getProdutoController().updateProduto(id, retrievedProduto);

            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void removerProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = di.getProdutoController().getProdutoById(id);

        if (retrievedProduto != null) {
            di.getProdutoController().deleteProduto(id);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void exibirProdutosByLoja() {
        String cnpj = receberInput("CNPJ", Validation::cpfCnpjValido);

        List<Produto> produtos = di.getProdutoController().getProdutosByLoja(cnpj);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado!");
        } else {
            System.out.println(produtos);
        }
    }

    private static String receberInput(String tipo, Predicate<String> validator) {
        System.out.print(tipo + ": ");
        String userInput = scanner.nextLine();
        while (!validator.test(userInput)) {
            if (tipo.endsWith("a")) {
                System.out.println(tipo + " inválida!");
            } else {
                System.out.println(tipo + " inválido!");
            }
            System.out.print(tipo + ": ");
            userInput = scanner.nextLine();
        }
        return userInput;
    }
}




