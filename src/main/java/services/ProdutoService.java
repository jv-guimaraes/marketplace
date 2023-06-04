package services;

import entities.Produto;
import port.services.IProdutoService;
import repositories.ProdutoRepository;

import java.util.List;

public class ProdutoService implements IProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository repository) {
        this.produtoRepository = repository;
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.getAllProdutos();
    }

    public Produto getProdutoById(long id) {
        return produtoRepository.getProdutoById(id);
    }

    public void createProduto(Produto produto) {
        produtoRepository.createProduto(produto);
    }

    public void updateProduto(long id, Produto produto) {
        produtoRepository.updateProduto(id, produto);
    }

    public void deleteProduto(long id) {
        produtoRepository.deleteProduto(id);
    }

    public List<Produto> getProdutosByLoja(String cnpj) {
        return produtoRepository.getProdutosByLoja(cnpj);
    }
}
