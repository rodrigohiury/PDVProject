package Main.exceptions;
import javax.swing.JOptionPane;

public class ValorReforcoInvalidoException extends Exception{

    private float valor;

    public ValorReforcoInvalidoException(Throwable cause, float valor) {
        super("Valor reforço inválido!", cause);
        this.valor = valor;
    }

    public ValorReforcoInvalidoException(float valor){
        super("Valor reforço inválido!");
        this.valor = valor;
    }
}
