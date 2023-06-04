package validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void emailValido() {
        String emailValido = "jose@gmail.com";
        assertTrue(Validation.emailValido(emailValido));
        String emailInvalido = "djaskldjasldj";
        assertFalse(Validation.emailValido(emailInvalido));
    }

    @Test
    void nomeProprioValido(){
        String nomeValido = "João Silva Santos";
        assertTrue(Validation.nomeProprioValido(nomeValido));
        String nomeInvalido = "Pedro3";
        assertFalse(Validation.nomeProprioValido(nomeInvalido));
    }

    @Test
    void senhaValida(){
        String senhaValida = "134bbss";
        assertTrue(Validation.senhaValida(senhaValida));
        String senhaInvalida = "dbv4";
        assertFalse(Validation.senhaValida(senhaInvalida));
        String senhaInvalida2 = "433345434dbv4gfhfkhdgdkhdgl34";
        assertFalse(Validation.senhaValida(senhaInvalida2));
    }

    @Test
    void cpfCnpjValido(){
        String cpfCnpjValido = "345.156.543-14";
        assertTrue(Validation.cpfCnpjValido(cpfCnpjValido));
        String cpfCnpjInvalida = "34@.156.543-14";
        assertFalse(Validation.cpfCnpjValido(cpfCnpjInvalida));
        String cpfCnpjInvalida2 = "34b.156.543-14";
        assertFalse(Validation.cpfCnpjValido(cpfCnpjInvalida2));
    }

    @Test
    void enderecoValido(){
        String enderecoValido = "Rua Jose Dirceu,13,Coimbra,116";
        assertTrue(Validation.enderecoValido(enderecoValido));
        String enderecoInvalido = "Rua Jose Dirceu,13,Coimbra,116,gfyeueuehghjdgjdgskhgdskhdsgskdhgsdkhdghdsgk";
        assertFalse(Validation.enderecoValido(enderecoInvalido));
        String enderecoInvalido2 = "Rua Jose Dirceu,13,Coimbra,116,gfye-e+eh¨*hjdgjdgskhgdskhdsgskdhgsdkhdghdsgk";
        assertFalse(Validation.enderecoValido(enderecoInvalido2));
    }

    @Test
    void valorProdutoValido(){
        Double valorProdutoValido = 1.0;
        assertTrue(Validation.valorProdutoValido(valorProdutoValido));
        Double  valorProdutoInvalido = -3.5;
        assertFalse(Validation.valorProdutoValido(valorProdutoInvalido));
    }

    @Test
    void  tipoProdutoValido(){
        String tipoProdutoValido = "Carne";
        assertTrue(Validation.tipoProdutoValido(tipoProdutoValido));
        String tipoProdutoInvalido = "3Carne";
        assertFalse(Validation.tipoProdutoValido(tipoProdutoInvalido));
        String tipoProdutoInvalido2 = "$Frio";
        assertFalse(Validation.tipoProdutoValido(tipoProdutoInvalido2));
    }

    @Test
    void  quantProdutoValido(){
        int quantProdutoValido = 1;
        assertTrue(Validation.quantProdutoValido(quantProdutoValido));
        int  valorProdutoInvalido = -3;
        assertFalse(Validation.quantProdutoValido(valorProdutoInvalido));
    }

    @Test
    void  marcaProdutoValido(){
        String marcaProdutoValido = "Carne";
        assertTrue(Validation.marcaProdutoValido(marcaProdutoValido));
        String marcaProdutoValido2 = "3Coracoes";
        assertTrue(Validation.marcaProdutoValido(marcaProdutoValido2));
        String marcaProdutoInvalido = "$Sea";
        assertFalse(Validation.marcaProdutoValido(marcaProdutoInvalido));
    }

    @Test
    void  descricaoProdutoValido(){
        String marcaProdutoValido = "Uma descrição Breve";
        assertFalse(Validation.marcaProdutoValido(marcaProdutoValido));
        String marcaProdutoInvalido = "maahsldhsjgasfsfgsljfsçkjfkfgfhgfhjgfhgfkhfkhfkfhgdkdhfkjhgkhgdldfjfhdflkfjfhfdkjglhflfljghlhglfdhfdlfdhjgljglhgljjllhfghjgfdhglfhgkgfkhgfkhfkhgfkfgfkhgkfgkfhgfkgdldudyrurghrgxhfl";
        assertFalse(Validation.marcaProdutoValido(marcaProdutoInvalido));
    }

    @Test
    void  nomeLojaValido(){

    }

    @Test
    void  CnpjValido(){

    }
    @Test
    void nomeProdutoValido(){

    }
}