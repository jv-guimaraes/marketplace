package unit.repositories;

import entities.Loja;
import org.junit.jupiter.api.Test;
import repositories.LojaRepository;
import util.JsonFileCRUDLojaUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LojaRepositoryTest {

    Loja loja = new Loja("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    List<Loja> lojaArray = Arrays.asList(loja, new Loja());

    @Test
    public void getLojaByCnpj() throws Exception {
        String cpf = loja.getCnpj();
        JsonFileCRUDLojaUtil mock = mock(JsonFileCRUDLojaUtil.class);
        when(mock.getLojaByCnpj(cpf)).thenReturn(loja);
        LojaRepository repository = new LojaRepository(mock);
        assertTrue(repository.getLojaByCnpj(cpf).equals(loja));
        verify(mock, times(1)).getLojaByCnpj(cpf);
    }

    @Test
    public void getAllLojas() throws Exception {
        JsonFileCRUDLojaUtil mock = mock();
        when(mock.getAllLojas()).thenReturn(lojaArray);
        LojaRepository repository = new LojaRepository(mock);
        assertEquals(repository.getAllLojas(), lojaArray);
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void createLoja() throws Exception {
        JsonFileCRUDLojaUtil mock = mock();
        LojaRepository repository = new LojaRepository(mock);
        repository.createLoja(loja);
        verify(mock, times(1)).createLoja(loja);
    }

    @Test
    public void updateLoja() throws Exception {
        String cpf = loja.getCnpj();
        JsonFileCRUDLojaUtil mock = mock();
        LojaRepository repository = new LojaRepository(mock);
        repository.updateLoja(cpf, loja);
        verify(mock, times(1)).updateLoja(cpf, loja);
    }

    @Test
    public void deleteLoja() throws Exception {
        String cpf = loja.getCnpj();
        JsonFileCRUDLojaUtil mock = mock();
        LojaRepository repository = new LojaRepository(mock);
        repository.deleteLoja(cpf);
        verify(mock, times(1)).deleteLoja(cpf);
    }
}
