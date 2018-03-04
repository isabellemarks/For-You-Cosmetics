/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.produto;

import java.util.Scanner;
import br.com.integrador.dao.ProdutoDao;
import br.com.integrador.modelos.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isabelle
 */
public class TesteConsultarProduto {

    public static void main(String[] args) throws SQLException {

        Scanner s = new Scanner(System.in);

        ProdutoDao produto = new ProdutoDao();
        
        System.out.println("Todos os produtos:");
        produto.consultarTodosOsProdutos();
                
        System.out.println("\nDigite o id do fornecedor que deseja pesquisar seus produtos:");
        int idDoFornecedor = s.nextInt();
        produto.consultarProdutosPorFornecedor(idDoFornecedor);
        
        s.nextLine();
        
        System.out.println("\nDigite o nome do produto para a ser consultado:");
        String nomeProduto = s.nextLine();
        produto.consultarProdutosPorNome(nomeProduto);

        System.out.println("\nInforme o ID do produto a ser pesquisado: ");
        int idProduto = s.nextInt();
        produto.consultaProdutoPorId(idProduto);
         
        
        
    }
}
