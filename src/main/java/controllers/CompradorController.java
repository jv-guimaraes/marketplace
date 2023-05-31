package controllers;

import entities.Comprador;
import services.CompradorService;

import java.util.List;

public class CompradorController {
    private final CompradorService compradorService;

    public CompradorController() {
        this.compradorService = new CompradorService();
    }

    public List<Comprador> getAllCompradores() {
        return compradorService.getAllCompradores();
    }

    public Comprador getCompradorByCpf(String cpf) {
        return compradorService.getCompradorByCpf(cpf);
    }

    public void createComprador(Comprador comprador) {
        compradorService.createComprador(comprador);
    }

    public void updateComprador(String cnpj, Comprador comprador) {
        compradorService.updateComprador(cnpj, comprador);
    }

    public void deleteComprador(String cnpj) {
        compradorService.deleteComprador(cnpj);
    }
}
