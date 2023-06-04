package services;

import entities.Loja;
import port.services.ILojaService;
import repositories.LojaRepository;

import java.util.List;

public class LojaService implements ILojaService {
    private final LojaRepository lojaRepository;

    public LojaService(LojaRepository repository) {
        this.lojaRepository = repository;
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


