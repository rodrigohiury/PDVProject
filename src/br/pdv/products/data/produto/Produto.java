package br.pdv.products.data.produto;

import java.util.Locale;
import java.util.Objects;

public class Produto implements Comparable{

    private String nome;
    private float precoVenda;
    private float precoCusto;
    private String codigo;
    private float estoque;

    public Produto(String nome, float precoVenda) {
        setNome(nome);
        setPrecoVenda(precoVenda);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase(Locale.ROOT);
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public float getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(float precoCusto) {
        this.precoCusto = precoCusto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.toUpperCase(Locale.ROOT);
    }

    public float getEstoque() {
        return estoque;
    }

    public void setEstoque(float estoque) {
        this.estoque = estoque;
    }

    public void adicionarEstoque(float estoque){
        this.estoque += estoque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome) && Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, codigo);
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof Produto){
            return ((Produto) o).getNome().compareTo(this.nome);
        }
        return 0;
    }
}
