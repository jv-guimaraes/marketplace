package entities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Comprador {

    String nome;
    String email;
    String senha;
    String cpf;
    String endereco;

    List<Long> carrinho;

    List<Produto> produtos;

    public Comprador() {
    }

    public Comprador(String nome, String email, String senha, String cpf, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
        this.carrinho = new ArrayList<Long>();
    }

    public Comprador(JSONObject jsonObject) {
        this.nome = jsonObject.getString("nome");
        this.email = jsonObject.getString("email");
        this.senha = jsonObject.getString("senha");
        this.cpf = jsonObject.getString("cpf");
        this.endereco = jsonObject.getString("endereco");
        this.carrinho = new ArrayList<Long>();
        var carrinhoJson = jsonObject.getJSONArray("carrinho");
        for (int i = 0; i < carrinhoJson.length(); i++) {
            carrinho.add(carrinhoJson.getLong(i));
        }
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Comprador clone() {
        Comprador newComprador = new Comprador();
        newComprador.setCpf(this.cpf);
        newComprador.setEmail(this.email);
        newComprador.setEndereco(this.endereco);
        newComprador.setSenha(this.senha);
        return new Comprador(nome, email, senha, cpf, endereco);
    }

    @Override
    public String toString() {
        return String.format("nome: %s, email: %s, senha: %s, cpf: %s, endereco: %s, nomesProdutosComprados: %s",
                nome, email, senha, cpf, endereco, produtos.stream().map(Produto::getNome).collect(Collectors.toList()));
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
}
