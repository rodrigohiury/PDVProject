package main.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import main.exceptions.CodigoInvalidoException;
import main.exceptions.ProdutoNaoCadastradoException;
import main.repository.ProdutoRepository;

/** A classe <b>GUIAlterarPreco</b> é responsável por criar a interface gráfica da janela
* de alteração de preço.
* @author Luiz Augusto e Miguel
* @since jul 2023
* @version 1.0
*/

public class GUIRemoverProduto extends JFrame {
	
	private static final long serialVersionUID = 1L;

	// Clone do repositório de produtos utilizado
    ProdutoRepository produtos = ProdutoRepository.getInstanceLoja();

    // Cria os componentes utilizados na janela
	private JPanel contentPane;
	private final JButton jBConfirm = new JButton("Confirmar");
	private final JButton jBCancel = new JButton("Cancelar");
	private final JPanel panel_1 = new JPanel();
	private JTextField jTCodigo;

	// Cria a GUI de cadastramento
	public GUIRemoverProduto(int LAF) {
		
		this.setLookAndFeel(LAF);
		
		// Funcionamento dos botões
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
					produtos.removerProduto(jTCodigo.getText());
					JOptionPane.showMessageDialog(null, "Produto removido com sucesso", "Remoção do Produto", JOptionPane.INFORMATION_MESSAGE);
				} catch (CodigoInvalidoException e1) {
					e1.printStackTrace();
				} catch (ProdutoNaoCadastradoException e2) {
					JOptionPane.showMessageDialog(null, "Produto Não Cadastrado!", "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		setType(Type.POPUP);
		setTitle("REMOVER PRODUTO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 568, 318);
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
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CÓDIGO DO PRODUTO:");
		lblNewLabel_1.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(95, 100, 144, 16);
		panel_1.add(lblNewLabel_1);
		
		jTCodigo = new JTextField();
		jTCodigo.setBounds(280, 100, 149, 19);
		panel_1.add(jTCodigo);
		jTCodigo.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("INSIRA O CÓDIGO DO PRODUTO");
		lblNewLabel_6.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		lblNewLabel_6.setBounds(160, 30, 193, 27);
		panel_1.add(lblNewLabel_6);
		
		this.setVisible(true);
	}
	
	// Método que define qual o LookAndFeel da página
	  	public void setLookAndFeel(int LAF) {
	  		switch (LAF) {
	  		case 1:
	  			try {
	  				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
	  			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
	  					| UnsupportedLookAndFeelException e3) {
	  				e3.printStackTrace();
	  			}
	  			break;
	  		case 2:
	  			try {
	  				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	  			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
	  					| UnsupportedLookAndFeelException e2) {
	  				e2.printStackTrace();
	  			}
	  			break;
	  		case 3:
	  			try {
	  				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	  			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
	  					| UnsupportedLookAndFeelException e1) {
	  				e1.printStackTrace();
	  			}
	  			break;
	  		case 4:
	  			try {
	  				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	  			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
	  					| UnsupportedLookAndFeelException e) {
	  				e.printStackTrace();
	  			}
	  			break;
	  		default:
	  			break;
	  		}
	  	}
}
