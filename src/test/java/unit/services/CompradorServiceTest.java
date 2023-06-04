package unit.services;
import entities.Comprador;
import services.CompradorService;
import repositories.CompradorRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

public class CompradorServiceTest {
    Comprador comprador = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    List<Comprador> compradorArray = Arrays.asList(comprador, new Comprador());
    @Test
    public void getCompradorByCpf() throws Exception {
        String cpf = comprador.getCpf();
        CompradorRepository mock = mock();
        when(mock.getCompradorByCpf(cpf)).thenReturn(comprador);
        CompradorService service = new CompradorService(mock);
        assertTrue(service.getCompradorByCpf(cpf).equals(comprador));
        verify(mock, times(1)).getCompradorByCpf(cpf);
    }
    @Test
    public void getAllCompradores() throws Exception {
        CompradorRepository mock = mock();
        when(mock.getAllCompradores()).thenReturn(compradorArray);
        CompradorService service = new CompradorService(mock);
        assertTrue(service.getAllCompradores().equals(compradorArray));
        verify(mock, times(1)).getAllCompradores();
    }
    @Test
    public void createComprador() throws Exception {
        CompradorRepository mock = mock();
        CompradorService service = new CompradorService(mock);
        service.createComprador(comprador);
        verify(mock, times(1)).createComprador(comprador);
    }
    @Test
    public void updateComprador() throws Exception {
        String cpf = comprador.getCpf();
        CompradorRepository mock = mock();
        CompradorService service = new CompradorService(mock);
        service.updateComprador(cpf, comprador);
        verify(mock, times(1)).updateComprador(cpf, comprador);
    }
    @Test
    public void deleteComprador() throws Exception {
        String cpf = comprador.getCpf();
        CompradorRepository mock = mock();
        CompradorService service = new CompradorService(mock);
        service.deleteComprador(cpf);
        verify(mock, times(1)).deleteComprador(cpf);
    }
}