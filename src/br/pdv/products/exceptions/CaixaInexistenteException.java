package br.pdv.products.exceptions;

public class CaixaInexistenteException extends Exception{

    public CaixaInexistenteException() {
        super("ERRO! Caixa não existe!");
    }
}
