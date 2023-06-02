import controllers.CompradorController;
import controllers.LojaController;
import controllers.ProdutoController;
import services.CompradorService;
import services.LojaService;
import services.ProdutoService;
import entities.Comprador;
import entities.Loja;
import entities.Produto;

import java.util.Scanner;

public class MarketplaceApplication {
    private static final LojaService lojaService = new LojaService();
    private static final CompradorService compradorService = new CompradorService();
    private static final ProdutoService produtoService = new ProdutoService();
    private static final LojaController lojaController = new LojaController(lojaService);

    private static final CompradorController compradorController = new CompradorController(compradorService);

    private static final ProdutoController produtoController = new ProdutoController(produtoService);


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //caso seja a opção de cadastrar
        //cadastrarLoja(scanner);

        //caso a opção seja ver a loja
        //exibirLoja(scanner);

        //opção atualizar loja
        //updateLoja(scanner);

        //opção de deletar a loja
        //removerLoja(scanner);

        //opção de buscar loja
        //função

        //System.out.println(lojaController.getAllLojas());

        //----------------------------------------------------------------------------------------------------------------

        //opção de cadastrar comprador
        //cadastrarComprador(scanner);

        //opção de exibir dados do comprador - buscra por cpf:
        //exibirComprador(scanner);

        //atualizar comprador
        //updateComprador(scanner);

        //remover comprador
        //removerComprador(scanner);


        //System.out.println(compradorController.getAllCompradores());

        //-------------------------------------------------------------------------------------------------------------------

        //cadatsrar - criar produto
        //cadastrarProduto(scanner);

        //Buscar produto por id
        //exibirProduto(scanner);

        //atualizar produto
        //atualizarProduto(scanner);

        //deletar produto
        //removerProduto(scanner);

        //produtoController.deleteProduto(1L);

        //System.out.println(produtoController.getAllProdutos());
    }

    //funções do crud para loja


    private static void cadastrarLoja(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Loja loja = new Loja(nome, email, senha, cnpj, endereco);

        lojaController.createLoja(loja);
    }


    private static void exibirLoja(Scanner scanner) {
        System.out.print("CNPJ da Loja: ");
        String cnpj = scanner.nextLine();

        Loja retrievedLoja = lojaController.getLojaByCnpj(cnpj);

        System.out.println(retrievedLoja);

    }

    private static void updateLoja(Scanner scanner) {
        System.out.print("Informe o CNPJ da Loja: ");
        String cnpj = scanner.nextLine();

        Loja retrievedLoja = lojaController.getLojaByCnpj(cnpj);

        if (retrievedLoja != null) {
            System.out.println("Digite os novos dados da loja:");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            retrievedLoja.setNome(nome);

            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            retrievedLoja.setEmail(email);

            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            retrievedLoja.setSenha(senha);

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            retrievedLoja.setEndereco(endereco);

            lojaController.updateLoja(cnpj, retrievedLoja);
            System.out.println(retrievedLoja);

            System.out.println("Loja atualizada com sucesso!");

        } else {
            System.out.println("Loja não encontrada.");
        }
    }

    private static void removerLoja(Scanner scanner) {
        System.out.print("CNPJ da Loja: ");
        String cnpj = scanner.nextLine();

        Loja retrievedLoja = lojaController.getLojaByCnpj(cnpj);

        lojaController.deleteLoja(cnpj);
    }


    //funções do crud para comprador
    private static void cadastrarComprador(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("cpf: ");
        String cpf = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);

        compradorController.createComprador(comprador);
    }

    private static void exibirComprador(Scanner scanner) {
        System.out.print("CPF do Comprador: ");
        String cpf = scanner.nextLine();

        Comprador retrievedComprador = compradorController.getCompradorByCpf(cpf);

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

    private static void updateComprador(Scanner scanner) {
        System.out.print("CPF do Comprador: ");
        String cpf = scanner.nextLine();

        Comprador retrievedComprador = compradorController.getCompradorByCpf(cpf);

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

            compradorController.updateComprador(cpf , retrievedComprador);
            System.out.println("Comprador atualizado com sucesso!");
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    private static void removerComprador(Scanner scanner) {
        System.out.print("CPF do Comprador: ");
        String cpf = scanner.nextLine();

        Comprador retrievedComprador = compradorController.getCompradorByCpf(cpf);

        if (retrievedComprador != null) {
            compradorController.deleteComprador(cpf);
            //System.out.println("Comprador removido com sucesso!");
        } else {
            System.out.println("Comprador não encontrado.");
        }
    }

    //funções do crud para produto

    private static void cadastrarProduto(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Produto produto = new Produto(nome, valor, tipo, quantidade, marca, descricao);
        produtoController.createProduto(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }


    private static void exibirProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoController.getProdutoById(id);

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


    private static void atualizarProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoController.getProdutoById(id);

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

            produtoController.updateProduto(id, retrievedProduto);

            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void removerProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        long id = Long.parseLong(scanner.nextLine());

        Produto retrievedProduto = produtoController.getProdutoById(id);

        if (retrievedProduto != null) {
            produtoController.deleteProduto(id);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}




