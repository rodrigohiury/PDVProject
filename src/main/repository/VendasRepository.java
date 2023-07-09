package main.repository;

import main.produto.Produto;
import main.transacao.Venda;

import java.io.*;
import java.util.*;

public class VendasRepository implements IVendasRepository {

    private ArrayList<Venda> vendasCadastradas;
    private Map<String ,Float> produtosMaisVendidos;
    private String pathDiretorio;
    private String pathArquivoVendas;
    private String pathArquivoProdutos;
    private File vendaDiretorio;
    private File vendaArquivo;
    private File produtosArquivo;

    public VendasRepository() throws ClassNotFoundException {
        vendasCadastradas = new ArrayList<>();
        produtosMaisVendidos = new TreeMap<>();
        pathDiretorio = "./src/archive/vendas";
        pathArquivoVendas = pathDiretorio + "/vendas_cadastradas.vnd";
        pathArquivoProdutos = pathDiretorio + "/produtos_mais_vendido.prd";
        vendaDiretorio = new File(pathDiretorio);
        if (!vendaDiretorio.exists()){
            vendaDiretorio.mkdir();
        }
        vendaArquivo = new File(pathArquivoVendas);
        produtosArquivo = new File(pathArquivoProdutos);
        if(vendaArquivo.exists()){
            this.deserializeVendas();
        }
        if(produtosArquivo.exists()){
            this.deserializeProdutos();
        }
        Venda.setSequencia(this.getUltimaSequencia());
    }

    @Override
    public void inserir(Venda venda) {
        this.vendasCadastradas.add(venda);
        for (Produto produto:
                venda.getCarrinho().keySet()) {
            String codigoProduto = produto.getCodigo();
            float quantidadeVendida = venda.getCarrinho().get(produto);
            if(this.produtosMaisVendidos.containsKey(codigoProduto)){
                float quantidadeJaVendida = this.produtosMaisVendidos.get(codigoProduto);
                quantidadeVendida += quantidadeJaVendida;
            }
            this.produtosMaisVendidos.put(codigoProduto, quantidadeVendida);
        }
        this.serializeVendas();
        this.serializeProdutosMaisVendidos();
    }

    @Override
    public void remover(Venda venda) {
        this.vendasCadastradas.remove(venda);
        for (Produto produto:
                venda.getCarrinho().keySet()) {
            String codigoProduto = produto.getCodigo();
            float quantidadeDevolvida = venda.getCarrinho().get(produto);
            float quantidadeVendida = 0;
            if(this.produtosMaisVendidos.containsKey(codigoProduto)){
                quantidadeVendida = this.produtosMaisVendidos.get(codigoProduto);
                quantidadeVendida -= quantidadeDevolvida;
            }
            this.produtosMaisVendidos.put(codigoProduto, quantidadeVendida);
        }
        this.serializeVendas();
        this.serializeProdutosMaisVendidos();
    }

    public void alterarVenda(Venda venda){
        Venda vendaAnterior = this.buscarVenda(venda.getNumero());
        vendasCadastradas.set(vendasCadastradas.indexOf(vendaAnterior), venda);
        this.atualizaProdutosMaisVendidos();
        this.serializeVendas();
        this.serializeProdutosMaisVendidos();
    }

    @Override
    public Collection<Venda> buscarVendas(Calendar data) {
        Collection<Venda> vendas = new ArrayList<>();
        for (Venda venda:
             this.vendasCadastradas) {
            if (this.isSameDay(venda.getDataCompra(), data)){
                vendas.add(venda);
            }
        }
        if (vendas != null && !vendas.isEmpty()){
            return vendas;
        }
        return null;
    }

    @Override
    public Venda buscarVenda(int numero) {
        for (Venda venda:
                this.vendasCadastradas) {
            if (venda.getNumero() == numero){
                return venda;
            }
        }
        return null;
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

    public boolean isEmpty(){
        return vendasCadastradas.isEmpty();
    }

    @Override
    public Collection<Venda> listarVendas() {
        return vendasCadastradas;
    }

    private void serializeVendas(){
        try{
            FileOutputStream gravador = new FileOutputStream(pathArquivoVendas);
            ObjectOutputStream conversor = new ObjectOutputStream(gravador);
            conversor.writeObject(this.vendasCadastradas);
            conversor.flush();
            conversor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void serializeProdutosMaisVendidos(){
        try{
            FileOutputStream gravador = new FileOutputStream(pathArquivoProdutos);
            ObjectOutputStream conversor = new ObjectOutputStream(gravador);
            conversor.writeObject(this.produtosMaisVendidos);
            conversor.flush();
            conversor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void deserializeVendas() throws ClassNotFoundException {
        try {
            FileInputStream leitor = new FileInputStream(pathArquivoVendas);
            ObjectInputStream conversor = new ObjectInputStream(leitor);
            this.vendasCadastradas = (ArrayList<Venda>) conversor.readObject();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void deserializeProdutos() throws ClassNotFoundException {
        try {
            FileInputStream leitor = new FileInputStream(pathArquivoProdutos);
            ObjectInputStream conversor = new ObjectInputStream(leitor);
            this.produtosMaisVendidos = (Map<String, Float>) conversor.readObject();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getUltimaSequencia(){
        int numero = 0;
        for (Venda venda:
             this.vendasCadastradas) {
            if(venda.getNumero() > numero){
                numero = venda.getNumero();
            }
        }
        return numero;
    }

    private void atualizaProdutosMaisVendidos(){
        this.produtosMaisVendidos.clear();
        for (Venda venda:
             this.vendasCadastradas) {
            for (Produto produto:
                 venda.getCarrinho().keySet()) {
                String codigoProduto = produto.getCodigo();
                float quantidadeVendida = venda.getCarrinho().get(produto);
                this.produtosMaisVendidos.put(codigoProduto, quantidadeVendida);
            }
        }
    }

    public Map<String, Float> getProdutosMaisVendidos() {
        return produtosMaisVendidos;
    }

    public void deleteArchives(){
        this.vendaArquivo.delete();
        this.produtosArquivo.delete();
        this.vendasCadastradas.clear();
        this.produtosMaisVendidos.clear();
    }
}
