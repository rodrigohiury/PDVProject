package Main.exceptions;
import javax.swing.JOptionPane;

public class VendaDuplicadaException extends Exception{

    public VendaDuplicadaException() {
        super("Venda duplicada!, tente novamente!");
    }
}
