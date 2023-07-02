package gui;

import entities.Comprador;
import entities.Loja;
import validation.Validation;

import static gui.GuiUtil.*;

public class LoginMenu {
    private GuiUtil util;
    private AdminMenu adminMenu;
    private LojaMenu lojaMenu;
    private CompradorMenu compradorMenu;

    public LoginMenu(GuiUtil util, AdminMenu adminMenu, LojaMenu lojaMenu, CompradorMenu compradorMenu) {
        this.util = util;
        this.adminMenu = adminMenu;
        this.lojaMenu = lojaMenu;
        this.compradorMenu = compradorMenu;
    }

    public void run() {
        while (true) {
            util.print("Bem vindo ao Marketplace!");
            util.print("Selecione o tipo de usuário:");
            util.print("1 - Comprador");
            util.print("2 - Loja");
            util.print("3 - Admninistrador");
            switch (util.getNumero()) {
                case 1 -> compradorMenu();
                case 2 -> lojaMenu();
                case 3 -> adminMenu.run();
                default -> util.print("Escolha inválida!");
            }
        }
    }

    public void compradorMenu() {
        util.print("COMPRADOR");
        util.print("1 - Fazer login");
        util.print("2 - Criar conta");
        switch (util.getNumero()) {
            case 1 -> compradorLogin();
            case 2 -> compradorSignup();
            default -> util.print("Escolha inválida!");
        }
    }

    private void compradorLogin() {
        String email = receberString("Email", Validation::emailValido);
        while (!compradorService.emailCadastrado(email)) {
            util.print("Email não encontrado.");
            email = receberString("Email", Validation::emailValido);
        }

        String senha = receberString("Senha", Validation::senhaValida);
        while (!compradorService.loginValido(email, senha)) {
            util.print("Senha incorreta.");
            senha = receberString("Senha", Validation::senhaValida);
        }

        compradorMenu.run(email);
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


    private void lojaMenu() {
        util.print("LOJA");
        util.print("1 - Fazer login");
        util.print("2 - Criar conta");
        switch (util.getNumero()) {
            case 1 -> lojaLogin();
            case 2 -> lojaSignup();
            default -> util.print("Escolha inválida!");
        }
    }

    private void lojaLogin() {
        String email = receberString("Email", Validation::emailValido);
        while (!lojaService.emailCadastrado(email)) {
            util.print("Email não encontrado.");
            email = receberString("Email", Validation::emailValido);
        }

        String senha = receberString("Senha", Validation::senhaValida);
        while (!lojaService.loginValido(email, senha)) {
            util.print("Senha incorreta.");
            senha = receberString("Senha", Validation::senhaValida);
        }

        this.lojaMenu.run(email);
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
