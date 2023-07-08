package main.exceptions;

public class ValorSangriaInvalidoException extends Exception{

    private float valor;

    public ValorSangriaInvalidoException(Throwable cause, float valor) {
        super("Valor de sangria inválido!", cause);
        this.valor = valor;
    }

    public ValorSangriaInvalidoException(float valor) {
        super("Valor de sangria inválido!");
        this.valor = valor;
    }
}
