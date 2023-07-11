package main.services;

import main.exceptions.NaoHaProdutosException;
import main.produto.Produto;
import main.transacao.Venda;
import main.exceptions.PeriodoInvalidoException;
import main.exceptions.VendaInexistenteException;
import main.exceptions.VendaInvalidaException;
import main.repository.IVendasRepository;

import java.util.*;

public class ControllerEstatisticas {

    private static IVendasRepository repositorioVendas;
    private static ControllerEstatisticas instance;

    private ControllerEstatisticas(IVendasRepository repositorioVendas) {
        this.repositorioVendas = repositorioVendas;
    }

    public static ControllerEstatisticas getInstance(IVendasRepository repositorioVendas) {
        if (instance == null){
            instance = new ControllerEstatisticas(repositorioVendas);
        }
        return instance;
    }

    public void inserirVenda(Venda venda) throws VendaInvalidaException {
        if (venda != null){
            repositorioVendas.inserir(venda);
        } else {
            throw new VendaInvalidaException();
        }
    }

    public void removerVenda(Venda venda) throws VendaInvalidaException, VendaInexistenteException {
        if (venda != null){
            if (repositorioVendas.buscarVenda(venda.getNumero()) != null){
                repositorioVendas.remover(venda);
            } else throw new VendaInexistenteException();
        } else throw new VendaInvalidaException();
    }

    public void alterarVenda(Venda venda) throws VendaInvalidaException, VendaInexistenteException {
        if (venda != null){
            Venda vendaAnterior = repositorioVendas.buscarVenda(venda.getNumero());
            if (vendaAnterior != null){
                repositorioVendas.alterarVenda(venda);
            } else throw new VendaInexistenteException();
        } else throw new VendaInvalidaException();
    }

    public Venda buscarVenda(int numero) throws VendaInexistenteException, VendaInvalidaException {
        if (numero > 0){
            Venda venda = repositorioVendas.buscarVenda(numero);
            if (venda != null){
                return venda;
            } else throw new VendaInexistenteException();
        } else throw new VendaInvalidaException();
    }

    public ArrayList<Venda> buscarVendasPeriodo (Calendar dataInicio, Calendar dataFim) throws PeriodoInvalidoException {
        ArrayList<Venda> vendasPeriodo = new ArrayList<>();
        if (dataInicio.compareTo(dataFim) <= 0){
            for (Venda venda:
                    repositorioVendas.listarVendas()) {
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
                repositorioVendas.listarVendas()) {
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
                repositorioVendas.listarVendas()) {
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
                repositorioVendas.listarVendas()) {
            numeroVendas++;
        }
        return numeroVendas;
    }

    public int getNumeroVendasPeriodo(Calendar dataInicio, Calendar dataFim) throws PeriodoInvalidoException {
        return this.buscarVendasPeriodo(dataInicio, dataFim).size();
    }

    public List<Map.Entry<String, Float>> getProdutosMaisVendidos() {
        List<Map.Entry<String, Float>> produtosMaisVendidosOrdenado = new ArrayList<>(this.repositorioVendas.getProdutosMaisVendidos().entrySet());
        Collections.sort(produtosMaisVendidosOrdenado, new Comparator<Map.Entry<String, Float>>() {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return produtosMaisVendidosOrdenado;
    }

    public List<Map.Entry<String, Float>> getProdutosVendidosPeriodo(Calendar dataInicio, Calendar dataFim) throws PeriodoInvalidoException {
        Map<String, Float> produtosVendidosPeriodo = new TreeMap<>();
        ArrayList<Venda> vendasPeriodo = this.buscarVendasPeriodo(dataInicio, dataFim);
        Set<Produto> produtosVendidos;
        float quantidadeVendida;
        if (vendasPeriodo != null){
            for (Venda venda:
                    vendasPeriodo) {
                produtosVendidos = venda.getCarrinho().keySet();
                for (Produto produto:
                     produtosVendidos) {
                    quantidadeVendida = venda.getCarrinho().get(produto);
                    produtosVendidosPeriodo.put(produto.getCodigo(), quantidadeVendida);
                }
            }
            List<Map.Entry<String, Float>> produtosAchados = new ArrayList<>(produtosVendidosPeriodo.entrySet());
            Collections.sort(produtosAchados, new Comparator<Map.Entry<String, Float>>() {
                @Override
                public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            return produtosAchados;
        }else {
            return null;
        }
    }

    public void deleteArchives(){
        this.repositorioVendas.deleteArchives();
    }
}
