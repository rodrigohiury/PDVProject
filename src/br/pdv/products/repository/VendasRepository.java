package br.pdv.products.repository;

import br.pdv.products.data.venda.Venda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class VendasRepository implements IVendasRepository{

    private ArrayList<Venda> vendasCadastradas = new ArrayList<>();

    @Override
    public void inserir(Venda venda) {
        this.vendasCadastradas.add(venda);
    }

    @Override
    public void remover(Venda venda) {
        this.vendasCadastradas.remove(venda);
    }

    public void alterarVenda(Venda venda){
        Venda vendaAnterior = this.buscarVenda(venda.getNumero());
        vendasCadastradas.set(vendasCadastradas.indexOf(vendaAnterior), venda);
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
        return null;
    }
}
