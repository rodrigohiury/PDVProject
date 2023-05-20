package br.pdv.products.services;

import br.pdv.products.data.produto.Produto;
import br.pdv.products.exceptions.ProdutoDuplicadoException;
import br.pdv.products.exceptions.ProdutoInexistenteException;
import br.pdv.products.exceptions.ProdutoInvalidoException;
import br.pdv.products.repository.IProdutoRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class ControllerProduto {

    private IProdutoRepository repProdutosCadastrados;

    public ControllerProduto(IProdutoRepository produtoRepository) {
        repProdutosCadastrados = produtoRepository;
    }

    public void adicionarProduto(Produto produto) throws IOException, ProdutoInvalidoException, ProdutoDuplicadoException {
        if(produto!=null){
            Produto produtoAchado = repProdutosCadastrados.buscarProduto(produto.getNome());
            if (produtoAchado != null){
                if (produtoAchado != produto){
                    repProdutosCadastrados.inserir(produto);
                } else {
                    throw new ProdutoDuplicadoException();
                }
            }
        } else {
            throw new ProdutoInvalidoException();
        }
    }

    public void removerProduto(Produto produto) throws IOException, ProdutoInvalidoException, ProdutoInexistenteException {
        if (produto!=null){
            if (repProdutosCadastrados.buscarProduto(produto.getNome()) != null){
                repProdutosCadastrados.remover(produto);
            }else {
                throw new ProdutoInexistenteException();
            }
        }else {
            throw new ProdutoInvalidoException();
        }
    }

    public void alterarProduto(Produto produto) throws IOException, ProdutoInvalidoException, ProdutoInexistenteException {
        if (produto!=null){
            repProdutosCadastrados.alterar(produto);
        } else {
            throw new ProdutoInvalidoException();
        }
    }

    public Collection<Produto> buscarProdutos(String chave) throws ProdutoInexistenteException {
        Collection<Produto> produtos = repProdutosCadastrados.buscarProdutos(chave);
        if (produtos == null || produtos.isEmpty()){
            throw new ProdutoInexistenteException();
        }
        return produtos;
    }

    public Collection<Produto> listarProdutos(){
        return repProdutosCadastrados.listarProdutos();
    }
}
