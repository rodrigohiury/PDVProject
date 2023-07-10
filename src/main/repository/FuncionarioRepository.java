package main.repository;

import main.exceptions.NaoHaFuncionariosException;
import main.funcionario.Funcionario;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class FuncionarioRepository implements IFuncionarioRepository{

    private Set<Funcionario> funcionariosCadastrados = new TreeSet<>();

    @Override
    public void inserirFuncionario(Funcionario funcionario) {
        funcionariosCadastrados.add(funcionario);
    }

    @Override
    public void removerFuncionario(Funcionario funcionario) {
        funcionariosCadastrados.remove(funcionario);
    }

    @Override
    public void alterarFuncionario(Funcionario funcionario) throws NaoHaFuncionariosException {
        Funcionario funcionarioProcurado = this.buscarFuncionario(funcionario.getCpf());
        if (funcionarioProcurado != null){
            funcionariosCadastrados.remove(funcionarioProcurado);
            funcionariosCadastrados.add(funcionario);
        }else {
            throw new NaoHaFuncionariosException();
        }
    }

    @Override
    public Funcionario buscarFuncionario(String chave) {
        if (chave != null){
            for (Funcionario funcionario :
                    funcionariosCadastrados) {
                if (funcionario.getUsername().equalsIgnoreCase(chave)){
                    return funcionario;
                }else if (funcionario.getNome().equalsIgnoreCase(chave)){
                    return funcionario;
                }else if (funcionario.getCpf().equalsIgnoreCase(chave)){
                    return funcionario;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<Funcionario> listarFuncionarios() {
        return funcionariosCadastrados;
    }

    @Override
    public Collection<Funcionario> buscarFuncionarios(String chave){
        if (chave != null){
            Set<Funcionario> funcionariosAchados = new TreeSet<>();
            for (Funcionario funcionario :
                    funcionariosCadastrados) {
                if (funcionario.getUsername().contains(chave)){
                    funcionariosAchados.add(funcionario);
                }else if (funcionario.getNome().contains(chave)){
                    funcionariosAchados.add(funcionario);
                }else if (funcionario.getCpf().contains(chave)){
                    funcionariosAchados.add(funcionario);
                }
            }
            return funcionariosAchados;
        }
        return null;
    }

    @Override
    public void incrementNumeroDeVendas(String chave, int numeroDeVendas){
        this.buscarFuncionario(chave).incrementNumeroDeVendas(numeroDeVendas);
    }

    @Override
    public int getNumeroDeVendas(String chave){
        return this.buscarFuncionario(chave).getNumeroDeVendas();
    }

    @Override
    public void setNumeroDeVendas(String chave, int numeroDeVendas){
        this.buscarFuncionario(chave).setNumeroDeVendas(numeroDeVendas);
    }
}
