package main.GUI;

import javax.swing.*;

import main.exceptions.CodigoInvalidoException;
import main.exceptions.CodigoJaCadastradoException;
import main.repository.ProdutoRepository;
import main.produto.Produto;
import java.awt.event.*;

public class GUICadastrarProduto extends JFrame {

    // Clone do repositório de produtos utilizado
    ProdutoRepository produtos = ProdutoRepository.getInstanceLoja();

    // Declaração dos componentes da tela inicial
    private JLabel jLNome = new JLabel("Nome do Produto: ");
    private JLabel jLCodigo = new JLabel("Código do Produto: ");
    private JLabel jLPreco = new JLabel("Novo preço: ");
    private JLabel jLCusto = new JLabel("Custo do Produto: ");
    private JLabel jLFornecedor = new JLabel("Nome do Fornecedor: ");
    private JLabel jLEstoque =  new JLabel("Quantidade no estoque: ");

    private JTextField jTNome = new JTextField();
    private JTextField jTCodigo = new JTextField();
    private JTextField jTPreco = new JTextField();
    private JTextField jTCusto =  new JTextField();
    private JTextField jTFornecedor = new JTextField();
    private JTextField jTEstoque = new JTextField();

    private JButton jBCancel = new JButton("Cancelar");
    private JButton jBConfirm = new JButton("Confirmar");

    // Construtor da classe
    GUICadastrarProduto (){
        super("Cadastrar Produto");
        initComponents();
    }

    public void initComponents (){
        // Funcioanmento dos botões
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
                    Produto produto = new Produto(jTNome.getText(), Float.valueOf(jTPreco.getText()).floatValue(), Float.valueOf(jTCusto.getText()).floatValue(),
                    jTCodigo.getText(), Integer.valueOf(jTEstoque.getText()).intValue(), jTFornecedor.getText());
                    produtos.cadastrarProduto(produto);
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Cadastro de Produto", JOptionPane.INFORMATION_MESSAGE);
                } catch (CodigoInvalidoException e1) {
                    JOptionPane.showMessageDialog(null, "Código Inválido!","Alerta", JOptionPane.WARNING_MESSAGE);
                } catch (CodigoJaCadastradoException e2) {
                    JOptionPane.showMessageDialog(null, "Produto Já Cadastrado!", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Criação d Layout da janela
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLFornecedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLCusto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLEstoque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                .addComponent(jTNome)
                                .addComponent(jTFornecedor)
                                .addComponent(jTPreco)
                                .addComponent(jTCusto)
                                .addComponent(jTEstoque)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLNome)
                                .addComponent(jTNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLFornecedor)
                                .addComponent(jTFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLPreco)
                                .addComponent(jTPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLCusto)
                                .addComponent(jTCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLEstoque)
                                .addComponent(jTEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                ));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
