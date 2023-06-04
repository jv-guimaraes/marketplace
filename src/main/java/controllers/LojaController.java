package controllers;

import entities.Loja;
import services.LojaService;

import java.util.List;

public class LojaController {
    private final LojaService lojaService;

    public LojaController(LojaService service) {
        this.lojaService = service;
    }

    public List<Loja> getAllLojas() {
        return lojaService.getAllLojas();
    }

    public Loja getLojaByCnpj(String cnpj) {
        return lojaService.getLojaByCnpj(cnpj);
    }

    public void createLoja(Loja loja) {
        lojaService.createLoja(loja);
    }

    public void updateLoja(String cnpj, Loja loja) {
        lojaService.updateLoja(cnpj, loja);
    }

    public void deleteLoja(String cnpj) {
        lojaService.deleteLoja(cnpj);
    }



}
