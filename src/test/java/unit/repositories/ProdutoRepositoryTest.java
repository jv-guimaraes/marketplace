package unit.repositories;

import controllers.ProdutoController;
import entities.Produto;
import entities.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ProdutoRepository;
import services.ProdutoService;
import util.JsonFileCRUDProdutoUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ProdutoRepositoryTest {

    Produto produto = new Produto("IPHONE XR", 2290.35, "CELULAR",1,"APPLE", "O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais", "93.763.071/0001-27");

    List<Produto> produtoArray = Arrays.asList(produto, new Produto());
    @BeforeEach
    void setProdutoId() {
        produto.setId(1L);
    }
    @Test
    public void getProdutoById() throws Exception {
        Long produtoId = produto.getId();
        JsonFileCRUDProdutoUtil mock = mock(JsonFileCRUDProdutoUtil.class);
        when(mock.getProdutoById(produtoId)).thenReturn(produto);
        ProdutoRepository repository = new ProdutoRepository(mock);
        assertTrue(repository.getProdutoById(produtoId).equals(produto));
        verify(mock, times(1)).getProdutoById(produtoId);
    }
    @Test
    public void getAllProdutos() throws Exception {
        JsonFileCRUDProdutoUtil mock = mock();
        when(mock.getAllProdutos()).thenReturn(produtoArray);
        ProdutoRepository repository = new ProdutoRepository(mock);
        assertTrue(repository.getAllProdutos().equals(produtoArray));
        verify(mock, times(1)).getAllProdutos();
    }
    @Test
    public void getProdutosByLoja() throws Exception {
        String lojaCnpj = produto.getLojaCnpj();
        JsonFileCRUDProdutoUtil mock = mock();
        ProdutoRepository repository = new ProdutoRepository(mock);
        repository.getProdutosByLoja(lojaCnpj);
        verify(mock, times(1)).getProdutosByLoja(lojaCnpj);
    }
    @Test
    public void createProduto() throws Exception {
        JsonFileCRUDProdutoUtil mock = mock();
        ProdutoRepository repository = new ProdutoRepository(mock);
        repository.createProduto(produto);
        verify(mock, times(1)).createProduto(produto);
    }
    @Test
    public void updateProduto() throws Exception {
        Long produtoId = produto.getId();
        JsonFileCRUDProdutoUtil mock = mock();
        ProdutoRepository repository = new ProdutoRepository(mock);
        repository.updateProduto(produtoId, produto);
        verify(mock, times(1)).updateProduto(produtoId, produto);
    }
    @Test
    public void deleteProduto() throws Exception {
        Long produtoId = produto.getId();
        JsonFileCRUDProdutoUtil mock = mock();
        ProdutoRepository repository = new ProdutoRepository(mock);
        repository.deleteProduto(produtoId);
        verify(mock, times(1)).deleteProduto(produtoId);
    }
}
