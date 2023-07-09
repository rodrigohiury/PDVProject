package main.unit.vendas;

import main.exceptions.NaoHaProdutosException;
import main.exceptions.VendaInexistenteException;
import main.exceptions.VendaInvalidaException;
import main.produto.Produto;
import main.repository.IVendasRepository;
import main.repository.VendasRepository;
import main.services.ControllerEstatisticas;
import main.transacao.Venda;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VendasControllerTest {

    public ControllerEstatisticas controllerEstatisticas;
    public static IVendasRepository vendasRepository;

    @BeforeAll
    public static void getInstanceVendas() throws ClassNotFoundException {
        VendasControllerTest.vendasRepository = new VendasRepository();
    }

    @BeforeEach
    public void getInstanceController() {
        controllerEstatisticas = new ControllerEstatisticas(VendasControllerTest.vendasRepository);
    }

    @Test
    @Order(1)
    public void inserirEBuscarVenda() throws VendaInvalidaException, ClassNotFoundException, VendaInexistenteException {
        Venda venda = new Venda();
        venda.inserirCompra(new Produto("Suporte TV Novissimo", 19.99f, 10, "0001", 10, "Fornecedor"), 1);
        controllerEstatisticas.inserirVenda(venda);
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
        Assertions.assertEquals("2021",numeroProdutoMaisVendido);
    }

}
