package unit.services;

import entities.Produto;
import infrastructure.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import services.ProdutoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {
    Produto produto = new Produto("IPHONE XR", 2290.35, "CELULAR", 1, "APPLE", "O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais", "93.763.071/0001-27");
    Produto produtoNotCreated = new Produto("MI 9", 1342.59, "CELULAR", 1, "XIAOMI", "Mi 9 é um celular da linha de custo benefício produzida pela XIAOMI", "93.763.071/0001-27");
    List<Produto> produtoArrayNotCreated;
    List<Produto> produtoArray;

    @BeforeEach
    void createNewStack() {
        produto.setId(1L);
        produtoNotCreated.setId(2L);
        produtoArrayNotCreated = new ArrayList<Produto>(Arrays.asList(produto));
        produtoArray = new ArrayList<Produto>(Arrays.asList(produto, produtoNotCreated));
    }

    @Test
    public void createProdutoServiceByPath() throws Exception {
        ProdutoService service = new ProdutoService("example");
    }
    @Test
    public void getProdutoByIdThatExists() throws Exception {
        Long produtoId = produto.getId();
        ProdutoRepository mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        ProdutoService service = new ProdutoService(mock);
        assertTrue(service.getProdutoById(produtoId).equals(produto));
        verify(mock, times(1)).getAllProdutos();
    }
    @Test
    public void getProdutoByIdThatNotExists() throws Exception {
        Long produtoId = produtoNotCreated.getId();
        ProdutoRepository mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArrayNotCreated);
        ProdutoService service = new ProdutoService(mock);
        assertNull(service.getProdutoById(produtoId));
        verify(mock, times(1)).getAllProdutos();
    }

    @Test
    public void getAllProdutos() throws Exception {
        ProdutoRepository mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        ProdutoService service = new ProdutoService(mock);
        assertEquals(service.getAllProdutos(), produtoArray);
        verify(mock, times(1)).getAllProdutos();
    }

    @Test
    public void createProdutoNotCreated() throws Exception {
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        when(mock.getAllProdutos()).thenReturn(produtoArrayNotCreated);
        service.createProduto(produtoNotCreated);
        verify(mock, times(1)).setAllProdutos(produtoArray);
    }

    @Test
    public void createProdutoAlreadyCreated() throws Exception {
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        service.createProduto(produto);
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock, times(1)).getAllProdutos();
        inOrder.verify(mock, times(1)).setAllProdutos(produtoArray);
    }
    @Test
    public void produtoExisteTrue(){
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        assertTrue(service.produtoExiste(produto.getId()));
        verify(mock, times(1)).getAllProdutos();
    }
    @Test
    public void produtoExisteFalse(){
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        when(mock.getAllProdutos()).thenReturn(produtoArrayNotCreated);
        assertFalse(service.produtoExiste(produtoNotCreated.getId()));
        verify(mock, times(1)).getAllProdutos();
    }
    @Test
    public void adicionarNota(){
        int nota = 8;
        List<Produto> copyProdutosArray = new ArrayList<Produto>();
        copyProdutosArray.addAll(produtoArrayNotCreated);
        Produto alteredProduto = produto.clone();
        alteredProduto.addNotaProduto(nota);
        copyProdutosArray.set(0, alteredProduto);
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        when(mock.getAllProdutos()).thenReturn(produtoArrayNotCreated);
        service.adicionarNota(produto.getId(), nota);
        verify(mock, times(1)).getAllProdutos();
        verify(mock, times(1)).setAllProdutos(copyProdutosArray);
    }


    @Test
    public void updateProduto() throws Exception {
        ProdutoRepository mock = mock();
        List<Produto> copyProdutosArray = new ArrayList<Produto>();
        copyProdutosArray.addAll(produtoArray);
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        ProdutoService service = new ProdutoService(mock);
        Produto alteredProduto = produto.clone();
        alteredProduto.setNome("otherName");
        copyProdutosArray.set(0, alteredProduto);
        service.updateProduto(produto.getId(), alteredProduto);
        verify(mock, times(1)).setAllProdutos(copyProdutosArray);
    }

    @Test
    public void getProdutosByProduto() throws Exception {
        String lojaCnpj = produto.getLojaCnpj();
        ProdutoRepository mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        ProdutoService service = new ProdutoService(mock);
        service.getProdutosByLoja(lojaCnpj);
        verify(mock, times(1)).getAllProdutos();
    }

    @Test
    public void deleteProduto() throws Exception {
        Long produtoId = produto.getId();
        ProdutoRepository mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        List<Produto> copyProdutoArray = new ArrayList<Produto>();
        copyProdutoArray.addAll(produtoArray);
        copyProdutoArray.remove(0);
        ProdutoService service = new ProdutoService(mock);
        service.deleteProduto(produtoId);
        verify(mock, times(1)).setAllProdutos(copyProdutoArray);
    }
}