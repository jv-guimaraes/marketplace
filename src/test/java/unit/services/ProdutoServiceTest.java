package unit.services;
import services.ProdutoService;
import entities.Produto;
import org.junit.jupiter.api.*;
import repositories.ProdutoRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class ProdutoServiceTest {
    Produto produto = new Produto("IPHONE XR", 2290.35, "CELULAR",1,"APPLE", "O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais", "93.763.071/0001-27");
    List<Produto> produtoArray = Arrays.asList(produto, new Produto());
    @BeforeEach
    void createNewStack() {
        produto.setId(1L);
    }
    @Test
    public void getProdutoById() throws Exception {
        Long produtoId = produto.getId();
        ProdutoRepository mock = mock();
        when(mock.getProdutoById(produtoId)).thenReturn(produto);
        ProdutoService service = new ProdutoService(mock);
        assert(service.getProdutoById(produtoId).equals(produto));
        verify(mock, times(1)).getProdutoById(produtoId);
    }
    @Test
    public void getAllProdutos() throws Exception {
        ProdutoRepository mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        ProdutoService service = new ProdutoService(mock);
        assertTrue(service.getAllProdutos().equals(produtoArray));
        verify(mock, times(1)).getAllProdutos();
    }
    @Test
    public void createProduto() throws Exception {
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        service.createProduto(produto);
        verify(mock, times(1)).createProduto(produto);
    }
    @Test
    public void updateProduto() throws Exception {
        Long produtoId = produto.getId();
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        service.updateProduto(produtoId, produto);
        verify(mock, times(1)).updateProduto(produtoId, produto);
    }
    @Test
    public void getProdutosByLoja() throws Exception {
        String lojaCnpj = produto.getLojaCnpj();
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        service.getProdutosByLoja(lojaCnpj);
        verify(mock, times(1)).getProdutosByLoja(lojaCnpj);
    }
    @Test
    public void deleteProduto() throws Exception {
        Long produtoId = produto.getId();
        ProdutoRepository mock = mock();
        ProdutoService service = new ProdutoService(mock);
        service.deleteProduto(produtoId);
        verify(mock, times(1)).deleteProduto(produtoId);
    }
}