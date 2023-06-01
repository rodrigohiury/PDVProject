package br.pdv.products.repository;

import br.pdv.products.data.produto.Produto;
import br.pdv.products.exceptions.ProdutoInexistenteException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;

public interface IProdutoRepository {

    public void inserir(Produto produto) throws IOException;

    public void alterar(Produto produto) throws IOException, ProdutoInexistenteException;

    public void remover(Produto produto) throws IOException;

    public Collection<Produto> buscarProdutos(String nome);

    public Produto buscarProduto(String nome);

    public Collection<Produto> listarProdutos();

}
