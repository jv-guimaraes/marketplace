package util;

import entities.Produto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonFileCRUDProdutoUtil {
    private static final String JSON_FILE_PATH = "produtos.json";

    public static void createProduto(Produto produto) {
        JSONArray jsonArray;
        File file = new File(JSON_FILE_PATH);

        if (file.exists()) {
            jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);
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

        JSONObject produtoJson = new JSONObject(produto);
        jsonArray.put(produtoJson);

        JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);

    }

    public static Produto getProdutoById(long id) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return null;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            if (produtoJson.getLong("id") == id) {
                String nome = produtoJson.getString("nome");
                double valor = produtoJson.getDouble("valor");
                String tipo = produtoJson.getString("tipo");
                int quantidade = produtoJson.getInt("quantidade");
                String marca = produtoJson.getString("marca");
                String descricao = produtoJson.getString("descricao");
                String lojaCnpj = produtoJson.getString("lojaCnpj");

                Produto produto = new Produto(nome, valor, tipo, quantidade, marca, descricao, lojaCnpj);
                produto.setId(id);

                return produto;
            }
        }

        return null;
    }

    public static void updateProduto(long id, Produto produto) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            if (produtoJson.getLong("id") == id) {
                produtoJson.put("nome", produto.getNome());
                produtoJson.put("valor", produto.getValor());
                produtoJson.put("tipo", produto.getTipo());
                produtoJson.put("quantidade", produto.getQuantidade());
                produtoJson.put("marca", produto.getMarca());
                produtoJson.put("descricao", produto.getDescricao());

                JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);
                System.out.println("Produto updated successfully.");
                return;
            }
        }

        System.out.println("Produto not found.");
    }

    public static void deleteProduto(long id) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            if (produtoJson.getLong("id") == id) {
                jsonArray.remove(i);
                JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);
                return;
            }
        }

        System.out.println("Produto not found.");
    }

    public static List<Produto> getAllProdutos() {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);
        List<Produto> produtos = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produtoJson = jsonArray.getJSONObject(i);
            long id = produtoJson.getLong("id");
            String nome = produtoJson.getString("nome");
            double valor = produtoJson.getDouble("valor");
            String tipo = produtoJson.getString("tipo");
            int quantidade = produtoJson.getInt("quantidade");
            String marca = produtoJson.getString("marca");
            String descricao = produtoJson.getString("descricao");
            String lojaCnpj = produtoJson.getString("lojaCnpj");

            Produto produto = new Produto(nome, valor, tipo, quantidade, marca, descricao, lojaCnpj);
            produto.setId(id);

            produtos.add(produto);
        }

        return produtos;
    }

}