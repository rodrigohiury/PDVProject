package main.exceptions;

import javax.swing.*;

/** Exceção levantada quando um veículo buscado não está cadastrado no sistema.
 * @author Luiz Augusto
 * @since jul 2023
 * @version 1.0
 */
public class ProdutoNaoCadastradoException extends Exception {

    private String codigo;



    /** Construtor da exceção pra armazenar qual codigo foi passado quando a exceção foi levantada
     * @param codigo - String que levantou a exceção.
     */
    public ProdutoNaoCadastradoException(String codigo) {
        this.codigo = codigo;
        JOptionPane.showMessageDialog(null,"O Produto com código " +
                this.getCodigo() + " não está cadastrado.","Aviso"
                ,JOptionPane.WARNING_MESSAGE);
    }
    
    /** Método que retorna a String da codigo que levantou a exceção.
     * @return String - código fornecido como parâmetro que levantou a exceção.
     */
    public String getCodigo(){
        return codigo;
    }
    
}
