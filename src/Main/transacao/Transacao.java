package Main.transacao;

public abstract class Transacao {

    private float valor;

    public Transacao(float valor) {
        this.valor = valor;
    }

    public Transacao() {

    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
