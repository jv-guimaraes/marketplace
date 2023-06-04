package repositories;

import entities.Produto;
import port.repositories.IProdutoRepository;
import util.JsonFileCRUDProdutoUtil;

import java.util.List;

public class ProdutoRepository implements IProdutoRepository {
    public List<Produto> getAllProdutos() {
        return JsonFileCRUDProdutoUtil.getAllProdutos();
    }

    public Produto getProdutoById(long id) {
        return JsonFileCRUDProdutoUtil.getProdutoById(id);
    }

    public void createProduto(Produto produto) {
        JsonFileCRUDProdutoUtil.createProduto(produto);
    }

    public void updateProduto(long id, Produto produto) {
        JsonFileCRUDProdutoUtil.updateProduto(id, produto);
    }

    public void deleteProduto(long id) {
        JsonFileCRUDProdutoUtil.deleteProduto(id);
    }

    public List<Produto> getProdutosByLoja(String cnpj) {
        return JsonFileCRUDProdutoUtil.getProdutosByLoja(cnpj);
    }
}
