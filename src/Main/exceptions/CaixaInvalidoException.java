package Main.exceptions;
import javax.swing.JOptionPane;

public class CaixaInvalidoException extends Exception{


    public CaixaInvalidoException() {
        super("ERRO!, Caixa Inválido!");
    }

}
