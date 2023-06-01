package br.pdv.products.repository;

import br.pdv.products.data.produto.Produto;
import br.pdv.products.exceptions.ProdutoInexistenteException;

import java.io.*;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class ProdutoRepository implements IProdutoRepository{

    private Set<Produto> produtosCadastrados = new TreeSet<>();
    private final String produtoArchive = "RegisteredProducts.ser";

    @Override
    public void inserir(Produto produto) throws IOException {
        this.produtosCadastrados.add(produto);
        this.save();
    }

    public void inserirNotSave(Produto produto) throws IOException {
        this.produtosCadastrados.add(produto);
    }

    @Override
    public void alterar(Produto produto) throws IOException, ProdutoInexistenteException {
        Produto produto1 = buscarProduto(produto.getNome());
        if (produto1 != null){
            this.removerNotSave(produto1);
            this.inserirNotSave(produto);
        }else {
            produto1 = buscarProduto(produto.getCodigo());
            if (produto1 != null){
                this.removerNotSave(produto1);
                this.inserirNotSave(produto);
            } else {
                throw new ProdutoInexistenteException();
            }
        }
        this.save();
    }

    @Override
    public void remover(Produto produto) throws IOException {
        this.produtosCadastrados.remove(produto);
        this.save();
    }

    public void removerNotSave(Produto produto) {
        this.produtosCadastrados.remove(produto);
    }

    @Override
    public Collection<Produto> buscarProdutos(String nome) {
        TreeSet<Produto> produtosBuscados = new TreeSet<>();
        Produto produto;
        while (produtosCadastrados.iterator().hasNext()){
            produto = produtosCadastrados.iterator().next();
            if (produto != null){
                if (produto.getNome().contains(nome)){
                    produtosBuscados.add(produto);
                }else if (produto.getCodigo().contains(nome)){
                    produtosBuscados.add(produto);
                }
            }
        }
        if (!produtosBuscados.isEmpty()){
            return produtosBuscados;
        }
        return null;
    }

    @Override
    public Produto buscarProduto(String nome) {
        while (produtosCadastrados.iterator().hasNext()){
            Produto produto = produtosCadastrados.iterator().next();
            if (produto != null){
                if (produto.getNome().equalsIgnoreCase(nome)){
                    return produto;
                }else if (produto.getCodigo().equalsIgnoreCase(nome)){
                    return produto;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<Produto> listarProdutos() {
        return produtosCadastrados;
    }

    private void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(this.produtoArchive);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.produtosCadastrados);
        fileOutputStream.close();
        objectOutputStream.close();
    }

    private TreeSet<Produto> load() throws IOException, ClassNotFoundException {
        TreeSet<Produto> produtos = new TreeSet<>();
        FileInputStream fileInputStream = new FileInputStream(this.produtoArchive);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        produtos = (TreeSet<Produto>) objectInputStream.readObject();
        return produtos;
    }
}
