package Main.repository;

import Main.exceptions.NaoHaProdutosException;
import Main.produto.Produto;
import Main.exceptions.ProdutoNaoCadastradoException;
import Main.exceptions.CodigoInvalidoException;
import Main.exceptions.CodigoJaCadastradoException;

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
