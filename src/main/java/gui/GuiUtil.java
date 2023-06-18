package gui;

import services.CompradorService;
import services.LojaService;
import services.ProdutoService;

import java.util.Scanner;
import java.util.function.Predicate;

public class GuiUtil {
    public static final LojaService lojaService = new LojaService();
    public static final CompradorService compradorService = new CompradorService();
    public static final ProdutoService produtoService = new ProdutoService();
    public static Scanner scanner = new Scanner(System.in);

    public static int getNumero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String receberString(String tipo, Predicate<String> validator) {
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
