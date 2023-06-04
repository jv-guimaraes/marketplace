package util;

import entities.Produto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonFileCRUDProdutoUtil {
    private final JsonFileUtil jsonHandler;
    private final String JSON_FILE_PATH = "database/produtos.json";

    public JsonFileCRUDProdutoUtil(JsonFileUtil jsonHandler){
        this.jsonHandler = jsonHandler;
    }

    public void createProduto(Produto produto) {
        JSONArray jsonArray;
        File file = new File(JSON_FILE_PATH);

        if (file.exists()) {
            jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
        } else {
            jsonArray = new JSONArray();
        }

        // Find the maximum id value
        long maxId = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            long id = produtoJson.getLong("id");
            if (id > maxId) {
                maxId = id;
            }
        }

        // Increment the id value
        long newId = maxId + 1;

        // Set the new id in the produto object
        produto.setId(newId);

        // Check if lojaCnpj is valid
        Boolean cnpjValido = false;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            if (produtoJson.getString("lojaCnpj").equals(produto.getLojaCnpj())) {
                cnpjValido = true;
            }
        }
        if (!cnpjValido) {
            System.out.println("Falha ao criar Produto. CNPJ da loja não foi encontrado.");
            return;
        }

        JSONObject produtoJson = new JSONObject(produto);
        jsonArray.put(produtoJson);

        this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);

    }

    public Produto getProdutoById(long id) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return null;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            if (produtoJson.getLong("id") == id) {
                Produto produto = new Produto(produtoJson);
                produto.setId(id);

                return produto;
            }
        }

        return null;
    }

    public List<Produto> getProdutosByLoja(String cnpj) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return null;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
        List<Produto> produtos = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            if (produtoJson.getString("lojaCnpj").equals(cnpj)) {
                Produto produto = new Produto(produtoJson);
                produtos.add(produto);
            }
        }

        return produtos;
    }

    public void updateProduto(long id, Produto produto) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            if (produtoJson.getLong("id") == id) {
                produtoJson.put("nome", produto.getNome());
                produtoJson.put("valor", produto.getValor());
                produtoJson.put("tipo", produto.getTipo());
                produtoJson.put("quantidade", produto.getQuantidade());
                produtoJson.put("marca", produto.getMarca());
                produtoJson.put("descricao", produto.getDescricao());

                this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);
                System.out.println("Produto updated successfully.");
                return;
            }
        }

        System.out.println("Produto not found.");
    }

    public void deleteProduto(long id) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            if (produtoJson.getLong("id") == id) {
                jsonArray.remove(i);
                this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);
                return;
            }
        }

        System.out.println("Produto not found.");
    }

    public List<Produto> getAllProdutos() {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
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

}