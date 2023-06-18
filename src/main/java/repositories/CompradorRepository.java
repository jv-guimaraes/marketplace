package repositories;

import entities.Comprador;
import entities.Produto;
import org.json.JSONArray;
import org.json.JSONObject;
import services.ProdutoService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompradorRepository {
    private String compradoresPath = "database/compradores.json";

    private ProdutoRepository produtoRepository = new ProdutoRepository();

    private ProdutoService produtoService = new ProdutoService();

    public CompradorRepository() {
    }

    public CompradorRepository(String compradoresPath) {
        this.compradoresPath = compradoresPath;
    }

    public List<Comprador> getAllCompradores() {
        File file = new File(compradoresPath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(compradoresPath);
        List<Comprador> compradores = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);

            JSONArray produtosIdsArray = compradorJson.getJSONArray("produtos");
            List<Produto> produtos = new ArrayList<>();
            for (int j = 0; j < produtosIdsArray.length(); j++) {

                long produtoId = produtosIdsArray.getLong(j);
                Produto produto = produtoService.getProdutoById(produtoId);
                if (produto != null) {
                    produtos.add(produto);
                }
            }

            Comprador comprador = new Comprador(compradorJson);
            comprador.setProdutos(produtos);

            compradores.add(comprador);
        }

        return compradores;
    }

    public void setAllCompradores(List<Comprador> compradores) {
        JSONArray auxJsonArray = JsonFileUtil.loadJsonArray(compradoresPath);
        JSONArray jsonArray = new JSONArray(compradores);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObject.put("produtos", auxJsonArray.getJSONObject(i).getJSONArray("produtos"));
        }

        JsonFileUtil.saveJsonArray(jsonArray, compradoresPath);
    }

    public void createComprador(JSONObject compradorJson) {
        JSONArray jsonArray = JsonFileUtil.loadJsonArray(compradoresPath);
        jsonArray.put(compradorJson);

        JsonFileUtil.saveJsonArray(jsonArray, compradoresPath);
    }

    public void addProduto(String cpf, Long produtoId) {
        JSONArray compradoresJsonArray = JsonFileUtil.loadJsonArray(compradoresPath);
        List<Produto> produtos = produtoRepository.getAllProdutos();

        // Check if the produto exists in the produtos list
        boolean produtoExists = produtos.stream()
                .anyMatch(p -> Objects.equals(p.getId(), produtoId));

        // If the produto exists, add it to the comprador
        if (produtoExists) {
            for (int i = 0; i < compradoresJsonArray.length(); i++) {
                JSONObject compradorJson = compradoresJsonArray.getJSONObject(i);
                if (compradorJson.getString("cpf").equals(cpf)) {
                    JSONArray produtosIdsArray = compradorJson.getJSONArray("produtos");

                    produtosIdsArray.put(produtoId);
                    JsonFileUtil.saveJsonArray(compradoresJsonArray, compradoresPath);
                    return;
                }
            }
        } else {
            System.out.println("The produto does not exist in the produtos list.");
        }
    }

}