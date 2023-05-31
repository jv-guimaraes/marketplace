package entities;

import java.util.Objects;

public class Comprador {

    String nome;
    String email;
    String senha;
    String cpf;
    String endereco;

    public Comprador() {
    }

    public Comprador(String nome, String email, String senha, String cpf, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
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
        }
        else if (!(o instanceof Comprador comprador)) {
            result = false;
        }
        else{
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

    @Override
    public String toString() {
        return "\n{\n" +
                "\"nome\":" + this.nome + ",\n" +
                "\"cpf\":" + this.cpf + ",\n" +
                "\"email\":" + this.email + ",\n" +
                "\"senha\":" + this.senha + ",\n" +
                "\"endereco\":" + this.endereco + ",\n" +
                "}\n";
    }
}
