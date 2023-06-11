package repositories;

import entities.Loja;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LojaRepository {
    private String lojasPath = "database/lojas.json";

    public LojaRepository() {

    }

    public LojaRepository(String lojasPath) {
        this.lojasPath = lojasPath;
    }

    public List<Loja> getAllLojas() {
        File file = new File(lojasPath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = JsonFileUtil.loadJsonArray(lojasPath);
        List<Loja> lojas = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lojaJson = jsonArray.getJSONObject(i);
            Loja loja = new Loja(lojaJson);
            lojas.add(loja);
        }

        return lojas;
    }

    public void setAllLojas(List<Loja> lojas) {
        JSONArray jsonArray = new JSONArray(lojas);
        JsonFileUtil.saveJsonArray(jsonArray, lojasPath);
    }

}
