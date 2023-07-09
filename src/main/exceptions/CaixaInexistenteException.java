package main.exceptions;

import javax.swing.JOptionPane;
/** Exceção levantada quando não existir Caixa
 *
 * @author Rodrigo
 * @since jul 2023
 * @version 1.0
 */
public class CaixaInexistenteException extends Exception {

    /**Construtor da exceção que fornece uma mensagem de erro na tela.
     */
    public CaixaInexistenteException(){
        JOptionPane.showMessageDialog(null,"Caixa não existe!."
                , "Aviso",JOptionPane.ERROR_MESSAGE);
    }

}
