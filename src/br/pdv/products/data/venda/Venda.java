package br.pdv.products.data.venda;

import br.pdv.products.data.produto.Produto;
import br.pdv.products.exceptions.ProdutoInexistenteException;
import br.pdv.products.exceptions.ProdutoInvalidoException;

import java.time.Instant;
import java.util.*;

public class Venda {

    private Map<Produto, Float> carrinho;
    private static int sequencia = 0;
    private int numero;
    private Calendar dataCompra = new GregorianCalendar();
    private float valor = 0.0f;
    private float valorPago;
    private float troco;
    private boolean finalizada = false;


    public Venda() {
        this.carrinho = new LinkedHashMap<>();
        this.dataCompra.setTime(Date.from(Instant.now()));
        this.numero = ++sequencia;
    }

    public void inserirCompra(Produto produto){
        if (!this.isFinalizada()){
            if (produto != null){
                if (!carrinho.containsKey(produto)){
                    carrinho.put(produto, 1.0f);
                }else {
                    carrinho.put(produto, carrinho.get(produto) + 1);
                }
            }
            this.atualizaValor();
        }
    }

    public void inserirCompra(Produto produto, float quantidade){
        if (!this.isFinalizada()){
            if (produto != null){
                if (!carrinho.containsKey(produto) && quantidade > 0){
                    carrinho.put(produto, quantidade);
                }
            }
            this.atualizaValor();
        }
    }

    public void removerCompra(Produto produto) throws ProdutoInexistenteException, ProdutoInvalidoException {
        if (produto != null){
            if (carrinho.containsKey(produto)){
                carrinho.remove(produto);
            }else {
                throw new ProdutoInexistenteException();
            }
        }else {
            throw new ProdutoInvalidoException();
        }
        this.atualizaValor();
    }

    public Map<Produto, Float> getCarrinho() {
        return carrinho;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public float getValor() {
        return valor;
    }

    public float getValorPago() {
        return valorPago;
    }

    public float getTroco() {
        return troco;
    }

    public void setValorPago(float valorPago) {
        if (!this.isFinalizada()){
            if (valorPago >= this.valor){
                this.valorPago = valorPago;
                this.troco = this.valor - this.valorPago;
                this.finalizada = true;
            }
        }
    }

    public boolean isFinalizada(){
        return finalizada;
    }

    public void atualizaValor(){
        this.valor = 0;
        for (Produto produto:
        carrinho.keySet()) {
            this.valor += produto.getPrecoVenda();
        }
    }

    public int getNumero() {
        return numero;
    }
}
