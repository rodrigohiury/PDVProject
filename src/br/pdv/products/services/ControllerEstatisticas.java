package br.pdv.products.services;

import br.pdv.products.data.caixa.Caixa;
import br.pdv.products.data.produto.Produto;
import br.pdv.products.data.venda.Venda;
import br.pdv.products.exceptions.CaixaInexistenteException;
import br.pdv.products.exceptions.VendaInexistenteException;
import br.pdv.products.exceptions.VendaInvalidaException;
import br.pdv.products.repository.IVendasRepository;

import java.time.Instant;
import java.util.*;

public class ControllerEstatisticas {

    private IVendasRepository reporitorioVendas;

    public ControllerEstatisticas(IVendasRepository repositorioVendas) {
        this.reporitorioVendas = repositorioVendas;
    }

    public void inserirCaixa(Venda venda) throws VendaInvalidaException {
        if (venda != null){
            reporitorioVendas.inserir(venda);
        } else {
            throw new VendaInvalidaException();
        }
    }

    public void removerVenda(Venda venda) throws VendaInvalidaException, VendaInexistenteException {
        if (venda != null){
            if (reporitorioVendas.buscarVenda(venda.getNumero()) != null){
                reporitorioVendas.remover(venda);
            } else throw new VendaInexistenteException();
        } else throw new VendaInvalidaException();
    }

    public void alterarVenda(Venda venda) throws VendaInvalidaException, VendaInexistenteException {
        if (venda != null){
            Venda vendaAnterior = reporitorioVendas.buscarVenda(venda.getNumero());
            if (vendaAnterior != null){
                reporitorioVendas.alterarVenda(venda);
            } else throw new VendaInexistenteException();
        } else throw new VendaInvalidaException();
    }

    public Venda buscarVenda(int numero) throws VendaInexistenteException, VendaInvalidaException {
        if (numero > 0){
            Venda venda = reporitorioVendas.buscarVenda(numero);
            if (venda != null){
                return venda;
            } else throw new VendaInexistenteException();
        } else throw new VendaInvalidaException();
    }

    public float getFaturamentoTotal() {
        float faturamento = 0.0f;
        for (Venda venda :
                reporitorioVendas.listarVendas()) {
            if (isVenda(venda)){
                faturamento += venda.getValorPago();
            }
        }
        return faturamento;
    }

    public float getFaturamentoDia(Calendar data) {
        float faturamento = 0.0f;
        for (Venda venda:
                reporitorioVendas.buscarVendas(data)) {
            if (isVenda(venda)){
                faturamento += venda.getValorPago();
            }
        }
        return faturamento;
    }

    public float getLucroTotal() {
        float precoVenda, precoCusto, quantidade, lucroUnit, lucro = 0.0f;
        for (Venda venda:
                reporitorioVendas.listarVendas()) {
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
        return lucro;
    }

    public float getLucroMensal() {
        float precoVenda, precoCusto, quantidade, lucroUnit, lucro = 0.0f;
        Calendar hoje = new GregorianCalendar();
        hoje.setTime(Date.from(Instant.now()));
        for (Venda venda:
                reporitorioVendas.listarVendas()) {
            if (isSameMonth(venda.getDataCompra(), hoje)) {
                if (isVenda(venda)) {
                    Set<Produto> produtos = venda.getCarrinho().keySet();
                    for (Produto produto :
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

    public float getTicketMedio() {
        return (this.getFaturamentoTotal() / this.getNumeroVendasTotal());
    }

    public int getNumeroVendasTotal() {
        int numeroVendas = 0;
        for (Venda venda:
                reporitorioVendas.listarVendas()) {
            if (isVenda(venda)){
                numeroVendas++;
            }
        }
        return numeroVendas;
    }

    public int getNumeroVendasMensal() {
        int numeroVendas = 0;
        Calendar hoje = new GregorianCalendar();
        hoje.setTime(Date.from(Instant.now()));
        for (Venda venda:
                reporitorioVendas.listarVendas()) {
            if (isSameMonth(venda.getDataCompra(), hoje)) {
                if (isVenda(venda)) {
                    numeroVendas++;
                }
            }
        }
        return numeroVendas;
    }

    public int getNumeroVendasHoje() {
        int numeroVendas = 0;
        Calendar hoje = new GregorianCalendar();
        hoje.setTime(Date.from(Instant.now()));
        for (Venda venda:
                reporitorioVendas.listarVendas()) {
            if (isSameDay(venda.getDataCompra(), hoje)){
                if (isVenda(venda)){
                    numeroVendas++;
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
