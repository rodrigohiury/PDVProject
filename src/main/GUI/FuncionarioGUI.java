package main.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.exceptions.NaoHaFuncionariosException;
import main.funcionario.Administrador;
import main.funcionario.Gerente;
import main.funcionario.Operador;
import main.funcionario.Funcionario;
import main.repository.FuncionarioRepository;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class FuncionarioGUI extends JFrame {

    private FuncionarioRepository funcionarioRepository;

    private JButton btnCadastrar;
    private JButton btnListar;
    private JButton btnBuscar;

    public FuncionarioGUI() {
        setTitle("Funcionários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        funcionarioRepository = FuncionarioRepository.getInstance();

        btnCadastrar = new JButton("Cadastrar");
        btnListar = new JButton("Listar");
        btnBuscar = new JButton("Buscar");

        add(btnCadastrar);
        add(btnListar);
        add(btnBuscar);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastro();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaListagem();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaBusca();
            }
        });
    }

    private void abrirTelaCadastro() {
        JFrame telaCadastro = new JFrame("Cadastro de Funcionário");
        telaCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaCadastro.setSize(300, 250);
        telaCadastro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(20);

        JLabel lblCpf = new JLabel("CPF:");
        JTextField txtCpf = new JTextField(20);

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        JLabel lblTipo = new JLabel("Tipo:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Administrador", "Operador", "Gerente"});

        JButton btnSalvar = new JButton("Salvar");

        telaCadastro.add(lblNome, gbc);
        telaCadastro.add(txtNome, gbc);
        telaCadastro.add(lblCpf, gbc);
        telaCadastro.add(txtCpf, gbc);
        telaCadastro.add(lblUsername, gbc);
        telaCadastro.add(txtUsername, gbc);
        telaCadastro.add(lblSenha, gbc);
        telaCadastro.add(txtSenha, gbc);
        telaCadastro.add(lblTipo, gbc);
        telaCadastro.add(comboTipo, gbc);
        telaCadastro.add(btnSalvar, gbc);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String cpf = txtCpf.getText();
                String username = txtUsername.getText();
                String senha = new String(txtSenha.getPassword());
                String tipo = comboTipo.getSelectedItem().toString();

                if (nome.isEmpty() || cpf.isEmpty() || username.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(telaCadastro, "Preencha todos os campos.");
                    return;
                }

                switch (tipo) {
                    case "Administrador":
                        funcionarioRepository.inserirFuncionario(new Administrador(username, senha, nome, cpf));
                        break;
                    case "Operador":
                        funcionarioRepository.inserirFuncionario(new Operador(username, senha, nome, cpf));
                        break;
                    case "Gerente":
                        funcionarioRepository.inserirFuncionario(new Gerente(username, senha, nome, cpf));
                        break;
                }

                JOptionPane.showMessageDialog(telaCadastro, "Funcionário cadastrado com sucesso!");

                telaCadastro.dispose();
            }
        });

        telaCadastro.setVisible(true);
    }

    private void abrirTelaListagem() {
        JFrame telaListagem = new JFrame("Listagem de Funcionários");
        telaListagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaListagem.setSize(300, 250);
        telaListagem.setLayout(new BorderLayout());

        JTextArea txtListagem = new JTextArea(10, 30);
        txtListagem.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtListagem);

        JButton btnAtualizar = new JButton("Atualizar");

        telaListagem.add(scrollPane, BorderLayout.CENTER);
        telaListagem.add(btnAtualizar, BorderLayout.SOUTH);

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Collection<Funcionario> funcionarios = funcionarioRepository.listarFuncionarios();
                    txtListagem.setText("");
                    for (Funcionario funcionario : funcionarios) {
                        txtListagem.append(funcionario.getNome() + " - " + funcionario.getCpf() + "\n");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(telaListagem, "Não há funcionários cadastrados.");
                }
            }
        });

        telaListagem.setVisible(true);
    }

    private void abrirTelaBusca() {
        JFrame telaBusca = new JFrame("Busca de Funcionários");
        telaBusca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaBusca.setSize(300, 200);
        telaBusca.setLayout(new FlowLayout());

        JLabel lblChave = new JLabel("Chave de busca:");
        JTextField txtChave = new JTextField(20);

        JButton btnBuscar = new JButton("Buscar");

        JTextArea txtResultado = new JTextArea(10, 30);
        txtResultado.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtResultado);

        telaBusca.add(lblChave);
        telaBusca.add(txtChave);
        telaBusca.add(btnBuscar);
        telaBusca.add(scrollPane);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chave = txtChave.getText();
                Collection<Funcionario> funcionarios = funcionarioRepository.buscarFuncionarios(chave);

                txtResultado.setText("");
                if (funcionarios != null && !funcionarios.isEmpty()) {
                    for (Funcionario funcionario : funcionarios) {
                        txtResultado.append(funcionario.getNome() + " - " + funcionario.getCpf() + "\n");
                    }
                } else {
                    txtResultado.append("Nenhum funcionário encontrado.");
                }
            }
        });

        telaBusca.setVisible(true);
    }
}
