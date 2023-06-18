package unit.services;

import entities.Loja;
import org.junit.jupiter.api.Test;
import repositories.LojaRepository;
import services.LojaService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LojaServiceTest {
    Loja comprador = new Loja("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");

    List<Loja> compradorArray = Arrays.asList(comprador, new Loja());

    @Test
    public void getLojaByCpf() throws Exception {
        String cnpj = comprador.getCnpj();
        LojaRepository mock = mock();
        when(mock.getLojaByCnpj(cnpj)).thenReturn(comprador);
        LojaService service = new LojaService(mock);
        assertEquals(service.getLojaByCnpj(cnpj), comprador);
        verify(mock, times(1)).getLojaByCnpj(cnpj);
    }

    @Test
    public void getAllLojas() throws Exception {
        LojaRepository mock = mock();
        when(mock.getAllLojas()).thenReturn(compradorArray);
        LojaService service = new LojaService(mock);
        assertEquals(service.getAllLojas(), compradorArray);
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void createLoja() throws Exception {
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        service.createLoja(comprador);
        verify(mock, times(1)).createLoja(comprador);
    }

    @Test
    public void updateLoja() throws Exception {
        String cnpj = comprador.getCnpj();
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        service.updateLoja(cnpj, comprador);
        verify(mock, times(1)).updateLoja(cnpj, comprador);
    }

    @Test
    public void deleteLoja() throws Exception {
        String cnpj = comprador.getCnpj();
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        service.deleteLoja(cnpj);
        verify(mock, times(1)).deleteLoja(cnpj);
    }
}