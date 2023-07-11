package main.services;

import main.funcionario.Administrador;
import main.repository.*;

public class ControllerSupermercado {

    public static ControllerEstatisticas estatisticas;
    public static ControllerVendas vendas;
    private static IVendasRepository vendasRepository;
    public static IProdutoRepository produtos;
    public static IFuncionarioRepository funcionarios;
    private static ControllerSupermercado instance;

    private ControllerSupermercado() {
        vendasRepository = VendasRepository.getInstance();
        estatisticas = ControllerEstatisticas.getInstance(vendasRepository);
        vendas = ControllerVendas.getInstance(vendasRepository);
        produtos = ProdutoRepository.getInstanceLoja();
        funcionarios = FuncionarioRepository.getInstance();
        if (funcionarios.listarFuncionarios().isEmpty()){
            funcionarios.inserirFuncionario(Administrador.getInstance());
        }
    }

    public static ControllerSupermercado getInstance() {
        if (instance == null){
            instance = new ControllerSupermercado();
        }
        return instance;
    }
}
