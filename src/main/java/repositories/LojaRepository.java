package repositories;
import port.repositories.ILojaRepository;
import util.JsonFileCRUDCompradorUtil;
import util.JsonFileCRUDLojaUtil;
import entities.Loja;

import java.util.List;

public class LojaRepository implements ILojaRepository {
    private JsonFileCRUDLojaUtil collection;
    public LojaRepository(JsonFileCRUDLojaUtil collection) {
        this.collection = collection;
    }
    public List<Loja> getAllLojas() {
        return this.collection.getAllLojas();
    }
    public Loja getLojaByCnpj(String cnpj) {
        return this.collection.getLojaByCnpj(cnpj);
    }
    public void createLoja(Loja loja) {
        this.collection.createLoja(loja);
    }
    public void updateLoja(String cnpj, Loja loja) {
        this.collection.updateLoja(cnpj, loja);
    }

    public void deleteLoja(String cnpj) {
        this.collection.deleteLoja(cnpj);
    }
}
