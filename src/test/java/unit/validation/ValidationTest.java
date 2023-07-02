package unit.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validation.Validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationTest {
    @Test
    void idValido(){
        String idValido = "12";
        Assertions.assertTrue(Validation.idValido(idValido));
        String idInvalido = "as231";
        Assertions.assertFalse(Validation.idValido(idInvalido));
    }

    @Test
    void emailValido() {
        String emailValido = "jose@gmail.com";
        Assertions.assertTrue(Validation.emailValido(emailValido));
        String emailInvalido = "djaskldjasldj";
        assertFalse(Validation.emailValido(emailInvalido));
    }

    @Test
    void nomeProprioValido() {
        String nomeValido = "João Silva Santos";
        assertTrue(Validation.nomeProprioValido(nomeValido));
        String nomeInvalido = "Pedro3";
        assertFalse(Validation.nomeProprioValido(nomeInvalido));
    }

    @Test
    void senhaValida() {
        String senhaValida = "134bbss";
        assertTrue(Validation.senhaValida(senhaValida));
        String senhaInvalida = "dbv4";
        assertFalse(Validation.senhaValida(senhaInvalida));
        String senhaInvalida2 = "433345434dbv4gfhfkhdgdkhdgl34";
        assertFalse(Validation.senhaValida(senhaInvalida2));
    }

    @Test
    void CnpjValido(){
        String cnpjValido = "93.763.071/0001-27";
        assertTrue(Validation.CnpjValido(cnpjValido));

    }

    @Test
    void cpfCnpjValido() {
        String cpfValido = "345.156.543-14";
        assertTrue(Validation.cpfCnpjValido(cpfValido));
        String cpfCnpjInvalida = "34@.156.543-14";
        assertFalse(Validation.cpfCnpjValido(cpfCnpjInvalida));
        String cpfCnpjInvalida2 = "34b.156.543-14";
        assertFalse(Validation.cpfCnpjValido(cpfCnpjInvalida2));
        String cnpjValido = "93.763.071/0001-27";
        assertTrue(Validation.cpfCnpjValido(cnpjValido));
        String cpfCnpjInvalido = "93.763.071001-2-7";
        assertFalse(Validation.cpfCnpjValido(cpfCnpjInvalido));
    }

    @Test
    void enderecoValido() {
        String enderecoValido = "Rua Jose Dirceu,13,Coimbra,116";
        assertTrue(Validation.enderecoValido(enderecoValido));
        String enderecoInvalido = "Rua Jose Dirceu,13,Coimbra,116,gfyeueuehghjdgjdgskhgdskhdsgskdhgsdkhdghdsgk";
        assertFalse(Validation.enderecoValido(enderecoInvalido));
        String enderecoInvalido2 = "Rua Jose Dirceu,13,Coimbra,116,gfye-e+eh¨*hjdgjdgskhgdskhdsgskdhgsdkhdghdsgk";
        assertFalse(Validation.enderecoValido(enderecoInvalido2));
    }

    @Test
    void valorProdutoValido() {
        String valorProdutoValido = "1.0";
        assertTrue(Validation.valorProdutoValido(valorProdutoValido));
        String valorProdutoInvalido = "-3.5";
        assertFalse(Validation.valorProdutoValido(valorProdutoInvalido));
    }

    @Test
    void tipoProdutoValido() {
        String tipoProdutoValido = "Carne";
        assertTrue(Validation.tipoProdutoValido(tipoProdutoValido));
        String tipoProdutoInvalido = "3Carne";
        assertFalse(Validation.tipoProdutoValido(tipoProdutoInvalido));
        String tipoProdutoInvalido2 = "$Frio";
        assertFalse(Validation.tipoProdutoValido(tipoProdutoInvalido2));
    }

    @Test
    void quantProdutoValido() {
        int quantProdutoValido = 1;
        assertTrue(Validation.quantProdutoValido(quantProdutoValido));
        int valorProdutoInvalido = -3;
        assertFalse(Validation.quantProdutoValido(valorProdutoInvalido));
    }

    @Test
    void marcaProdutoValido() {
        String marcaProdutoValido = "Carne";
        assertTrue(Validation.marcaProdutoValido(marcaProdutoValido));
        String marcaProdutoValido2 = "3Coracoes";
        assertTrue(Validation.marcaProdutoValido(marcaProdutoValido2));
        String marcaProdutoInvalido = "$Sea";
        assertFalse(Validation.marcaProdutoValido(marcaProdutoInvalido));
    }

    @Test
    void descricaoProdutoValido() {
        String descricaoProdutoValido = "UmadescricaoBreve";
        assertTrue(Validation.descricaoProdutoValido(descricaoProdutoValido));
        String descricaoProdutoInvalido = "maahsldhsjgasfsfgsljfsçkjfkfgfhgfhjgfhgfkhfkhfkfhgdkdhfkjhgkhgdldfjfhdflkfjfhfdkjglhflfljghlhglfdhfdlfdhjgljglhgljjllhfghjgfdhglfhgkgfkhgfkhfkhgfkfgfkhgkfgkfhgfkgdldudyrurghrgxhfl";
        assertFalse(Validation.descricaoProdutoValido(descricaoProdutoInvalido));
    }

    @Test
    void nomeLojaValido() {
        String nomeLojaValido = "americanas";
        assertTrue(Validation.nomeLojaValido(nomeLojaValido));
        String nomeLojaInvalido = "Americanas1#Company";
        assertFalse(Validation.nomeLojaValido(nomeLojaInvalido));
    }

    @Test
    void nomeProdutoValido() {
        String nomeProdutoValido = "IPHONE";
        assertTrue(Validation.nomeProdutoValido(nomeProdutoValido));
        String nomeProdutoInvalido = "I#BOP";
        assertFalse(Validation.nomeProdutoValido(nomeProdutoInvalido));
        String nomeProdutoBlank = "";
        assertFalse(Validation.nomeProdutoValido(nomeProdutoBlank));
    }

}