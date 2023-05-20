package br.pdv.products.exceptions;

public class ProdutoInvalidoException extends Exception{
    public ProdutoInvalidoException() {
        super("Produto inválido, tente novamente!");
    }
}
