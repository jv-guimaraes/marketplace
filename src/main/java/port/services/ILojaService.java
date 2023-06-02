package port.services;

import entities.Loja;

import java.util.List;

public interface ILojaService {
    List<Loja> getAllLojas();
    Loja getLojaByCnpj(String cnpj);
    void createLoja(Loja loja);
    void updateLoja(String cnpj, Loja loja);
    void deleteLoja(String cnpj);
}
