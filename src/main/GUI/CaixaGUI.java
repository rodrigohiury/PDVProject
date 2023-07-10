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
    private JButton btnNovaVenda;
    private JButton btnAdicionarFormaPagamento;
    private JButton btnGerarNotaFiscal;
    private JButton btnSangrarCaixa;
    private JButton btnReforcarCaixa;
    private JTextArea txtStatus;

    public CaixaGUI() {
        setTitle("Caixa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        caixaRepository = new CaixaRepository();

        JLabel lblFuncionario = new JLabel("Funcionário:");
        txtFuncionario = new JTextField(15);

        btnAbrirCaixa = new JButton("Abrir Caixa");
        btnFecharCaixa = new JButton("Fechar Caixa");
        btnNovaVenda = new JButton("Nova Venda");
        btnAdicionarFormaPagamento = new JButton("Adicionar Forma de Pagamento");
        btnGerarNotaFiscal = new JButton("Gerar Nota Fiscal da Última Venda");
        btnSangrarCaixa = new JButton("Sangrar Caixa");
        btnReforcarCaixa = new JButton("Reforçar Caixa");

        txtStatus = new JTextArea(10, 30);
        txtStatus.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtStatus);

        add(lblFuncionario);
        add(txtFuncionario);
        add(btnAbrirCaixa);
        add(btnFecharCaixa);
        add(btnNovaVenda);
        add(btnAdicionarFormaPagamento);
        add(btnGerarNotaFiscal);
        add(btnSangrarCaixa);
        add(btnReforcarCaixa);
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

        btnNovaVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaVenda();
            }
        });

        btnAdicionarFormaPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarFormaPagamento();
            }
        });

        btnGerarNotaFiscal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarNotaFiscal();
            }
        });

        btnSangrarCaixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sangrarCaixa();
            }
        });

        btnReforcarCaixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reforcarCaixa();
            }
        });
    }
    
    private void abrirCaixa() {
        String nomeFuncionario = txtFuncionario.getText();

        if (nomeFuncionario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo de Funcionário.");
            return;
        }

        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
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

    private void novaVenda() {
        if (caixaAtual == null) {
            JOptionPane.showMessageDialog(this, "Não há caixa aberto.");
            return;
        }

        // Aqui você pode adicionar a lógica para criar uma nova venda
        // Pode ser aberta uma nova janela ou diálogo para adicionar produtos à venda

        JOptionPane.showMessageDialog(this, "Nova venda criada.");
    }

    private void adicionarFormaPagamento() {
        if (caixaAtual == null) {
            JOptionPane.showMessageDialog(this, "Não há caixa aberto.");
            return;
        }

        // Aqui você pode adicionar a lógica para adicionar uma nova forma de pagamento ao caixa
        // Pode ser aberta uma nova janela ou diálogo para inserir a forma de pagamento

        JOptionPane.showMessageDialog(this, "Forma de pagamento adicionada.");
    }

    private void gerarNotaFiscal() {
        if (caixaAtual == null) {
            JOptionPane.showMessageDialog(this, "Não há caixa aberto.");
            return;
        }

        ArrayList<Venda> vendasDoDia = caixaAtual.getVendasDoDia();
        if (vendasDoDia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há vendas realizadas.");
            return;
        }

        Venda ultimaVenda = vendasDoDia.get(vendasDoDia.size() - 1);
        String notaFiscal = caixaAtual.gerarNotaFiscal(ultimaVenda);

        JOptionPane.showMessageDialog(this, "Nota Fiscal da Última Venda:\n\n" + notaFiscal);
    }

    private void sangrarCaixa() {
        if (caixaAtual == null) {
            JOptionPane.showMessageDialog(this, "Não há caixa aberto.");
            return;
        }

        String valorSangriaText = JOptionPane.showInputDialog(this, "Digite o valor da sangria:");
        if (valorSangriaText == null || valorSangriaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Valor inválido.");
            return;
        }

        float valorSangria;
        try {
            valorSangria = Float.parseFloat(valorSangriaText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido.");
            return;
        }
        try {
            caixaAtual.sangrarCaixa(valorSangria);
            caixaRepository.alterarCaixa(caixaAtual);
            atualizarStatus("Sangria realizada:\n" + getInformacoesCaixa(caixaAtual));
            JOptionPane.showMessageDialog(this, "Sangria realizada com sucesso!");
        } catch (ValorSangriaInvalidoException e) {
            JOptionPane.showMessageDialog(this, "Valor de sangria inválido.");
        } catch (SangriaInvalidaException e) {
            JOptionPane.showMessageDialog(this, "Sangria não permitida. Valor superior ao valor em caixa.");
        }
    }

    private void reforcarCaixa() {
        if (caixaAtual == null) {
            JOptionPane.showMessageDialog(this, "Não há caixa aberto.");
            return;
        }

        String valorReforcoText = JOptionPane.showInputDialog(this, "Digite o valor do reforço:");
        if (valorReforcoText == null || valorReforcoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Valor inválido.");
            return;
        }

        float valorReforco;
        try {
            valorReforco = Float.parseFloat(valorReforcoText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido.");
            return;
        }

        try {
            caixaAtual.reforcarCaixa(valorReforco);
            caixaRepository.alterarCaixa(caixaAtual);
            atualizarStatus("Reforço realizado:\n" + getInformacoesCaixa(caixaAtual));
            JOptionPane.showMessageDialog(this, "Reforço realizado com sucesso!");
        } catch (ValorReforcoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "Valor de reforço inválido.");
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CaixaGUI caixaGUI = new CaixaGUI();
                caixaGUI.setVisible(true);
            }
        });
    }
}
