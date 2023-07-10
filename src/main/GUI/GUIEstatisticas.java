package main.GUI;

import main.exceptions.CodigoInvalidoException;
import main.exceptions.ProdutoNaoCadastradoException;
import main.repository.IProdutoRepository;
import main.services.ControllerEstatisticas;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class GUIEstatisticas extends JFrame {

    private JLabel jLFaturamento = new JLabel("Faturamento: ");
    private JLabel jLLucroBruto = new JLabel("Lucro Bruto: ");
    private JLabel jLTicketMedio = new JLabel("Ticket Medio: ");
    private JLabel jLNumeroVendas = new JLabel("Número de Vendas: ");
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

    public GUIEstatisticas(ControllerEstatisticas controllerEstatisticas, IProdutoRepository produtoRepository) throws HeadlessException, ProdutoNaoCadastradoException, CodigoInvalidoException {
        super("Estatísticas de Venda");
        int larguraMinima = 400;
        int alturaMinima = 300;
        this.setMinimumSize(new Dimension(larguraMinima, alturaMinima));
        this.controllerEstatisticas = controllerEstatisticas;
        this.produtoRepository = produtoRepository;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        jLFaturamentoAchado = new JLabel("R$ " + String.valueOf(decimalFormat.format(this.controllerEstatisticas.getFaturamentoTotal())));
        jLLucroBrutoAchado = new JLabel("R$ " + String.valueOf(decimalFormat.format(this.controllerEstatisticas.getLucroTotal())));
        jLTicketMedioAchado = new JLabel("R$ " + String.valueOf(decimalFormat.format(this.controllerEstatisticas.getTicketMedioTotal())));
        jLNumeroVendasAchado = new JLabel(decimalFormat.format(this.controllerEstatisticas.getNumeroVendasTotal()));
        int row = controllerEstatisticas.getProdutosMaisVendidos().size();
        int collumn = 2;
        jPProdutosMaisVendidos.setLayout(new GridLayout(row, collumn));
        JLabel nomeProduto;
        JLabel quantidadeVendida;
        jPPainelGeral.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        jPPainelGeral.add(jLFaturamento, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLFaturamentoAchado, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        jPPainelGeral.add(jLLucroBruto, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLLucroBrutoAchado, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        jPPainelGeral.add(jLTicketMedio, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLTicketMedioAchado, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        jPPainelGeral.add(jLNumeroVendas, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLNumeroVendasAchado, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        jPPainelGeral.add(jLProdutosMaisVendidos, constraints);
        constraints.gridx = 1;
        jPPainelGeral.add(jLQuantidade, constraints);
        constraints.gridx = 0;
        constraints.gridy = 5;
        for (int i = row ; i > 0; i--){
            String nome = produtoRepository.consultarProduto(controllerEstatisticas.getProdutosMaisVendidos().get(i-1).getKey()).getNome();
            nomeProduto = new JLabel(nome);
            String quantidade = String.valueOf(decimalFormat.format(controllerEstatisticas.getProdutosMaisVendidos().get(i-1).getValue()));
            quantidadeVendida = new JLabel(quantidade);
            jPProdutosMaisVendidos.add(nomeProduto);
            jPProdutosMaisVendidos.add(quantidadeVendida);
        }
        jPPainelGeral.add(jPProdutosMaisVendidos, constraints);
        setContentPane(jPPainelGeral);
        pack();
        setVisible(true);
    }
}
