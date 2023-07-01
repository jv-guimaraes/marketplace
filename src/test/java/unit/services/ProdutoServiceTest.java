package unit.services;

import entities.Produto;
import infrastructure.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import services.ProdutoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        produtoArrayNotCreated = new ArrayList<Produto>(Collections.singletonList(produto));
        produtoArray = new ArrayList<Produto>(Arrays.asList(produto, produtoNotCreated));
    }

    @Test
    public void getProdutoById() throws Exception {
        Long produtoId = produto.getId();
        ProdutoRepository mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        ProdutoService service = new ProdutoService(mock);
        assert (service.getProdutoById(produtoId).equals(produto));
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