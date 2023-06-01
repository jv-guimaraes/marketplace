package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import entities.Loja;
import org.json.*;

public class JsonFileCRUDLojaUtil {

    private static final String JSON_FILE_PATH = "lojas.json";

    public static void createLoja(Loja loja) {
        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);

        // Check if the CNPJ already exists
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lojaJson = jsonArray.getJSONObject(i);
            if (lojaJson.getString("cnpj").equals(loja.getCnpj())) {
                System.out.println("Cannot create Loja. CNPJ already exists.");
                return;
            }
        }

        // CNPJ doesn't exist, proceed with creating the Loja
        JSONObject lojaJson = new JSONObject(loja);
        jsonArray.put(lojaJson);

        JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);
        System.out.println("Loja created successfully.");
    }

    public static Loja getLojaByCnpj(String cnpj) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return null;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lojaJson = jsonArray.getJSONObject(i);
            if (lojaJson.getString("cnpj").equals(cnpj)) {
                String nome = lojaJson.getString("nome");
                String email = lojaJson.getString("email");
                String senha = lojaJson.getString("senha");
                String endereco = lojaJson.getString("endereco");

                return new Loja(nome, email, senha, cnpj, endereco);
            }
        }

        return null;
    }

    public static List<Loja> getAllLojas() {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);
        List<Loja> lojas = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lojaJson = jsonArray.getJSONObject(i);
            String nome = lojaJson.getString("nome");
            String email = lojaJson.getString("email");
            String senha = lojaJson.getString("senha");
            String cnpj = lojaJson.getString("cnpj");
            String endereco = lojaJson.getString("endereco");

            Loja loja = new Loja(nome, email, senha, cnpj, endereco);
            lojas.add(loja);
        }

        return lojas;
    }

    public static void updateLoja(String cnpj, Loja updatedLoja) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);
        boolean isUpdated = false;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lojaJson = jsonArray.getJSONObject(i);
            if (lojaJson.getString("cnpj").equals(cnpj)) {
                lojaJson.put("nome", updatedLoja.getNome());
                lojaJson.put("email", updatedLoja.getEmail());
                lojaJson.put("senha", updatedLoja.getSenha());
                lojaJson.put("endereco", updatedLoja.getEndereco());

                isUpdated = true;
                break;
            }
        }

        if (isUpdated) {
            JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);
            System.out.println("Loja updated successfully.");
        } else {
            System.out.println("Loja not found.");
        }
    }

    public static void deleteLoja(String cnpj) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);
        boolean isDeleted = false;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lojaJson = jsonArray.getJSONObject(i);
            if (lojaJson.getString("cnpj").equals(cnpj)) {
                jsonArray.remove(i);
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);
            System.out.println("Loja deleted successfully.");
        } else {
            System.out.println("Loja not found.");
        }
    }

}
