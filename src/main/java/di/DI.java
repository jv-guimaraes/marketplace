package di;

import controllers.CompradorController;
import controllers.LojaController;
import controllers.ProdutoController;
import repositories.CompradorRepository;
import repositories.LojaRepository;
import repositories.ProdutoRepository;
import services.CompradorService;
import services.LojaService;
import services.ProdutoService;
import util.JsonFileCRUDCompradorUtil;
import util.JsonFileCRUDLojaUtil;
import util.JsonFileCRUDProdutoUtil;
import util.JsonFileUtil;

public class DI {
    private static final JsonFileUtil jsonFileUtil = new JsonFileUtil();
    private static final JsonFileCRUDCompradorUtil jsonFileCRUDComprador = new JsonFileCRUDCompradorUtil(jsonFileUtil);
    private static final JsonFileCRUDLojaUtil jsonFileCRUDLoja = new JsonFileCRUDLojaUtil(jsonFileUtil);
    private static final JsonFileCRUDProdutoUtil jsonFileCRUDProduto = new JsonFileCRUDProdutoUtil(jsonFileUtil);
    private static final CompradorRepository compradorRepository = new CompradorRepository(jsonFileCRUDComprador);
    private static final LojaRepository lojaRepository = new LojaRepository(jsonFileCRUDLoja);
    private static final ProdutoRepository produtoRepository = new ProdutoRepository(jsonFileCRUDProduto);
    private static final LojaService lojaService = new LojaService(lojaRepository);
    private static final LojaController lojaController = new LojaController(lojaService);
    private static final CompradorService compradorService = new CompradorService(compradorRepository);
    private static final CompradorController compradorController = new CompradorController(compradorService);
    private static final ProdutoService produtoService = new ProdutoService(produtoRepository);
    private static final ProdutoController produtoController = new ProdutoController(produtoService);


    public CompradorController getCompradorController(){
        return this.compradorController;
    }
    public LojaController getLojaController(){
        return this.lojaController;
    }
    public ProdutoController getProdutoController(){
        return this.produtoController;
    }
}
