package controllers;

import entities.Produto;
import services.LojaService;
import services.ProdutoService;

import java.util.List;

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService service) {
        this.produtoService = service;
    }

    public List<Produto> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    public Produto getProdutoById(Long id) {
        return produtoService.getProdutoById(id);
    }

    public void createProduto(Produto produto) {
        produtoService.createProduto(produto);
    }

    public void updateProduto(Long id, Produto produto) {
        produtoService.updateProduto(id, produto);
    }

    public void deleteProduto(Long id) {
        produtoService.deleteProduto(id);
    }
}
