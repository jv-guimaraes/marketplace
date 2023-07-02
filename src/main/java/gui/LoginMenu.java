package gui;

import entities.Comprador;
import entities.Loja;
import services.CompradorService;
import services.LojaService;
import validation.Validation;

import static gui.GuiUtil.*;

public class LoginMenu {
    private GuiUtil util;
    private AdminMenu adminMenu;
    private LojaMenu lojaMenu;
    private CompradorMenu compradorMenu;
    private LojaService lojaService;
    private CompradorService compradorService;

    public LoginMenu(GuiUtil util, AdminMenu adminMenu, LojaMenu lojaMenu, CompradorMenu compradorMenu, LojaService lojaService, CompradorService compradorService) {
        this.util = util;
        this.adminMenu = adminMenu;
        this.lojaMenu = lojaMenu;
        this.compradorMenu = compradorMenu;
        this.lojaService = lojaService;
        this.compradorService = compradorService;
    }

    public void run() {
        boolean running = true;
        while (running) {
            util.print("Bem vindo ao Marketplace!");
            util.print("Selecione o tipo de usuário:");
            util.print("1 - Comprador");
            util.print("2 - Loja");
            util.print("3 - Admninistrador");
            switch (util.getNumero()) {
                case 1 -> compradorMenu();
                case 2 -> lojaMenu();
                case 3 -> adminMenu.run();
                case 4 -> running = false;
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

    public void compradorLogin() {
        String email = util.receberString("Email", Validation::emailValido);
        System.out.println(email);
        while (!compradorService.emailCadastrado(email)) {
            System.out.println(email);
            util.print("Email não encontrado.");
            email = util.receberString("Email", Validation::emailValido);
        }

        String senha = util.receberString("Senha", Validation::senhaValida);
        while (!compradorService.loginValido(email, senha)) {
            util.print("Senha incorreta.");
            senha = util.receberString("Senha", Validation::senhaValida);
        }

        compradorMenu.run(email);
    }

    public void compradorSignup() {
        String nome = util.receberString("Nome", Validation::nomeProprioValido);
        String email = util.receberString("E-mail", Validation::emailValido);
        String senha = util.receberString("Senha", Validation::senhaValida);
        String cpf = util.receberString("CPF", Validation::cpfCnpjValido);
        String endereco = util.receberString("Endereço", Validation::enderecoValido);
        Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);
        compradorService.createComprador(comprador);
    }


    public void lojaMenu() {
        util.print("LOJA");
        util.print("1 - Fazer login");
        util.print("2 - Criar conta");
        switch (util.getNumero()) {
            case 1 -> lojaLogin();
            case 2 -> lojaSignup();
            default -> util.print("Escolha inválida!");
        }
    }

    public void lojaLogin() {
        String email = util.receberString("Email", Validation::emailValido);
        while (!lojaService.emailCadastrado(email)) {
            util.print("Email não encontrado.");
            email = util.receberString("Email", Validation::emailValido);
        }

        String senha = util.receberString("Senha", Validation::senhaValida);
        while (!lojaService.loginValido(email, senha)) {
            util.print("Senha incorreta.");
            senha = util.receberString("Senha", Validation::senhaValida);
        }

        this.lojaMenu.run(email);
    }

    public void lojaSignup() {
        String nome = util.receberString("Nome", Validation::nomeProprioValido);
        String email = util.receberString("E-mail", Validation::emailValido);
        String senha = util.receberString("Senha", Validation::senhaValida);
        String cnpj = util.receberString("CPF", Validation::cpfCnpjValido);
        String endereco = util.receberString("Endereço", Validation::enderecoValido);
        Loja loja = new Loja(nome, email, senha, cnpj, endereco);
        lojaService.createLoja(loja);
    }

}
