package main.GUI;

import main.exceptions.CodigoInvalidoException;
import main.exceptions.ProdutoNaoCadastradoException;
import main.repository.IProdutoRepository;
import main.services.ControllerEstatisticas;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.ParseException;

public class GUIEstatisticas extends JFrame {

    private JLabel jLFaturamento = new JLabel("Faturamento: ");
    private JLabel jLLucroBruto = new JLabel("Lucro Bruto: ");
    private JLabel jLTicketMedio = new JLabel("Ticket Medio: ");
    private JLabel jLNumeroVendas = new JLabel("Qtd. de Vendas: ");
    private JPanel jPProdutosMaisVendidos = new JPanel();
    private JPanel jPPainelGeral = new JPanel();
    private JLabel jLFaturamentoAchado;
    private JLabel jLLucroBrutoAchado;
    private JLabel jLTicketMedioAchado;
    private JLabel jLNumeroVendasAchado;
    private ControllerEstatisticas controllerEstatisticas;
    private IProdutoRepository produtoRepository;
    private JLabel jLProdutosMaisVendidos = new JLabel("Produtos mais Vendidos");
    private JLabel jLQuantidade = new JLabel("Quant.");
    private JLabel jLTituloJanela = new JLabel("Estatísticas de Venda");
    private JLabel jLDataInicio = new JLabel("Data de Início: ");
    private JLabel jLDataFim = new JLabel("Data de Fim: ");
    private JFormattedTextField jFTFDataInicio;
    private JFormattedTextField jFTFDataFim;

    public GUIEstatisticas(ControllerEstatisticas controllerEstatisticas, IProdutoRepository produtoRepository) throws HeadlessException, ProdutoNaoCadastradoException, CodigoInvalidoException, ParseException {
        super("PDVProject");
        int larguraMinima = 400;
        int alturaMinima = 300;
        this.setMinimumSize(new Dimension(larguraMinima, alturaMinima));
        this.controllerEstatisticas = controllerEstatisticas;
        this.produtoRepository = produtoRepository;
        MaskFormatter dataFormat = new MaskFormatter("##/##/####");
        dataFormat.setPlaceholder("________");
        jFTFDataInicio = new JFormattedTextField(dataFormat);
        jFTFDataFim = new JFormattedTextField(dataFormat);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        jLFaturamentoAchado = new JLabel("R$ " + String.valueOf(decimalFormat.format(this.controllerEstatisticas.getFaturamentoTotal())));
        jLLucroBrutoAchado = new JLabel("R$ " + String.valueOf(decimalFormat.format(this.controllerEstatisticas.getLucroTotal())));
        jLTicketMedioAchado = new JLabel("R$ " + String.valueOf(decimalFormat.format(this.controllerEstatisticas.getTicketMedioTotal())));
        jLNumeroVendasAchado = new JLabel(decimalFormat.format(this.controllerEstatisticas.getNumeroVendasTotal()));
        int row = controllerEstatisticas.getProdutosMaisVendidos().size();
        if (row > 5){
            row = 5;
        }
        int collumn = 2;
        JLabel nomeProduto;
        JLabel quantidadeVendida;
        jPPainelGeral.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        jPPainelGeral.add(jLTituloJanela, constraints);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.ipadx = 1;
        jPPainelGeral.add(jLDataInicio, constraints);
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        jPPainelGeral.add(jFTFDataInicio, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.BOTH;
        jPPainelGeral.add(jLDataFim, constraints);
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        jPPainelGeral.add(jFTFDataFim, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.BOTH;
        jPPainelGeral.add(jLFaturamento, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLFaturamentoAchado, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        jPPainelGeral.add(jLLucroBruto, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLLucroBrutoAchado, constraints);
        constraints.gridx = 0;
        constraints.gridy = 5;
        jPPainelGeral.add(jLTicketMedio, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLTicketMedioAchado, constraints);
        constraints.gridx = 0;
        constraints.gridy = 6;
        jPPainelGeral.add(jLNumeroVendas, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLNumeroVendasAchado, constraints);
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.fill = GridBagConstraints.SOUTH;
        jPPainelGeral.add(jLProdutosMaisVendidos, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLQuantidade, constraints);
        constraints.fill = GridBagConstraints.BOTH;
        for (int i = 0 ; i < row; i++){
            String nome = produtoRepository.consultarProduto(controllerEstatisticas.getProdutosMaisVendidos().get(i).getKey()).getNome();
            nomeProduto = new JLabel(nome);
            String quantidade = String.valueOf(decimalFormat.format(controllerEstatisticas.getProdutosMaisVendidos().get(i).getValue()));
            quantidadeVendida = new JLabel(quantidade);
            constraints.gridx--;
            constraints.gridy++;
            jPPainelGeral.add(nomeProduto, constraints);
            constraints.gridx++;
            jPPainelGeral.add(quantidadeVendida, constraints);
        }
        jPPainelGeral.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setContentPane(jPPainelGeral);
        pack();
        setVisible(true);
    }
}
