package Main.unit.vendas;

import Main.produto.Produto;
import Main.transacao.Venda;
import Main.repository.VendasRepository;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VendasRepositoryTest {

    public VendasRepository vendasRepository;
    public static int vendaNumero;

    @Test
    @Order(1)
    public void inserirESerializar() throws ClassNotFoundException {
        vendasRepository = new VendasRepository();
        Venda vendaTest = new Venda();
        this.vendaNumero = vendaTest.getNumero();
        vendaTest.inserirCompra(new Produto("Banana", 0.50f, 0.20f, "5050", 40, "Delit"), 12);
        vendaTest.setValorPago(20);
        vendasRepository.inserir(vendaTest);
        Venda vendaAchada = vendasRepository.buscarVenda(vendaTest.getNumero());
        Assertions.assertEquals(vendaTest.getValor(), vendaAchada.getValor());
        Assertions.assertTrue(vendaTest.getCarrinho().equals(vendaAchada.getCarrinho()));
    }

    @Test
    @Order(2)
    public void desserializar() throws ClassNotFoundException {
        vendasRepository = new VendasRepository();
        Assertions.assertDoesNotThrow(() -> vendasRepository.buscarVenda(vendaNumero));
        Venda venda = vendasRepository.buscarVenda(vendaNumero);
        if(venda != null){
            Produto produto = (Produto) venda.getCarrinho().keySet().toArray()[0];
            String nomeProduto = produto.getNome();
            Assertions.assertTrue(nomeProduto.equalsIgnoreCase("Banana"));
        }
    }
}
