package unit.repositories;

import entities.Comprador;
import org.junit.jupiter.api.Test;
import repositories.CompradorRepository;
import util.JsonFileCRUDCompradorUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CompradorRepositoryTest {

    Comprador comprador = new Comprador("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    List<Comprador> compradorArray = Arrays.asList(comprador, new Comprador());
    @Test
    public void getCompradorByCpf() throws Exception {
        String cpf = comprador.getCpf();
        JsonFileCRUDCompradorUtil mock = mock(JsonFileCRUDCompradorUtil.class);
        when(mock.getCompradorByCpf(cpf)).thenReturn(comprador);
        CompradorRepository repository = new CompradorRepository(mock);
        assertTrue(repository.getCompradorByCpf(cpf).equals(comprador));
        verify(mock, times(1)).getCompradorByCpf(cpf);
    }
    @Test
    public void getAllCompradores() throws Exception {
        JsonFileCRUDCompradorUtil mock = mock();
        when(mock.getAllCompradores()).thenReturn(compradorArray);
        CompradorRepository repository = new CompradorRepository(mock);
        assertTrue(repository.getAllCompradores().equals(compradorArray));
        verify(mock, times(1)).getAllCompradores();
    }
    @Test
    public void createComprador() throws Exception {
        JsonFileCRUDCompradorUtil mock = mock();
        CompradorRepository repository = new CompradorRepository(mock);
        repository.createComprador(comprador);
        verify(mock, times(1)).createComprador(comprador);
    }
    @Test
    public void updateComprador() throws Exception {
        String cpf = comprador.getCpf();
        JsonFileCRUDCompradorUtil mock = mock();
        CompradorRepository repository = new CompradorRepository(mock);
        repository.updateComprador(cpf, comprador);
        verify(mock, times(1)).updateComprador(cpf, comprador);
    }
    @Test
    public void deleteComprador() throws Exception {
        String cpf = comprador.getCpf();
        JsonFileCRUDCompradorUtil mock = mock();
        CompradorRepository repository = new CompradorRepository(mock);
        repository.deleteComprador(cpf);
        verify(mock, times(1)).deleteComprador(cpf);
    }
}
