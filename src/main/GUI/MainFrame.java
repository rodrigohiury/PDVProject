package main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setTitle("PDV Project");
        this.setSize(400,400);
        this.getContentPane().add(painelPrincipal);
        JTextField somaTxt = new JTextField();
        somaTxt.setColumns(4);
        JTextField somaTxt1 = new JTextField();
        somaTxt1.setColumns(4);
        c.gridx = 2;
        c.gridy = 2;
        painelPrincipal.add(somaTxt, c);
        c.gridy = 3;
        painelPrincipal.add(somaTxt1, c);
        c.gridx = 4;
        c.gridy = 4;
        JTextArea resultadoTxt = new JTextArea("Resultado");
        painelPrincipal.add(resultadoTxt, c);
        c.gridx = 1;
        c.gridy = 1;
        JButton exButton = new JButton("Soma");
        exButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int num1 = Integer.parseInt(somaTxt.getText());
                int num2 = Integer.parseInt(somaTxt1.getText());
                resultadoTxt.replaceRange(num1 + num2 + "", 0, 9);
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        painelPrincipal.add(exButton, c);
        this.setVisible(true);
    }
}


