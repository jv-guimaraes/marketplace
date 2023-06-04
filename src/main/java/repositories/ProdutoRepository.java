package repositories;

import entities.Produto;
import port.repositories.IProdutoRepository;
import util.JsonFileCRUDProdutoUtil;

import java.util.List;

public class ProdutoRepository implements IProdutoRepository {
    private JsonFileCRUDProdutoUtil collection;
    public ProdutoRepository(JsonFileCRUDProdutoUtil collection){
        this.collection = collection;
    }
    public List<Produto> getAllProdutos() {
        return this.collection.getAllProdutos();
    }

    public Produto getProdutoById(long id) {
        return this.collection.getProdutoById(id);
    }

    public void createProduto(Produto produto) {
        this.collection.createProduto(produto);
    }

    public void updateProduto(long id, Produto produto) {
        this.collection.updateProduto(id, produto);
    }

    public void deleteProduto(long id) {
        this.collection.deleteProduto(id);
    }

    public List<Produto> getProdutosByLoja(String cnpj) {
        return this.collection.getProdutosByLoja(cnpj);
    }
}
