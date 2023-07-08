package Main.exceptions;
import javax.swing.JOptionPane;

public class ValorAberturaInvalidoException extends Exception{
    private float valor;

    public ValorAberturaInvalidoException(float valor) {
        super("Valor de Abertura Inválido!");
        this.valor = valor;
    }
}
