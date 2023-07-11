package main.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.GUI.GUIConsultaProduto;
import main.exceptions.CodigoInvalidoException;
import main.exceptions.ProdutoNaoCadastradoException;
import main.repository.ProdutoRepository;
import main.repository.VendasRepository;
import main.services.ControllerEstatisticas;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

/** A classe <b>GUIAlterarPreco</b> é responsável por criar a interface gráfica da janela
* de alteração de preço.
* @author Luiz Augusto e Miguel
* @since jul 2023
* @version 1.0
*/

public class MainFrame extends JFrame {

    private JPanel contentPane;

    private int LookAndFeelSettings = 0;

    // Cria a janela principal
    public MainFrame() {
        setTitle("BEM VINDO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 776, 416);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel jLCadastros = new JLabel("PRODUTOS");
        jLCadastros.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
        jLCadastros.setLabelFor(this);
        jLCadastros.setHorizontalAlignment(SwingConstants.CENTER);
        jLCadastros.setBounds(201, 18, 82, 13);
        contentPane.add(jLCadastros);

        JLabel jLEstatisticas = new JLabel("ESTATÍSTICAS / CAIXA / CONFIGURAÇÕES");
        jLEstatisticas.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
        jLEstatisticas.setHorizontalAlignment(SwingConstants.CENTER);
        jLEstatisticas.setBounds(482, 18, 259, 13);
        contentPane.add(jLEstatisticas);

        JButton jBCadastrarProduto = new JButton("Cadastrar Produto");
        jBCadastrarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUICadastrarProduto janela_1 = new GUICadastrarProduto(LookAndFeelSettings);
            }
        });
        jBCadastrarProduto.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconCadastroProdutos.png"));
        jBCadastrarProduto.setBounds(36, 156, 201, 70);
        contentPane.add(jBCadastrarProduto);

        JButton jBAlterarPreco = new JButton("Alterar Preço de Produto");
        jBAlterarPreco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIAlterarPreco janela_2 = new GUIAlterarPreco(LookAndFeelSettings);
            }
        });
        jBAlterarPreco.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconAlterarPreco.jpg"));
        jBAlterarPreco.setBounds(258, 65, 201, 70);
        contentPane.add(jBAlterarPreco);

        JButton jBRemoverProduto = new JButton("Remover Produto");
        jBRemoverProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIRemoverProduto janela_3 = new GUIRemoverProduto(LookAndFeelSettings);
            }
        });
        jBRemoverProduto.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconRemoverProduto.jpg"));
        jBRemoverProduto.setBounds(150, 246, 201, 70);
        contentPane.add(jBRemoverProduto);

        JButton jBListarProdutos = new JButton("Listar Produtos");
        jBListarProdutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUITableListagemProdutos janela_4 = new GUITableListagemProdutos(LookAndFeelSettings);
            }
        });
        jBListarProdutos.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconListarProdutos.png"));
        jBListarProdutos.setBounds(258, 156, 201, 70);
        contentPane.add(jBListarProdutos);

        JButton jBConsultarProdutos = new JButton("Consultar Produto");
        jBConsultarProdutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIConsultaProduto janela_5 = new GUIConsultaProduto(LookAndFeelSettings);
            }
        });
        jBConsultarProdutos.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconConsultaProduto.jpg"));
        jBConsultarProdutos.setBounds(36, 65, 201, 70);
        contentPane.add(jBConsultarProdutos);

        JButton jBEstatisticas = new JButton("Estatísticas Gerais");
        jBEstatisticas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GUIEstatisticas janela_6 = new GUIEstatisticas(LookAndFeelSettings);
                } catch (HeadlessException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jBEstatisticas.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconEstatisticas.png"));
        jBEstatisticas.setBounds(506, 65, 201, 70);
        contentPane.add(jBEstatisticas);

        JButton jBCaixa = new JButton("Caixa");
        jBCaixa.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconCaixa.png"));
        jBCaixa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CaixaGUI janela_7 = new CaixaGUI(LookAndFeelSettings);
            }
        });
        jBCaixa.setBounds(506, 162, 201, 65);
        contentPane.add(jBCaixa);

        JButton jBConfiguracoes = new JButton("Configurações");
        jBConfiguracoes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIConfiguracoes janela_8 = new GUIConfiguracoes(LookAndFeelSettings);
                LookAndFeelSettings = janela_8.getLookAndFeel();

                switch (LookAndFeelSettings) {
                    case 1:
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedLookAndFeelException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedLookAndFeelException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedLookAndFeelException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedLookAndFeelException e1) {
                            e1.printStackTrace();
                        }
                        break;
                }
            }
        });
        jBConfiguracoes.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconConfiguracao.jpg"));
        jBConfiguracoes.setBounds(506, 246, 201, 70);
        contentPane.add(jBConfiguracoes);

        JButton btnFuncionarios = new JButton("Funcionários");
        btnFuncionarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FuncionarioGUI funcionarioGUI = new FuncionarioGUI();
                funcionarioGUI.setVisible(true);
            }
        });
        btnFuncionarios.setIcon(new ImageIcon("C:\\Users\\sousa\\OneDrive\\Desktop\\Projeto POO\\PDVProject\\src\\Main\\GUI\\Icons\\IconFuncionarios.png"));
        btnFuncionarios.setBounds(36, 246, 201, 70);
        // contentPane.add(btnFuncionarios);

        this.setVisible(true);
    }
}
