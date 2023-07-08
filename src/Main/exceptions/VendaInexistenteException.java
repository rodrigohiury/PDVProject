package Main.exceptions;
import javax.swing.JOptionPane;

public class VendaInexistenteException extends Exception{

    public VendaInexistenteException() {
        super("ERRO! Venda Inexistente!");
    }
}
