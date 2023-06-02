package unit.controllers;
import controllers.CompradorController;
import entities.Comprador;
import services.CompradorService;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CompradorControllerTest {
    Comprador comprador = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    List<Comprador> compradorArray = Arrays.asList(comprador, new Comprador());
    @Test
    public void getCompradorByCpf() throws Exception {
        String cpf = comprador.getCpf();
        CompradorService mock = mock();
        when(mock.getCompradorByCpf(cpf)).thenReturn(comprador);
        CompradorController controller = new CompradorController(mock);
        assertTrue(controller.getCompradorByCpf(cpf).equals(comprador));
        verify(mock, times(1)).getCompradorByCpf(cpf);
    }
    @Test
    public void getAllCompradores() throws Exception {
        CompradorService mock = mock();
        when(mock.getAllCompradores()).thenReturn(compradorArray);
        CompradorController controller = new CompradorController(mock);
        assertTrue(controller.getAllCompradores().equals(compradorArray));
        verify(mock, times(1)).getAllCompradores();
    }
    @Test
    public void createComprador() throws Exception {
        CompradorService mock = mock();
        CompradorController controller = new CompradorController(mock);
        controller.createComprador(comprador);
        verify(mock).createComprador(comprador);
    }
    @Test
    public void updateComprador() throws Exception {
        String cpf = comprador.getCpf();
        CompradorService mock = mock();
        CompradorController controller = new CompradorController(mock);
        controller.updateComprador(cpf, comprador);
        verify(mock).updateComprador(cpf, comprador);
    }
    @Test
    public void deleteComprador() throws Exception {
        String cpf = comprador.getCpf();
        CompradorService mock = mock();
        CompradorController controller = new CompradorController(mock);
        controller.deleteComprador(cpf);
        verify(mock).deleteComprador(cpf);
    }
}