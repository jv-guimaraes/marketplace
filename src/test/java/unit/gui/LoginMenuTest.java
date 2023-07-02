package unit.gui;
import gui.AdminMenu;
import gui.CompradorMenu;
import gui.GuiUtil;
import gui.LoginMenu;
import gui.LojaMenu;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import services.CompradorService;
import services.LojaService;
import validation.Validation;

import static org.mockito.Mockito.*;

public class LoginMenuTest {
    class LoginMenuRun extends LoginMenu {
        public LoginMenuRun(GuiUtil util, AdminMenu adminMenu, LojaMenu lojaMenu, CompradorMenu compradorMenu, LojaService lojaService, CompradorService compradorService) {
            super(util, adminMenu, lojaMenu, compradorMenu, lojaService, compradorService);
        }
        @Override
        public void compradorMenu(){
            return;
        }
        @Override
        public void lojaMenu(){
            return;
        }
    }
    class LoginMenuComprador extends LoginMenu {
        public LoginMenuComprador(GuiUtil util, AdminMenu adminMenu, LojaMenu lojaMenu, CompradorMenu compradorMenu, LojaService lojaService, CompradorService compradorService) {
            super(util, adminMenu, lojaMenu, compradorMenu, lojaService, compradorService);
        }
        @Override
        public void compradorLogin(){
            return;
        }
        @Override
        public void compradorSignup(){
            return;
        }
    }
    class LoginMenuLoja extends LoginMenu {
        public LoginMenuLoja(GuiUtil util, AdminMenu adminMenu, LojaMenu lojaMenu, CompradorMenu compradorMenu, LojaService lojaService, CompradorService compradorService) {
            super(util, adminMenu, lojaMenu, compradorMenu, lojaService, compradorService);
        }
        @Override
        public void lojaLogin(){
            return;
        }
        @Override
        public void lojaSignup(){
            return;
        }
    }

