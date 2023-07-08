package main.exceptions;

import javax.swing.JOptionPane;

/** Exceção levantada quando não há funcionários cadastrados no sistema
 * @author Luiz
 * @since jul 2023
 * @version 1.0
 */
public class NaoHaFuncionariosException extends Exception {

    /** Construtor da exceção que fornece uma mensagem de erro na tela.
     */
    public NaoHaFuncionariosException(){
        JOptionPane.showMessageDialog(null,"Não há funcionários cadastrados.",
                "Aviso",JOptionPane.ERROR_MESSAGE);
    }
}
