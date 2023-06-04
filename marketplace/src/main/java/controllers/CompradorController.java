package controllers;

import entities.Comprador;
import services.CompradorService;

import java.util.List;

public class CompradorController {
    private final CompradorService service;

    public CompradorController(CompradorService service) {
        this.service = service;
    }

    public List<Comprador> getAllCompradores() {
        //List<String> spyList = Mockito.spy(new ArrayList<String>());
        return service.getAllCompradores();
    }

    public Comprador getCompradorByCpf(String cpf) {
        return service.getCompradorByCpf(cpf);
    }

    public void createComprador(Comprador comprador) {
        service.createComprador(comprador);
    }

    public void updateComprador(String cpf, Comprador comprador) {
        service.updateComprador(cpf, comprador);
    }

    public void deleteComprador(String cpf) {
        service.deleteComprador(cpf);
    }
}
