package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import entities.Loja;
import org.json.*;

public class JsonFileCRUDLojaUtil {
    private final JsonFileUtil jsonHandler;

    private final String JSON_FILE_PATH = "database/lojas.json";

    public JsonFileCRUDLojaUtil(JsonFileUtil jsonHandler){
        this.jsonHandler = jsonHandler;
    }
    public String getFilePath(){
        return JSON_FILE_PATH;
    }
    private File getFile(){
        return new File(JSON_FILE_PATH);
    }

    public void createLoja(Loja loja) {
        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);

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

        this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);
        System.out.println("Loja created successfully.");
    }

    public Loja getLojaByCnpj(String cnpj) {
        File file = this.getFile();
        if (!file.exists()) {
            return null;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lojaJson = jsonArray.getJSONObject(i);
            if (lojaJson.getString("cnpj").equals(cnpj)) {
                return new Loja(lojaJson);
            }
        }

        return null;
    }

    public List<Loja> getAllLojas() {
        File file = this.getFile();
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
        List<Loja> lojas = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lojaJson = jsonArray.getJSONObject(i);
            Loja loja = new Loja(lojaJson);
            lojas.add(loja);
        }

        return lojas;
    }

    public void updateLoja(String cnpj, Loja updatedLoja) {
        File file = this.getFile();
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
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
            this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);
            System.out.println("Loja updated successfully.");
        } else {
            System.out.println("Loja not found.");
        }
    }

    public void deleteLoja(String cnpj) {
        File file = this.getFile();
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
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
            this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);
            System.out.println("Loja deleted successfully.");
        } else {
            System.out.println("Loja not found.");
        }
    }

}
