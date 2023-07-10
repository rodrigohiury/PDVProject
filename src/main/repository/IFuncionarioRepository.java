package main.repository;

import main.exceptions.NaoHaFuncionariosException;
import main.funcionario.Funcionario;

import java.util.Collection;

public interface IFuncionarioRepository {

    public void inserirFuncionario(Funcionario funcionario);

    public void removerFuncionario(Funcionario funcionario);

    public void alterarFuncionario(Funcionario funcionario) throws NaoHaFuncionariosException;

    public Funcionario buscarFuncionario(String chave);

    public Collection<Funcionario> listarFuncionarios();

    Collection<Funcionario> buscarFuncionarios(String chave);

    void incrementNumeroDeVendas(String chave, int numeroDeVendas);

    int getNumeroDeVendas(String chave);

    void setNumeroDeVendas(String chave, int numeroDeVendas);
}
