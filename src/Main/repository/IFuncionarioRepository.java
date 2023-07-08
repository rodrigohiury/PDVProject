package Main.repository;

import Main.funcionario.Funcionario;

import java.util.Collection;

public interface IFuncionarioRepository {

    public void inserirFuncionario(Funcionario funcionario);

    public void removerFuncionario(Funcionario funcionario);

    public void alterarFuncionario(Funcionario funcionario);

    public Funcionario buscarFuncionario(String chave);

    public Collection<Funcionario> listarFuncionarios();

}
