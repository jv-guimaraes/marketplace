package unit.JsonFiles;

import entities.Comprador;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;/*
import util.JsonFileCRUDCompradorUtil;
import util.JsonFileUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class JsonFileCRUDCompradorUtilTest {

    Comprador comprador = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    List<Comprador> compradorArray = Arrays.asList(comprador, new Comprador());
    JSONArray getFilledArray;

    @BeforeEach
    void addToArray() {
        JSONObject compradorJSON = new JSONObject();
        compradorJSON.put("nome", comprador.getNome());
        compradorJSON.put("email", comprador.getEmail());
        compradorJSON.put("endereco", comprador.getEndereco());
        compradorJSON.put("senha", comprador.getSenha());
        compradorJSON.put("cpf", comprador.getCpf());
        List<JSONObject> array = List.of(compradorJSON);
        getFilledArray = new JSONArray(array);
    }

    @Test
    public void getCompradorByCpf() throws Exception {
        String cpf = comprador.getCpf();
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDCompradorUtil jsonFileCRUD = new JsonFileCRUDCompradorUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        assertTrue(jsonFileCRUD.getCompradorByCpf(cpf).equals(comprador));
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    @Test
    public void getAllCompradores() throws Exception {
        List<Comprador> result = Collections.singletonList(comprador);
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDCompradorUtil jsonFileCRUD = new JsonFileCRUDCompradorUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        assertTrue(jsonFileCRUD.getAllCompradores().equals(result));
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    @Test
    public void createComprador() throws Exception {
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDCompradorUtil jsonFileCRUD = new JsonFileCRUDCompradorUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.createComprador(comprador);
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    @Test
    public void updateComprador() throws Exception {
        String cpf = comprador.getCpf();

        String alteredEmail = "altered@gmail.com";

        Comprador alteredComprador = comprador.clone();
        alteredComprador.setEmail(alteredEmail);

        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDCompradorUtil jsonFileCRUD = new JsonFileCRUDCompradorUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.updateComprador(cpf, alteredComprador);
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    @Test
    public void deleteComprador() throws Exception {
        String cpf = comprador.getCpf();
        MyJsonFileUtil mock = mock(MyJsonFileUtil.class);
        JsonFileCRUDCompradorUtil jsonFileCRUD = new JsonFileCRUDCompradorUtil(mock);
        String filePath = jsonFileCRUD.getFilePath();

        when(mock.loadJsonArray(filePath)).thenReturn(getFilledArray);
        jsonFileCRUD.deleteComprador(cpf);
        verify(mock, times(1)).loadJsonArray(filePath);
    }

    class MyJsonFileUtil extends JsonFileUtil {
        @Override
        public JSONArray loadJsonArray(String path) {
            return super.loadJsonArray(path);
        }
    }
}*/
