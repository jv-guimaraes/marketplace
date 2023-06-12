package unit.entities;

import entities.Comprador;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class CompradorEntityTest {
    private Comprador comprador;
    private final Comprador compradorFilled = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    @BeforeEach
    void createComprador() {
        comprador = new Comprador();
    }

    @Test
    public void compradorFromObject() {
        JSONObject json = new JSONObject();
        json.put("nome", compradorFilled.getNome());
        json.put("email", compradorFilled.getEmail());
        json.put("senha", compradorFilled.getSenha());
        json.put("cpf", compradorFilled.getCpf());
        json.put("endereco", compradorFilled.getEndereco());
        Comprador compradorFjson = new Comprador(json);
        assert (compradorFjson.equals(compradorFilled));
    }

    @Test
    public void getCpf() {
        String cpf = "701.254.231-72";
        assertNull(comprador.getCpf());
        comprador.setCpf(cpf);
        assertEquals(comprador.getCpf(), cpf);
    }

    @Test
    public void getEmail() {
        String email = "hugo@gmail.com";
        assertNull(comprador.getEmail());
        comprador.setEmail(email);
        assertEquals(comprador.getEmail(), email);
    }

    @Test
    public void getNome() {
        String nome = "josé";
        assertNull(comprador.getNome());
        comprador.setNome(nome);
        assertEquals(comprador.getNome(), nome);
    }

    @Test
    public void getEndereco() {
        String nome = "myhome";
        assertNull(comprador.getEndereco());
        comprador.setEndereco(nome);
        assertEquals(comprador.getEndereco(), nome);
    }

    @Test
    public void getSenha() {
        String senha = "123asds";
        assertNull(comprador.getSenha());
        comprador.setSenha(senha);
        assertEquals(comprador.getSenha(), senha);
    }

    @Test
    public void testHashCode() {
        String cpf = compradorFilled.getCpf();
        assertEquals(compradorFilled.hashCode(), Objects.hash(cpf));
    }

    @Test
    public void testEquals() {
        assertNotEquals(1, compradorFilled);
    }

    @Test
    public void testEmptyToString() {
        assertEquals(comprador.toString(), "\n" +
                "{\n" +
                "\"nome\":null,\n" +
                "\"cpf\":null,\n" +
                "\"email\":null,\n" +
                "\"senha\":null,\n" +
                "\"endereco\":null,\n" +
                "}\n");
    }

    @Test
    public void testFilledToString() {
        assertEquals(compradorFilled.toString(), "\n" +
                "{\n" +
                "\"nome\":josé,\n" +
                "\"cpf\":701.254.231-72,\n" +
                "\"email\":hugo@gmail.com,\n" +
                "\"senha\":mustbe a hash,\n" +
                "\"endereco\":myhome,\n" +
                "}\n");
    }
}
