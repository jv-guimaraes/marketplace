package unit.services;

import entities.Loja;
import infrastructure.repositories.LojaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import services.LojaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LojaServiceTest {
    Loja loja;
    Loja lojaNotCreated;
    List<Loja> lojasArrayNotCreated;
    List<Loja> lojasArray;

    @BeforeEach
    void addToArray() {
        loja = new Loja("josé", "hugo@gmail.com", "mustbe a hash", "701.254.231-72", "myhome");
        lojaNotCreated = new Loja("maria", "maria@gmail.com", "other a hash", "701.254.231-73", "otherplace");
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
    public void createLojaDefault() throws Exception {
        LojaService service = new LojaService();
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
    public void getLojaByCnpjThatExists(){
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        assertEquals(service.getLojaByCnpj(loja.getCnpj()), loja);
        verify(mock, times(1)).getAllLojas();
    }
    @Test
    public void getLojaByCnpjThatNotExists(){
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        assertEquals(service.getLojaByCnpj(lojaNotCreated.getCnpj()), null);
        verify(mock, times(1)).getAllLojas();
    }
    @Test
    public void emailCadastradoMustReturnTrue() {
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        assertTrue(service.emailCadastrado(loja.getEmail()));
        verify(mock, times(1)).getAllLojas();
    }
    @Test
    public void emailCadastradoMustReturnFalse() {
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        assertFalse(service.emailCadastrado(lojaNotCreated.getEmail()));
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void loginValido() {
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        assertTrue(service.loginValido(loja.getEmail(), loja.getSenha()));
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void loginInvalido() {
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        assertFalse(service.loginValido(lojaNotCreated.getEmail(), lojaNotCreated.getSenha()));
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void getCnpjFromEmail(){
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        assertEquals(service.getCnpjFromEmail(loja.getEmail()),loja.getCnpj());
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void getCnpjFromEmailThatNotExists(){
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        assertNull(service.getCnpjFromEmail(lojaNotCreated.getEmail()), lojaNotCreated.getCnpj());
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void setAvaliacao(){
        String avaliacao = "boa";
        List<Loja> copyLojasArray = new ArrayList<Loja>();
        copyLojasArray.addAll(lojasArrayNotCreated);
        Loja alteredLoja = loja.clone();
        alteredLoja.setAvaliacao(avaliacao);
        copyLojasArray.set(0, alteredLoja);
        LojaRepository mock = mock();
        LojaService service = new LojaService(mock);
        when(mock.getAllLojas()).thenReturn(lojasArrayNotCreated);
        service.setAvaliacao(loja.getCnpj(), avaliacao);
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock, times(1)).getAllLojas();
        inOrder.verify(mock, times(1)).setAllLojas(copyLojasArray);

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