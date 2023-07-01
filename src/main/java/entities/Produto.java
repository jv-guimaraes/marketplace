package entities;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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
    List<Integer> notas;

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
        this.notas = new ArrayList<>();


    }

    public Produto(JSONObject jsonObject) {
        this.id = jsonObject.getLong("id");
        this.nome = jsonObject.getString("nome");
        this.valor = jsonObject.getDouble("valor");
        this.tipo = jsonObject.getString("tipo");
        this.quantidade = jsonObject.getInt("quantidade");
        this.marca = jsonObject.getString("marca");
        this.descricao = jsonObject.getString("descricao");
        this.lojaCnpj = jsonObject.getString("lojaCnpj");
        this.notas = new ArrayList<>();
        var notasArray = jsonObject.getJSONArray("notas");
        for (int i = 0; i < notasArray.length(); i++) {
            this.notas.add(notasArray.getInt(i));
        }
    }

    public Produto clone() {
        Produto newProduto = new Produto();
        newProduto.setNome(this.nome);
        newProduto.setDescricao(this.descricao);
        newProduto.setTipo(this.tipo);
        newProduto.setMarca(this.marca);
        newProduto.setQuantidade(this.quantidade);
        newProduto.setValor(this.valor);
        newProduto.setNotas(this.notas);
        return newProduto;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getLojaCnpj() {
        return lojaCnpj;
    }

    public void setLojaCnpj(String lojaCnpj) {
        this.lojaCnpj = lojaCnpj;
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
        } else if (!(o instanceof Produto produto)) {
            result = false;
        } else {
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
        return String.format("%d, %s, %s, %s, %d, %s, %s", id, nome, valor, tipo, quantidade, marca, descricao);
    }

    public void addNotaProduto(int nota) {
        notas.add(nota);
    }

    public double getNota() {
        if (notas.isEmpty()) return 0;
        return (double) notas.stream().reduce(0, Integer::sum) / notas.size();
    }
}
