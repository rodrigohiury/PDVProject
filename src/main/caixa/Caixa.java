package main.caixa;

import main.funcionario.Funcionario;
import main.produto.Produto;
import main.transacao.Venda;
import main.exceptions.*;
import main.transacao.*;

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
    private ArrayList<Transacao> vendasDoDia = new ArrayList<>();

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
        ArrayList<Venda> vendas = new ArrayList<>();
        for (Transacao transacao:
             this.vendasDoDia) {
            if(transacao instanceof Venda){
                vendas.add((Venda) transacao);
            }
        }
        return vendas;
    }

    public Venda buscarVenda(int numero) throws NumeroVendaInvalidoException {
        if(numero > 0){
            for (Transacao transacao:
                    this.vendasDoDia) {
                if(transacao instanceof Venda){
                    if (((Venda) transacao).getNumero() == numero){
                        return (Venda) transacao;
                    }
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

    public void devolverProduto(Produto produto, int numero) throws NumeroVendaInvalidoException, VendaInexistenteException, ProdutoNaoCadastradoException, ProdutoNaoCadastradoException {
        if (produto != null){
            Venda venda = this.buscarVenda(numero);
            if (venda != null){
                venda.removerCompra(produto);
                this.atualizaValorEmCaixa();
            }else {
                throw new VendaInexistenteException();
            }
        }else {
            throw new NullPointerException();
        }
    }

    public void sangrarCaixa(float valor) throws ValorSangriaInvalidoException, SangriaInvalidaException {
        if (valor > 0){
            if(this.valorEmCaixa >= valor){
                Transacao sangria = new Sangria(valor);
                this.vendasDoDia.add(sangria);
                this.atualizaValorEmCaixa();
            }else {
                throw new SangriaInvalidaException(valor);
            }
        }else {
            throw new ValorSangriaInvalidoException(valor);
        }
    }

    public void reforcarCaixa(float valor) throws ValorReforcoInvalidoException {
        if (valor > 0){
            Transacao reforco = new Reforco(valor);
            this.vendasDoDia.add(reforco);
            this.atualizaValorEmCaixa();
        }else {
            throw new ValorReforcoInvalidoException(valor);
        }
    }

    public void atualizaValorEmCaixa(){
        this.valorEmCaixa = this.valorAbertura;
        for (Transacao transacao :
            this.vendasDoDia) {
            this.valorEmCaixa += transacao.getValor();
        }
    }

    public void fecharCaixa(){
        dataFechamento.setTime(Date.from(Instant.now()));
    }
}
