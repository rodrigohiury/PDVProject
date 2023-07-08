package main.repository;

import main.transacao.Venda;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

public interface IVendasRepository {

    public void inserir(Venda venda);

    public void remover(Venda venda);

    public void alterarVenda(Venda venda);

    public Collection<Venda> buscarVendas(Calendar data);

    public Venda buscarVenda(int numero);

    public Collection<Venda> listarVendas();

    public boolean isEmpty();

    public Map<String, Float> getProdutosMaisVendidos();


}
