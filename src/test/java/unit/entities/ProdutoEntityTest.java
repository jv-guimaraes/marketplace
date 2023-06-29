package unit.entities;

import entities.Produto;
import entities.Loja;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoEntityTest {
    Produto produto = new Produto();
    Produto produtoFilled = new Produto("IPHONE XR", 2290.35, "CELULAR", 1, "APPLE", "O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais", "93.763.071/0001-27");

    @Test
    public void produtoFromObject() {
        JSONObject json = new JSONObject();
        produtoFilled.setId(1L);
        produtoFilled.setNotas(
                new ArrayList<Integer>(Arrays.asList(1,2,3))
        );
        json.put("id", produtoFilled.getId());
        json.put("nome", produtoFilled.getNome());
        json.put("valor", produtoFilled.getValor());
        json.put("tipo", produtoFilled.getTipo());
        json.put("quantidade", produtoFilled.getQuantidade());
        json.put("marca", produtoFilled.getMarca());
        json.put("notas", produtoFilled.getNotas());
        json.put("descricao", produtoFilled.getDescricao());
        json.put("lojaCnpj", produtoFilled.getLojaCnpj());
        Produto produtoFjson = new Produto(json);
        produtoFjson.setId(produtoFilled.getId());
        assertTrue(produtoFjson.equals(produtoFilled));
    }

    @Test
    public void getNome() {
        String nome = "IPHONE XR";
        assertNull(produto.getNome());
        produto.setNome(nome);
        assertEquals(produto.getNome(), nome);
    }

    @Test
    public void getNotas() {
        new ArrayList<Produto>(Arrays.asList(produto));
        List<Integer> notas = new ArrayList<Integer>(Arrays.asList(1,2,3));
        assertNotNull(produto.getNotas());
        assertTrue(produto.getNotas().isEmpty());
        produto.setNotas(notas);
        assertEquals(produto.getNotas(), notas);
    }
    @Test
    public void getValor() {
        Double valor = 2290.35;
        assertNull(produto.getValor());
        produto.setValor(valor);
        assertEquals(produto.getValor(), valor);
    }

    @Test
    public void getTipo() {
        String tipo = "CELULAR";
        assertNull(produto.getTipo());
        produto.setTipo(tipo);
        assertEquals(produto.getTipo(), tipo);
    }

    @Test
    public void getQuantidade() {
        Integer quantidade = 1;
        assertNull(produto.getQuantidade());
        produto.setQuantidade(quantidade);
        assertEquals(produto.getQuantidade(), quantidade);
    }

    @Test
    public void getMarca() {
        String marca = "APPLE";
        assertNull(produto.getMarca());
        produto.setMarca(marca);
        assertEquals(produto.getMarca(), marca);
    }

    @Test
    public void getDescricao() {
        String descricao = "O iPhone XR tem tela Liquid Retina de 6,1 polegadas";
        assertNull(produto.getDescricao());
        produto.setDescricao(descricao);
        assertEquals(produto.getDescricao(), descricao);
    }

    @Test
    public void getLojaCnpj() {
        String cnpj = "93.763.071/0001-27";
        assertNull(produto.getLojaCnpj());
        produto.setLojaCnpj(cnpj);
        assertEquals(produto.getLojaCnpj(), cnpj);
    }

    @Test
    public void addNota(){
        Integer nota = 2;
        List<Integer> notas = new ArrayList<Integer>(Arrays.asList());
        notas.add(nota);
        assertNotNull(produto.getNotas());
        assertTrue(produto.getNotas().isEmpty());
        produto.addNotaProduto(nota);
        assertEquals(produto.getNotas(), notas);

    }
    @Test
    public void testHashCode() {
        Long id = produtoFilled.getId();
        assertEquals(produtoFilled.hashCode(), Objects.hash(id));
    }

    @Test
    public void testEquals() {
        Loja loja = new Loja();
        assertFalse(produtoFilled.equals(1));
        assertFalse(produtoFilled.equals(loja));
    }

    @Test
    public void testEmptyToString() {
        assertEquals(produto.toString(), "null, null, null, null, null, null, null");
    }

    @Test
    public void testFilledToString() {
        assertEquals(produtoFilled.toString(), "null, IPHONE XR, 2290.35, CELULAR, 1, APPLE, O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais");

    }
}
