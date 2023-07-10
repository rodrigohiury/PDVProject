package main.GUI;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JButton;

public class GUIConfiguracoes extends JFrame {

	private JPanel contentPane;

	// Variável que mantém o tipo de look and feel do sistema
	private int setLookAndFeel;
	

	// Cria a janela de configurações 
	public GUIConfiguracoes(int LAF) {
		setTitle("CONFIGURAÇÕES");
		setType(Type.POPUP);
		
		this.setLookAndFeel(LAF);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jLTitulo = new JLabel("ESCOLHA SEU ESTILO");
		jLTitulo.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		jLTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		jLTitulo.setBounds(99, 28, 213, 13);
		contentPane.add(jLTitulo);
		
		JRadioButton jRBGTK = new JRadioButton("GTK+");
		jRBGTK.setBounds(151, 79, 103, 21);
		contentPane.add(jRBGTK);
		
		JRadioButton jRBMetal = new JRadioButton("Metal");
		jRBMetal.setBounds(151, 113, 103, 21);
		contentPane.add(jRBMetal);
		
		JRadioButton jRBClassicWindows = new JRadioButton("Classic Windows");
		jRBClassicWindows.setBounds(151, 148, 103, 21);
		contentPane.add(jRBClassicWindows);
		
		JRadioButton jRBMotif = new JRadioButton("Motif");
		jRBMotif.setBounds(151, 185, 103, 21);
		contentPane.add(jRBMotif);
		
		ButtonGroup grupo = new ButtonGroup();
        grupo.add(jRBGTK);
        grupo.add(jRBMetal);
        grupo.add(jRBClassicWindows);
        grupo.add(jRBMotif);
        
     // Ação no botão de GTK
		jRBGTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLookAndFeel = 1;
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
			}
		});
		
		// Ação no botão de Metal
		jRBMetal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLookAndFeel = 2;
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
			}
		});
		
		// Ação no botão de Windows
		jRBClassicWindows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLookAndFeel = 3;
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
			}
		});
		
		// Ação no botão de Motif 
		jRBMotif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLookAndFeel = 4;
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
			}
		});
		
        // Funcionamento dos botões
        JButton jBConfirm = new JButton("Confirmar");
        jBConfirm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		getLookAndFeel();
        		dispose();
        	}
        });
        jBConfirm.setBounds(112, 232, 85, 21);
        contentPane.add(jBConfirm);
        
        JButton jBCancel = new JButton("Cancelar");
        jBCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        jBCancel.setBounds(219, 232, 85, 21);
        contentPane.add(jBCancel);
        
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
	
	// Método que informa qual o look and feel do sistema
	public int getLookAndFeel () {
		return setLookAndFeel;
	}
}
