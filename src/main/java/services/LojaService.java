package services;

import entities.Loja;
import repositories.LojaRepository;

import java.util.List;

public class LojaService {
    private final LojaRepository lojaRepository;

    public LojaService() {
        this.lojaRepository = new LojaRepository();
    }

    public List<Loja> getAllLojas() {
        return lojaRepository.getAllLojas();
    }

    public Loja getLojaByCnpj(String cnpj) {
        return lojaRepository.getLojaByCnpj(cnpj);
    }

    public void createLoja(Loja loja) {
        lojaRepository.createLoja(loja);
    }

    public void updateLoja(String cnpj, Loja loja) {
        lojaRepository.updateLoja(cnpj, loja);
    }

    public void deleteLoja(String cnpj) {
        lojaRepository.deleteLoja(cnpj);
    }


}


