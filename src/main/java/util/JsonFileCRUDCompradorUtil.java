package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import entities.Comprador;
import org.json.*;

public class JsonFileCRUDCompradorUtil {
    private final JsonFileUtil jsonHandler;

    public JsonFileCRUDCompradorUtil(JsonFileUtil jsonHandler){
        this.jsonHandler = jsonHandler;
    }

    private static String JSON_FILE_PATH = "database/compradores.json";
    public String getFilePath(){
        return JSON_FILE_PATH;
    }

    public void createComprador(Comprador comprador) {
        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);

        // Check if the CPF already exists
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);
            if (compradorJson.getString("cpf").equals(comprador.getCpf())) {
                System.out.println("Cannot create Comprador. CPF already exists.");
                return;
            }
        }

        // Check if the email already exists
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);
            if (compradorJson.getString("email").equals(comprador.getEmail())) {
                System.out.println("Cannot create Comprador. Email already exists.");
                return;
            }
        }

        // CPF and email don't exist, proceed with creating the Comprador
        JSONObject compradorJson = new JSONObject(comprador);
        jsonArray.put(compradorJson);

        this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);
        System.out.println("Comprador created successfully.");
    }

    public Comprador getCompradorByCpf(String cpf) {
        File file = this.getFile();
        if (!file.exists()) {
            return null;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);
            if (compradorJson.getString("cpf").equals(cpf)) {
                return new Comprador(compradorJson);
            }
        }

        return null;
    }

    public void updateComprador(String cpf, Comprador updatedComprador) {
        File file = this.getFile();
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
        boolean isUpdated = false;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);
            if (compradorJson.getString("cpf").equals(cpf)) {
                compradorJson.put("nome", updatedComprador.getNome());
                compradorJson.put("email", updatedComprador.getEmail());
                compradorJson.put("senha", updatedComprador.getSenha());
                compradorJson.put("endereco", updatedComprador.getEndereco());

                isUpdated = true;
                break;
            }
        }

        if (isUpdated) {
            this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);
            System.out.println("Comprador updated successfully.");
        } else {
            System.out.println("Comprador not found.");
        }
    }
    private File getFile(){
        return new File(JSON_FILE_PATH);
    }

    public void deleteComprador(String cpf) {
        File file = this.getFile();
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
        boolean isDeleted = false;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);
            if (compradorJson.getString("cpf").equals(cpf)) {
                jsonArray.remove(i);
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            this.jsonHandler.saveJsonArray(jsonArray, JSON_FILE_PATH);
            System.out.println("Comprador deleted successfully.");
        } else {
            System.out.println("Comprador not found.");
        }
    }

    public List<Comprador> getAllCompradores() {
        File file = this.getFile();
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = this.jsonHandler.loadJsonArray(JSON_FILE_PATH);
        List<Comprador> compradores = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);
            Comprador comprador = new Comprador(compradorJson);
            compradores.add(comprador);
        }

        return compradores;
    }

}