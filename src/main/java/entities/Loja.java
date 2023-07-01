package entities;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Loja {
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private String endereco;
    private List<Integer> notas;

    public Loja() {
    }

    public Loja(String nome, String email, String senha, String cnpj, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.notas = new ArrayList();
    }

    public Loja(JSONObject jsonObject) {
        this.nome = jsonObject.getString("nome");
        this.email = jsonObject.getString("email");
        this.senha = jsonObject.getString("senha");
        this.cnpj = jsonObject.getString("cnpj");
        this.endereco = jsonObject.getString("endereco");
        this.notas = new ArrayList();
        var notasJsonArray = jsonObject.getJSONArray("notas");
        for (int i = 0; i < notasJsonArray.length(); i++) {
            this.notas.add(notasJsonArray.getInt(i));
        }
    }

    public Loja clone() {
        Loja newLoja = new Loja();
        newLoja.setCnpj(this.cnpj);
        newLoja.setEmail(this.email);
        newLoja.setEndereco(this.endereco);
        newLoja.setSenha(this.senha);
        newLoja.setNotas(this.notas);
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

    public List<Integer> getNotas() {
        return notas;
    }

    public void setNotas(List<Integer> notas) {
        this.notas = notas;
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

    public void addNota(int nota) {
        notas.add(nota);
    }

    public String getAvaliacao() {
        if (this.notas.isEmpty()) return "Sem avaliação";
        var media = (double) notas.stream().reduce(0, Integer::sum) / notas.size();
        if (media <= 1.25) return "ruim";
        if (media <= 2.5) return "médio";
        if (media <= 3.75) return "bom";
        else return "excelente";
    }
}
