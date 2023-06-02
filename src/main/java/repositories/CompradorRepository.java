package repositories;


import entities.Comprador;
import port.repositories.ICompradorRepository;
import util.JsonFileCRUDCompradorUtil;

import java.util.List;

public class CompradorRepository implements ICompradorRepository {

    public CompradorRepository() {
    }

    public List<Comprador> getAllCompradores() {
        return JsonFileCRUDCompradorUtil.getAllCompradores();
    }

    public Comprador getCompradorByCpf(String cpf) {
        return JsonFileCRUDCompradorUtil.getCompradorByCpf(cpf);
    }

    public void createComprador(Comprador comprador) {
        JsonFileCRUDCompradorUtil.createComprador(comprador);
    }

    public void updateComprador(String cpf, Comprador comprador) {
        JsonFileCRUDCompradorUtil.updateComprador(cpf, comprador);
    }

    public void deleteComprador(String cpf) {
        JsonFileCRUDCompradorUtil.deleteComprador(cpf);
    }
}