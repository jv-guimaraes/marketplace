package unit.JsonFiles;

import entities.Loja;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;/*
import util.JsonFileCRUDLojaUtil;
import util.JsonFileUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class JsonFileCRUDLojaUtilTest {

    Loja loja = new Loja("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    List<Loja> lojaArray = Arrays.asList(loja, new Loja());
    JSONArray getFilledArray;

    @BeforeEach
    void addToArray() {
        JSONObject lojaJSON = new JSONObject();
        lojaJSON.put("nome", loja.getNome());
        lojaJSON.put("email", loja.getEmail());
        lojaJSON.put("endereco", loja.getEndereco());
        lojaJSON.put("senha", loja.getSenha());
        lojaJSON.put("cnpj", loja.getCnpj());
        List<JSONObject> array = List.of(lojaJSON);
        getFilledArray = new JSONArray(array);
    }

    @Test
    public void getLojaByCnpj() throws Exception {
        String cnpj = loja.getCnpj();
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDLojaUtil jsonFileCRUD = new JsonFileCRUDLojaUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        assertTrue(jsonFileCRUD.getLojaByCnpj(cnpj).equals(loja));
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    @Test
    public void getAllLojas() throws Exception {
        List<Loja> result = Collections.singletonList(loja);
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDLojaUtil jsonFileCRUD = new JsonFileCRUDLojaUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        assertTrue(jsonFileCRUD.getAllLojas().equals(result));
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    @Test
    public void createLoja() throws Exception {
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDLojaUtil jsonFileCRUD = new JsonFileCRUDLojaUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.createLoja(loja);
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    @Test
    public void updateLoja() throws Exception {
        String cnpj = loja.getCnpj();

        String alteredEmail = "altered@gmail.com";

        Loja alteredLoja = loja.clone();
        alteredLoja.setEmail(alteredEmail);

        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDLojaUtil jsonFileCRUD = new JsonFileCRUDLojaUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.updateLoja(cnpj, alteredLoja);
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    @Test
    public void deleteLoja() throws Exception {
        String cnpj = loja.getCnpj();
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDLojaUtil jsonFileCRUD = new JsonFileCRUDLojaUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.deleteLoja(cnpj);
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    class MyJsonFileUtil extends JsonFileUtil {
        @Override
        public JSONArray loadJsonArray(String path) {
            return super.loadJsonArray(path);
        }
    }
}*/
