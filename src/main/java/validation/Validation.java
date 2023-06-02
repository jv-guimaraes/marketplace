package validation;

import java.util.regex.Pattern;

public class Validation {
    private static String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static String CPF_CNPJ_REGEX = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]" + "?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";

    private static boolean isAlphabetic(String string) {
        if (string == null) return true;
        return string.chars().noneMatch(Character::isDigit);
    }

    public static boolean patternMatches(String string, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(string).matches();
    }

    public static boolean nomePropioValido(String nome) {
        if (nome == null || nome.isBlank()) return false;
        if (nome.length() > 50) return false;
        return isAlphabetic(nome);
    }

    public static boolean emailValido(String email) {
        if (email == null || email.isBlank()) return false;
        return patternMatches(email, EMAIL_REGEX);
    }

    public static boolean senhaValida(String senha) {
        if (senha == null || senha.isBlank()) return false;
        if (senha.length() < 6 || senha.length() > 20) return false;
        return true;
    }

    public static boolean cpfCnpjValido(String cpf) {
        if (cpf == null || cpf.isBlank()) return false;
        return patternMatches(cpf, CPF_CNPJ_REGEX);
    }

    public static boolean enderecoValido(String endereco) {
        if (endereco == null || endereco.isBlank()) return false;
        if (endereco.length() > 50) return false;
        return true;
    }
}
