package main.unit;

import main.caixa.Caixa;
import main.funcionario.Funcionario;
import main.funcionario.Operador;
import main.produto.Produto;
import main.transacao.Venda;
import main.exceptions.*;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CaixaTest {

    private Caixa caixaTest;
    private Funcionario funcionario;

    public CaixaTest() {
        funcionario = new Operador("user", "", "", "");
    }


    @BeforeEach
    public void getInstance(){
        caixaTest = new Caixa(funcionario);
    }

    @Test
    public void aberturaVazio(){
        float caixaInicialEsperado = 0.0f;
        Assertions.assertEquals(caixaInicialEsperado, caixaTest.getValorAbertura());
    }

    @Test
    public void aberturaValor() throws ValorAberturaInvalidoException {
        caixaTest = new Caixa(funcionario, 100f);
        float caixaInicialEsperado = 100f;
        Assertions.assertEquals(caixaInicialEsperado, caixaTest.getValorAbertura());
    }

    @Test
    public void aberturaValorNegativo(){
        Assertions.assertThrows(ValorAberturaInvalidoException.class,
                () -> new Caixa(funcionario, -10));
    }

    @Test
    public void getFuncionario(){
        caixaTest = new Caixa(funcionario);
        Assertions.assertEquals(this.funcionario, caixaTest.getVendedor());
    }

    @Test
    public void getDataAbertura(){
        Calendar dataEsperada = new GregorianCalendar();
        dataEsperada.setTime(Date.from(Instant.now()));
        Assertions.assertEquals(this.dateFormat(dataEsperada),
                this.dateFormat(caixaTest.getDataAbertura()));
    }

    private String dateFormat(Calendar date){
        return date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/"
                + date.get(Calendar.YEAR) + " " + date.get(Calendar.HOUR_OF_DAY) +
                ":" + date.get(Calendar.MINUTE);
    }

    @Test
    public void getDataFechamento(){
        caixaTest.fecharCaixa();
        Calendar dataEsperada = new GregorianCalendar();
        dataEsperada.setTime(Date.from(Instant.now()));
        Assertions.assertEquals(this.dateFormat(dataEsperada),
                this.dateFormat(caixaTest.getDataFechamento()));
    }

    @Test
    public void getValorEmCaixa() throws ValorReforcoInvalidoException {
        caixaTest.reforcarCaixa(100);
        Assertions.assertEquals(100, caixaTest.getValorEmCaixa());
    }

    @Test
    public void setVendedor(){
        caixaTest.setVendedor(this.funcionario);
        Assertions.assertEquals(this.funcionario, caixaTest.getVendedor());
    }


    @Test
    public void getVendasDoDia() throws VendaInvalidaException {
        Venda venda = new Venda();
        Produto produto = new Produto("PRODUTO", 10, 2, "0001", 20, "Fornecedor");
        venda.inserirCompra(produto);
        ArrayList<Venda> vendaArrayList = new ArrayList<>();
        vendaArrayList.add(venda);
        caixaTest.adicionarVenda(venda);
        Assertions.assertTrue(vendaArrayList.equals(caixaTest.getVendasDoDia()));
    }

    @Test
    public void buscarVenda() throws NumeroVendaInvalidoException, VendaInvalidaException {
        Venda venda = new Venda();
        Produto produto = new Produto("PRODUTO", 10, 2, "0001", 20, "Fornecedor");
        venda.inserirCompra(produto);
        int numero = venda.getNumero();
        caixaTest.adicionarVenda(venda);
        Assertions.assertEquals(venda, caixaTest.buscarVenda(numero));
    }

    @Test
    public void buscarVendaVazio() throws NumeroVendaInvalidoException {
        Assertions.assertEquals(null, caixaTest.buscarVenda(1));
    }

    @Test
    public void buscarVendaInvalida(){
        Assertions.assertThrows(NumeroVendaInvalidoException.class, () -> caixaTest.buscarVenda(-2));
    }

    @Test
    public void adicionarVenda() throws NumeroVendaInvalidoException, VendaInvalidaException {
        Venda venda = new Venda();
        Produto produto = new Produto("PRODUTO", 10, 2, "0001", 20, "Fornecedor");
        venda.inserirCompra(produto);
        caixaTest.adicionarVenda(venda);
        Assertions.assertEquals(venda, caixaTest.buscarVenda(venda.getNumero()));
    }

    @Test
    public void adicionarVendaNull(){
        Assertions.assertThrows(VendaInvalidaException.class, () -> caixaTest.adicionarVenda(null));
    }

    @Test
    public void removerVenda() throws NumeroVendaInvalidoException, VendaInexistenteException, VendaInvalidaException {
        Venda venda = new Venda();
        Produto produto = new Produto("PRODUTO", 10, 2, "0001", 20, "Fornecedor");
        venda.inserirCompra(produto);
        int numero = venda.getNumero();
        caixaTest.adicionarVenda(venda);
        caixaTest.removerVenda(venda.getNumero());
        Assertions.assertTrue(caixaTest.getVendasDoDia().isEmpty());
    }

    @Test
    public void removerVendaInvalido(){
        Assertions.assertThrows(NumeroVendaInvalidoException.class, () -> caixaTest.removerVenda(-1));
    }

    @Test
    public void removerVendaInexistente(){
        Assertions.assertThrows(VendaInexistenteException.class, () -> caixaTest.removerVenda(1));
    }

    @Test
    public void devolverProduto() throws NumeroVendaInvalidoException, VendaInexistenteException, VendaInvalidaException, ProdutoNaoCadastradoException {
        Venda venda = new Venda();
        Produto produto = new Produto("PRODUTO", 10, 2, "0001", 20, "Fornecedor");
        venda.inserirCompra(produto);
        Produto produto2 = new Produto("PRODUTO2", 10, 2, "0002", 20, "Fornecedor");
        venda.inserirCompra(produto2);
        int numeroVenda = venda.getNumero();
        caixaTest.adicionarVenda(venda);
        caixaTest.devolverProduto(produto2, numeroVenda);
        Assertions.assertEquals(10, caixaTest.getValorEmCaixa());
    }

    @Test
    public void devolverProdutoInvalido(){
        Assertions.assertThrows(NullPointerException.class, () -> caixaTest.devolverProduto(null, 1));
    }

    @Test
    public void devolverProdutoInexistente() throws VendaInvalidaException {
        Venda venda = new Venda();
        Produto produto = new Produto("PRODUTO", 10, 2, "0001", 20, "Fornecedor");
        venda.inserirCompra(produto);
        Produto produto2 = new Produto("PRODUTO2", 15, 7.5f, "0002", 24, "Fornecedor");
        caixaTest.adicionarVenda(venda);
        Assertions.assertThrows(ProdutoNaoCadastradoException.class, () -> caixaTest.devolverProduto(produto2, venda.getNumero()));
    }

    @Test
    public void sangrarCaixa() throws ValorSangriaInvalidoException, VendaInvalidaException, SangriaInvalidaException {
        Venda venda = new Venda();
        Produto produto = new Produto("PRODUTO", 10, 2, "0001", 20, "Fornecedor");
        venda.inserirCompra(produto);
        caixaTest.adicionarVenda(venda);
        float valorAntes = caixaTest.getValorEmCaixa();
        caixaTest.sangrarCaixa(10);
        float valorDepois = caixaTest.getValorEmCaixa();
        float resultado = valorDepois - valorAntes;
        Assertions.assertEquals(-10, resultado);
    }

    @Test
    public void sangrarCaixaInvalido(){
        Assertions.assertThrows(ValorSangriaInvalidoException.class, () -> caixaTest.sangrarCaixa(-12));
    }

    @Test
    public void sangrarCaixaVazio(){
        Assertions.assertThrows(SangriaInvalidaException.class, () -> caixaTest.sangrarCaixa(10));
    }

    @Test
    public void reforcarCaixa() throws ValorReforcoInvalidoException {
        float valorAntes = caixaTest.getValorEmCaixa();
        caixaTest.reforcarCaixa(20);
        float valorDepois = caixaTest.getValorEmCaixa();
        float resultado = valorAntes - valorDepois;
        Assertions.assertEquals(-20, resultado);
    }

    @Test
    public void reforcarCaixaInvalido(){
        Assertions.assertThrows(ValorReforcoInvalidoException.class,() -> caixaTest.reforcarCaixa(-10));
    }

    @Test
    public void atualizaValorEmCaixaZerado(){
        caixaTest.atualizaValorEmCaixa();
        Assertions.assertEquals(0, caixaTest.getValorEmCaixa());
    }

    @Test
    public void atualizaValorEmCaixaCheio() throws VendaInvalidaException {
        Venda venda1 = new Venda();
        Produto produto1 = new Produto("PRODUTO", 10, 2, "0001", 20, "Fornecedor");
        venda1.inserirCompra(produto1);
        Venda venda2 = new Venda();
        Produto produto2 = new Produto("PRODUTO2", 234.25f, 180, "0002", 20, "Fornecedor");
        venda2.inserirCompra(produto2);
        Venda venda3 = new Venda();
        Produto produto3 = new Produto("PRODUTO3", 321.05f, 230, "0003", 20, "Fornecedor");
        venda3.inserirCompra(produto3);
        caixaTest.adicionarVenda(venda1);
        caixaTest.adicionarVenda(venda2);
        caixaTest.adicionarVenda(venda3);
        float valorAntes = caixaTest.getValorEmCaixa();
        caixaTest.atualizaValorEmCaixa();
        Assertions.assertEquals(valorAntes, caixaTest.getValorEmCaixa());

    }

    @Test
    public void fecharCaixa(){
        Calendar data = new GregorianCalendar();
        data.setTime(Date.from(Instant.now()));
        caixaTest.fecharCaixa();
        Assertions.assertEquals(this.dateFormat(data), this.dateFormat(caixaTest.getDataFechamento()));
    }
}
