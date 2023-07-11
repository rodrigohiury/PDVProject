package main.funcionario;

public class Administrador extends Funcionario {

    private static Administrador adminInstance;

    public Administrador(String username, String senha, String nome, String cpf) {
        super(username, senha, nome, cpf);
        adminInstance = this;
    }

    public static Administrador getInstance() {
        if (adminInstance != null) {
            return adminInstance;
        } else {
            adminInstance = new Administrador("admin", "admin", "", "");
            return adminInstance;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o != null) {
            if (o != this) {
                if (o instanceof Administrador) {
                    return ((Administrador) o).getNome().compareTo(this.getNome());
                }
            } else {
                return 0;
            }
        } else {
            throw new NullPointerException();
        }
        return 0;
    }
}
