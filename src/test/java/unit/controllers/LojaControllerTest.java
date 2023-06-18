package unit.controllers;

import controllers.LojaController;
import entities.Loja;
import org.junit.jupiter.api.Test;
import services.LojaService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class LojaControllerTest {
    Loja loja = new Loja("americanas", "americanas@gmail.com", "mustbe a hash", "93.763.071/0001-27", "av.brasil 232, campina grande");

    List<Loja> lojaArray = Arrays.asList(loja, new Loja());

    @Test
    public void getLojagetCnpj() throws Exception {
        String cnpj = loja.getCnpj();
        LojaService mock = mock();
        when(mock.getLojaByCnpj(cnpj)).thenReturn(loja);
        LojaController controller = new LojaController(mock);
        assertTrue(controller.getLojaByCnpj(cnpj).equals(loja));
        verify(mock, times(1)).getLojaByCnpj(cnpj);
    }

    @Test
    public void getAllLojas() throws Exception {
        LojaService mock = mock();
        when(mock.getAllLojas()).thenReturn(lojaArray);
        LojaController controller = new LojaController(mock);
        assertTrue(controller.getAllLojas().equals(lojaArray));
        verify(mock, times(1)).getAllLojas();
    }

    @Test
    public void createLoja() throws Exception {
        LojaService mock = mock();
        LojaController controller = new LojaController(mock);
        controller.createLoja(loja);
        verify(mock, times(1)).createLoja(loja);
    }

    @Test
    public void updateLoja() throws Exception {
        String cnpj = loja.getCnpj();
        LojaService mock = mock();
        LojaController controller = new LojaController(mock);
        controller.updateLoja(cnpj, loja);
        verify(mock, times(1)).updateLoja(cnpj, loja);
    }

    @Test
    public void deleteLoja() throws Exception {
        String cnpj = loja.getCnpj();
        LojaService mock = mock();
        LojaController controller = new LojaController(mock);
        controller.deleteLoja(cnpj);
        verify(mock, times(1)).deleteLoja(cnpj);
    }
}