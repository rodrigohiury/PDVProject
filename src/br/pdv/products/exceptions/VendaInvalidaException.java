package br.pdv.products.exceptions;

public class VendaInvalidaException extends Exception{

    public VendaInvalidaException() {
        super("Venda Inválida, tente novamente!");
    }
}
