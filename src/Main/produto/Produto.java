package Main.produto;

import java.io.Serializable;
import java.util.Objects;

/** A classe <b>Produto</b> define atributos que um produto necessitará para ser
 * criado como objeto, além de definir os métodos que esse objeto possuirá
 * @author Luiz Augusto e Miguel
 * @since julho 2023
 * @version 1.0
 */
public class Produto implements Comparable, Serializable {

    private static final long serialVersionUID = 30l;
    private String nome;
    private float precoVenda;
    private float precoCusto;
    private String codigo;
    private int estoque;
    private String fornecedor;

    /**
     * Construtor da classe <b>Produto</b><br><br>
     * <b>Uso:</b><br>
     * Produto produto = new Produto("Barra de chocolate",12f,8f,"2563",86,"Nestlé");<br><br>
     * <b>Onde:</b><br>
     * @param nome - String que identifica o nome do produto.
     * @param precoVenda - float que identifica o preço de venda do produto.
     * @param precoCusto - float que identifica o preço do custo do produto.
     * @param codigo- String que identifica o codigo de identificação do produto.
     * @param estoque - int que identifica a quantidade do produto.
     * @param fornecedor - String identifica o nome do fornecedor do produto.
     *o.
     */
    public Produto(String nome, float precoVenda, float precoCusto, String codigo, int estoque, String fornecedor) {
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.precoCusto = precoCusto;
        this.codigo = codigo;
        this.estoque = estoque;
        this.fornecedor = fornecedor;
    }

    /** Método para transformar os atributos do veículo em uma String única
     * @return String - Dados do funcionário formatados conforme o modelo abaixo:<br><br>
     * ---------------------------------
     * <br>Nome: XXXXXXXX
     * <br>Preço de Venda: XXXXX
     * <br>Preço de Custo: XXXXX
     * <br>Codigo: XXXX
     * <br>Estoque: XXXXX
     * <br>Fornecedor: XXXXX
     */

    @Override
    public String toString() {
        String ret ="";
        ret  =   "---------------------------------"+
                "\nNome:" + this.nome +
                "\nPreco de Venda:" + this.precoVenda +
                "\nPreco de Custo:" + this.precoCusto +
                "\nCodigo:'" + this.codigo +
                "\nEstoque:" + this.estoque +
                "\nFornecedor:'" + this.fornecedor;
        return ret;
    }
    //Métodos Gets e Sets

    /** Método para retorno do nome do produto.<br>
     * @return String - nome do produto.
     */
    public String getNome() {return nome;}

    /** Método definir o nome do produto.<br>
     * @param nome - String que identifica o nome do produto a ser modificado no objeto produto.
     */
    public void setNome(String nome) {this.nome = nome;}

    /**
     * Método para retorno do preço de venda do produto.<br>
     *
     * @return String - Preço de Venda.
     */
    public float getPrecoVenda() {return precoVenda;}

    /** Método definir o nome do produto.<br>
     * @param precoVenda - String que identifica o preço de venda do produto a ser modificado no objeto produto.
     */
    public void setPrecoVenda(float precoVenda) {this.precoVenda = precoVenda;}

    /** Método para retorno do preço de custo do produto.<br>
     * @return String - Preço de Custo.
     */
    public float getPrecoCusto() {return precoCusto;}

    /** Método definir o nome do produto.<br>
     * @param precoCusto - String que identifica o preço de custo do produto a ser modificado no objeto produto.
     */
    public void setPrecoCusto(float precoCusto) {this.precoCusto = precoCusto;}

    /** Método para retorno do codigo do produto.<br>
     * @return String - codigo do produto.
     */
    public String getCodigo() {return codigo;}

    /** Método definir o nome do produto.<br>
     * @param codigo - String que identifica o codigo do produto a ser modificado no objeto produto.
     */
    public void setCodigo(String codigo) {this.codigo = codigo;}

    /** Método para retorno da quantidade do produto.<br>
     * @return String - Quantidade do produto.
     */
    public int getEstoque() {return estoque;}

    /** Método definir o nome do produto.<br>
     * @param estoque - String que identifica a quantidade do produto a ser modificado no objeto produto.
     */
    public void setEstoque(int estoque) {this.estoque = estoque;}

    /** Método para retorno do nome do fornecedor do produto.<br>
     * @return String - nome do fornecedor do produto.
     */
    public String getFornecedor() {return fornecedor;}

    /** Método definir o nome do produto.<br>
     * @param fornecedor - String que identifica o nome do fornecedor do produto a ser modificado no objeto produto.
     */
    public void setFornecedor(String fornecedor) {this.fornecedor = fornecedor;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome) && Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, codigo);
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof Produto){
            return ((Produto) o).getNome().compareTo(this.nome);
        }
        return 0;
    }
}
