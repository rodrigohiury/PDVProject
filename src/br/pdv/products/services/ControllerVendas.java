package br.pdv.products.services;

import br.pdv.products.data.venda.Venda;
import br.pdv.products.exceptions.SemVendasCadastradasException;
import br.pdv.products.exceptions.VendaDuplicadaException;
import br.pdv.products.exceptions.VendaInexistenteException;
import br.pdv.products.exceptions.VendaInvalidaException;
import br.pdv.products.repository.IVendasRepository;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Collection;

public class ControllerVendas {

    private IVendasRepository repVendas;

    public ControllerVendas(IVendasRepository repVendas) {
        this.repVendas = repVendas;
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
