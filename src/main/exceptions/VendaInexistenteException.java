package main.exceptions;

public class VendaInexistenteException extends Exception{

    public VendaInexistenteException() {
        super("ERRO! Venda Inexistente!");
    }
}
