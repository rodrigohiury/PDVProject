package Main.exceptions;
import javax.swing.JOptionPane;

public class VendaInvalidaException extends Exception{

    public VendaInvalidaException() {
        super("Venda Inválida, tente novamente!");
    }
}
