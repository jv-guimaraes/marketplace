package services;

import entities.Loja;
import infrastructure.repositories.LojaRepository;

import java.util.List;

public class LojaService {
    private LojaRepository lojaRepository;

    public LojaService() {
        this.lojaRepository = new LojaRepository();
    }

    public LojaService(LojaRepository repository) {
        this.lojaRepository = repository;
    }

    public void createLoja(Loja loja) {
        var lojas = lojaRepository.getAllLojas();

        // Checar se CNPJ já existe no banco de dados
        for (Loja lojaCadastrada : lojas) {
            if (lojaCadastrada.getCnpj().equals(loja.getCnpj())) {
                System.out.println("Cannot create Loja. CNPJ already exists.");
                return;
            }
        }
        lojas.add(loja);
        lojaRepository.setAllLojas(lojas);
    }

    public Loja getLojaByCnpj(String cnpj) {
        var lojas = lojaRepository.getAllLojas();

        for (Loja loja : lojas) {
            if (loja.getCnpj().equals(cnpj)) {
                return loja;
            }
        }

        return null;
    }

    public List<Loja> getAllLojas() {
        return lojaRepository.getAllLojas();
    }

    public void updateLoja(String cnpj, Loja updatedLoja) {
        var lojas = lojaRepository.getAllLojas();
        for (int i = 0; i < lojas.size(); i++) {
            if (lojas.get(i).getCnpj().equals(cnpj)) {
                lojas.set(i, updatedLoja);
            }
        }
        lojaRepository.setAllLojas(lojas);
    }

    public void deleteLoja(String cnpj) {
        var lojas = lojaRepository.getAllLojas();
        for (int i = 0; i < lojas.size(); i++) {
            if (lojas.get(i).getCnpj().equals(cnpj)) {
                lojas.remove(i);
            }
        }
        lojaRepository.setAllLojas(lojas);
    }

    public boolean emailCadastrado(String email) {
        var lojas = lojaRepository.getAllLojas();
        for (int i = 0; i < lojas.size(); i++) {
            if (lojas.get(i).getEmail().equals(email)) return true;
        }
        return false;
    }

    public boolean loginValido(String email, String senha) {
        var lojas = lojaRepository.getAllLojas();
        for (var loja : lojas) {
            if (loja.getEmail().equals(email) && loja.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public String getCnpjFromEmail(String email) {
        var lojas = lojaRepository.getAllLojas();
        for (var loja : lojas) {
            if (loja.getEmail().equals(email)) {
                return loja.getCnpj();
            }
        }
        return null;
    }
}
