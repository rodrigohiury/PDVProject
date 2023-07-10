package main.GUI;

import main.GUI.CaixaGUI;
import main.caixa.Caixa;
import main.exceptions.*;
import main.funcionario.Funcionario;
import main.produto.Produto;
import main.repository.CaixaRepository;
import main.repository.FuncionarioRepository;
import main.repository.ProdutoRepository;
import main.transacao.Venda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TelaOperacoesCaixaGUI extends JFrame {

    private Caixa caixaAtual;

    private JButton btnFecharCaixa;
    private JButton btnNovaVenda;
    private JButton btnAdicionarFormaPagamento;
    private JButton btnGerarNotaFiscal;
    private JButton btnSangrarCaixa;
    private JButton btnReforcarCaixa;
    private JTextArea txtStatus;

    public TelaOperacoesCaixaGUI(Caixa caixa) {
        caixaAtual = caixa;

        setTitle("Operações de Caixa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        btnFecharCaixa = new JButton("Fechar Caixa");
        btnNovaVenda = new JButton("Nova Venda");
        btnAdicionarFormaPagamento = new JButton("Adicionar Forma de Pagamento");
        btnGerarNotaFiscal = new JButton("Gerar Nota Fiscal da Última Venda");
        btnSangrarCaixa = new JButton("Sangrar Caixa");
        btnReforcarCaixa = new JButton("Reforçar Caixa");

        txtStatus = new JTextArea(10, 30);
        txtStatus.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtStatus);

        add(btnFecharCaixa);
        add(btnNovaVenda);
        add(btnAdicionarFormaPagamento);
        add(btnGerarNotaFiscal);
        add(btnSangrarCaixa);
        add(btnReforcarCaixa);
        add(scrollPane);

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

    private void fecharCaixa() {
        caixaAtual.fecharCaixa();
        CaixaRepository caixaRepository = new CaixaRepository();
        caixaRepository.alterarCaixa(caixaAtual);
        atualizarStatus("Caixa fechado:\n" + getInformacoesCaixa(caixaAtual));
        caixaAtual = null;
        JOptionPane.showMessageDialog(this, "Caixa fechado com sucesso!");
        dispose();
        CaixaGUI caixaGUI = new CaixaGUI(2);
        caixaGUI.setVisible(true);
    }

    private static void novaVenda() {
        // Criar uma nova janela para adicionar produtos à venda
        JFrame frame = new JFrame("Adicionar Produtos à Venda");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Painel para exibir a lista de produtos
        JPanel panelProdutos = new JPanel();
        panelProdutos.setLayout(new GridLayout(0, 1));

        // Botão para adicionar produtos à venda
        JButton btnAdicionarProduto = new JButton("Adicionar Produto");

        // Painel para os botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Botão para finalizar a venda
        JButton btnFinalizarVenda = new JButton("Finalizar Venda");

        // TextArea para exibir o resumo da venda
        JTextArea txtResumoVenda = new JTextArea(10, 30);
        txtResumoVenda.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtResumoVenda);

        panelBotoes.add(btnFinalizarVenda);

        frame.add(panelProdutos, BorderLayout.NORTH);
        frame.add(btnAdicionarProduto, BorderLayout.CENTER);
        frame.add(panelBotoes, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.EAST);

        Venda venda = new Venda(); // Criar uma nova instância da classe Venda

        btnAdicionarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir uma nova janela para selecionar o produto a ser adicionado à venda
                JFrame selectProductFrame = new JFrame("Selecionar Produto");
                selectProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                selectProductFrame.setSize(400, 200);
                selectProductFrame.setLayout(new FlowLayout());

                // ComboBox para selecionar o produto
                JComboBox<String> cmbProdutos = new JComboBox<>();
                try {
                    ArrayList<Object> produtos = ProdutoRepository.getInstanceLoja().listarProdutos();
                    for (Object obj : produtos) {
                        Object[] linha = (Object[]) obj;
                        String codigo = (String) linha[2];
                        cmbProdutos.addItem(codigo);
                    }
                } catch (NaoHaProdutosException ex) {
                    ex.printStackTrace();
                }

                selectProductFrame.add(cmbProdutos);

                // Botão para adicionar o produto selecionado à venda
                JButton btnSelecionarProduto = new JButton("Selecionar Produto");

                selectProductFrame.add(btnSelecionarProduto);

                btnSelecionarProduto.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Adicionar o produto selecionado à venda
                        String codigoProdutoSelecionado = cmbProdutos.getSelectedItem().toString();
                        try {
                            Produto produtoSelecionado = ProdutoRepository.getInstanceLoja()
                                    .consultarProduto(codigoProdutoSelecionado);
                            venda.inserirCompra(produtoSelecionado); // Adicionar o produto à venda

                            // Atualizar o resumo da venda
                            txtResumoVenda
                                    .append(codigoProdutoSelecionado + " - " + produtoSelecionado.getNome() + "\n");

                            // Fechar a janela de seleção de produto
                            selectProductFrame.dispose();
                        } catch (ProdutoNaoCadastradoException ex) {
                            JOptionPane.showMessageDialog(selectProductFrame, "Produto não cadastrado.");
                        } catch (CodigoInvalidoException ex) {
                            JOptionPane.showMessageDialog(selectProductFrame, "Código de produto inválido.");
                        }

                    }
                });

                selectProductFrame.setVisible(true);
            }
        });

        btnFinalizarVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicionar a lógica para finalizar a venda
                venda.finalizarVenda(venda.getValorPago());

                // Fechar a janela de adição de produtos à venda
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void adicionarFormaPagamento() {
        JFrame dialog = new JFrame("Adicionar Forma de Pagamento");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(300, 150);
        dialog.setLayout(new FlowLayout());

        JLabel lblFormaPagamento = new JLabel("Forma de Pagamento:");
        JComboBox<String> cmbFormasPagamento = new JComboBox<>();

        // Adicionar as formas de pagamento atuais ao JComboBox
        List<String> formasPagamento = caixaAtual.getFormasDePagamento();
        for (String forma : formasPagamento) {
            cmbFormasPagamento.addItem(forma);
        }

        JButton btnAdicionar = new JButton("Adicionar");

        dialog.add(lblFormaPagamento);
        dialog.add(cmbFormasPagamento);
        dialog.add(btnAdicionar);

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formaPagamento = cmbFormasPagamento.getSelectedItem().toString();
                caixaAtual.getFormasDePagamento().add(formaPagamento);
                JOptionPane.showMessageDialog(dialog, "Forma de pagamento adicionada.");
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    private void gerarNotaFiscal() {
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
            CaixaRepository caixaRepository = new CaixaRepository();
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
            CaixaRepository caixaRepository = new CaixaRepository();
            caixaRepository.alterarCaixa(caixaAtual);
            atualizarStatus("Reforço realizado:\n" + getInformacoesCaixa(caixaAtual));
            JOptionPane.showMessageDialog(this, "Reforço realizado com sucesso!");
        } catch (ValorReforcoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "Valor de reforço inválido.");
        }
    }

    private String getInformacoesCaixa(Caixa caixa) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append("Funcionário: ").append(caixa.getVendedor().getNome()).append("\n");
        sb.append("Data e Hora de Abertura: ").append(dateFormat.format(caixa.getDataAbertura())).append("\n");
        sb.append("Valor Inicial: R$ ").append(caixa.getValorAbertura()).append("\n");
        sb.append("Valor em Caixa: R$ ").append(caixa.getValorEmCaixa()).append("\n");

        return sb.toString();
    }

    private void atualizarStatus(String status) {
        txtStatus.setText(status);
    }
}
