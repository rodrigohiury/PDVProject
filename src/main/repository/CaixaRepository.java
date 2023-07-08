package main.repository;

import main.caixa.Caixa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class CaixaRepository implements ICaixaRepository {

    private ArrayList<Caixa> caixaArrayList = new ArrayList<>();


    @Override
    public void inserirCaixa(Caixa caixa) {
        caixaArrayList.add(caixa);
    }

    @Override
    public void removerCaixa(Caixa caixa) {
        caixaArrayList.remove(caixa);
    }

    @Override
    public void alterarCaixa(Caixa caixa) {
        if (caixa != null){
            Caixa caixaAnterior = this.buscarCaixa(caixa.getDataAbertura());
            if (caixaAnterior != null){
                caixaArrayList.set(caixaArrayList.indexOf(caixaAnterior),caixa );
            }
        }
    }

    @Override
    public Caixa buscarCaixa(Calendar data) {
        for (Caixa caixa:
             this.caixaArrayList) {
            if (caixa.getDataAbertura().equals(data)){
                return caixa;
            }
        }
        return null;
    }

    @Override
    public Collection<Caixa> listarCaixas() {
        return this.caixaArrayList;
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
}
