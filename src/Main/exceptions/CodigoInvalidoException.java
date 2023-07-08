package Main.exceptions;

import javax.swing.*;

/** Exceção levantada quando o código não segue os molde.
 * @author Luiz
 * @since jul 2023
 * @version 1.0
 */
public class CodigoInvalidoException extends Exception{
    /**Construtor da exceção que fornece uma mensagem de aviso na tela.
     */
	public CodigoInvalidoException() {
		JOptionPane.showMessageDialog(null,"Código inválido.","Aviso", JOptionPane.ERROR_MESSAGE);
	}

}
