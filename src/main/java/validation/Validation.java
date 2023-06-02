package validation;

import java.util.regex.Pattern;

public class Validation {
    private static String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static String CPF_CNPJ_REGEX = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]" + "?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";

    private static String NOME_PRODUTO_REGEX = "([0-9]*[a-zA-Z]+)|([a-zA-Z]+[0-9]*)";

    private static String TIPO_REGEX = "^[a-zA-Z]*$";

    private static String VALOR_REGEX = "^(?!0\\.0*$)(?!0+$)\\d*(\\.\\d+)?$";

    private static String QUANTIDADE_REGEX = "^(?!0\\.0*$)(?!0+$)\\d*(\\.\\d+)?$";

    private static String MARCA_REGEX = "([0-9]*[a-zA-Z]+)|([a-zA-Z]+[0-9]*)";

    private static String DESCRICAO_REGEX = ".*";
    private static boolean isAlphabetic(String string) {
        if (string == null) return true;
        return string.chars().noneMatch(Character::isDigit);
    }

    public static boolean patternMatches(String string, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(string).matches();
    }

    //COMPRADOR VALIDAÇÃO
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

    //PRODUTO VALIDAÇÃO

    public static boolean nomeProdutoValido(String nome) {
        if (nome == null || nome.isBlank()) return false;
        if (nome.length() > 20) return false;
        return patternMatches(nome,NOME_PRODUTO_REGEX);
    }

    public static boolean valorProdutoValido(Double valor) {
       if(valor <= 0 || valor == null) { return false;}
       return patternMatches(Double.toString(valor),VALOR_REGEX);
    }

    public static boolean tipoProdutoValido(String tipo){
        if (tipo == null || tipo.isBlank()) return false;
        if (tipo.length() > 50) return false;
        return patternMatches(tipo,TIPO_REGEX);
    }

    public static boolean quantProdutoValido(Integer quantidade) {
        if(quantidade <= 0 || quantidade == null) { return false;}
        return patternMatches(Double.toString(quantidade),QUANTIDADE_REGEX);
        }

    public static boolean marcaProdutoValido(String marca){
            if (marca == null || marca.isBlank()) return false;
            if (marca.length() > 20) return false;
            return patternMatches(marca,MARCA_REGEX);
        }


    public static boolean descricaoProdutoValido(String descricao){
            if (descricao == null || descricao.isBlank()) return false;
            if (descricao.length() > 100) return false;
            return patternMatches(descricao,DESCRICAO_REGEX);
        }

}



