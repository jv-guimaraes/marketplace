package di;

import infrastructure.repositories.CompradorRepository;
import infrastructure.repositories.LojaRepository;
import infrastructure.repositories.ProdutoRepository;
import services.CompradorService;
import services.LojaService;
import services.ProdutoService;

public class DI {
    /*
    private static final JsonFileUtil jsonFileUtil = new JsonFileUtil();
    private static final JsonFileCRUDCompradorUtil jsonFileCRUDComprador = new JsonFileCRUDCompradorUtil(jsonFileUtil);
    private static final JsonFileCRUDLojaUtil jsonFileCRUDLoja = new JsonFileCRUDLojaUtil(jsonFileUtil);
    private static final JsonFileCRUDProdutoUtil jsonFileCRUDProduto = new JsonFileCRUDProdutoUtil(jsonFileUtil);*/
    private static final CompradorRepository compradorRepository = new CompradorRepository();
    private static final LojaRepository lojaRepository = new LojaRepository();
    private static final ProdutoRepository produtoRepository = new ProdutoRepository();
    private static final LojaService lojaService = new LojaService(lojaRepository);
    private static final ProdutoService produtoService = new ProdutoService(produtoRepository);
    private static final CompradorService compradorService = new CompradorService(compradorRepository, produtoService);


    public CompradorService getCompradorService() {
        return compradorService;
    }

    public LojaService getLojaService() {
        return lojaService;
    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }
}