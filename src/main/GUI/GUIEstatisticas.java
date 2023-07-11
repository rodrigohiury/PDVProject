package main.GUI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class GUIEstatisticas extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JTextField jTDataInicio;
	private JTextField jTDataTermino;


	// Cria a janela
	public GUIEstatisticas(int LAF) {
		
		this.setLookAndFeel(LAF);
		
		setOpacity(1f);
		setType(Type.POPUP);
		setTitle("ESTATÍSTICAS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jLFaturamento = new JLabel("FATURAMENTO TOTAL:");
		jLFaturamento.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		jLFaturamento.setBounds(55, 165, 151, 13);
		contentPane.add(jLFaturamento);
		
		JLabel jLLucro = new JLabel("LUCRO BRUTO:");
		jLLucro.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		jLLucro.setBounds(54, 210, 105, 13);
		contentPane.add(jLLucro);
		
		JLabel jLTicket = new JLabel("TICKET MÉDIO:");
		jLTicket.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		jLTicket.setBounds(54, 250, 131, 13);
		contentPane.add(jLTicket);
		
		JLabel jLQuantidadeVendas = new JLabel("QUANTIDADE TOTAL DE VENDAS:");
		jLQuantidadeVendas.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		jLQuantidadeVendas.setBounds(54, 291, 199, 13);
		contentPane.add(jLQuantidadeVendas);
        
		/*
		 * Aqui nessa parte precisa passar os códigos e os números de vendas de cada produto dos mais vendidos pra tabela
		 * quando o botão de atualizar for apertado
		 */
        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"c\u00F3gigo do produto", new Integer(54)},
        		{"", null},
        		{null, null},
        		{null, null},
        		{null, null},
        	},
        	new String[] {
        		"PRODUTO", "N\u00BA VENDAS"
        	}
        ));
        table_1.getColumnModel().getColumn(0).setResizable(false);
        table_1.setBounds(54, 401, 336, 80);
        contentPane.add(table_1);
        
        JLabel jLTop5 = new JLabel(" TOP 5 PRODUTOS MAIS VENDIDOS");
        jLTop5.setFont(new Font("Nirmala UI", Font.BOLD, 12));
        jLTop5.setBounds(122, 343, 207, 13);
        contentPane.add(jLTop5);
        
        // Tem que passar o valor do faturamento quando o botão atualizar for apertado
        JTextArea jTFaturamento = new JTextArea();
        jTFaturamento.setEditable(false);
        jTFaturamento.setBounds(259, 160, 131, 19);
        contentPane.add(jTFaturamento);
        
        // Tem que passar o valor do lucro quando o botão atualizar for apertado
        JTextArea jTLucro = new JTextArea();
        jTLucro.setEditable(false);
        jTLucro.setBounds(259, 205, 131, 19);
        contentPane.add(jTLucro);
        
        // Tem que passar o valor do ticket médio quando o botão atualizar for apertado
        JTextArea jTTicket = new JTextArea();
        jTTicket.setEditable(false);
        jTTicket.setBounds(259, 245, 131, 19);
        contentPane.add(jTTicket);
        
        // Tem que passar o valor do quantidade de vendas quando o botão atualizar for apertado
        JTextArea jTQuantidadeVendas = new JTextArea();
        jTQuantidadeVendas.setEditable(false);
        jTQuantidadeVendas.setBounds(259, 286, 131, 19);
        contentPane.add(jTQuantidadeVendas);
        
        JLabel jLCodigo = new JLabel("CÓDIGO");
        jLCodigo.setFont(new Font("Nirmala UI", Font.BOLD, 12));
        jLCodigo.setBounds(115, 378, 56, 13);
        contentPane.add(jLCodigo);
        
        JLabel jLVendas = new JLabel("VENDAS");
        jLVendas.setFont(new Font("Nirmala UI", Font.BOLD, 12));
        jLVendas.setBounds(285, 378, 56, 13);
        contentPane.add(jLVendas);
        
        JLabel jLTitulo = new JLabel("INDIQUE O PERÍODO EM QUE DESEJA CONSULTAR AS ESTATÍSTICAS");
        jLTitulo.setFont(new Font("Nirmala UI", Font.BOLD, 12));
        jLTitulo.setBounds(55, 26, 335, 13);
        contentPane.add(jLTitulo);
        
        JLabel jLDataInicio = new JLabel("DATA DE INÍCIO:");
        jLDataInicio.setFont(new Font("Nirmala UI", Font.BOLD, 12));
        jLDataInicio.setBounds(55, 67, 104, 13);
        contentPane.add(jLDataInicio);
        
        JLabel jLDataTermino = new JLabel("DATA DE TÉRMINO:");
        jLDataTermino.setFont(new Font("Nirmala UI", Font.BOLD, 12));
        jLDataTermino.setBounds(55, 102, 130, 13);
        contentPane.add(jLDataTermino);
        
        jTDataInicio = new JTextField();
        jTDataInicio.setBounds(259, 64, 131, 19);
        contentPane.add(jTDataInicio);
        jTDataInicio.setColumns(10);
        
        jTDataTermino = new JTextField();
        jTDataTermino.setBounds(259, 99, 131, 19);
        contentPane.add(jTDataTermino);
        jTDataTermino.setColumns(10);
        
        // Fazendo os passos necessários para a conversão das Strings em Dates
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        JButton jBAtualizar = new JButton("Atualizar");
        jBAtualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String dataInicio = jTDataInicio.getText();
        		String dataFim = jTDataTermino.getText();

                try {
                    Date inicio = (Date) formato.parse(dataInicio);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    Date fim = (Date) formato.parse(dataFim);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                // Agora é utilizar essas datas para atualizar o sistema que está sendo mostrado
        	}
        });
        jBAtualizar.setBounds(176, 502, 91, 32);
        contentPane.add(jBAtualizar);
        setVisible(true);
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
