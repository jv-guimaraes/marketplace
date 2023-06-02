package port.repositories;

import entities.Produto;

import java.util.List;

public interface IProdutoRepository {
    List<Produto> getAllProdutos();
    Produto getProdutoById(long id);
    void createProduto(Produto produto);
    void updateProduto(long id, Produto produto);
    void deleteProduto(long id);
    List<Produto> getProdutosByLoja(String cnpj);
}

