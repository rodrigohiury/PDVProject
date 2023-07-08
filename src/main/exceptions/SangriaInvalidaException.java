package main.exceptions;

public class SangriaInvalidaException extends Exception{

    private float valor;

    public SangriaInvalidaException(float valor) {
        super("Sangria Inválida!");
        this.valor = valor;
    }

    public float getValor() {
        return valor;
    }
}
