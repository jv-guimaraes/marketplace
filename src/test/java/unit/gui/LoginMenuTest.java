package unit.gui;
import gui.AdminMenu;
import gui.CompradorMenu;
import gui.GuiUtil;
import gui.LoginMenu;
import gui.LojaMenu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginMenuTest {

    @Test
    public void runOption1(){
        GuiUtil guimock = mock();
        AdminMenu adminMock = mock();
        LojaMenu lojaMock = mock();
        CompradorMenu compradorMock = mock();
        LoginMenu menu = new LoginMenu(
            guimock,
            adminMock,
            lojaMock,
            compradorMock
        );
        when(guimock.getNumero()).thenReturn(1);
        menu.run();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock).print("Bem vindo ao Marketplace!");
    }
}
