package br.pdv.products.data.caixa;

import br.pdv.products.data.funcionario.Funcionario;
import br.pdv.products.data.produto.Produto;
import br.pdv.products.data.venda.Venda;
import br.pdv.products.exceptions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Caixa {

    private Funcionario vendedor;
    private Calendar dataAbertura = new GregorianCalendar();
    private Calendar dataFechamento = new GregorianCalendar();
    private float valorAbertura;
    private float valorEmCaixa;
    private ArrayList<Venda> vendasDoDia = new ArrayList<>();

    public Caixa(Funcionario vendedor) {
        this.vendedor = vendedor;
        this.dataAbertura.setTime(Date.from(Instant.now()));
        this.valorAbertura = 0.0f;
        this.valorEmCaixa = valorAbertura;
    }

    public Caixa(Funcionario vendedor, float valor) throws ValorAberturaInvalidoException {
        this.vendedor = vendedor;
        this.dataAbertura.setTime(Date.from(Instant.now()));
        if(valor < 0){
            throw new ValorAberturaInvalidoException(valor);
        }
        this.valorAbertura = valor;
        this.valorEmCaixa = valor;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public Calendar getDataFechamento() {
        return dataFechamento;
    }

    public float getValorAbertura() {
        return valorAbertura;
    }

    public float getValorEmCaixa() {
        return valorEmCaixa;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<Venda> getVendasDoDia() {
        return vendasDoDia;
    }

    public Venda buscarVenda(int numero) throws NumeroVendaInvalidoException {
        if(numero > 0){
            for (Venda venda:
                    this.vendasDoDia) {
                if (venda.getNumero() == numero){
                    return venda;
                }
            }
            return null;
        }else {
            throw new NumeroVendaInvalidoException(numero);
        }
    }

    public void adicionarVenda(Venda venda) throws VendaInvalidaException {
        if (venda != null){
            this.vendasDoDia.add(venda);
            this.valorEmCaixa += venda.getValor();
        }else {
            throw new VendaInvalidaException();
        }
    }

    public void removerVenda(int numero) throws NumeroVendaInvalidoException, VendaInexistenteException {
        Venda venda = this.buscarVenda(numero);
        if(venda != null){
            this.vendasDoDia.remove(venda);
            this.valorEmCaixa -= venda.getValor();
        }else{
            throw new VendaInexistenteException();
        }
    }

    public void devolverProduto(Produto produto, int numero) throws NumeroVendaInvalidoException, VendaInexistenteException, ProdutoInvalidoException, ProdutoInexistenteException {
        if (produto != null){
            Venda venda = this.buscarVenda(numero);
            if (venda != null){
                venda.removerCompra(produto);
                this.atualizaValorEmCaixa();
            }else {
                throw new VendaInexistenteException();
            }
        }else {
            throw new ProdutoInvalidoException();
        }
    }

    public void sangrarCaixa(float valor) throws ValorSangriaInvalidoException, SangriaInvalidaException {
        if (valor > 0){
            if(this.valorEmCaixa >= valor){
                Venda sangria = new Venda();
                Produto sangriaProduto = new Produto("Sangria", -valor);
                sangria.inserirCompra(sangriaProduto);
                sangria.setValorPago(-valor);
                try {
                    this.adicionarVenda(sangria);
                } catch (VendaInvalidaException vi) {
                    throw new ValorSangriaInvalidoException(vi, valor);
                }
            }else {
                throw new SangriaInvalidaException(valor);
            }
        }else {
            throw new ValorSangriaInvalidoException(valor);
        }
    }

    public void reforcarCaixa(float valor) throws ValorReforcoInvalidoException {
        if (valor > 0){
            Venda reforco = new Venda();
            Produto reforcoProduto = new Produto("Reforço", valor);
            reforco.inserirCompra(reforcoProduto);
            reforco.setValorPago(valor);
            try {
                this.adicionarVenda(reforco);
            } catch (VendaInvalidaException vi) {
                throw new ValorReforcoInvalidoException(vi, valor);

            }
        }else {
            throw new ValorReforcoInvalidoException(valor);
        }
    }

    public void atualizaValorEmCaixa(){
        this.valorEmCaixa = this.valorAbertura;
        for (Venda venda :
            this.vendasDoDia) {
            this.valorEmCaixa += venda.getValor();
        }
    }

    public void fecharCaixa(){
        dataFechamento.setTime(Date.from(Instant.now()));
    }
}
