package infrastructure.repositories;

import entities.Produto;
import infrastructure.jsonFiles.JsonFileUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private String produtosPath = "database/produtos.json";

    public ProdutoRepository() {
    }

    public ProdutoRepository(String produtosPath) {
        this.produtosPath = produtosPath;
    }

    public List<Produto> getAllProdutos() {
        File file = new File(produtosPath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(produtosPath);
        List<Produto> produtos = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            long id = produtoJson.getLong("id");
            Produto produto = new Produto(produtoJson);
            produto.setId(id);

            produtos.add(produto);
        }

        return produtos;
    }

    public void setAllProdutos(List<Produto> produtos) {
        JSONArray jsonArray = new JSONArray(produtos);
        JsonFileUtil.saveJsonArray(jsonArray, produtosPath);
    }
}