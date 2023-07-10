import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import main.exceptions.CodigoInvalidoException;
import main.exceptions.CodigoJaCadastradoException;
import main.repository.ProdutoRepository;
import main.produto.Produto;

/** A classe <b>GUIAlterarPreco</b> é responsável por criar a interface gráfica da janela
* de remover produto.
* @author Luiz Augusto e Miguel
* @since jul 2023
* @version 1.0
*/

public class GUICadastrarProduto extends JFrame {
	
	private static final long serialVersionUID = 1L;

	// Clone do repositório de produtos utilizado
    ProdutoRepository produtos = ProdutoRepository.getInstanceLoja();

    // Cria os componentes utilizados na janela
	private JPanel contentPane;
	private final JButton jBConfirm = new JButton("Confirmar");
	private final JButton jBCancel = new JButton("Cancelar");
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewLabel = new JLabel("NOME DO PRODUTO: ");
	private final JTextField jTNome = new JTextField();
	private JTextField jTCodigo;
	private JTextField jTEstoque;
	private JTextField jTFornecedor;
	private JTextField jTPreco;
	private JTextField jTCusto;

	// Cria a GUI de cadastramento
	public GUICadastrarProduto() {
		// Funcioanmento dos botões
        jBCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1Event) {
                dispose();
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
                	e1.printStackTrace();
                } catch (CodigoJaCadastradoException e2) {
                    JOptionPane.showMessageDialog(null, "Produto Já Cadastrado!", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
		
		setType(Type.POPUP);
		setTitle("CADASTRAMENTO DE PRODUTO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(jBConfirm);
		panel.add(jBCancel);
		
		contentPane.add(panel_1, BorderLayout.CENTER);
		jTNome.setBounds(280, 95, 236, 19);
		jTNome.setColumns(1);
		panel_1.setLayout(null);
		lblNewLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(113, 94, 126, 19);
		panel_1.add(lblNewLabel);
		panel_1.add(jTNome);
		
		JLabel lblNewLabel_1 = new JLabel("CÓDIGO DO PRODUTO:");
		lblNewLabel_1.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(113, 129, 144, 16);
		panel_1.add(lblNewLabel_1);
		
		jTCodigo = new JTextField();
		jTCodigo.setBounds(280, 126, 236, 19);
		panel_1.add(jTCodigo);
		jTCodigo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("QUANTIDADE EM ESTOQUE:");
		lblNewLabel_2.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(113, 166, 164, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("FORNECEDOR:");
		lblNewLabel_3.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(113, 201, 110, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PREÇO:");
		lblNewLabel_4.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(113, 237, 164, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CUSTO:");
		lblNewLabel_5.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(113, 274, 126, 13);
		panel_1.add(lblNewLabel_5);
		
		jTEstoque = new JTextField();
		jTEstoque.setColumns(1);
		jTEstoque.setBounds(280, 163, 236, 19);
		panel_1.add(jTEstoque);
		
		jTFornecedor = new JTextField();
		jTFornecedor.setColumns(1);
		jTFornecedor.setBounds(280, 198, 236, 19);
		panel_1.add(jTFornecedor);
		
		jTPreco = new JTextField();
		jTPreco.setColumns(1);
		jTPreco.setBounds(280, 234, 236, 19);
		panel_1.add(jTPreco);
		
		jTCusto = new JTextField();
		jTCusto.setColumns(1);
		jTCusto.setBounds(280, 271, 236, 19);
		panel_1.add(jTCusto);
		
		JLabel lblNewLabel_6 = new JLabel("INSIRA AS INFORMAÇÕES NOS CAMPOS ABAIXO");
		lblNewLabel_6.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		lblNewLabel_6.setBounds(166, 33, 298, 27);
		panel_1.add(lblNewLabel_6);
		
		this.setVisible(true);
	}
}
