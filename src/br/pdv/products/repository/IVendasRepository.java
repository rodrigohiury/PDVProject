package br.pdv.products.repository;

import br.pdv.products.data.produto.Produto;
import br.pdv.products.data.venda.Venda;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public interface IVendasRepository {

    public void inserir(Venda venda);

    public void remover(Venda venda);

    public void alterarVenda(Venda venda);

    public Collection<Venda> buscarVendas(Calendar data);

    public Venda buscarVenda(int numero);

    public Collection<Venda> listarVendas();

    public boolean isEmpty();


}
