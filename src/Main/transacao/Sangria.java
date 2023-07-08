package Main.transacao;

public class Sangria extends Transacao{
    public Sangria(float valor) {
        super(-valor);
    }

    public Sangria() {
        super();
    }

    @Override
    public void setValor(float valor) {
        super.setValor(-valor);
    }
}
