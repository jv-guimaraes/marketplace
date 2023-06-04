package unit.entities;

import entities.Loja;
import entities.Produto;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoEntityTest {
    Produto produto = new Produto();
    Produto produtoFilled = new Produto("IPHONE XR", 2290.35, "CELULAR",1,"APPLE", "O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais", "93.763.071/0001-27");

    @Test
    public void produtoFromObject(){
        JSONObject json = new JSONObject();
        produtoFilled.setId(1L);
        json.put("id", produtoFilled.getId());
        json.put("nome", produtoFilled.getNome());
        json.put("valor", produtoFilled.getValor());
        json.put("tipo", produtoFilled.getTipo());
        json.put("quantidade", produtoFilled.getQuantidade());
        json.put("marca", produtoFilled.getMarca());
        json.put("descricao", produtoFilled.getDescricao());
        json.put("lojaCnpj", produtoFilled.getLojaCnpj());
        Produto produtoFjson = new Produto(json);
        produtoFjson.setId(produtoFilled.getId());
        produtoFjson.equals(produtoFilled);
    }
    @Test
    public void getNome(){
        String nome = "IPHONE XR";
        assertNull(produto.getNome());
        produto.setNome(nome);
        assertEquals(produto.getNome(), nome);
    }
    @Test
    public void getValor(){
        Double valor = 2290.35;
        assertNull(produto.getValor());
        produto.setValor(valor);
        assertEquals(produto.getValor(), valor);
    }
    @Test
    public void getTipo(){
        String tipo = "CELULAR";
        assertNull(produto.getTipo());
        produto.setTipo(tipo);
        assertEquals(produto.getTipo(), tipo);
    }
    @Test
    public void getQuantidade(){
        Integer quantidade = 1;
        assertNull(produto.getQuantidade());
        produto.setQuantidade(quantidade);
        assertEquals(produto.getQuantidade(), quantidade);
    }
    @Test
    public void getMarca(){
        String marca = "APPLE";
        assertNull(produto.getMarca());
        produto.setMarca(marca);
        assertEquals(produto.getMarca(), marca);
    }
    @Test
    public void getDescricao(){
        String descricao = "O iPhone XR tem tela Liquid Retina de 6,1 polegadas";
        assertNull(produto.getDescricao());
        produto.setDescricao(descricao);
        assertEquals(produto.getDescricao(), descricao);
    }
    @Test
    public void getLojaCnpj(){
        String cnpj = "93.763.071/0001-27";
        assertNull(produto.getLojaCnpj());
        produto.setLojaCnpj(cnpj);
        assertEquals(produto.getLojaCnpj(), cnpj);
    }
    @Test
    public void testHashCode(){
        Long id = produtoFilled.getId();
        assertEquals(produtoFilled.hashCode(), Objects.hash(id));
    }
    @Test
    public void testEquals(){
        assertFalse(produtoFilled.equals(1));
    }
    @Test
    public void testEmptyToString(){
        assertEquals(produto.toString(), "\n" +
                "{\n" +
                "\"id\":null,\n" +
                "\"nome\":null,\n" +
                "\"valor\":null,\n" +
                "\"tipo\":null,\n" +
                "\"quantidade\":null,\n" +
                "\"marca\":null,\n" +
                "\"descricao\":null,\n" +
                "}\n");
    }

    @Test
    public void testFilledToString(){
        assertEquals(produtoFilled.toString(), "\n" +
                "{\n" +
                "\"id\":null,\n" +
                "\"nome\":IPHONE XR,\n" +
                "\"valor\":2290.35,\n" +
                "\"tipo\":CELULAR,\n" +
                "\"quantidade\":1,\n" +
                "\"marca\":APPLE,\n" +
                "\"descricao\":O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais,\n" +
                "}\n");

    }
}
