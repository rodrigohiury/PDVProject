package main.repository;

import main.exceptions.NaoHaProdutosException;
import main.produto.Produto;
import main.exceptions.ProdutoNaoCadastradoException;
import main.exceptions.CodigoInvalidoException;
import main.exceptions.CodigoJaCadastradoException;

import java.util.ArrayList;
public interface IProdutoRepository {

    public Produto consultarProduto(String codigo) throws ProdutoNaoCadastradoException,CodigoInvalidoException;
    boolean confereCodigo(String codigo);
    public void cadastrarProduto(Produto produto) throws CodigoJaCadastradoException,CodigoInvalidoException;
    public void removerProduto(String codigo) throws ProdutoNaoCadastradoException,CodigoInvalidoException;
    public void alterarPreco(String codigo, float novoPreco) throws ProdutoNaoCadastradoException, CodigoInvalidoException;
    public ArrayList<Object> listarProdutos() throws NaoHaProdutosException;
    public boolean salvaProdutoRepository();
    public void lerProdutoRepository();

}
