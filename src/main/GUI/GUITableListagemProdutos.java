package main.GUI;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import main.exceptions.NaoHaProdutosException;
import main.repository.ProdutoRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/** A classe <b>GUITableListagemProdutos</b> é responsável por criar a interface gráfica da tabela
* que lista os produtos cadastrados.
* @author Luiz Augusto e Miguel.
* @since jul 2023
* @version 1.0
*/
public class GUITableListagemProdutos {

   //Intanciação da Produto pelo Singleton
   ProdutoRepository produto= ProdutoRepository.getInstanceLoja();

   public GUITableListagemProdutos(int LAF){
	   
	   this.setLookAndFeel(LAF);
	   
       ArrayList<Object> linhas = new ArrayList<>();
       DefaultTableModel produtosTableModel = new DefaultTableModel();

       //Criando Colunas
       produtosTableModel.addColumn("Nome");
       produtosTableModel.addColumn("Fornecedor");
       produtosTableModel.addColumn("Codigo");
       produtosTableModel.addColumn("Preço de Venda");
       produtosTableModel.addColumn("Preço de Custo");
       produtosTableModel.addColumn("Estoque");

       //criando linhas
       try{
           linhas = produto.listarProdutos();
       } catch(NaoHaProdutosException ex){}

       //Adicionando linhas
       for(int i=0;i<linhas.size();i++){
           produtosTableModel.insertRow(i,(Object[])linhas.get(i));
       }

       //Instanciação da Tabela de Produtoss Cadastrados
       JTable veiculosTable = new JTable(produtosTableModel);

       //Configurações da Tabela
       JFrame tabelaVeiculos = new JFrame();
       tabelaVeiculos.setSize(1200,600);
       tabelaVeiculos.setResizable(false);
       tabelaVeiculos.setLocationRelativeTo(null);
       tabelaVeiculos.add(new JScrollPane(veiculosTable));
       tabelaVeiculos.setVisible(true);

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
