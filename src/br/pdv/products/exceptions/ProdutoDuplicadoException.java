package br.pdv.products.exceptions;

public class ProdutoDuplicadoException extends Exception{
    public ProdutoDuplicadoException() {
        super("Entrada duplicada!, Verifique os dados e tente novamente!");
    }
}
