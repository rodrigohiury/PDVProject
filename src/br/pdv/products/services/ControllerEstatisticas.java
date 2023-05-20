package br.pdv.products.services;

import br.pdv.products.data.caixa.Caixa;
import br.pdv.products.data.produto.Produto;
import br.pdv.products.data.venda.Venda;
import br.pdv.products.exceptions.CaixaInexistenteException;
import br.pdv.products.exceptions.CaixaInvalidoException;
import br.pdv.products.repository.ICaixaRepository;

import java.time.Instant;
import java.util.*;

public class ControllerEstatisticas {

    private ICaixaRepository repCaixa;

    public ControllerEstatisticas(ICaixaRepository repCaixa) {
        this.repCaixa = repCaixa;
    }

    public void inserirCaixa(Caixa caixa) throws CaixaInvalidoException {
        if (caixa != null){
            repCaixa.inserirCaixa(caixa);
        } else {
            throw new CaixaInvalidoException();
        }
    }

    public void removerCaixa(Caixa caixa) throws CaixaInexistenteException, CaixaInvalidoException {
        if (caixa != null){
            if (repCaixa.buscarCaixa(caixa.getDataAbertura()) != null){
                repCaixa.removerCaixa(caixa);
            } else throw new CaixaInexistenteException();
        } else throw new CaixaInvalidoException();
    }

    public void alterarCaixa(Caixa caixa) throws CaixaInvalidoException, CaixaInexistenteException {
        if (caixa != null){
            Caixa caixaAnterior = repCaixa.buscarCaixa(caixa.getDataAbertura());
            if (caixaAnterior != null){
                repCaixa.alterarCaixa(caixa);
            } else throw new CaixaInexistenteException();
        } else throw new CaixaInvalidoException();
    }

    public Caixa buscarCaixa(Calendar data) throws CaixaInexistenteException, NullPointerException {
        if (data != null){
            Caixa caixa = repCaixa.buscarCaixa(data);
            if (caixa != null){
                return caixa;
            } else throw new CaixaInexistenteException();
        } else throw new NullPointerException();
    }

    public float getFaturamentoTotal() {
        float faturamento = 0.0f;
        for (Caixa caixa:
                repCaixa.listarCaixas()) {
            for (Venda venda :
                    caixa.getVendasDoDia()) {
                if (isVenda(venda)){
                    faturamento += venda.getValorPago();
                }
            }
        }
        return faturamento;
    }

    public float getFaturamentoDia(Calendar data) {
        float faturamento = 0.0f;
        for (Caixa caixa:
                repCaixa.listarCaixas()) {
            if (isSameDay(caixa.getDataAbertura(), data)){
                for (Venda venda:
                        caixa.getVendasDoDia()) {
                    if (isVenda(venda)){
                        faturamento += venda.getValorPago();
                    }
                }
            }
        }
        return faturamento;
    }

    public float getLucroTotal() {
        float precoVenda, precoCusto, quantidade, lucroUnit, lucro = 0.0f;
        for (Caixa caixa:
                repCaixa.listarCaixas()) {
            for (Venda venda:
                    caixa.getVendasDoDia()) {
                if (isVenda(venda)){
                    Set<Produto> produtos = venda.getCarrinho().keySet();
                    for (Produto produto:
                         produtos) {
                        precoVenda = produto.getPrecoVenda();
                        precoCusto = produto.getPrecoCusto();
                        quantidade = venda.getCarrinho().get(produto);
                        lucroUnit = (precoVenda - precoCusto) * quantidade;
                        lucro += lucroUnit;
                    }
                }
            }
        }
        return lucro;
    }

    public float getLucroMensal() {
        float precoVenda, precoCusto, quantidade, lucroUnit, lucro = 0.0f;
        for (Caixa caixa:
                repCaixa.listarCaixas()) {
            Calendar hoje = new GregorianCalendar();
            hoje.setTime(Date.from(Instant.now()));
            if (isSameMonth(caixa.getDataAbertura(), hoje)){
                for (Venda venda:
                        caixa.getVendasDoDia()) {
                    if (isVenda(venda)){
                        Set<Produto> produtos = venda.getCarrinho().keySet();
                        for (Produto produto:
                                produtos) {
                            precoVenda = produto.getPrecoVenda();
                            precoCusto = produto.getPrecoCusto();
                            quantidade = venda.getCarrinho().get(produto);
                            lucroUnit = (precoVenda - precoCusto) * quantidade;
                            lucro += lucroUnit;
                        }
                    }
                }
            }
        }
        return lucro;
    }

    public float getTicketMedio() {
        return (this.getFaturamentoTotal() / this.getNumeroVendasTotal());
    }

    public int getNumeroVendasTotal() {
        int numeroVendas = 0;
        for (Caixa caixa : repCaixa.listarCaixas()) {
            for (Venda venda:
                    caixa.getVendasDoDia()) {
                if (isVenda(venda)){
                    numeroVendas++;
                }
            }
        }
        return numeroVendas;
    }

    public int getNumeroVendasMensal() {
        int numeroVendas = 0;
        for (Caixa caixa : repCaixa.listarCaixas()) {
            Calendar hoje = new GregorianCalendar();
            hoje.setTime(Date.from(Instant.now()));
            if (isSameMonth(caixa.getDataAbertura(), hoje)){
                for (Venda venda:
                        caixa.getVendasDoDia()) {
                    if (isVenda(venda)){
                        numeroVendas++;
                    }
                }
            }
        }
        return numeroVendas;
    }

    public int getNumeroVendasHoje() {
        int numeroVendas = 0;
        for (Caixa caixa : repCaixa.listarCaixas()) {
            Calendar hoje = new GregorianCalendar();
            hoje.setTime(Date.from(Instant.now()));
            if (isSameDay(caixa.getDataAbertura(), hoje)){
                for (Venda venda:
                        caixa.getVendasDoDia()) {
                    if (isVenda(venda)){
                        numeroVendas++;
                    }
                }
            }
        }
        return numeroVendas;
    }

    public boolean isVenda(Venda venda){
        if (venda.getCarrinho().size() == 1){
            Produto produto = venda.getCarrinho().keySet().iterator().next();
            if (produto.getNome().equals("Sangria") || produto.getNome().equals("Reforço")){
                return false;
            }
        }
        return true;
    }

    private boolean isSameDay(Calendar date1, Calendar date2){
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)){
            if (date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)){
                if (date1.get(Calendar.DATE) == date1.get(Calendar.DATE)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSameMonth(Calendar date1, Calendar date2){
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)){
            if (date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)){
                return true;
            }
        }
        return false;
    }
}
