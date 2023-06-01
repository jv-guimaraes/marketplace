package entities;

import java.util.Objects;

public class Produto {

    Long id;
    String nome;
    Double valor;
    String tipo;
    Integer quantidade;
    String marca;
    String descricao;
    String lojaCnpj;
    public Produto() {
    }

    public Produto(String nome, Double valor, String tipo, Integer quantidade, String marca, String descricao, String lojaCnpj) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.marca = marca;
        this.descricao = descricao;
        this.lojaCnpj = lojaCnpj;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLojaCnpj() { return lojaCnpj; }

    public void setLojaCnpj() { this.lojaCnpj = lojaCnpj; }

    @Override
    public boolean equals(Object o) {

        boolean result;
        if (this == o) {
            result = true;
        }
        else if (!(o instanceof Produto produto)) {
            result = false;
        }
        else{
            result = id.equals(produto.id);
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "\n{\n" +
                "\"id\":" + this.id + ",\n" +
                "\"nome\":" + this.nome + ",\n" +
                "\"valor\":" + this.valor + ",\n" +
                "\"tipo\":" + this.tipo + ",\n" +
                "\"quantidade\":" + this.quantidade + ",\n" +
                "\"marca\":" + this.marca + ",\n" +
                "\"descricao\":" + this.descricao + ",\n" +
                "}\n";
    }
}
