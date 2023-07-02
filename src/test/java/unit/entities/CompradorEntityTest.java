package unit.entities;

import entities.Comprador;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class CompradorEntityTest {
    private final Comprador compradorFilled = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");
    private Comprador comprador;

    @BeforeEach
    void createComprador() {
        List<Long> carrinho = new ArrayList<Long>(Arrays.asList(1L,2L,3L));
        List<Long> historico = new ArrayList<Long>(Arrays.asList(1L,2L,3L));
        comprador = new Comprador();
        compradorFilled.setCarrinho(carrinho);
        compradorFilled.setHistorico(historico);
    }

    @Test
    public void compradorFromObject() {
        JSONObject json = new JSONObject();
        json.put("nome", compradorFilled.getNome());
        json.put("email", compradorFilled.getEmail());
        json.put("senha", compradorFilled.getSenha());
        json.put("cpf", compradorFilled.getCpf());
        json.put("endereco", compradorFilled.getEndereco());
        json.put("carrinho", compradorFilled.getCarrinho());
        json.put("historico", compradorFilled.getHistorico());
        json.put("pontos", compradorFilled.getPontos());
        Comprador compradorFjson = new Comprador(json);
        assertTrue(compradorFjson.equals(compradorFilled));
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
    public void setPontos(){
        Integer pontos = 12;
        assertEquals(comprador.getPontos(), 0);
        comprador.setPontos(pontos);
        assertEquals(comprador.getPontos(), pontos);
    }
    @Test
    public void addProdutoCarrinho() {
        Long produtoId = 1L;
        List<Long> carrinho = new ArrayList<Long>(Arrays.asList(produtoId));
        assertNotNull(comprador.getCarrinho());
        assertTrue(comprador.getCarrinho().isEmpty());
        comprador.addProdutoCarrinho(produtoId);
        assertFalse(comprador.getCarrinho().isEmpty());
        assertEquals(comprador.getCarrinho(), carrinho);
    }
    @Test
    public void clearCarrinho() {
        assertNotNull(compradorFilled.getCarrinho());
        assertFalse(compradorFilled.getCarrinho().isEmpty());
        compradorFilled.clearCarrinho();
        assertTrue(compradorFilled.getCarrinho().isEmpty());
    }
    @Test

    public void carrinhoContem() {
        Long produtoId = 1L;
        List<Long> carrinho = new ArrayList<Long>(Arrays.asList(produtoId));
        comprador.setCarrinho(carrinho);
        assertTrue(comprador.carrinhoContem(produtoId));
    }
    @Test
    public void addToHistorico() {
        Long produtoId = 1L;
        List<Long> historico = new ArrayList<Long>(Arrays.asList(produtoId));
        assertNotNull(comprador.getHistorico());
        assertTrue(comprador.getHistorico().isEmpty());
        comprador.addToHistorico(produtoId);
        assertFalse(comprador.getHistorico().isEmpty());
        assertEquals(comprador.getHistorico(), historico);
    }
    @Test
    public void addPontos(){
        double ponto = 21.1;
        comprador.addPontos(ponto);
        assertEquals(comprador.getPontos(), 4);

    }
    @Test
    public void testHashCode() {
        String cpf = compradorFilled.getCpf();
        assertEquals(compradorFilled.hashCode(), Objects.hash(cpf));
    }

    @Test
    public void testEquals() {
        assertFalse(compradorFilled.equals(1));
    }

    @Test
    public void testEmptyToString() {
        assertEquals(comprador.toString(), "nome: null, email: null, senha: null, cpf: null, endereco: null, nomesProdutosComprados: []");
    }

    @Test
    public void testFilledToString() {
        assertEquals(compradorFilled.toString(), "nome: josé, email: hugo@gmail.com, senha: mustbe a hash, cpf: 701.254.231-72, endereco: myhome, nomesProdutosComprados: [1, 2, 3]");
    }
}
