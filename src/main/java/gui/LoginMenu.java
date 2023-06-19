package gui;

import entities.Comprador;
import entities.Loja;
import validation.Validation;

import static gui.GuiUtil.*;

public class LoginMenu {
    public static void run() {
        while (true) {
            System.out.println("Bem vindo ao Marketplace!");
            System.out.println("Selecione o tipo de usuário:");
            System.out.println("1 - Comprador");
            System.out.println("2 - Loja");
            System.out.println("3 - Admninistrador");
            switch (getNumero()) {
                case 1 -> compradorMenu();
                case 2 -> lojaMenu();
                case 3 -> AdminMenu.run();
                default -> System.out.println("Escolha inválida!");
            }
        }
    }

    public static void compradorMenu() {
        System.out.println("COMPRADOR");
        System.out.println("1 - Fazer login");
        System.out.println("2 - Criar conta");
        switch (getNumero()) {
            case 1 -> compradorLogin();
            case 2 -> compradorSignup();
            default -> System.out.println("Escolha inválida!");
        }
    }

    private static void compradorLogin() {
        String email = receberString("Email", Validation::emailValido);
        while (!compradorService.emailCadastrado(email)) {
            System.out.println("Email não encontrado.");
            email = receberString("Email", Validation::emailValido);
        }

        String senha = receberString("Senha", Validation::senhaValida);
        while (!compradorService.loginValido(email, senha)) {
            System.out.println("Senha incorreta.");
            senha = receberString("Senha", Validation::senhaValida);
        }

        CompradorMenu.run(email);
    }

    private static void compradorSignup() {
        String nome = receberString("Nome", Validation::nomeProprioValido);
        String email = receberString("E-mail", Validation::emailValido);
        String senha = receberString("Senha", Validation::senhaValida);
        String cpf = receberString("CPF", Validation::cpfCnpjValido);
        String endereco = receberString("Endereço", Validation::enderecoValido);
        Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);
        compradorService.createComprador(comprador);
    }


    private static void lojaMenu() {
        System.out.println("LOJA");
        System.out.println("1 - Fazer login");
        System.out.println("2 - Criar conta");
        switch (getNumero()) {
            case 1 -> lojaLogin();
            case 2 -> lojaSignup();
            default -> System.out.println("Escolha inválida!");
        }
    }

    private static void lojaLogin() {
        String email = receberString("Email", Validation::emailValido);
        while (!lojaService.emailCadastrado(email)) {
            System.out.println("Email não encontrado.");
            email = receberString("Email", Validation::emailValido);
        }

        String senha = receberString("Senha", Validation::senhaValida);
        while (!lojaService.loginValido(email, senha)) {
            System.out.println("Senha incorreta.");
            senha = receberString("Senha", Validation::senhaValida);
        }

        LojaMenu.run(email);
    }

    private static void lojaSignup() {
        String nome = receberString("Nome", Validation::nomeProprioValido);
        String email = receberString("E-mail", Validation::emailValido);
        String senha = receberString("Senha", Validation::senhaValida);
        String cnpj = receberString("CPF", Validation::cpfCnpjValido);
        String endereco = receberString("Endereço", Validation::enderecoValido);
        Loja loja = new Loja(nome, email, senha, cnpj, endereco);
        lojaService.createLoja(loja);
    }

}
