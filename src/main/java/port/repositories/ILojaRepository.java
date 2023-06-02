package port.repositories;

import entities.Loja;

import java.util.List;

public interface ILojaRepository{
    List<Loja> getAllLojas();
    Loja getLojaByCnpj(String cnpj);
    void createLoja(Loja loja);
    void updateLoja(String cnpj, Loja loja);
    void deleteLoja(String cnpj);
}
