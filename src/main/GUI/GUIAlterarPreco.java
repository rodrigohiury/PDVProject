package main.GUI;

import main.exceptions.CodigoInvalidoException;
import main.exceptions.ProdutoNaoCadastradoException;
import main.repository.ProdutoRepository;
import main.repository.ProdutoRepository;

import java.awt.event.*;
import javax.swing.*;

/** A classe <b>GUIAlterarPreco</b> é responsável por criar a interface gráfica da janela
* de alteração de preço.
* @author Luiz Augusto e Miguel
* @since jul 2023
* @version 1.0
*/

public class GUIAlterarPreco extends JFrame {

    // Clone do repositório de produtos utilizado
    ProdutoRepository produtos = ProdutoRepository.getInstanceLoja();

    // Declaração dos componentes da tela inicial
    private JLabel jLCodigo = new JLabel("Código do Produto: ");
    private JLabel jLNovoPreco = new JLabel("Novo preço: ");

    private JTextField jTCodigo = new JTextField();
    private JTextField jTNovoPreco = new JTextField();

    private JButton jBCancel = new JButton("Cancelar");
    private JButton jBConfirm = new JButton("Confirmar");

    // Construtor da classe que inicia todo o processo
    GUIAlterarPreco (){
        super("Alterar Preço");
        initComponents();
    }

    // Método para funcionamento dos botões e criação do layout
    public void initComponents(){
        // Funcionamento dos botões
        jBCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1Event) {
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });

        jBConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1Event) {
                try{
                    produtos.alterarPreco(jTCodigo.getText(), Float.valueOf(jTNovoPreco.getText()).floatValue());
                    JOptionPane.showMessageDialog(null, "Preço alterado com sucesso", "Alteração de Preço", JOptionPane.INFORMATION_MESSAGE);
                } catch (CodigoInvalidoException e1) {
                    JOptionPane.showMessageDialog(null, "Código Inválido!","Alerta", JOptionPane.WARNING_MESSAGE);
                } catch (ProdutoNaoCadastradoException e2) {
                    JOptionPane.showMessageDialog(null, "Produto Não Cadastrado!", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Definição do Layout 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLNovoPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                    .addComponent(jTNovoPreco)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(39, 39, Short.MAX_VALUE)
        ))));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLCodigo)
                                    .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jBCancel)
                                    .addComponent(jBConfirm))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLNovoPreco)
                                    .addComponent(jTNovoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                        ));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}