    @Test
    public void runOptionComprador(){
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuRun menu = new LoginMenuRun(
            guimock,
            adminMock,
            lojaMock,
            compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );
        when(guimock.getNumero()).thenReturn(1).thenReturn(4);
        menu.run();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock, times(1)).print("Bem vindo ao Marketplace!");
        inOrderGui.verify(guimock, times(1)).print("Selecione o tipo de usuário:");
        inOrderGui.verify(guimock, times(1)).print("1 - Comprador");
        inOrderGui.verify(guimock, times(1)).print("2 - Loja");
        inOrderGui.verify(guimock, times(1)).print("3 - Admninistrador");
        inOrderGui.verify(guimock, times(0)).print("Escolha inválida!");

    }
    @Test
    public void runOptionLoja(){
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuRun menu = new LoginMenuRun(
                guimock,
                adminMock,
                lojaMock,
                compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );
        when(guimock.getNumero()).thenReturn(2).thenReturn(4);
        menu.run();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock, times(1)).print("Bem vindo ao Marketplace!");
        inOrderGui.verify(guimock, times(1)).print("Selecione o tipo de usuário:");
        inOrderGui.verify(guimock, times(1)).print("1 - Comprador");
        inOrderGui.verify(guimock, times(1)).print("2 - Loja");
        inOrderGui.verify(guimock, times(1)).print("3 - Admninistrador");
        inOrderGui.verify(guimock, times(0)).print("Escolha inválida!");

    }
    @Test
    public void runOptionAdmninistrador(){
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuRun menu = new LoginMenuRun(
                guimock,
                adminMock,
                lojaMock,
                compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );
        when(guimock.getNumero()).thenReturn(3).thenReturn(4);
        menu.run();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock, times(1)).print("Bem vindo ao Marketplace!");
        inOrderGui.verify(guimock, times(1)).print("Selecione o tipo de usuário:");
        inOrderGui.verify(guimock, times(1)).print("1 - Comprador");
        inOrderGui.verify(guimock, times(1)).print("2 - Loja");
        inOrderGui.verify(guimock, times(1)).print("3 - Admninistrador");
        verify(adminMock, times(1)).run();
        inOrderGui.verify(guimock, times(0)).print("Escolha inválida!");

    }
    @Test
    public void runOptionInvalidOption(){
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuRun menu = new LoginMenuRun(
                guimock,
                adminMock,
                lojaMock,
                compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );
        when(guimock.getNumero()).thenReturn(5).thenReturn(4);
        menu.run();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock, times(1)).print("Bem vindo ao Marketplace!");
        inOrderGui.verify(guimock, times(1)).print("Selecione o tipo de usuário:");
        inOrderGui.verify(guimock, times(1)).print("1 - Comprador");
        inOrderGui.verify(guimock, times(1)).print("2 - Loja");
        inOrderGui.verify(guimock, times(1)).print("3 - Admninistrador");
        inOrderGui.verify(guimock, times(1)).print("Escolha inválida!");
    }
    @Test
    public void OptionCompradorLogin(){
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuComprador menu = new LoginMenuComprador(
                guimock,
                adminMock,
                lojaMock,
                compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );
        when(guimock.getNumero()).thenReturn(1);
        menu.compradorMenu();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock, times(1)).print("COMPRADOR");
        inOrderGui.verify(guimock, times(1)).print("1 - Fazer login");
        inOrderGui.verify(guimock, times(1)).print("2 - Criar conta");
        inOrderGui.verify(guimock, times(0)).print("Escolha inválida!");
    }
    @Test
    public void OptionCompradorConta(){
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuComprador menu = new LoginMenuComprador(
                guimock,
                adminMock,
                lojaMock,
                compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );
        when(guimock.getNumero()).thenReturn(2);
        menu.compradorMenu();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock, times(1)).print("COMPRADOR");
        inOrderGui.verify(guimock, times(1)).print("1 - Fazer login");
        inOrderGui.verify(guimock, times(1)).print("2 - Criar conta");
        inOrderGui.verify(guimock, times(0)).print("Escolha inválida!");
    }
    @Test
    public void OptionCompradorInvalidOption() {
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuComprador menu = new LoginMenuComprador(
                guimock,
                adminMock,
                lojaMock,
                compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );
        when(guimock.getNumero()).thenReturn(3);
        menu.compradorMenu();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock, times(1)).print("COMPRADOR");
        inOrderGui.verify(guimock, times(1)).print("1 - Fazer login");
        inOrderGui.verify(guimock, times(1)).print("2 - Criar conta");
        inOrderGui.verify(guimock, times(1)).print("Escolha inválida!");
    }
    @Test
    public void compradorLogin(){
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuRun menu = new LoginMenuRun(
                guimock,
                adminMock,
                lojaMock,
                compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );

        String emailNotRegistered = "algo1@gmail.com";
        String emailRegistered = "algo@gmail.com";
        String senhaInvalida = "wrongPasswd";
        String senhaValida = "rightPasswd";

        when(guimock.receberString(eq("Email"), any())).thenReturn(emailNotRegistered).thenReturn(emailRegistered);
        when(compradorServiceMock.emailCadastrado(emailNotRegistered)).thenReturn(false);
        when(compradorServiceMock.emailCadastrado(emailRegistered)).thenReturn(true);

        when(guimock.receberString(eq("Senha"), any())).thenReturn(senhaInvalida).thenReturn(senhaValida);
        when(compradorServiceMock.loginValido(emailRegistered, senhaInvalida)).thenReturn(false);
        when(compradorServiceMock.loginValido(emailRegistered, senhaValida)).thenReturn(true);

        menu.compradorLogin();

        InOrder inOrderService = inOrder(compradorServiceMock);
        InOrder inOrderGui = inOrder(guimock);
        inOrderService.verify(compradorServiceMock, times(1)).emailCadastrado(emailNotRegistered);
        inOrderGui.verify(guimock, times(1)).print("Email não encontrado.");
        inOrderService.verify(compradorServiceMock, times(1)).emailCadastrado(emailRegistered);
        inOrderGui.verify(guimock, times(1)).print("Senha incorreta.");
        inOrderService.verify(compradorServiceMock, times(1)).loginValido(emailRegistered, senhaInvalida);
        inOrderService.verify(compradorServiceMock, times(1)).loginValido(emailRegistered, senhaValida);
        verify(compradorMock, times(1)).run(emailRegistered);
    }
    @Test
    public void lojaMenuLogin(){
        GuiUtil guimock = mock(GuiUtil.class);
        AdminMenu adminMock = mock(AdminMenu.class);
        LojaMenu lojaMock = mock(LojaMenu.class);
        CompradorMenu compradorMock = mock(CompradorMenu.class);
        LojaService lojaServiceMock = mock(LojaService.class);
        CompradorService compradorServiceMock = mock(CompradorService.class);
        LoginMenuLoja menu = new LoginMenuLoja(
                guimock,
                adminMock,
                lojaMock,
                compradorMock,
                lojaServiceMock,
                compradorServiceMock
        );
        when(guimock.getNumero()).thenReturn(1).thenReturn(3);
        menu.lojaMenu();
        InOrder inOrderGui = inOrder(guimock);
        inOrderGui.verify(guimock, times(1)).print("LOJA");
        inOrderGui.verify(guimock, times(1)).print("1 - Fazer login");
        inOrderGui.verify(guimock, times(1)).print("2 - Criar conta");
        inOrderGui.verify(guimock, times(0)).print("Escolha inválida!");
    }
}
