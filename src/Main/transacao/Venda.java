package Main.transacao;

import Main.exceptions.ProdutoNaoCadastradoException;
import Main.funcionario.Funcionario;
import Main.produto.Produto;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

public class Venda extends Transacao implements Serializable {

    private static final long serialVersionUID = 20l;
    private Map<Produto, Float> carrinho;
    private static int sequencia = 0;
    private int numero;
    private Calendar dataCompra = new GregorianCalendar();
    private float valorPago;
    private float troco;
    private boolean finalizada = false;
    private Funcionario vendedor;


    public Venda() {
        this.carrinho = new LinkedHashMap<>();
        this.dataCompra.setTime(Date.from(Instant.now()));
        this.numero = ++sequencia;
        super.setValor(0);
    }

    public Venda(Funcionario vendedor){
        this.carrinho = new LinkedHashMap<>();
        this.dataCompra.setTime(Date.from(Instant.now()));
        this.numero = ++sequencia;
        this.vendedor = vendedor;
        super.setValor(0);
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

    public void removerCompra(Produto produto) throws ProdutoNaoCadastradoException {
        if (produto != null){
            if (carrinho.containsKey(produto)){
                carrinho.remove(produto);
            }else {
                throw new ProdutoNaoCadastradoException(produto.getCodigo());
            }
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
        return super.getValor();
    }

    public float getValorPago() {
        return valorPago;
    }

    public float getTroco() {
        return troco;
    }

    public void setValorPago(float valorPago) {
        if (!this.isFinalizada()){
            if (valorPago >= this.getValor()){
                this.valorPago = valorPago;
                this.troco = this.getValor() - this.valorPago;
                this.finalizada = true;
            }
        }
    }

    public boolean isFinalizada(){
        return finalizada;
    }

    public void atualizaValor(){
        float valorAtualizado = 0;
        for (Produto produto:
        carrinho.keySet()) {
            valorAtualizado += produto.getPrecoVenda();
        }
        super.setValor(valorAtualizado);
    }

    public int getNumero() {
        return numero;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public static void setSequencia(int sequencia) {
        Venda.sequencia = sequencia;
    }
}
