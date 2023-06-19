package unit.services;

import entities.Loja;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import infrastructure.repositories.LojaRepository;
import org.mockito.InOrder;
import services.LojaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LojaServiceTest {
    Loja loja = new Loja("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");
    Loja lojaNotCreated = new Loja("maria", "maria@gmail.com", "other a hash", "701.254.231-73", "otherplace");
    List<Loja> lojasArrayNotCreated;
    List<Loja> lojasArray;
    @BeforeEach
    void addToArray() {
        lojasArrayNotCreated = new ArrayList<Loja>(Arrays.asList(loja));
        lojasArray = new ArrayList<Loja>(Arrays.asList(loja, lojaNotCreated));
    }
    @Test
    public void getAllLojas() throws Exception {
        LojaRepository mock = mock();
        when(mock.getAllLojas()).thenReturn(lojasArray);
        LojaService service = new LojaService(mock);
        assertEquals(service.getAllLojas(), lojasArray);
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void createLojaNotCreated() throws Exception {
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        service.createLoja(lojaNotCreated);
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock, times(1)).getAllLojas();
        inOrder.verify(mock, times(1)).setAllLojas(lojasArray);
    }

    @Test
    public void createLojaAlreadyCreated() throws Exception {
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        service.createLoja(loja);
        verify(mock, times(0)).setAllLojas(lojasArray);
        verify(mock, times(0)).setAllLojas(lojasArrayNotCreated);
    }

    @Test
    public void updateLoja() throws Exception {
        LojaRepository mock = mock();
        List<Loja> copyLojasArray = new ArrayList<Loja>();
        copyLojasArray.addAll(lojasArray);
        when(mock.getAllLojas()).thenReturn(lojasArray);
        LojaService service = new LojaService(mock);
        Loja alteredLoja = loja.clone();
        alteredLoja.setEmail("other@email.com");
        copyLojasArray.set(0, alteredLoja);
        service.updateLoja(loja.getCnpj(), alteredLoja);
        verify(mock, times(1)).setAllLojas(copyLojasArray);
    }

    @Test
    public void deleteLoja() throws Exception {
        String cnpj = loja.getCnpj();
        LojaRepository mock = mock();
        when(mock.getAllLojas()).thenReturn(lojasArray);
        List<Loja> copyLojasArray = new ArrayList<Loja>();
        copyLojasArray.addAll(lojasArray);
        copyLojasArray.remove(0);
        LojaService service = new LojaService(mock);
        service.deleteLoja(cnpj);
        verify(mock, times(1)).setAllLojas(copyLojasArray);
    }
}