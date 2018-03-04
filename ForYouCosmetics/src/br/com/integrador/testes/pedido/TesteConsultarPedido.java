/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.pedido;

import br.com.integrador.dao.PedidoDao;
import br.com.integrador.modelos.Pedido;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteConsultarPedido {
    
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);
        PedidoDao pedido = new PedidoDao();
        Pedido pedidos = new Pedido();
        
        System.out.println("Todos os pedidos:");
       // pedido.consultarTodosOsPedido(pedidos);
        
        System.out.println("\nInforme o ID do pedido para a pesquisa:");
        int idPedido = s.nextInt();
       // pedido.consultarPedidoPorNomeCliente(idPedido);
        
        System.out.println("\nInforme o ID do cliente para a pesquisa de seus pedidos:");
        int idDoCliente = s.nextInt();
        pedido.consultarPedidosPorCliente(idDoCliente);
    }
}
