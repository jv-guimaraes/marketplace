package unit.di;

import controllers.CompradorController;
import controllers.LojaController;
import controllers.ProdutoController;
import di.DI;
import org.junit.jupiter.api.Test;


public class DITest {
    private final DI di = new DI();

    @Test
    public void getCompradorController() {
        assert (di.getCompradorController().getClass() == CompradorController.class);
    }

    @Test
    public void getLojaController() {
        assert (di.getLojaController().getClass() == LojaController.class);
    }

    @Test
    public void getProdutoController() {
        assert (di.getProdutoController().getClass() == ProdutoController.class);
    }
}
