package main.repository;

import main.exceptions.NaoHaFuncionariosException;
import main.funcionario.Funcionario;
import main.transacao.Venda;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class FuncionarioRepository implements IFuncionarioRepository{

    private static Set<Funcionario> funcionariosCadastrados = new TreeSet<>();
    private static FuncionarioRepository instance;
    private static String pathFuncionarioDiretorio = "./src/archive/funcionarios";
    private static String pathFuncionarioArquivo = pathFuncionarioDiretorio + "/funcionarios.fun";
    private static File funcionariosDiretorio = new File(pathFuncionarioDiretorio);
    private static File funcionariosArquivo = new File(pathFuncionarioArquivo);

    private FuncionarioRepository() {
        instance = new FuncionarioRepository();
        if (!funcionariosDiretorio.exists()){
            funcionariosDiretorio.mkdir();
        }
        if (funcionariosArquivo.exists()){
            this.deserializeFuncionarios();
        }
    }

    public static FuncionarioRepository getInstance(){
        if (instance != null){
            return instance;
        }else {
            instance = new FuncionarioRepository();
            return instance;
        }
    }

    @Override
    public void inserirFuncionario(Funcionario funcionario) {
        funcionariosCadastrados.add(funcionario);
        serializeFuncionarios();
    }

    @Override
    public void removerFuncionario(Funcionario funcionario) {
        funcionariosCadastrados.remove(funcionario);
        serializeFuncionarios();
    }

    @Override
    public void alterarFuncionario(Funcionario funcionario) throws NaoHaFuncionariosException {
        Funcionario funcionarioProcurado = this.buscarFuncionario(funcionario.getCpf());
        if (funcionarioProcurado != null){
            funcionariosCadastrados.remove(funcionarioProcurado);
            funcionariosCadastrados.add(funcionario);
            serializeFuncionarios();
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
        serializeFuncionarios();
    }

    @Override
    public int getNumeroDeVendas(String chave){
        return this.buscarFuncionario(chave).getNumeroDeVendas();
    }

    @Override
    public void setNumeroDeVendas(String chave, int numeroDeVendas){
        this.buscarFuncionario(chave).setNumeroDeVendas(numeroDeVendas);
        serializeFuncionarios();
    }

    private void serializeFuncionarios(){
        try {
            FileOutputStream gravador = new FileOutputStream(pathFuncionarioArquivo);
            ObjectOutputStream conversor = new ObjectOutputStream(gravador);
            conversor.writeObject(FuncionarioRepository.funcionariosCadastrados);
            conversor.flush();
            conversor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void deserializeFuncionarios(){
        try {
            FileInputStream leitor = new FileInputStream(pathFuncionarioArquivo);
            ObjectInputStream conversor = new ObjectInputStream(leitor);
            FuncionarioRepository.funcionariosCadastrados = (Set<Funcionario>) conversor.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
