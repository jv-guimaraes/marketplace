package entities;

import org.json.JSONObject;

import java.util.Objects;

public class Loja {
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private String endereco;

    public Loja() {
    }

    public Loja(String nome, String email, String senha, String cnpj, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public Loja(JSONObject jsonObject) {
        this.nome = jsonObject.getString("nome");
        this.email = jsonObject.getString("email");
        this.senha = jsonObject.getString("senha");
        this.cnpj = jsonObject.getString("cnpj");
        this.endereco = jsonObject.getString("endereco");
    }

    public Loja clone() {
        Loja newLoja = new Loja();
        newLoja.setCnpj(this.cnpj);
        newLoja.setEmail(this.email);
        newLoja.setEndereco(this.endereco);
        newLoja.setSenha(this.senha);
        return newLoja;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
        } else if (!(o instanceof Loja loja)) {
            result = false;
        } else {
            result = cnpj.equals(loja.cnpj);
        }

        return result;

    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", nome, email, senha, cnpj, endereco);
    }
}
