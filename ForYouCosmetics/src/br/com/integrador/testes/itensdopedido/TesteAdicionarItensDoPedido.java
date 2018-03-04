/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.itensdopedido;

import br.com.integrador.dao.ItensDoPedidoDao;
import br.com.integrador.dao.PedidoDao;
import br.com.integrador.dao.ProdutoDao;
import br.com.integrador.modelos.ItensdoPedido;
import br.com.integrador.modelos.Pedido;
import br.com.integrador.modelos.Produto;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteAdicionarItensDoPedido {
    
    static ItensDoPedidoDao daoItensDoPedido = new ItensDoPedidoDao();
    static PedidoDao daoPedido = new PedidoDao();
    static ProdutoDao daoProduto = new ProdutoDao();
    static ItensdoPedido itensDoPedido = new ItensdoPedido();
    static Pedido pedido = new Pedido();
    static Produto produto = new Produto();
    
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Informe o ID do pedido:");
        pedido.setIdPedido(s.nextInt());
        
        System.out.println("Informe o ID do produto:");
        produto.setIdProduto(s.nextInt());
        
        s.nextLine();
        
        System.out.println("Quantidade do produto:");
        String quantidadeDoProdutoString = s.nextLine();
        int quantidadeDoProduto = Integer.parseInt(quantidadeDoProdutoString);
        
        
        ItensdoPedido meuItensDoPedido = new ItensdoPedido(pedido, produto, quantidadeDoProduto);
        int idItensDoPedido = daoItensDoPedido.cadastrarItensDoPedido(meuItensDoPedido);
        meuItensDoPedido.setIdItensDoPedido(idItensDoPedido);
    }
    
}
