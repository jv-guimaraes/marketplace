package entities;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Comprador {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String endereco;

    private List<Long> carrinho;

    private List<Long> historico;

    private int pontos;

    public Comprador() {
        this.carrinho = new ArrayList<>();
        this.historico = new ArrayList<>();
        this.pontos = 0;
    }

    public Comprador(String nome, String email, String senha, String cpf, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
        this.carrinho = new ArrayList<>();
        this.historico = new ArrayList<>();
        this.pontos = 0;
    }

    public Comprador(JSONObject jsonObject) {
        this.nome = jsonObject.getString("nome");
        this.email = jsonObject.getString("email");
        this.senha = jsonObject.getString("senha");
        this.cpf = jsonObject.getString("cpf");
        this.endereco = jsonObject.getString("endereco");
        this.carrinho = new ArrayList<>();
        this.historico = new ArrayList<>();
        var carrinhoJson = jsonObject.getJSONArray("carrinho");
        for (int i = 0; i < carrinhoJson.length(); i++) {
            carrinho.add(carrinhoJson.getLong(i));
        }
        var historicoJson = jsonObject.getJSONArray("historico");
        for (int i = 0; i < historicoJson.length(); i++) {
            historico.add(historicoJson.getLong(i));
        }
        this.pontos = jsonObject.getInt("pontos");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (!(o instanceof Comprador comprador)) {
            result = false;
        } else {
            result = cpf.equals(comprador.cpf);
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Long> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Long> carrinho) {
        this.carrinho = carrinho;
    }

    public List<Long> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Long> historico) {
        this.historico = historico;
    }

    public Comprador clone() {
        Comprador newComprador = new Comprador(nome, email, senha, cpf, endereco);
        newComprador.setCarrinho(this.carrinho);
        newComprador.setHistorico(this.historico);
        return newComprador;
    }

    @Override
    public String toString() {
        return String.format("nome: %s, email: %s, senha: %s, cpf: %s, endereco: %s, nomesProdutosComprados: %s",
                nome, email, senha, cpf, endereco, historico);
    }

    public void addProdutoCarrinho(long produto) {
        carrinho.add(produto);
    }

    public void clearCarrinho() {
        carrinho.clear();
    }

    public boolean carrinhoContem(long id) {
        return carrinho.contains(id);
    }

    public void addToHistorico(Long produtoId) {
        historico.add(produtoId);
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void addPontos(Double valorDoProduto) {
        var fracaoDoValor = valorDoProduto * 0.2;
        pontos += (int) fracaoDoValor;
    }
}
