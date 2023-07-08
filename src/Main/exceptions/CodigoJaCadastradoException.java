package Main.exceptions;

import Main.produto.Produto;

import javax.swing.*;

/** Exceção levantada quando um Produto de mesmo código está tentando ser recadastrado no sistema
 * @author Luiz
 * @since jul 2023
 * @version 1.0
 */
public class CodigoJaCadastradoException extends Exception {
    //Criando variaveis e construtor para armazenar o produto que gerou a exceção
    private Produto produto;

    /** Construtor da exceção que recebe como parâmetro um objeto Produto
     *  e fornece uma mensagem de aviso na tela.
     * @param produto - objeto Produto passado como parâmetro no construtor da exceção.
     */
    public CodigoJaCadastradoException(Produto produto){
        this.produto = produto;
        JOptionPane.showMessageDialog(null,"O Produto de código "
                + getProduto().getCodigo() + " já foi cadastrado no Sistema.","Aviso",
                JOptionPane.WARNING_MESSAGE);
    }
    
    /** Método que retorna o produto que gerou a exceção.
     * @return Produto - objeto Produto que deu origem a exceção.
     */
    public Produto getProduto(){
        return this.produto;
    }
}
