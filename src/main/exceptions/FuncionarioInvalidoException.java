package main.exceptions;

public class FuncionarioInvalidoException extends Exception{

    public FuncionarioInvalidoException(String message) {
        super("ERRO! Funcionário Inváido");
    }
}
