package validation;

import java.util.regex.Pattern;

public class Validation {
    //EMAIL_REGEX: input para aceitar todos os simbolos e rejeitar input sem @
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    //CPF_CNPJ_REGEX: input para aceitar números e símbolos e rejeitar letras
    private static final String CPF_CNPJ_REGEX = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]" + "?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";
    //NOME_PRODUTO_REGEX : input para aceitar números e letras maiusculas e minusculas rejeita caracteres símbolos
    private static final String NOME_PRODUTO_REGEX = "^[a-zA-Z0-9 ]+$";
    //TIPO_REGEX : aceita letras maiusculas e minusculas rejeita números e símbolos
    private static final String TIPO_REGEX = "^[a-zA-Z]*$";
    //QUANTIDADE_REGEX = aceita números rejeita letras e símbolos
    private static final String QUANTIDADE_REGEX = "^(?!0\\.0*$)(?!0+$)\\d*(\\.\\d+)?$";
    //MARCA_REGEX : aceita letras e números rejeita símbolos
    private static final String MARCA_REGEX = "([0-9]*[a-zA-Z]+)|([a-zA-Z]+[0-9]*)";
    //DESCRICAO_REGEX = aceita letras,simbolos,numeros (todos os caracteres)
    private static final String DESCRICAO_REGEX = ".*";

    private static boolean isAlphabetic(String string) {
        if (string == null) return true;
        return string.chars().noneMatch(Character::isDigit);
    }

    protected static boolean patternMatches(String string, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(string).matches();
    }

    //COMPRADOR VALIDAÇÃO
    public static boolean idValido(String input) {
        try {
            int id = Integer.parseInt(input);
            return id >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean nomeProprioValido(String nome) {
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
        return senha.length() >= 6 && senha.length() <= 20;
    }

    public static boolean cpfCnpjValido(String cpf) {
        if (cpf == null || cpf.isBlank()) return false;
        return patternMatches(cpf, CPF_CNPJ_REGEX);
    }

    public static boolean enderecoValido(String endereco) {
        if (endereco == null || endereco.isBlank()) return false;
        return endereco.length() <= 50;
    }

    //PRODUTO VALIDAÇÃO
    public static boolean nomeProdutoValido(String nome) {
        if (nome == null || nome.isBlank()) return false;
        if (nome.length() > 20) return false;
        return patternMatches(nome, NOME_PRODUTO_REGEX);
    }

    public static boolean valorProdutoValido(String valor) {
        try {
            var valorInt = Double.parseDouble(valor);
            if (valorInt >= 0) return true;
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public static boolean tipoProdutoValido(String tipo) {
        if (tipo == null || tipo.isBlank()) return false;
        if (tipo.length() > 50) return false;
        return patternMatches(tipo, TIPO_REGEX);
    }

    public static boolean quantProdutoValido(Integer quantidade) {
        if (quantidade <= 0 || quantidade == null) {
            return false;
        }
        return patternMatches(Double.toString(quantidade), QUANTIDADE_REGEX);
    }

    public static boolean marcaProdutoValido(String marca) {
        if (marca == null || marca.isBlank()) return false;
        if (marca.length() > 20) return false;
        return patternMatches(marca, MARCA_REGEX);
    }

    public static boolean descricaoProdutoValido(String descricao) {
        if (descricao == null || descricao.isBlank()) return false;
        if (descricao.length() > 100) return false;
        return patternMatches(descricao, DESCRICAO_REGEX);
    }

    //LOJA VALIDAÇÃO
    public static boolean nomeLojaValido(String nome) {
        if (nome == null || nome.isBlank()) return false;
        if (nome.length() > 15) return false;
        return patternMatches(nome, NOME_PRODUTO_REGEX);
    }

    public static boolean CnpjValido(String cnpj) {
        if (cnpj == null || cnpj.isBlank()) return false;
        if (cnpj.length() > 18) return false;
        return patternMatches(cnpj, CPF_CNPJ_REGEX);
    }
}



