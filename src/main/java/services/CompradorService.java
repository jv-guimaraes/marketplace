package services;

import entities.Comprador;
import repositories.CompradorRepository;

import java.util.List;

public class CompradorService {
    private CompradorRepository compradorRepository = new CompradorRepository();
    private ProdutoService produtoService = new ProdutoService();

    public CompradorService() {

    }

    public CompradorService(String compradoresPath, String produtosPath) {
        this.compradorRepository = new CompradorRepository(compradoresPath);
        this.produtoService = new ProdutoService(produtosPath);
    }

    public void createComprador(String nome, String email, String senha, String cpf, String endereco) {
        Comprador comprador = new Comprador(nome, email, senha, cpf, endereco);
        var compradores = compradorRepository.getAllCompradores();
        // Check if the CPF already exists
        for (var compradorJaCadastrado : compradores) {
            if (comprador.getCpf().equals(compradorJaCadastrado.getCpf())) {
                System.out.println("Cannot create Comprador. CPF already exists.");
                return;
            }
        }

        // Check if the email already exists
        for (var compradorJaCadastrado : compradores) {
            if (comprador.getEmail().equals(compradorJaCadastrado.getEmail())) {
                System.out.println("Cannot create Comprador. CPF already exists.");
                return;
            }
        }

        // CPF and email don't exist, proceed with creating the Comprador
        compradores.add(comprador);
        compradorRepository.setAllCompradores(compradores);

        System.out.println("Comprador created successfully.");
    }

    public Comprador getCompradorByCpf(String cpf) {
        var compradores = compradorRepository.getAllCompradores();
        for (Comprador comprador : compradores) {
            if (comprador.getCpf().equals(cpf)) {
                return comprador;
            }
        }
        return null;
    }

    public void updateComprador(String cpf, Comprador updatedComprador) {
        var compradores = compradorRepository.getAllCompradores();
        for (int i = 0; i < compradores.size(); i++) {
            if (compradores.get(i).getCpf().equals(cpf)) {
                compradores.set(i, updatedComprador);
                break;
            }
        }
        compradorRepository.setAllCompradores(compradores);
    }

    public void deleteComprador(String cpf) {
        var compradores = compradorRepository.getAllCompradores();
        for (int i = 0; i < compradores.size(); i++) {
            if (compradores.get(i).getCpf().equals(cpf)) {
                compradores.remove(i);
                break;
            }
        }
        compradorRepository.setAllCompradores(compradores);
    }

    public List<Comprador> getAllCompradores() {
        return compradorRepository.getAllCompradores();
    }

    public boolean compradorExiste(String cpf) {
        return getCompradorByCpf(cpf) != null;
    }

    public void addProdutoCarrinho(String cpf, long produtoId) {
        if (!produtoService.produtoExiste(produtoId)) return;
        var compradores = compradorRepository.getAllCompradores();
        for (var comprador : compradores) {
            if (comprador.getCpf().equals(cpf)) {
                comprador.addProdutoCarrinho(produtoId);
            }
        }
        compradorRepository.setAllCompradores(compradores);
    }

    public void clearCarrinho(String cpf) {
        var compradores = compradorRepository.getAllCompradores();
        for (var comprador : compradores) {
            if (comprador.getCpf().equals(cpf)) {
                comprador.clearCarrinho();
            }
        }
        compradorRepository.setAllCompradores(compradores);
    }

    public boolean emailCadastrado(String email) {
        var compradores = compradorRepository.getAllCompradores();
        for (var comprador : compradores) {
            if (comprador.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginValido(String email, String senha) {
        var compradores = compradorRepository.getAllCompradores();
        for (var comprador : compradores) {
            if (comprador.getEmail().equals(email) && comprador.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public String getCpfFromEmail(String email) {
        var compradores = compradorRepository.getAllCompradores();
        for (var comprador : compradores) {
            if (comprador.getEmail().equals(email)) {
                return comprador.getCpf();
            }
        }
        return null;
    }

    public Comprador getCompradorByEmail(String email) {
        var compradores = compradorRepository.getAllCompradores();
        for (var comprador : compradores) {
            if (comprador.getEmail().equals(email)) {
                return comprador;
            }
        }
        return null;
    }
}
