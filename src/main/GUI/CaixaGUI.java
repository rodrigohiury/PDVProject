package main.GUI;

import main.caixa.Caixa;
import main.exceptions.*;
import main.funcionario.Funcionario;
import main.produto.Produto;
import main.repository.CaixaRepository;
import main.repository.FuncionarioRepository;
import main.transacao.Venda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CaixaGUI extends JFrame {

    private CaixaRepository caixaRepository;
    private Caixa caixaAtual;

    private JTextField txtFuncionario;
    private JButton btnAbrirCaixa;
    private JButton btnFecharCaixa;
    private JTextArea txtStatus;

    public CaixaGUI(int LAF) {
    	this.setLookAndFeel(LAF);
    	
        setTitle("Caixa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        caixaRepository = CaixaRepository.getInstance();

        JLabel lblFuncionario = new JLabel("Funcionário:");
        txtFuncionario = new JTextField(15);

        btnAbrirCaixa = new JButton("Abrir Caixa");
        btnFecharCaixa = new JButton("Fechar Caixa");

        txtStatus = new JTextArea(10, 30);
        txtStatus.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtStatus);

        add(lblFuncionario);
        add(txtFuncionario);
        add(btnAbrirCaixa);
        add(btnFecharCaixa);
        add(scrollPane);

        btnAbrirCaixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirCaixa();
            }
        });

        btnFecharCaixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharCaixa();
            }
        });
        
        setVisible(true);
    }

    private void abrirCaixa(){
        String nomeFuncionario = txtFuncionario.getText();

        if (nomeFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo de Funcionário.");
            return;
        }

        FuncionarioRepository funcionarioRepository = FuncionarioRepository.getInstance();
        Funcionario funcionario = funcionarioRepository.buscarFuncionario(nomeFuncionario);

        if (funcionario == null) {
            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
            return;
        }

        try {
            caixaAtual = new Caixa(funcionario);
            caixaRepository.inserirCaixa(caixaAtual);
            atualizarStatus("Caixa aberto:\n" + getInformacoesCaixa(caixaAtual));
            JOptionPane.showMessageDialog(this, "Caixa aberto com sucesso!");

            // Redirecionar para a tela de operações de caixa
            dispose();
            TelaOperacoesCaixaGUI telaOperacoesCaixaGUI = new TelaOperacoesCaixaGUI(caixaAtual);
            telaOperacoesCaixaGUI.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir o caixa.");
        }
    }

    private void fecharCaixa() {
        if (caixaAtual == null) {
            JOptionPane.showMessageDialog(this, "Não há caixa aberto.");
            return;
        }

        caixaAtual.fecharCaixa();
        caixaRepository.alterarCaixa(caixaAtual);
        atualizarStatus("Caixa fechado:\n" + getInformacoesCaixa(caixaAtual));
        caixaAtual = null;
        JOptionPane.showMessageDialog(this, "Caixa fechado com sucesso!");
    }

    private String getInformacoesCaixa(Caixa caixa) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        StringBuilder builder = new StringBuilder();
        builder.append("Número do Caixa: ").append(caixa.getNumero()).append("\n");
        builder.append("Funcionário: ").append(caixa.getVendedor().getNome()).append("\n");
        builder.append("Data de Abertura: ").append(sdf.format(caixa.getDataAbertura().getTime())).append("\n");
        builder.append("Data de Fechamento: ").append(sdf.format(caixa.getDataFechamento().getTime())).append("\n");
        builder.append("Valor de Abertura: ").append(caixa.getValorAbertura()).append("\n");
        builder.append("Valor em Caixa: ").append(caixa.getValorEmCaixa()).append("\n");

        return builder.toString();
    }

    private void atualizarStatus(String mensagem) {
        txtStatus.setText(mensagem);
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