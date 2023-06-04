package repositories;


import entities.Comprador;
import port.repositories.ICompradorRepository;
import util.JsonFileCRUDCompradorUtil;

import java.util.List;

public class CompradorRepository implements ICompradorRepository {
    private JsonFileCRUDCompradorUtil collection;

    public CompradorRepository(JsonFileCRUDCompradorUtil collection) {
        this.collection = collection;
    }

    public List<Comprador> getAllCompradores() {
        return this.collection.getAllCompradores();
    }

    public Comprador getCompradorByCpf(String cpf) {
        return this.collection.getCompradorByCpf(cpf);
    }

    public void createComprador(Comprador comprador) {
        this.collection.createComprador(comprador);
    }

    public void updateComprador(String cpf, Comprador comprador) {
        this.collection.updateComprador(cpf, comprador);
    }

    public void deleteComprador(String cpf) {
        this.collection.deleteComprador(cpf);
    }
}