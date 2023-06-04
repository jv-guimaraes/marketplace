package services;

import entities.Comprador;
import port.services.ICompradorService;
import repositories.CompradorRepository;

import java.util.List;

public class CompradorService implements ICompradorService {
    private final CompradorRepository compradorRepository;

    public CompradorService(CompradorRepository repository) {
        this.compradorRepository = repository;
    }

    public List<Comprador> getAllCompradores() {
        return compradorRepository.getAllCompradores();
    }

    public Comprador getCompradorByCpf(String cpf) {
        return compradorRepository.getCompradorByCpf(cpf);
    }

    public void createComprador(Comprador comprador) {
        compradorRepository.createComprador(comprador);
    }

    public void updateComprador(String cpf, Comprador comprador) {
        compradorRepository.updateComprador(cpf, comprador);
    }

    public void deleteComprador(String cpf) {
        compradorRepository.deleteComprador(cpf);
    }
}
