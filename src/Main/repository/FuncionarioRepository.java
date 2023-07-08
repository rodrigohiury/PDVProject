package Main.repository;

import Main.funcionario.Funcionario;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class FuncionarioRepository implements IFuncionarioRepository{

    private Set<Funcionario> funcionariosCadastrados = new TreeSet<>();

    @Override
    public void inserirFuncionario(Funcionario funcionario) {

    }

    @Override
    public void removerFuncionario(Funcionario funcionario) {

    }

    @Override
    public void alterarFuncionario(Funcionario funcionario) {

    }

    @Override
    public Funcionario buscarFuncionario(String chave) {
        return null;
    }

    @Override
    public Collection<Funcionario> listarFuncionarios() {
        return null;
    }
}
