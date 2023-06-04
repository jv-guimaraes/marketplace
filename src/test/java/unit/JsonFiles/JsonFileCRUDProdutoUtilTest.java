package unit.JsonFiles;

import entities.Produto;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ProdutoRepository;
import util.JsonFileCRUDProdutoUtil;
import util.JsonFileUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import org.json.JSONArray;
public class JsonFileCRUDProdutoUtilTest {
    Produto produto = new Produto("IPHONE XR", 2290.35, "CELULAR",1,"APPLE", "O iPhone XR tem tela Liquid Retina de 6,1 polegadas** e seis lindas cores. Face ID avançado para desbloquear o aparelho e acessar apps só com o olhar. Chip A12 Bionic, que usa aprendizado de máquina em tempo real para transformar sua maneira de interagir com fotos, jogos, realidade aumentada e muito mais", "93.763.071/0001-27");
    List<Produto> produtoArray = Arrays.asList(produto, new Produto());
    JSONArray getFilledArray;

    class MyJsonFileUtil extends JsonFileUtil{
        @Override
        public JSONArray loadJsonArray(String path){
            return super.loadJsonArray(path);
        }
    }
    @BeforeEach
    void addToArray() {
        produto.setId(1L);
        JSONObject produtoJSON = new JSONObject();
        produtoJSON.put("id", produto.getId());
        produtoJSON.put("nome", produto.getNome());
        produtoJSON.put("descricao", produto.getDescricao());
        produtoJSON.put("quantidade", produto.getQuantidade());
        produtoJSON.put("tipo", produto.getTipo());
        produtoJSON.put("marca", produto.getMarca());
        produtoJSON.put("valor", produto.getValor());
        produtoJSON.put("lojaCnpj", produto.getLojaCnpj());
        List<JSONObject> array = Arrays.asList(produtoJSON);
        getFilledArray = new JSONArray(array);
    }
    @Test
    public void getProdutoById() throws Exception {
        Long produtoId = produto.getId();
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDProdutoUtil jsonFileCRUD = new JsonFileCRUDProdutoUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        assertTrue(jsonFileCRUD.getProdutoById(produtoId).equals(produto));
        verify(mock, times(1)).loadJsonArray(filePath);
    }
    @Test
    public void getProdutosByLoja() throws Exception {
        List<Produto> result = Arrays.asList(produto);

        String lojaId = produto.getLojaCnpj();
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDProdutoUtil jsonFileCRUD = new JsonFileCRUDProdutoUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        assertTrue(jsonFileCRUD.getProdutosByLoja(lojaId).equals(result));
        verify(mock, times(1)).loadJsonArray(filePath);
    }
    @Test
    public void getAllProdutos() throws Exception {
        List<Produto> result = Arrays.asList(produto);
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDProdutoUtil jsonFileCRUD = new JsonFileCRUDProdutoUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        assertTrue(jsonFileCRUD.getAllProdutos().equals(result));
        verify(mock, times(1)).loadJsonArray(filePath);
    }
    @Test
    public void createProduto() throws Exception {
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDProdutoUtil jsonFileCRUD = new JsonFileCRUDProdutoUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.createProduto(produto);
        verify(mock, times(1)).loadJsonArray(filePath);
    }
    @Test
    public void updateProduto() throws Exception {
        Long produtoId = produto.getId();

        String alteredNome = "altered@gmail.com";

        Produto alteredProduto = produto.clone();
        alteredProduto.setNome(alteredNome);

        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDProdutoUtil jsonFileCRUD = new JsonFileCRUDProdutoUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.updateProduto(produtoId, alteredProduto);
        verify(mock, times(1)).loadJsonArray(filePath);
    }
    @Test
    public void deleteProduto() throws Exception {
        Long produtoId = produto.getId();
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDProdutoUtil jsonFileCRUD = new JsonFileCRUDProdutoUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.deleteProduto(produtoId);
        verify(mock, times(1)).loadJsonArray(filePath);
    }
}
