package br.pdv.products.exceptions;

public class ProdutoInexistenteException extends Exception {

    public ProdutoInexistenteException() {
        super("Produto não encontrado!");
    }
}
