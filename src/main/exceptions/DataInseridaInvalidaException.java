package main.exceptions;

public class DataInseridaInvalidaException extends Exception{

    public DataInseridaInvalidaException() {
        super("Data Inserida é Inválida! Data esperada DD/MM/YYYY");
    }
}
