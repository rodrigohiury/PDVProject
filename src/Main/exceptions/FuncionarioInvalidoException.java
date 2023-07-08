package Main.exceptions;
import javax.swing.JOptionPane;

public class FuncionarioInvalidoException extends Exception{

    public FuncionarioInvalidoException(String message) {
        super("ERRO! Funcionário Inváido");
    }
}
