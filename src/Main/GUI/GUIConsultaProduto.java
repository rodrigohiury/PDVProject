package Main.GUI;
import Main.exceptions.CodigoInvalidoException;
import Main.exceptions.ProdutoNaoCadastradoException;
import Main.repository.ProdutoRepository;
import Main.produto.Produto;

import javax.swing.*;

/** A classe <b>GUIConsultaProduto</b> é responsável por criar a interface gráfica da janela
     * de consulta de produtos.
     * @author Luiz Augusto e Miguel
     * @since jul 2023
     * @version 1.0
     */

public class GUIConsultaProduto extends javax.swing.JFrame {
//Intanciação da Loja pelo Singleton

        /**
         * Construtor default da classe <b>GUIConsultaProduto</b>.<br><br>
         * <b>Uso</b>:<br><br>
         * GUIConsultaProduto gui = new GUIConsultaProduto()
         */
        ProdutoRepository produtos= ProdutoRepository.getInstanceLoja();
        public GUIConsultaProduto() {initComponents();}

    /* Este método instancia, configura, adiciona e cria os ActionListener de cada componente
 da interface gráfica */

    private void initComponents() {
        jLCodigoProduto = new javax.swing.JLabel();
        jTCodigo = new javax.swing.JTextField();
        jLNome = new javax.swing.JLabel();
        jLFornecedor = new javax.swing.JLabel();
        jLPrecoVenda = new javax.swing.JLabel();
        jLPrecoCusto = new javax.swing.JLabel();
        jLEstoque = new javax.swing.JLabel();
        jBConsultar = new javax.swing.JButton();
        jTNome = new javax.swing.JTextField();
        jTFornecedor = new javax.swing.JTextField();
        jTPrecoVenda = new javax.swing.JTextField();
        jTPrecoCusto = new javax.swing.JTextField();
        jTEstoque = new javax.swing.JTextField();
        jBLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Produtos");
        setResizable(false);

        jLCodigoProduto.setText("Código do Produto:");

        jLNome.setText("Nome:");

        jLFornecedor.setText("Fornecedor:");

        jLPrecoVenda.setText("Preço de Venda:");

        jLPrecoCusto.setText("Preço de custo:");

        jLEstoque.setText("Estoque:");

        jBConsultar.setText("Consultar");

        jBConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jBConsultarActionPerformed(evt);
                } catch (CodigoInvalidoException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        jTNome.setEditable(false);
        jTNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFabricanteActionPerformed(evt);
            }
        });

        jTFornecedor.setEditable(false);

        jTPrecoVenda.setEditable(false);

        jTPrecoCusto.setEditable(false);

        jTEstoque.setEditable(false);

        jBLimpar.setText("Limpar");
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLFornecedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLPrecoVenda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLPrecoCusto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLEstoque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                        .addComponent(jTNome)
                                        .addComponent(jTFornecedor)
                                        .addComponent(jTPrecoVenda)
                                        .addComponent(jTPrecoCusto)
                                        .addComponent(jTEstoque)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jBConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jBLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, Short.MAX_VALUE)
        ))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLCodigoProduto)
                                        .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jBConsultar)
                                        .addComponent(jBLimpar))
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
                                        .addComponent(jLPrecoVenda)
                                        .addComponent(jTPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLPrecoCusto)
                                        .addComponent(jTPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLEstoque)
                                        .addComponent(jTEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)

                        ));

        pack();
        setLocationRelativeTo(null);
    }

    // Configuração dos ActionPerformed
    private void jTFabricanteActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) throws CodigoInvalidoException {
        String codigo = jTCodigo.getText();
        if("".equals(codigo))
            JOptionPane.showMessageDialog(null,"O Código deve ser informado para que a consulta seja realizada.","Aviso", JOptionPane.WARNING_MESSAGE);
        else{
            try{
                Produto produto = produtos.consultarProduto(codigo);
                jTNome.setText(produto.getNome());
                jTFornecedor.setText(produto.getFornecedor());
                jTPrecoVenda.setText(produto.getPrecoVenda()+"");
                jTPrecoCusto.setText(produto.getPrecoCusto()+"");
                jTEstoque.setText(produto.getEstoque()+"");

            } catch(ProdutoNaoCadastradoException ex){}
            catch(CodigoInvalidoException ex) {}
        }
    }
    private void jBLimparActionPerformed(java.awt.event.ActionEvent evt) {
        jTCodigo.setText("");
    }
   /**
   *
   * @param args
   */
   public static void main(String args[]) {

       try {
           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
               if ("Nimbus".equals(info.getName())) {
                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   break;
               }
           }
       } catch (ClassNotFoundException ex) {
           java.util.logging.Logger.getLogger(GUIConsultaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (InstantiationException ex) {
           java.util.logging.Logger.getLogger(GUIConsultaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (IllegalAccessException ex) {
           java.util.logging.Logger.getLogger(GUIConsultaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
           java.util.logging.Logger.getLogger(GUIConsultaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       }

       //Exibe a janela de Consulta de Produtos

       java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
               new GUIConsultaProduto().setVisible(true);
           }
       });
   }

    // Atributos que foram usados para fazer os componentes da janela da Consulta de Veículos
    private javax.swing.JButton jBConsultar;
    private javax.swing.JButton jBLimpar;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLFornecedor;
    private javax.swing.JLabel jLPrecoVenda;
    private javax.swing.JLabel jLPrecoCusto;
    private javax.swing.JLabel jLCodigoProduto;
    private javax.swing.JLabel jLEstoque;

    private javax.swing.JTextField jTNome;
    private javax.swing.JTextField jTFornecedor;
    private javax.swing.JTextField jTPrecoVenda;
    private javax.swing.JTextField jTPrecoCusto;
    private javax.swing.JTextField jTCodigo;
    private javax.swing.JTextField jTEstoque;

}

