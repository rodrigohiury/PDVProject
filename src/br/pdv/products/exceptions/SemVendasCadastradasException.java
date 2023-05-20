package br.pdv.products.exceptions;

public class SemVendasCadastradasException extends Exception{

    public SemVendasCadastradasException() {
        super("ERRO! Não há vendas Cadastradas!");
    }
}
