package main.unit.vendas;

import main.exceptions.VendaInexistenteException;
import main.exceptions.VendaInvalidaException;
import main.produto.Produto;
import main.repository.IVendasRepository;
import main.repository.VendasRepository;
import main.services.ControllerEstatisticas;
import main.transacao.Venda;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class controllerEstatisticasTest {

    public ControllerEstatisticas controllerEstatisticas;
    public static IVendasRepository vendasRepository;

    @BeforeAll
    public static void getInstanceVendas() throws ClassNotFoundException {
        controllerEstatisticasTest.vendasRepository = new VendasRepository();
    }

    @BeforeEach
    public void getInstanceController() {
        controllerEstatisticas = new ControllerEstatisticas(controllerEstatisticasTest.vendasRepository);
    }

    @Test
    @Order(1)
    public void inserirEBuscarVenda() throws VendaInvalidaException, ClassNotFoundException, VendaInexistenteException {
        Venda venda = new Venda();
        venda.inserirCompra(new Produto("Suporte TV Novissimo", 19.99f, 10, "0001", 10, "Fornecedor"), 5);
        venda.setValorPago(99.95f);
        Venda venda1 = new Venda();
        venda1.inserirCompra(new Produto("Abraçadeira", 0.50f, 0.20f, "2020", 50, "Fornecedor"), 12);
        venda1.setValorPago(6);
        controllerEstatisticas.inserirVenda(venda);
        controllerEstatisticas.inserirVenda(venda1);
        this.getInstanceVendas();
        this.getInstanceController();
        Assertions.assertDoesNotThrow(() -> controllerEstatisticas.buscarVenda(venda.getNumero()));
        Venda vendaAchada = controllerEstatisticas.buscarVenda(venda.getNumero());
        Assertions.assertEquals(venda.getNumero(), vendaAchada.getNumero());
    }

    @Test
    @Order(2)
    public void produtosMaisVendidos(){
        String numeroProdutoMaisVendido = controllerEstatisticas.getProdutosMaisVendidos().get(0).getKey();
        Assertions.assertEquals("2020",numeroProdutoMaisVendido);
    }

    @Test
    @Order(3)
    public void getFaturamentoTotal(){
        Assertions.assertEquals(105.95f, controllerEstatisticas.getFaturamentoTotal());
    }


}
