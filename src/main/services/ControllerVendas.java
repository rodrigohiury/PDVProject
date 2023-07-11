package main.services;

import main.transacao.Venda;
import main.exceptions.SemVendasCadastradasException;
import main.exceptions.VendaDuplicadaException;
import main.exceptions.VendaInexistenteException;
import main.exceptions.VendaInvalidaException;
import main.repository.IVendasRepository;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Collection;

public class ControllerVendas {

    private static IVendasRepository repVendas;
    private static ControllerVendas instance;

    private ControllerVendas(IVendasRepository repVendas) {
        this.repVendas = repVendas;
    }

    public static ControllerVendas getInstance(IVendasRepository repVendas) {
        if (instance == null){
            instance = new ControllerVendas(repVendas);
        }
        return instance;
    }

    public void adicionarVenda(Venda venda) throws VendaDuplicadaException, VendaInvalidaException {
        if (venda != null){
            if (repVendas.buscarVenda(venda.getNumero()) == null){
                repVendas.inserir(venda);
            }else {
                throw new VendaDuplicadaException();
            }
        } else {
            throw new VendaInvalidaException();
        }
    }

    public void removerVenda(Venda venda) throws VendaInvalidaException, VendaInexistenteException {
        if (venda != null){
            if (repVendas.buscarVenda(venda.getNumero()) != null){
                repVendas.remover(venda);
            }else {
                throw new VendaInexistenteException();
            }
        } else {
            throw new VendaInvalidaException();
        }
    }

    public void alterarVenda(Venda venda) throws VendaInvalidaException, VendaInexistenteException {
        if (venda != null){
            Venda vendaAnterior =  repVendas.buscarVenda(venda.getNumero());
            if (vendaAnterior != null){
                repVendas.alterarVenda(venda);
            }else {
                throw new VendaInexistenteException();
            }
        } else {
            throw new VendaInvalidaException();
        }
    }

    public Venda buscarVenda(int numero) throws VendaInexistenteException {
        if (numero > 0){
            return repVendas.buscarVenda(numero);
        } else {
            throw new VendaInexistenteException();
        }
    }

    public Collection<Venda> buscarVendas(Calendar data){
        if (data != null){
            return repVendas.buscarVendas(data);
        }else {
            throw new DateTimeException("Data Inválida, Tente Novamente!");
        }
    }

    public Collection<Venda> listaVenda() throws SemVendasCadastradasException {
        if (!repVendas.isEmpty()){
            return repVendas.listarVendas();
        }else {
            throw new SemVendasCadastradasException();
        }
    }
}
