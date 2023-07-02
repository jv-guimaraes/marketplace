package unit.entities;

import entities.Loja;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class LojaEntityTest {
    Loja loja = new Loja();
    Loja lojaFilled = new Loja("americanas", "americanas@gmail.com", "mustbe a hash", "93.763.071/0001-27", "av.brasil 232, campina grande");

    @Test
    public void lojaFromObject() {
        List<Integer> notas = new ArrayList<Integer>(Arrays.asList(8));
        JSONObject json = new JSONObject();
        json.put("nome", lojaFilled.getNome());
        json.put("email", lojaFilled.getEmail());
        json.put("senha", lojaFilled.getSenha());
        json.put("cnpj", lojaFilled.getCnpj());
        json.put("notas", notas);
        json.put("endereco", lojaFilled.getEndereco());
        Loja lojaFjson = new Loja(json);
        assertTrue(lojaFjson.equals(lojaFilled));
    }
    @Test
    public void lojaEqualsloja() {
        Loja lj = new Loja();
        assertTrue(lj.equals(lj));
    }

    @Test
    public void lojaClone() {
        Loja ljClone = loja.clone();
        assertEquals(ljClone.getCnpj(), loja.getCnpj());
        assertEquals(ljClone.getNotas(), loja.getNotas());
        assertEquals(ljClone.getNome(), loja.getNome());
        assertEquals(ljClone.getEndereco(), loja.getEndereco());
        assertEquals(ljClone.getEmail(), loja.getEmail());
        assertEquals(ljClone.getSenha(), loja.getSenha());
    }

    @Test
    public void getCnpj() {
        String cnpj = "93.763.071/0001-27";
        assertNull(loja.getCnpj());
        loja.setCnpj(cnpj);
        assertEquals(loja.getCnpj(), cnpj);
    }

    @Test
    public void getEmail() {
        String email = "americanas@gmail.com";
        assertNull(loja.getEmail());
        loja.setEmail(email);
        assertEquals(loja.getEmail(), email);
    }
    @Test
    public void getNome() {
        String nome = "americanas";
        assertNull(loja.getNome());
        loja.setNome(nome);
        assertEquals(loja.getNome(), nome);
    }

    @Test
    public void getEndereco() {
        String nome = "myhome";
        assertNull(loja.getEndereco());
        loja.setEndereco(nome);
        assertEquals(loja.getEndereco(), nome);
    }

    @Test
    public void getSenha() {
        String senha = "mustbe a hash";
        assertNull(loja.getSenha());
        loja.setSenha(senha);
        assertEquals(loja.getSenha(), senha);
    }

    @Test
    public void addNota(){
        Integer nota = 8;
        List<Integer> notas = new ArrayList<Integer>(Arrays.asList(nota));
        assertNotNull(loja.getNotas());
        assertTrue(loja.getNotas().isEmpty());
        loja.addNota(nota);
        assertTrue(loja.getNotas().equals(notas));
    }
    @Test
    public void getAvaliacaoRuim(){
        List<Integer> notasRuim = new ArrayList<Integer>(Arrays.asList(1,2,1,1));
        loja.setNotas(notasRuim);
        assertEquals(loja.getAvaliacao(), "ruim");
    }
    @Test
    public void getAvaliacaoMedio(){
        List<Integer> notasMedia = new ArrayList<Integer>(Arrays.asList(2,2,3,2));
        loja.setNotas(notasMedia);
        assertEquals(loja.getAvaliacao(), "médio");
    }
    @Test
    public void getAvaliacaoBoa(){
        List<Integer> notasBoa = new ArrayList<Integer>(Arrays.asList(3,3,3,5));
        loja.setNotas(notasBoa);
        assertEquals(loja.getAvaliacao(), "bom");
    }
    @Test
    public void getAvaliacaoExcelente(){
        List<Integer> notasExcelente = new ArrayList<Integer>(Arrays.asList(4,3,4,5));
        loja.setNotas(notasExcelente);
        assertEquals(loja.getAvaliacao(), "excelente");
    }
    @Test
    public void testHashCode() {
        String cnpj = lojaFilled.getCnpj();
        assertEquals(lojaFilled.hashCode(), Objects.hash(cnpj));
    }

    @Test
    public void testEquals() {
        assertFalse(lojaFilled.equals(1));
    }

    @Test
    public void testEmptyToString() {
        assertEquals(loja.toString(),"null | null | null | null | null");
    }

    @Test
    public void testFilledToString() {
        assertEquals(lojaFilled.toString(), "americanas | americanas@gmail.com | mustbe a hash | 93.763.071/0001-27 | av.brasil 232, campina grande");
    }
}
