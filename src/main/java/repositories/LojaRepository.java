package repositories;

import util.JsonFileCRUDLojaUtil;
import entities.Loja;

import java.util.List;

public class LojaRepository {

    public LojaRepository() {
    }

    public List<Loja> getAllLojas() {
        return JsonFileCRUDLojaUtil.getAllLojas();
    }

    public Loja getLojaByCnpj(String cnpj) {
        return JsonFileCRUDLojaUtil.getLojaByCnpj(cnpj);
    }

    public void createLoja(Loja loja) {
        JsonFileCRUDLojaUtil.createLoja(loja);
    }

    public void updateLoja(String cnpj, Loja loja) {
        JsonFileCRUDLojaUtil.updateLoja(cnpj, loja);
    }

    public void deleteLoja(String cnpj) {
        JsonFileCRUDLojaUtil.deleteLoja(cnpj);
    }
}
