package br.com.integrador.testes.produto;

import br.com.integrador.dao.FornecedorDao;
import br.com.integrador.dao.ProdutoDao;
import br.com.integrador.modelos.Fornecedor;
import br.com.integrador.modelos.Produto;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author 20121164010406
 */
public class TesteAdicionarProduto {
    static ProdutoDao daoPro = new ProdutoDao();
    static FornecedorDao daoFor = new FornecedorDao();
    static Produto produto = new Produto();
    static Fornecedor fornecedor = new Fornecedor();

    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);

        System.out.println("Nome do produto:");
        String nomeDoProduto = s.nextLine();

        System.out.println("Informe a descrição:");
        String descricaoDoProduto = s.nextLine();

        System.out.println("Informe o preço unitário:");
        String precoUnitarioDoProdutoEmString = s.nextLine();
        float precoUnitarioDoProduto = Float.parseFloat(precoUnitarioDoProdutoEmString);
        
        System.out.println("Informe o ID do fornecedor:");
        fornecedor.setIdFornecedor(s.nextInt());
        
        int status = 1;
        
        
        Produto meuProduto = new Produto(nomeDoProduto, descricaoDoProduto, precoUnitarioDoProduto, status, fornecedor);
        int idProduto = daoPro.cadastrarUmProduto(meuProduto);
        meuProduto.setIdProduto(idProduto);
    }
}
