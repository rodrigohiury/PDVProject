package Main.exceptions;
import javax.swing.JOptionPane;

public class NumeroVendaInvalidoException extends Exception{

    private int numero;

    public NumeroVendaInvalidoException(int numero) {
        super("Numero de venda inválido!");
        this.numero = numero;
    }
}
