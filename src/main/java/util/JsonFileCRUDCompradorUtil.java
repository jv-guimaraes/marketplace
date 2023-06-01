package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import entities.Comprador;
import org.json.*;

public class JsonFileCRUDCompradorUtil {

    private static final String JSON_FILE_PATH = "compradores.json";

    public static void createComprador(Comprador comprador) {
        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);

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

        JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);
        System.out.println("Comprador created successfully.");
    }

    public static Comprador getCompradorByCpf(String cpf) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return null;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);
            if (compradorJson.getString("cpf").equals(cpf)) {
                String nome = compradorJson.getString("nome");
                String email = compradorJson.getString("email");
                String senha = compradorJson.getString("senha");
                String endereco = compradorJson.getString("endereco");

                return new Comprador(nome, email, senha, cpf, endereco);
            }
        }

        return null;
    }

    public static void updateComprador(String cpf, Comprador updatedComprador) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);
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
            JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);
            System.out.println("Comprador updated successfully.");
        } else {
            System.out.println("Comprador not found.");
        }
    }

    public static void deleteComprador(String cpf) {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);
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
            JsonFileUtil.saveJsonArray(jsonArray, JSON_FILE_PATH);
            System.out.println("Comprador deleted successfully.");
        } else {
            System.out.println("Comprador not found.");
        }
    }

    public static List<Comprador> getAllCompradores() {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(JSON_FILE_PATH);
        List<Comprador> compradores = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject compradorJson = jsonArray.getJSONObject(i);
            String nome = compradorJson.getString("nome");
            String email = compradorJson.getString("email");
            String senha = compradorJson.getString("senha");
            String cpf = compradorJson.getString("cpf");
            String endereco = compradorJson.getString("endereco");

            Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);
            compradores.add(comprador);
        }

        return compradores;
    }

}