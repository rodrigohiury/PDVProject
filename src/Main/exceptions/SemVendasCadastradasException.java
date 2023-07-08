package Main.exceptions;
import javax.swing.JOptionPane;

public class SemVendasCadastradasException extends Exception{

    public SemVendasCadastradasException() {
        super("ERRO! Não há vendas Cadastradas!");
    }
}
