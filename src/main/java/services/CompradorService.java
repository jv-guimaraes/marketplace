package services;

import entities.Comprador;
import repositories.CompradorRepository;

import java.util.List;

public class CompradorService {
    private final CompradorRepository compradorRepository;

    public CompradorService() {
        this.compradorRepository = new CompradorRepository();
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
