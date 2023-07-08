package Main.repository;

import Main.exceptions.CodigoInvalidoException;
import Main.exceptions.CodigoJaCadastradoException;
import Main.exceptions.NaoHaProdutosException;
import Main.exceptions.ProdutoNaoCadastradoException;
import Main.produto.Produto;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ProdutoRepository implements IProdutoRepository,Serializable{
    static private ProdutoRepository instance = null;
    private static ArrayList <Produto> produtos;

    /**Método utilizado para instanciar ou pegar a instância já existente do ProdutoRepository.
     * @return ProdutoRepository - objeto ProdutoRepository que será utilizado pra administrar os produtos.
     */
    public static ProdutoRepository getInstanceLoja(){

        if(instance==null) {
            instance = new ProdutoRepository();
            instance.lerProdutoRepository();
        }
        return instance;

    }
    private ProdutoRepository() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public Produto consultarProduto(String codigo) throws ProdutoNaoCadastradoException, CodigoInvalidoException {
        Produto ret = null;
        //Busca na lista de veículos, se existir, retorna o veiculo, se não, levanta uma exceção
        //A exceção está guardando a placa que gerou a exceção

        if(!confereCodigo(codigo))
            throw new CodigoInvalidoException();
        else {
            for(Produto produto : this.produtos){
                if(produto.getCodigo().equals(codigo)){
                    ret = produto;
                    break;
                }
            }

            if(ret==null){
                throw new ProdutoNaoCadastradoException(codigo);
            }
        }

        return ret;
    }

    /**
     * Método para conferir se um codigo segue o padrão estabelecido.
     *
     * @param codigo - String Codigo ser conferida.
     */
    @Override
    public boolean confereCodigo(String codigo) {
        boolean res = true;
        if (codigo.length() != 7) {
            res = false;
        } else {
            if (!codigo.substring(0, 4).matches("[0-9]*")) {
                res = false;
            }
            return res;
        }
        return res;
    }

    /** Método para cadastrar um Produto no sistema.
     * @param produto - objeto Produto que será cadastrado no sistema.
     * @throws CodigoJaCadastradoException - Exceção levantada caso o objeto Produto já esteja cadastrado no sistema,
     * produtos que possuam códigos iguais levantam essa exceção.
     * @throws CodigoInvalidoException - Exceção levantada caso o codigo do produto não siga o padrão estabelecido,
     * Codigos que não possuam 4 números levantam essa exceção.
     */
    @Override
    public void cadastrarProduto(Produto produto) throws CodigoJaCadastradoException, CodigoInvalidoException {
        //Testando se o produto já está cadastrado no sistema, se sim, levanta uma exceção, se não, cadastra o produto na lista
        //A Exceção está guardando o produto que gerou a exceção
        if (this.produtos.contains(produto) == true) {
            throw new CodigoJaCadastradoException(produto);
        }

        for (Produto v : this.produtos) {
            if (v.getCodigo().equals(produto.getCodigo()))
                throw new CodigoJaCadastradoException(produto);
        }

        if (!confereCodigo(produto.getCodigo())) {
            throw new CodigoInvalidoException();
        }

        this.produtos.add(produto);

    }

    /** Método para remover um produto que recebe o codigo do produto a ser removido.
     * @param codigo - String que identifica o codigo do produto.
     * @throws ProdutoNaoCadastradoException - Exceção levantada caso o produto com o codigo informado não esteja cadastrado no sistema.
     * @throws CodigoInvalidoException
     */
    @Override
    public void removerProduto(String codigo) throws ProdutoNaoCadastradoException, CodigoInvalidoException {
        //Varre a lista de produtos, se o produto procurado pelo codigo fornecida, se o produto existir, então remove-se o produto
        //Senao levanta uma exceção; A exceção guarda a codigo que gerou ela.
        Produto p = null;
        int i = 0;
        if (!confereCodigo(codigo))
            throw new CodigoInvalidoException();
        else {
            for (Produto produto : this.produtos) {

                if (produto.getCodigo().equals(codigo)) {
                    p = produto;
                    i = this.produtos.indexOf(produto);
                }
            }
        }
        if (p == null)
            throw new ProdutoNaoCadastradoException(codigo);
        else
            this.produtos.remove(this.produtos.get(i));

    }
    /** Método que altera o preço de um produto recebendo como parâmetros o codigo do prdouto e o novo preço de produto.
     * @param codigo - String que identifica o codigo do produto.
     * @param novoPreco - float em decimais correspondente ao novo preço de venda do produto.
     * @throws ProdutoNaoCadastradoException - Exceção levantada caso o produto com o codigo informado não esteja cadastrado no sistema.
     */
    @Override
    public void alterarPreco(String codigo, float novoPreco) throws ProdutoNaoCadastradoException, CodigoInvalidoException {
        Produto p = null;
        int indexV = 0;

        if (!confereCodigo(codigo))
            throw new CodigoInvalidoException();
        for (Produto produto : this.produtos) {
            if (produto.getCodigo().equals(codigo)) {
                p = produto;
                indexV = this.produtos.indexOf(p);
            }
        }
        if (p == null)
            throw new ProdutoNaoCadastradoException(codigo);

        this.produtos.get(indexV).setPrecoVenda(novoPreco);


    }
    /** Método que lista os produtos cadastrados no sistema.
     * @return String - que informa os produtos registrados no sistema.
     * @throws NaoHaProdutosException - Exceção levantada caso não existam produtos cadastrados no sistema.
     */
    @Override
    public ArrayList<Object> listarProdutos() throws NaoHaProdutosException {
        ArrayList<Object> linhas = new ArrayList<>();
        int j = 0;


        for (Produto produto : this.produtos) {

            Object linha[] = {
                    produto.getNome(),
                    produto.getFornecedor(),
                    produto.getCodigo(),
                    produto.getPrecoVenda(),
                    produto.getPrecoCusto(),
                    produto.getEstoque(),

            };
            linhas.add(j, linha);
            j++;


        }
        return linhas;
    }
    /** Método para gravar os dados dos produtos da Supermercado de maneira persistente.
     * Retorna true quando os dados são salvos corretamente.
     * Retorna false quando ocorre um erro na gravação.
     */
    @Override
    public boolean salvaProdutoRepository() {
        try {
            FileOutputStream writeData = new FileOutputStream("produtos.ser");
            ObjectOutputStream obj = new ObjectOutputStream(writeData);
            obj.writeObject(produtos);
            obj.flush();
            obj.close();
            return true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a gravação do arquivo. \n" + erro.getMessage());
            return false;
        }
    }

    /** Método para ler os dados dos produtos de maneira persistente
     */
    @Override
    public void lerProdutoRepository() {
        try {
            File arquivo = new File("produtos.ser");
            if (arquivo.createNewFile() != true) {
                FileInputStream leitor = new FileInputStream("produtos.ser");
                ObjectInputStream obj = new ObjectInputStream(leitor);
                produtos = (ArrayList<Produto>) obj.readObject();
                obj.close();
            } else {
                FileOutputStream writeData = new FileOutputStream("produtos.ser");
                ObjectOutputStream obj = new ObjectOutputStream(writeData);
                obj.writeObject(produtos);
                obj.close();
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Falha ao ler os dados dos Produtos. \n");
        }

    }
}








