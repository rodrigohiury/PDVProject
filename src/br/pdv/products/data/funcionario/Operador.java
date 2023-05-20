package br.pdv.products.data.funcionario;

public class Operador extends Funcionario{

    public Operador(String username, String password, String nome, String cpf) {
        super(username, password, nome, cpf);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
