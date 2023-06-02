package port.repositories;
import entities.Comprador;

import java.util.List;

public interface ICompradorRepository {
    List<Comprador> getAllCompradores();

    Comprador getCompradorByCpf(String cpf);

    void createComprador(Comprador comprador);

    void updateComprador(String cpf, Comprador comprador);

    void deleteComprador(String cpf);
}
