package port.services;
import entities.Comprador;

import java.util.List;

public interface ICompradorService {
    List<Comprador> getAllCompradores();

    Comprador getCompradorByCpf(String cpf);

    void createComprador(Comprador comprador);

    void updateComprador(String cpf, Comprador comprador);

    void deleteComprador(String cpf);
}
