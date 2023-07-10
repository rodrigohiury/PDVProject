package main.repository;

import main.caixa.Caixa;
import main.funcionario.Funcionario;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Set;

public class CaixaRepository implements ICaixaRepository {

    private static ArrayList<Caixa> caixaArrayList = new ArrayList<>();
    private static CaixaRepository instance;
    private static String pathCaixasDiretorio = "./src/archive/caixas";
    private static String pathCaixasArquivo = pathCaixasDiretorio + "/caixas.cxs";
    private static File caixasDiretorio = new File(pathCaixasDiretorio);
    private static File caixasArquivo = new File(pathCaixasArquivo);

    private CaixaRepository(){
        instance = new CaixaRepository();
        if (!caixasDiretorio.exists()){
            caixasDiretorio.mkdir();
        }
        if (caixasArquivo.exists()){
            deserializeCaixas();
        }
    }

    public static CaixaRepository getInstance(){
        if (instance == null){
            instance = new CaixaRepository();
        }
        return instance;
    }


    @Override
    public void inserirCaixa(Caixa caixa) {
        caixaArrayList.add(caixa);
        serializeCaixas();
    }

    @Override
    public void removerCaixa(Caixa caixa) {
        caixaArrayList.remove(caixa);
        serializeCaixas();
    }

    @Override
    public void alterarCaixa(Caixa caixa) {
        if (caixa != null){
            Caixa caixaAnterior = this.buscarCaixa(caixa.getDataAbertura());
            if (caixaAnterior != null){
                caixaArrayList.set(caixaArrayList.indexOf(caixaAnterior),caixa );
                serializeCaixas();
            }
        }
    }

    @Override
    public Caixa buscarCaixa(Calendar data) {
        for (Caixa caixa:
             this.caixaArrayList) {
            if (caixa.getDataAbertura().equals(data)){
                return caixa;
            }
        }
        return null;
    }

    @Override
    public Collection<Caixa> listarCaixas() {
        return this.caixaArrayList;
    }

    private void serializeCaixas(){
        try {
            FileOutputStream gravador = new FileOutputStream(pathCaixasArquivo);
            ObjectOutputStream conversor = new ObjectOutputStream(gravador);
            conversor.writeObject(CaixaRepository.caixaArrayList);
            conversor.flush();
            conversor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void deserializeCaixas(){
        try {
            FileInputStream leitor = new FileInputStream(pathCaixasArquivo);
            ObjectInputStream conversor = new ObjectInputStream(leitor);
            CaixaRepository.caixaArrayList = (ArrayList<Caixa>) conversor.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
