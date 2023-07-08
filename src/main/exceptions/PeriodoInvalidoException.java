package main.exceptions;

import java.util.Calendar;

public class PeriodoInvalidoException extends Exception{

    Calendar dataInicial;
    Calendar dataFinal;

    public PeriodoInvalidoException(Calendar dataInicial, Calendar dataFinal){
        super("Periodo de vendas Inválido! Data Inicial depois da Data Final");
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }
}
