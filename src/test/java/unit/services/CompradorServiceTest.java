package unit.services;

import entities.Comprador;
import entities.Produto;
import infrastructure.repositories.CompradorRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import services.CompradorService;
import services.ProdutoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompradorServiceTest {
    Comprador comprador = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    Comprador compradorNotCreated = new Comprador("marcio", "marcio@gmail.com", "asda hash", "701.254.231-73", "otherhome");
    List<Comprador> compradorArrayNotCreated;
    List<Comprador> compradorArray;
    JSONObject compradorJSON;

    @BeforeEach
    void addToArray() {
        compradorJSON = new JSONObject();
        compradorJSON.put("nome", compradorNotCreated.getNome());
        compradorJSON.put("email", compradorNotCreated.getEmail());
        compradorJSON.put("senha", compradorNotCreated.getSenha());
        compradorJSON.put("cpf", compradorNotCreated.getCpf());
        compradorJSON.put("endereco", compradorNotCreated.getEndereco());
        compradorJSON.put("carrinho", new JSONArray());
        compradorJSON.put("produtos", new JSONArray());
        compradorArrayNotCreated = new ArrayList<Comprador>(Collections.singletonList(comprador));
        compradorArray = new ArrayList<Comprador>(Arrays.asList(comprador, compradorNotCreated));
    }
    @Test
    public void createCompradorServiceDefault(){
        CompradorService service = new CompradorService();
    }
    @Test
    public void createCompradorServicePath(){
        CompradorService service = new CompradorService("./comprador","./produtos");
    }
    @Test
    public void addProdutoCarrinhoThatExists(){
        Produto produto = new Produto();
        produto.setId(5L);
        produto.setNome("AnyProduct");

        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(produtoServiceMock.produtoExiste(produto.getId())).thenReturn(true);
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        service.addProdutoCarrinho(comprador.getCpf(), produto.getId());
        verify(produtoServiceMock, times(1)).produtoExiste(produto.getId());
        verify(mockRepository, times(1)).getAllCompradores();
    }
    @Test
    public void addProdutoCarrinhoThatNotExists(){
        Produto produto = new Produto();
        produto.setId(5L);
        produto.setNome("AnyProduct");

        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(produtoServiceMock.produtoExiste(produto.getId())).thenReturn(false);
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        service.addProdutoCarrinho(comprador.getCpf(), produto.getId());
        verify(produtoServiceMock, times(1)).produtoExiste(produto.getId());
        verify(mockRepository, times(0)).getAllCompradores();
    }
    @Test
    public void getCpfFromEmailThatExists(){
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertEquals(service.getCpfFromEmail(comprador.getEmail()), comprador.getCpf());
        verify(mockRepository, times(1)).getAllCompradores();
    }
    @Test
    public void getCpfFromEmailThatNotExists(){
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertNull(service.getCpfFromEmail(compradorNotCreated.getEmail()));
        verify(mockRepository, times(1)).getAllCompradores();
    }
    @Test
    public void getCompradorByEmailThatExists(){
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertEquals(service.getCompradorByEmail(comprador.getEmail()), comprador);
        verify(mockRepository, times(1)).getAllCompradores();
    }
    @Test
    public void getCompradorByEmailThatNotExists(){
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertNull(service.getCompradorByEmail(compradorNotCreated.getEmail()));
        verify(mockRepository, times(1)).getAllCompradores();
    }

    @Test
    public void getCompradorByCpfThatExists() throws Exception {
        String cpf = comprador.getCpf();
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertEquals(service.getCompradorByCpf(cpf), comprador);
        verify(mockRepository, times(1)).getAllCompradores();
    }
    @Test
    public void getCompradorByCpfThatNotExists() throws Exception {
        String cpf = compradorNotCreated.getCpf();
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertNull(service.getCompradorByCpf(cpf));
        verify(mockRepository, times(1)).getAllCompradores();
    }
    @Test
    public void compradorExisteReturnsTrue(){
        String cpf = comprador.getCpf();
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertTrue(service.compradorExiste(cpf));
    }
    @Test
    public void compradorExisteReturnsFalse(){
        String cpf = compradorNotCreated.getCpf();
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertFalse(service.compradorExiste(cpf));
    }

    @Test
    public void getAllCompradores() throws Exception {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertEquals(service.getAllCompradores(), compradorArray);
        verify(mockRepository, times(1)).getAllCompradores();
    }
    @Test
    public void createCompradorNotCreated() throws Exception {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        service.createComprador(compradorNotCreated);
        InOrder inOrder = inOrder(mockRepository);
        inOrder.verify(mockRepository, times(1)).getAllCompradores();
        //inOrder.verify(mock, times(1)).createComprador(any(JSONObject.class));
    }

    @Test
    public void createCompradorAlreadyCreated() throws Exception {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        service.createComprador(comprador);
        verify(mockRepository, times(0)).setAllCompradores(compradorArray);
        verify(mockRepository, times(0)).setAllCompradores(compradorArrayNotCreated);
    }
    @Test
    public void loginValido() {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertTrue(service.loginValido(comprador.getEmail(), comprador.getSenha()));
        verify(mockRepository, times(1)).getAllCompradores();
    }

    @Test
    public void loginInvalido() {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertFalse(service.loginValido(compradorNotCreated.getEmail(), compradorNotCreated.getSenha()));
        verify(mockRepository, times(1)).getAllCompradores();
    }

    @Test
    public void emailCadastradoMustReturnTrue() {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertTrue(service.emailCadastrado(comprador.getEmail()));
        verify(mockRepository, times(1)).getAllCompradores();
    }
    @Test
    public void emailCadastradoMustReturnFalse() {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);
        assertFalse(service.emailCadastrado(compradorNotCreated.getEmail()));
        verify(mockRepository, times(1)).getAllCompradores();
    }

    @Test
    public void updateComprador() throws Exception {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);

        List<Comprador> copyCompradorArray = new ArrayList<Comprador>();
        copyCompradorArray.addAll(compradorArray);
        Comprador alteredComprador = comprador.clone();
        alteredComprador.setEmail("other@email.com");
        copyCompradorArray.set(0, alteredComprador);

        service.updateComprador(comprador.getCpf(), alteredComprador);
        verify(mockRepository, times(1)).setAllCompradores(copyCompradorArray);
    }

    @Test
    public void deleteComprador() throws Exception {
        CompradorRepository mockRepository = mock();
        ProdutoService produtoServiceMock = mock();
        when(mockRepository.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mockRepository, produtoServiceMock);

        String cpf = comprador.getCpf();
        List<Comprador> copyCompradorArray = new ArrayList<Comprador>();
        copyCompradorArray.addAll(compradorArray);
        copyCompradorArray.remove(0);
        service.deleteComprador(cpf);
        verify(mockRepository, times(1)).setAllCompradores(copyCompradorArray);
    }
}