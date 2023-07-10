package main.services;

import main.funcionario.Administrador;
import main.repository.*;

public class ControllerSupermercado {

    public ControllerEstatisticas estatisticas;
    public ControllerVendas vendas;
    private IVendasRepository vendasRepository;
    public IProdutoRepository produtos;
    public IFuncionarioRepository funcionarios;

    public ControllerSupermercado() throws ClassNotFoundException {
        this.vendasRepository = new VendasRepository();
        this.estatisticas = new ControllerEstatisticas(this.vendasRepository);
        this.vendas = new ControllerVendas(this.vendasRepository);
        this.produtos = ProdutoRepository.getInstanceLoja();
        this.funcionarios = FuncionarioRepository.getInstance();
        if (funcionarios.listarFuncionarios().isEmpty()){
            funcionarios.inserirFuncionario(Administrador.getInstance());
        }
    }
}
