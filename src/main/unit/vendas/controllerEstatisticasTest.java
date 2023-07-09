package main.unit.vendas;

import main.exceptions.PeriodoInvalidoException;
import main.exceptions.VendaInexistenteException;
import main.exceptions.VendaInvalidaException;
import main.produto.Produto;
import main.repository.IVendasRepository;
import main.repository.VendasRepository;
import main.services.ControllerEstatisticas;
import main.transacao.Venda;
import org.junit.jupiter.api.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class controllerEstatisticasTest {

    public ControllerEstatisticas controllerEstatisticas;
    public static IVendasRepository vendasRepository;
    public static float faturamentoTotalCalculado;

    @BeforeAll
    public static void getInstanceVendas() throws ClassNotFoundException {
        controllerEstatisticasTest.vendasRepository = new VendasRepository();
        vendasRepository.deleteArchives();
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
        faturamentoTotalCalculado = venda.getValorPago() + venda1.getValorPago();
        controllerEstatisticas.inserirVenda(venda);
        controllerEstatisticas.inserirVenda(venda1);
        controllerEstatisticasTest.vendasRepository = new VendasRepository();
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
        Assertions.assertEquals(faturamentoTotalCalculado, controllerEstatisticas.getFaturamentoTotal());
    }

    @Test
    @Order(4)
    public void buscarVendasPeriodoVazio() throws PeriodoInvalidoException {
        Calendar dataInicio = new GregorianCalendar();
        dataInicio.set(2023, Calendar.FEBRUARY, 12, 00, 00, 00);
        Calendar dataFim = new GregorianCalendar();
        dataFim.set(2023, Calendar.FEBRUARY, 12, 23, 59, 59);
        ArrayList<Venda> vendasEsperadas = new ArrayList<>();
        Assertions.assertEquals(vendasEsperadas, this.controllerEstatisticas.getProdutosVendidosPeriodo(dataInicio, dataFim));
    }

    @Test
    @Order(5)
    public void buscarVendasPeriodoCheio() throws PeriodoInvalidoException {
        Calendar dataInicio = new GregorianCalendar();
        dataInicio.set(2023, Calendar.JULY, 9, 00, 00, 00);
        Calendar dataFim = new GregorianCalendar();
        dataFim.set(2023, Calendar.JULY, 9, 23, 59, 59);
        ArrayList<Integer> codigoVendasCadastradasEsperado = new ArrayList<>();
        codigoVendasCadastradasEsperado.add(1);
        codigoVendasCadastradasEsperado.add(2);
        ArrayList<Integer> codigoVendasCadastradas = new ArrayList<>();
        ArrayList<Venda> vendasCadastradas = new ArrayList<>(controllerEstatisticas.buscarVendasPeriodo(dataInicio, dataFim));
        for (Venda venda:
             vendasCadastradas) {
            codigoVendasCadastradas.add(venda.getNumero());
        }
        Assertions.assertEquals(codigoVendasCadastradasEsperado, codigoVendasCadastradas);
    }

    @Test
    @Order(6)
    public void produtosVendidosPeriodoVazio() throws PeriodoInvalidoException {
        Calendar dataInicio = new GregorianCalendar();
        dataInicio.set(2023, Calendar.FEBRUARY, 12, 00, 00, 00);
        Calendar dataFim = new GregorianCalendar();
        dataFim.set(2023, Calendar.FEBRUARY, 12, 23, 59, 59);
        List<Map.Entry<String, Float>> produtosVendidosPeriodo = controllerEstatisticas.getProdutosVendidosPeriodo(dataInicio, dataFim);
        Assertions.assertTrue(produtosVendidosPeriodo.isEmpty());
    }

    @Test
    @Order(7)
    public void produtosVendidosPeriodoCheio() throws PeriodoInvalidoException {
        Calendar dataInicio = new GregorianCalendar();
        dataInicio.set(2023, Calendar.JULY, 9, 00, 00, 00);
        Calendar dataFim = new GregorianCalendar();
        dataFim.set(2023, Calendar.JULY, 9, 23, 59, 59);
        ArrayList<String> produtosVendidosPeriodoEsperado = new ArrayList<>();
        produtosVendidosPeriodoEsperado.add("0001");
        produtosVendidosPeriodoEsperado.add("2020");
        ArrayList<String> produtosVendidosPeriodoObtido = new ArrayList<>();
        List<Map.Entry<String, Float>> produtosVendidosPeriodo = controllerEstatisticas.getProdutosVendidosPeriodo(dataInicio, dataFim);
        for (Map.Entry<String, Float> produtoVendido:
             produtosVendidosPeriodo) {
            produtosVendidosPeriodoObtido.add(produtoVendido.getKey());
        }
        Assertions.assertEquals(2,produtosVendidosPeriodo.size());
        Assertions.assertEquals(produtosVendidosPeriodoEsperado, produtosVendidosPeriodoObtido);
    }

}
