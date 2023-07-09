package main.caixa;

import main.funcionario.Funcionario;
import main.produto.Produto;
import main.exceptions.*;
import main.transacao.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.lang.StringBuilder;

public class Caixa {

    protected int numero;
    protected Funcionario vendedor;
    protected Calendar dataAbertura = new GregorianCalendar();
    protected Calendar dataFechamento = new GregorianCalendar();
    protected float valorAbertura;
    protected float valorEmCaixa;
    protected ArrayList<Transacao> vendasDoDia = new ArrayList<>();
    protected ArrayList<String> formasDePagamento = new ArrayList<>();
    protected String notaFiscal;

    public Caixa(Funcionario vendedor, int numero, ArrayList<String> formasDePagamento) throws CodigoInvalidoException {
        this.vendedor = vendedor;
        if (numero > 0) {
            this.numero = numero;
        } else {
            throw new CodigoInvalidoException();
        }
        this.dataAbertura.setTime(Date.from(Instant.now()));
        this.valorAbertura = 0.0f;
        this.valorEmCaixa = valorAbertura;
        this.formasDePagamento = formasDePagamento;
    }

    public Caixa(Funcionario vendedor, int numero, ArrayList<String> formasDePagamento, float valor)
            throws ValorAberturaInvalidoException {
        this.vendedor = vendedor;
        if (numero > 0) {
            this.numero = numero;
        }
        this.dataAbertura.setTime(Date.from(Instant.now()));
        if (valor < 0) {
            throw new ValorAberturaInvalidoException(valor);
        }
        this.valorAbertura = valor;
        this.valorEmCaixa = valor;
        this.formasDePagamento = formasDePagamento;
    }

    public Venda buscarVenda(int numero) throws NumeroVendaInvalidoException {
        if (numero > 0) {
            for (Transacao transacao : this.vendasDoDia) {
                if (transacao instanceof Venda) {
                    if (((Venda) transacao).getNumero() == numero) {
                        return (Venda) transacao;
                    }
                }
            }
            return null;
        } else {
            throw new NumeroVendaInvalidoException(numero);
        }
    }

    public void adicionarVenda(Venda venda) throws VendaInvalidaException {
        if (venda != null) {
            this.vendasDoDia.add(venda);
            this.valorEmCaixa += venda.getValor();
        } else {
            throw new VendaInvalidaException();
        }
    }

    public void removerVenda(int numero) throws NumeroVendaInvalidoException, VendaInexistenteException {
        Venda venda = this.buscarVenda(numero);
        if (venda != null) {
            this.vendasDoDia.remove(venda);
            this.valorEmCaixa -= venda.getValor();
        } else {
            throw new VendaInexistenteException();
        }
    }

    public void devolverProduto(Produto produto, int numero) throws NumeroVendaInvalidoException,
            VendaInexistenteException, ProdutoNaoCadastradoException, ProdutoNaoCadastradoException {
        if (produto != null) {
            Venda venda = this.buscarVenda(numero);
            if (venda != null) {
                venda.removerCompra(produto);
                this.atualizaValorEmCaixa();
            } else {
                throw new VendaInexistenteException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    public void sangrarCaixa(float valor) throws ValorSangriaInvalidoException, SangriaInvalidaException {
        if (valor > 0) {
            if (this.valorEmCaixa >= valor) {
                Transacao sangria = new Sangria(valor);
                this.vendasDoDia.add(sangria);
                this.atualizaValorEmCaixa();
            } else {
                throw new SangriaInvalidaException(valor);
            }
        } else {
            throw new ValorSangriaInvalidoException(valor);
        }
    }

    public void reforcarCaixa(float valor) throws ValorReforcoInvalidoException {
        if (valor > 0) {
            Transacao reforco = new Reforco(valor);
            this.vendasDoDia.add(reforco);
            this.atualizaValorEmCaixa();
        } else {
            throw new ValorReforcoInvalidoException(valor);
        }
    }

    public void atualizaValorEmCaixa() {
        this.valorEmCaixa = this.valorAbertura;
        for (Transacao transacao : this.vendasDoDia) {
            this.valorEmCaixa += transacao.getValor();
        }
    }

    public ArrayList<Venda> getVendasDoDia() {
        ArrayList<Venda> vendas = new ArrayList<>();
        for (Transacao transacao : this.vendasDoDia) {
            if (transacao instanceof Venda) {
                vendas.add((Venda) transacao);
            }
        }
        return vendas;
    }

    public String gerarNotaFiscal(Venda venda) {
        StringBuilder notaFiscalBuilder = new StringBuilder();

        notaFiscalBuilder.append("===== NOTA FISCAL =====\n");
        notaFiscalBuilder.append("Data: ").append(venda.getDataCompra().getTime()).append("\n");
        notaFiscalBuilder.append("Vendedor: ").append(venda.getVendedor().getNome()).append("\n");
        notaFiscalBuilder.append("Número da Venda: ").append(venda.getNumero()).append("\n");
        notaFiscalBuilder.append("Itens da Venda:\n");

        for (Map.Entry<Produto, Float> entry : venda.getCarrinho().entrySet()) {
            Produto produto = entry.getKey();
            Float quantidade = entry.getValue();
            notaFiscalBuilder.append("- Produto: ").append(produto.getNome())
                    .append(" | Preço Unitário: ").append(produto.getPrecoVenda())
                    .append(" | Quantidade: ").append(quantidade)
                    .append(" | Subtotal: ").append(produto.getPrecoVenda() * quantidade).append("\n");
        }

        notaFiscalBuilder.append("Valor Total: ").append(venda.getValor()).append("\n");
        notaFiscalBuilder.append("=======================\n");
        return notaFiscalBuilder.toString();
    }

    public void fecharCaixa() {
        dataFechamento.setTime(Date.from(Instant.now()));
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

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<String> getFormasDePagamento() {
        return formasDePagamento;
    }

    public void setFormasDePagamento(ArrayList<String> formasDePagamento) {
        this.formasDePagamento = formasDePagamento;
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

}
