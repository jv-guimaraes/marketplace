import gui.LoginMenu;
import di.DI;

public class App {

    public static void main(String[] args) {
        DI di = new DI();
        LoginMenu loginMenu = di.getLoginMenu();
        loginMenu.run();
    }
}
