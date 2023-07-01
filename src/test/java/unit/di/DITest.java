package unit.di;

import di.DI;
import org.junit.jupiter.api.Test;
import services.CompradorService;
import services.LojaService;
import services.ProdutoService;


public class DITest {
    private final DI di = new DI();

    @Test
    public void getCompradorService() {
        assert (di.getCompradorService().getClass() == CompradorService.class);
    }

    @Test
    public void getLojaService() {
        assert (di.getLojaService().getClass() == LojaService.class);
    }

    @Test
    public void getProdutoService() {
        assert (di.getProdutoService().getClass() == ProdutoService.class);
    }
}
