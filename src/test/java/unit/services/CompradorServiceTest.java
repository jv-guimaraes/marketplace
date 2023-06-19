package unit.services;

import entities.Comprador;
import entities.Loja;
import infrastructure.repositories.LojaRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import infrastructure.repositories.CompradorRepository;
import org.mockito.InOrder;
import services.CompradorService;
import services.LojaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CompradorServiceTest {
    Comprador comprador = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    Comprador compradorNotCreated = new Comprador("marcio", "marcio@gmail.com", "asda hash", "701.254.231-73", "otherhome");
    List<Comprador> compradorArrayNotCreated;
    List<Comprador> compradorArray;
    JSONObject compradorJSON;
    @BeforeEach
    void addToArray() {
        compradorJSON = new JSONObject();
        compradorJSON.put("nome", compradorNotCreated.getNome());
        compradorJSON.put("email", compradorNotCreated.getEmail());
        compradorJSON.put("senha", compradorNotCreated.getSenha());
        compradorJSON.put("cpf", compradorNotCreated.getCpf());
        compradorJSON.put("endereco", compradorNotCreated.getEndereco());
        compradorJSON.put("carrinho", new JSONArray());
        compradorJSON.put("produtos", new JSONArray());
        compradorArrayNotCreated = new ArrayList<Comprador>(Arrays.asList(comprador));
        compradorArray = new ArrayList<Comprador>(Arrays.asList(comprador, compradorNotCreated));
    }
    @Test
    public void getCompradorByCpf() throws Exception {
        String cpf = comprador.getCpf();
        CompradorRepository mock = mock();
        when(mock.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mock);
        assertEquals(service.getCompradorByCpf(cpf), comprador);
        verify(mock, times(1)).getAllCompradores();
    }

    @Test
    public void getAllCompradores() throws Exception {
        CompradorRepository mock = mock();
        when(mock.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mock);
        assertEquals(service.getAllCompradores(), compradorArray);
        verify(mock, times(1)).getAllCompradores();
    }


    @Test
    public void createCompradorNotCreated() throws Exception {
        CompradorRepository mock = mock(CompradorRepository.class);
        CompradorService service = new CompradorService(mock);
        when(mock.getAllCompradores()).thenReturn(compradorArrayNotCreated);
        service.createComprador(compradorNotCreated);
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock, times(1)).getAllCompradores();
        //inOrder.verify(mock, times(1)).createComprador(any(JSONObject.class));
    }

    @Test
    public void createCompradorAlreadyCreated() throws Exception {
        CompradorRepository mock = mock();
        CompradorService service = new CompradorService(mock);
        when(mock.getAllCompradores()).thenReturn(compradorArray);
        service.createComprador(comprador);
        verify(mock, times(0)).setAllCompradores(compradorArray);
        verify(mock, times(0)).setAllCompradores(compradorArrayNotCreated);
    }

    @Test
    public void updateComprador() throws Exception {
        CompradorRepository mock = mock();
        List<Comprador> copyCompradorArray = new ArrayList<Comprador>();
        copyCompradorArray.addAll(compradorArray);
        when(mock.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mock);
        Comprador alteredComprador = comprador.clone();
        alteredComprador.setEmail("other@email.com");
        copyCompradorArray.set(0, alteredComprador);
        service.updateComprador(comprador.getCpf(), alteredComprador);
        verify(mock, times(1)).setAllCompradores(copyCompradorArray);
    }

    @Test
    public void deleteComprador() throws Exception {
        String cpf = comprador.getCpf();
        CompradorRepository mock = mock();
        when(mock.getAllCompradores()).thenReturn(compradorArray);
        List<Comprador> copyCompradorArray = new ArrayList<Comprador>();
        copyCompradorArray.addAll(compradorArray);
        copyCompradorArray.remove(0);
        CompradorService service = new CompradorService(mock);
        service.deleteComprador(cpf);
        verify(mock, times(1)).setAllCompradores(copyCompradorArray);
    }
}