package unit.entities;

import entities.Loja;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class LojaEntityTest {
    Loja loja = new Loja();
    Loja lojaFilled = new Loja("americanas", "americanas@gmail.com", "mustbe a hash", "93.763.071/0001-27", "av.brasil 232, campina grande");

    @Test
    public void lojaFromObject() {
        JSONObject json = new JSONObject();
        json.put("nome", lojaFilled.getNome());
        json.put("email", lojaFilled.getEmail());
        json.put("senha", lojaFilled.getSenha());
        json.put("cnpj", lojaFilled.getCnpj());
        json.put("avaliacao", lojaFilled.getAvaliacao());
        json.put("endereco", lojaFilled.getEndereco());
        Loja lojaFjson = new Loja(json);
        assertTrue(lojaFjson.equals(lojaFilled));
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
    public void getAvaliacao() {
        String avaliacao = "boa";
        assertNull(loja.getAvaliacao());
        loja.setAvaliacao(avaliacao);
        assertEquals(loja.getAvaliacao(), avaliacao);
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
        assertEquals(loja.toString(),"null, null, null, null, null");
    }

    @Test
    public void testFilledToString() {
        assertEquals(lojaFilled.toString(), "americanas, americanas@gmail.com, mustbe a hash, 93.763.071/0001-27, av.brasil 232, campina grande");
    }
}
