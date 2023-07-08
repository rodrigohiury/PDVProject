package main.exceptions;

import javax.swing.JOptionPane;
/** Exceção levantada quando não há Produtos cadastrados no sistema.
 * @author Luiz
 * @since jul 2023
 * @version 1.0
 */
public class NaoHaProdutosException extends Exception {

    /** Construtor da exceção que fornece uma mensagem de aviso na tela.
     */
    public NaoHaProdutosException(){
        JOptionPane.showMessageDialog(null,"Não há Produtos Cadastrados","Aviso",
                JOptionPane.WARNING_MESSAGE);
    }
    
}
