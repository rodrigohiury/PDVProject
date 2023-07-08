package Main.funcionario;

public class Gerente extends Funcionario{

    public Gerente(String username, String password, String nome, String cpf) {
        super(username, password, nome, cpf);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
