package br.pdv.products.exceptions;

public class VendaDuplicadaException extends Exception{

    public VendaDuplicadaException() {
        super("Venda duplicada!, tente novamente!");
    }
}
