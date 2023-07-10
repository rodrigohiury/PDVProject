package main.funcionario;

public abstract class Funcionario implements Comparable{

    private String username;
    private String password;
    private String nome;
    private String cpf;
    private int numeroDeVendas;

    public Funcionario(String username, String password, String nome, String cpf) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroDeVendas() {
        return numeroDeVendas;
    }

    public void setNumeroDeVendas(int numeroDeVendas) {
        this.numeroDeVendas = numeroDeVendas;
    }

    public void incrementNumeroDeVendas(int numeroDeVendas){
        this.numeroDeVendas += numeroDeVendas;
    }
}
