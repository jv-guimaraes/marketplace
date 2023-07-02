package gui;

import services.CompradorService;
import services.LojaService;
import services.ProdutoService;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import entities.Comprador;
import entities.Loja;
import entities.Produto;

public class GuiUtil {
    public static final ProdutoService produtoService = new ProdutoService();
    public static Scanner scanner = new Scanner(System.in);

    public int getNumero() {
        try {
            return 1; //Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void print(String text){
        System.out.println(text);
    }

    public void print(Loja loja){
        System.out.println(loja);
    }

    public void print(Comprador comprador){
        System.out.println(comprador);
    }

    public void print(Produto produto){
        System.out.println(produto);
    }

    public void print(List<Produto> produtos){
        System.out.println(produtos);
    }

    public void print(){
        System.out.println();
    }

    public String receberString(String tipo, Predicate<String> validator) {
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
