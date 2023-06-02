package unit.controllers;
import controllers.ProdutoController;
import entities.Produto;
import org.junit.jupiter.api.*;
import services.ProdutoService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class ProdutoControllerTest {
    Produto produto = new Produto("IPHONE XR", 2290.35, "CELULAR",1,"APPLE", "O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais", "93.763.071/0001-27");
    List<Produto> produtoArray = Arrays.asList(produto, new Produto());
    @BeforeEach
    void createNewStack() {
        produto.setId(1L);
    }
    @Test
    public void getProdutoById() throws Exception {
        Long produtoId = produto.getId();
        ProdutoService mock = mock();
        when(mock.getProdutoById(produtoId)).thenReturn(produto);
        ProdutoController controller = new ProdutoController(mock);
        assert(controller.getProdutoById(produtoId).equals(produto));
        verify(mock, times(1)).getProdutoById(produtoId);
    }
    @Test
    public void getAllProdutos() throws Exception {
        ProdutoService mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        ProdutoController controller = new ProdutoController(mock);
        assertTrue(controller.getAllProdutos().equals(produtoArray));
        verify(mock, times(1)).getAllProdutos();
    }
    @Test
    public void createProduto() throws Exception {
        ProdutoService mock = mock();
        ProdutoController controller = new ProdutoController(mock);
        controller.createProduto(produto);
        verify(mock).createProduto(produto);
    }
    @Test
    public void updateProduto() throws Exception {
        Long produtoId = produto.getId();
        ProdutoService mock = mock();
        ProdutoController controller = new ProdutoController(mock);
        controller.updateProduto(produtoId, produto);
        verify(mock).updateProduto(produtoId, produto);
    }
    @Test
    public void deleteProduto() throws Exception {
        Long produtoId = produto.getId();
        ProdutoService mock = mock();
        ProdutoController controller = new ProdutoController(mock);
        controller.deleteProduto(produtoId);
        verify(mock).deleteProduto(produtoId);
    }
}