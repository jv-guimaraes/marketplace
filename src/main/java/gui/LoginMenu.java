package gui;

import entities.Comprador;
import entities.Loja;
import validation.Validation;

import static gui.GuiUtil.*;

public class LoginMenu {
    public static void run() {
        while (true) {
            print("Bem vindo ao Marketplace!");
            print("Selecione o tipo de usuário:");
            print("1 - Comprador");
            print("2 - Loja");
            print("3 - Admninistrador");
            switch (getNumero()) {
                case 1 -> compradorMenu();
                case 2 -> lojaMenu();
                case 3 -> AdminMenu.run();
                default -> print("Escolha inválida!");
            }
        }
    }

    public static void compradorMenu() {
        print("COMPRADOR");
        print("1 - Fazer login");
        print("2 - Criar conta");
        switch (getNumero()) {
            case 1 -> compradorLogin();
            case 2 -> compradorSignup();
            default -> print("Escolha inválida!");
        }
    }

    private static void compradorLogin() {
        String email = receberString("Email", Validation::emailValido);
        while (!compradorService.emailCadastrado(email)) {
            print("Email não encontrado.");
            email = receberString("Email", Validation::emailValido);
        }

        String senha = receberString("Senha", Validation::senhaValida);
        while (!compradorService.loginValido(email, senha)) {
            print("Senha incorreta.");
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
        print("LOJA");
        print("1 - Fazer login");
        print("2 - Criar conta");
        switch (getNumero()) {
            case 1 -> lojaLogin();
            case 2 -> lojaSignup();
            default -> print("Escolha inválida!");
        }
    }

    private static void lojaLogin() {
        String email = receberString("Email", Validation::emailValido);
        while (!lojaService.emailCadastrado(email)) {
            print("Email não encontrado.");
            email = receberString("Email", Validation::emailValido);
        }

        String senha = receberString("Senha", Validation::senhaValida);
        while (!lojaService.loginValido(email, senha)) {
            print("Senha incorreta.");
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
