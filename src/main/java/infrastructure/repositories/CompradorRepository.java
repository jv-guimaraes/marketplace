package infrastructure.repositories;

import entities.Comprador;
import infrastructure.jsonFiles.JsonFileUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import services.ProdutoService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CompradorRepository {
    private String compradoresPath = "database/compradores.json";

    private final ProdutoRepository produtoRepository = new ProdutoRepository();

    private final ProdutoService produtoService = new ProdutoService();

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
            Comprador comprador = new Comprador(compradorJson);
            compradores.add(comprador);
        }

        return compradores;
    }

    public void setAllCompradores(List<Comprador> compradores) {
        JSONArray jsonArray = new JSONArray(compradores);
        JsonFileUtil.saveJsonArray(jsonArray, compradoresPath);
    }
}