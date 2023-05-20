package br.pdv.products.repository;

import br.pdv.products.data.caixa.Caixa;

import java.util.Calendar;
import java.util.Collection;

public interface ICaixaRepository {

    public void inserirCaixa(Caixa caixa);

    public void removerCaixa(Caixa caixa);

    public void alterarCaixa(Caixa caixa);

    public Caixa buscarCaixa(Calendar data);

    public Collection<Caixa> listarCaixas();

}
