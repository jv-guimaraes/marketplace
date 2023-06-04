package unit.JsonFiles;

import entities.Comprador;
import org.junit.jupiter.api.Test;
import repositories.CompradorRepository;
import util.JsonFileCRUDCompradorUtil;
import util.JsonFileUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import org.json.JSONArray;
public class JsonFileCRUDCompradorUtilTest {

    Comprador comprador = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    List<Comprador> compradorArray = Arrays.asList(comprador, new Comprador());
    JSONArray getFilledArray = new JSONArray();

    class MyJsonFileUtil extends JsonFileUtil{
        @Override
        public JSONArray loadJsonArray(String path){
            return super.loadJsonArray(path);
        }
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
}
