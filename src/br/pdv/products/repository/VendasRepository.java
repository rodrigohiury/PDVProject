package br.pdv.products.repository;

import br.pdv.products.data.venda.Venda;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class VendasRepository implements IVendasRepository {

    private ArrayList<Venda> vendasCadastradas;
    private String pathDiretorio;
    private String pathArquivo;
    private File vendaDiretorio;
    private File vendaArquivo;

    public VendasRepository() throws ClassNotFoundException {
        vendasCadastradas = new ArrayList<>();
        pathDiretorio = "./src/archive/vendas";
        pathArquivo = pathDiretorio + "/vendasCadastradas.vnd";
        vendaDiretorio = new File(pathDiretorio);
        if (!vendaDiretorio.exists()){
            if(!vendaDiretorio.mkdir()){
                System.out.println("Não deu!");
            }else {
                System.out.println("Deu!");
            }
        }
        vendaArquivo = new File(pathArquivo);
        if(vendaArquivo.exists()){
            this.deserializeVendas();
        }
        Venda.setSequencia(this.getUltimaSequencia());
    }

    @Override
    public void inserir(Venda venda) {
        this.vendasCadastradas.add(venda);
        this.serializeVendas();
    }

    @Override
    public void remover(Venda venda) {
        this.vendasCadastradas.remove(venda);
        this.serializeVendas();
    }

    public void alterarVenda(Venda venda){
        Venda vendaAnterior = this.buscarVenda(venda.getNumero());
        vendasCadastradas.set(vendasCadastradas.indexOf(vendaAnterior), venda);
        this.serializeVendas();
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
            FileOutputStream gravador = new FileOutputStream(pathArquivo);
            ObjectOutputStream conversor = new ObjectOutputStream(gravador);
            conversor.writeObject(this.vendasCadastradas);
            conversor.flush();
            conversor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void deserializeVendas() throws ClassNotFoundException {
        try {
            FileInputStream leitor = new FileInputStream(pathArquivo);
            ObjectInputStream conversor = new ObjectInputStream(leitor);
            this.vendasCadastradas = (ArrayList<Venda>) conversor.readObject();
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
}
