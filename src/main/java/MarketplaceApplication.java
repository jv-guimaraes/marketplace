import entities.Comprador;
import entities.Loja;
import entities.Produto;
import validation.Validation;
import di.DI;

import java.util.List;
import java.util.Scanner;

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
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (!Validation.nomeLojaValido(nome)) {
            System.out.println("Nome inválido! O nome não pode conter simbolos e deve ter até no máximo 50 caracteres");
            return;
        }

        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        if (!Validation.emailValido(email)) {
            System.out.println("Email inválido! O email deve conter simbolos.");
            return;
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        if (!Validation.senhaValida(senha)) {
            System.out.println("Senha inválida! A senha deve conter de 6 a 20 caracteres.");
            return;
        }

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        if(!Validation.CnpjValido(cnpj)){
            System.out.println("Cnpj inválido!");
            return;
         }
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        if (!Validation.enderecoValido(endereco)) {
            System.out.println("Endereço Inválido!");
            return;
        }

        Loja loja = new Loja(nome, email, senha, cnpj, endereco);

        di.getLojaController().createLoja(loja);
    }


    private static void exibirLoja() {
        System.out.print("CNPJ da Loja: ");
        String cnpj = scanner.nextLine();
        if(!Validation.CnpjValido(cnpj)){
            System.out.println("Cnpj inválido!");
            return;
        }
        Loja retrievedLoja = di.getLojaController().getLojaByCnpj(cnpj);

        System.out.println(retrievedLoja);

    }

    private static void atualizarLoja() {
        System.out.print("Informe o CNPJ da Loja: ");
        String cnpj = scanner.nextLine();
        if(!Validation.CnpjValido(cnpj)){
            System.out.println("Cnpj inválido!");
            return;
        }
        Loja retrievedLoja = di.getLojaController().getLojaByCnpj(cnpj);

        if (retrievedLoja != null) {
            System.out.println("Digite os novos dados da loja:");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            if (!Validation.nomeLojaValido(nome)) {
                System.out.println("Nome inválido! O nome não pode conter digitos e deve ter até no máximo 50 caracteres");
                return;
            }
            retrievedLoja.setNome(nome);
            

            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            if (!Validation.emailValido(email)) {
                System.out.println("Email inválido!");
                return;
            }
            retrievedLoja.setEmail(email);

            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            if (!Validation.senhaValida(senha)) {
                System.out.println("Senha inválida! A senha deve conter de 6 a 20 caracteres.");
                return;
            }
            retrievedLoja.setSenha(senha);

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            if (!Validation.enderecoValido(endereco)) {
                System.out.println("Endereço Inválido!");
                return;
            }
            retrievedLoja.setEndereco(endereco);

            di.getLojaController().updateLoja(cnpj, retrievedLoja);
            System.out.println(retrievedLoja);

            System.out.println("Loja atualizada com sucesso!");

        } else {
            System.out.println("Loja não encontrada.");
        }
    }

    private static void removerLoja() {
        System.out.print("CNPJ da Loja: ");
        String cnpj = scanner.nextLine();
        if(!Validation.CnpjValido(cnpj)){
            System.out.println("Cnpj inválido!");
            return;
        }
        Loja retrievedLoja = di.getLojaController().getLojaByCnpj(cnpj);

        di.getLojaController().deleteLoja(cnpj);
    }

    private static void cadastrarComprador() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (!Validation.nomeProprioValido(nome)) {
            System.out.println("Nome inválido! O nome não pode conter digitos e deve ter até no máximo 50 caracteres");
            return;
        }

        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        if (!Validation.emailValido(email)) {
            System.out.println("Email inválido!");
            return;
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        if (!Validation.senhaValida(senha)) {
            System.out.println("Senha inválida! A senha deve conter de 6 a 20 caracteres.");
            return;
        }

        System.out.print("cpf: ");
        String cpf = scanner.nextLine();
        if (!Validation.cpfCnpjValido(cpf)) {
            System.out.println("CPF Inválido!");
            return;
        }

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        if (!Validation.enderecoValido(endereco)) {
            System.out.println("Endereço Inválido!");
            return;
        }

        Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);

        di.getCompradorController().createComprador(comprador);
    }

    private static void exibirComprador() {
        System.out.print("CPF do Comprador: ");
        String cpf = scanner.nextLine();

        Comprador retrievedComprador = di.getCompradorController().getCompradorByCpf(cpf);

        if (retrievedComprador != null) {
            System.out.println("Nome: " + retrievedComprador.getNome());
            System.out.println("CPF: " + retrievedComprador.getCpf());
            System.out.println("Email: " + retrievedComprador.getEmail());
            System.out.println("Endereço: " + retrievedComprador.getEndereco());
            System.out.println("Senha: " + retrievedComprador.getSenha());

        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void atualizarComprador() {
        System.out.print("CPF do Comprador: ");
        String cpf = scanner.nextLine();

        Comprador retrievedComprador = di.getCompradorController().getCompradorByCpf(cpf);

        if (retrievedComprador != null) {
            System.out.println("Digite os novos dados do comprador:");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            retrievedComprador.setNome(nome);

            System.out.print("Email: ");
            String email = scanner.nextLine();
            retrievedComprador.setEmail(email);

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            retrievedComprador.setEndereco(endereco);

            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            retrievedComprador.setSenha(senha);

            di.getCompradorController().updateComprador(cpf, retrievedComprador);
            System.out.println("Comprador atualizado com sucesso!");
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void removerComprador() {
        System.out.print("CPF do Comprador: ");
        String cpf = scanner.nextLine();

        Comprador retrievedComprador = di.getCompradorController().getCompradorByCpf(cpf);

        if (retrievedComprador != null) {
            di.getCompradorController().deleteComprador(cpf);
            //System.out.println("Comprador removido com sucesso!");
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void cadastrarProduto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (!Validation.nomeProdutoValido(nome)) {
            System.out.println("Nome inválido! O nome não deve ter símbolos e ter até no máximo 30 caracteres.");
            return;
        }

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

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        if(!Validation.tipoProdutoValido(tipo)){
            System.out.println("Tipo de produto inválido! O tipo não deve conter números ou símbolos e ter até no máximo 20 caracteres.");
            return;
        }

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

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        if(!Validation.marcaProdutoValido(marca)){
            System.out.println("Marca de produto inválida! A marca não deve conter símbolos e ter até no máximo 20 caracteres.");
            return;
        }

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        if(!Validation.descricaoProdutoValido(descricao)){
            System.out.println("Descrição inválida! A descrição deve ter até no máximo 100 caracteres.");
            return;
        }

        System.out.print("Cnpj da Loja: ");
        String lojaCnpj = scanner.nextLine();
        if(!Validation.CnpjValido(lojaCnpj)){
            System.out.println("Cnpj inválido!");
            return;
        }

        Produto produto = new Produto(nome, valor, tipo, quantidade, marca, descricao, lojaCnpj);
        di.getProdutoController().createProduto(produto);
    }


    private static void exibirProduto() {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = di.getProdutoController().getProdutoById(id);

        if (retrievedProduto != null) {
            System.out.println("Nome: " + retrievedProduto.getNome());
            System.out.println("Valor: " + retrievedProduto.getValor());
            System.out.println("Tipo: " + retrievedProduto.getTipo());
            System.out.println("Quantidade: " + retrievedProduto.getQuantidade());
            System.out.println("Marca: " + retrievedProduto.getMarca());
            System.out.println("Descrição: " + retrievedProduto.getDescricao());
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

            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            retrievedProduto.setNome(nome);

            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine());
            retrievedProduto.setValor(valor);

            System.out.print("Tipo: ");
            String tipo = scanner.nextLine();
            retrievedProduto.setTipo(tipo);

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            retrievedProduto.setQuantidade(quantidade);

            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            retrievedProduto.setMarca(marca);

            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
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
        System.out.print("CNPJ da Loja: ");
        String cnpj = scanner.nextLine();

        List<Produto> produtos = di.getProdutoController().getProdutosByLoja(cnpj);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado!");
        } else {
            System.out.println(produtos);
        }
    }
}




