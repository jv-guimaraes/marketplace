package services;

import entities.Produto;
import infrastructure.repositories.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private ProdutoRepository produtoRepository = new ProdutoRepository();
    private LojaService lojaService = new LojaService();
    private Produto produto = new Produto();
    public ProdutoService() {
    }

    public ProdutoService(ProdutoRepository repository) {
        this.produtoRepository = repository;
    }

    public ProdutoService(String produtosPath) {
        this.produtoRepository = new ProdutoRepository(produtosPath);
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.getAllProdutos();
    }

    public Produto getProdutoById(long id) {
        var produtos = produtoRepository.getAllProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public void createProduto(Produto produto) {
        var produtos = produtoRepository.getAllProdutos();
        produto.setId(0);
        for (var produtoCadastrado : produtos) {
            if (produtoCadastrado.getId() >= produto.getId()) {
                produto.setId(produtoCadastrado.getId() + 1);
            }
        }
        produtos.add(produto);
        produtoRepository.setAllProdutos(produtos);
    }

    public void updateProduto(long id, Produto produto) {
        var produtos = produtoRepository.getAllProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                produtos.set(i, produto);
            }
        }
        produtoRepository.setAllProdutos(produtos);
    }

    public void deleteProduto(long id) {
        var produtos = produtoRepository.getAllProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                produtos.remove(i);
                break;
            }
        }
        produtoRepository.setAllProdutos(produtos);
    }

    public List<Produto> getProdutosByLoja(String cnpj) {
        var produtos = produtoRepository.getAllProdutos();
        var resultado = new ArrayList<Produto>();
        for (Produto produto : produtos) {
            if (produto.getLojaCnpj().equals(cnpj)) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    public boolean produtoExiste(long id) {
        var produtos = produtoRepository.getAllProdutos();
        for (var produto : produtos) {
            if (produto.getId() == id) return true;
        }
        return false;
    }
    public void adicionarNota(long id, int nota) {
        var produtos = produtoRepository.getAllProdutos();
        for (var produto : produtos) {
            if (produto.getId()== id) {
                produto.addNotaProduto(nota);
                AdicionarNotaLoja(id);
            }
        }

        produtoRepository.setAllProdutos(produtos);
    }

    public void AdicionarNotaLoja(long id){
        var loja = lojaService.getLojaByProduto(id);
        List<Integer> notas = new ArrayList<Integer>();
        notas = produto.getNotas();
        int soma =0;
        for(int nota : notas){
            soma += nota;
        }
        double media = soma/notas.size();
        String avaliacao = " ";
        String cnpj = loja.getCnpj();
        if(media>= 0 && media<= 2 ){avaliacao = "Ruim";}
        if(media> 2 && media<= 3 ){avaliacao = "Médio";}
        if(media> 3 && media<= 4 ){avaliacao = "Bom";}
        if(media> 4 && media<= 5 ){avaliacao = "Excelente";}
        lojaService.setAvaliacao(cnpj, avaliacao);

    }
}
