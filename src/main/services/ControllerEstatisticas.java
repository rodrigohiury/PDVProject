package main.services;

import main.exceptions.NaoHaProdutosException;
import main.produto.Produto;
import main.repository.IProdutoRepository;
import main.transacao.Venda;
import main.exceptions.PeriodoInvalidoException;
import main.exceptions.VendaInexistenteException;
import main.exceptions.VendaInvalidaException;
import main.repository.IVendasRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerEstatisticas {

    private IVendasRepository reporitorioVendas;
    private IProdutoRepository produtoRepository;

    public ControllerEstatisticas(IVendasRepository repositorioVendas, IProdutoRepository produtoRepository) {
        this.reporitorioVendas = repositorioVendas;
        this.produtoRepository = produtoRepository;
    }

    public void inserirVenda(Venda venda) throws VendaInvalidaException {
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

    public ArrayList<Venda> buscarVendasPeriodo (Calendar dataInicio, Calendar dataFim) throws PeriodoInvalidoException {
        ArrayList<Venda> vendasPeriodo = new ArrayList<>();
        if (dataInicio.compareTo(dataFim) <= 0){
            for (Venda venda:
                    reporitorioVendas.listarVendas()) {
                int inicioCompare = venda.getDataCompra().compareTo(dataInicio);
                int fimCompare = venda.getDataCompra().compareTo(dataFim);
                if(inicioCompare >= 0 && fimCompare <= 0){
                    vendasPeriodo.add(venda);
                }
            }
            return vendasPeriodo;
        } else throw new PeriodoInvalidoException(dataInicio, dataFim);
    }

    public float getFaturamentoTotal() {
        float faturamento = 0.0f;
        for (Venda venda :
                reporitorioVendas.listarVendas()) {
            faturamento += venda.getValorPago();
        }
        return faturamento;
    }

    public float getFaturamentoPeriodo(Calendar dataInicio, Calendar dataFim) throws PeriodoInvalidoException {
        float faturamento = 0;
        for (Venda venda:
             this.buscarVendasPeriodo(dataInicio, dataFim)) {
            faturamento += venda.getValor();
        }
        return faturamento;
    }

    public float getLucroTotal() {
        float precoVenda, precoCusto, quantidade, lucroUnit, lucro = 0.0f;
        for (Venda venda:
                reporitorioVendas.listarVendas()) {
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
        return lucro;
    }

    public float getLucroPeriodo(Calendar dataInicio, Calendar dataFim) throws PeriodoInvalidoException {
        float precoVenda, precoCusto, quantidade, lucroUnit, lucro = 0.0f;
        for (Venda venda:
                this.buscarVendasPeriodo(dataInicio, dataFim)) {
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
        return lucro;
    }

    public float getTicketMedioTotal() {
        return (this.getFaturamentoTotal() / this.getNumeroVendasTotal());
    }

    public float getTicketMedioPeriodo(Calendar dataInicio, Calendar dataFim) throws PeriodoInvalidoException {
        return (this.getFaturamentoPeriodo(dataInicio, dataFim)/this.getNumeroVendasPeriodo(dataInicio, dataFim));
    }

    public int getNumeroVendasTotal() {
        int numeroVendas = 0;
        for (Venda venda:
                reporitorioVendas.listarVendas()) {
            numeroVendas++;
        }
        return numeroVendas;
    }

    public int getNumeroVendasPeriodo(Calendar dataInicio, Calendar dataFim) throws PeriodoInvalidoException {
        return this.buscarVendasPeriodo(dataInicio, dataFim).size();
    }

    public List<Map.Entry<String, Float>> getProdutosMaisVendidos() throws NaoHaProdutosException {
        List<Map.Entry<String, Float>> produtosMaisVendidosOrdenado = new ArrayList<>(this.reporitorioVendas.getProdutosMaisVendidos().entrySet());
        produtosMaisVendidosOrdenado.sort(Map.Entry.comparingByValue());
        return produtosMaisVendidosOrdenado;
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
