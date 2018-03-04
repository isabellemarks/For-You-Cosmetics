/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.pedidoeitens;

import br.com.integrador.dao.ClienteDao;
import br.com.integrador.dao.ItensDoPedidoDao;
import br.com.integrador.dao.PedidoDao;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.ItensdoPedido;
import br.com.integrador.modelos.Pedido;
import br.com.integrador.modelos.Produto;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteCadastrarPedidoComItens {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        Cliente cliente = new Cliente();
        Pedido pedido = new Pedido();
        PedidoDao pedidoDao = new PedidoDao();
        Produto produto = new Produto();
        ItensDoPedidoDao itensDoPedidoDao = new ItensDoPedidoDao();

        System.out.println("Informe o ID do Cliente que esta solicitando o pedido");
        cliente.setIdCliente(s.nextInt());
        s.nextLine();
        System.out.println("Valor do pedido:");
        String valorEmString = s.nextLine();
        float valorPedido = Float.parseFloat(valorEmString);

        System.out.println("Data de Pedido");
        System.out.println("Informe o dia: ");
        int dia = s.nextInt();
        System.out.println("Informe o mês: ");
        int mes = s.nextInt();
        System.out.println("Informe o ano: ");
        int ano = s.nextInt();
        s.nextLine();
        java.sql.Date dataInformada = new java.sql.Date(ano - 1900, mes - 1, dia);
        pedido.setDataPedido(dataInformada);

        //ADICIONAR OS SEUS ITENS
        ItensDoPedidoDao itensDoPedido = new ItensDoPedidoDao();
        
        System.out.println("Informe o ID do produto:");
        produto.setIdProduto(s.nextInt());
        
        s.nextLine();
        
        System.out.println("Quantidade do produto:");
        String quantidadeDoProdutoString = s.nextLine();
        int quantidadeDoProduto = Integer.parseInt(quantidadeDoProdutoString);
        
        
        ItensdoPedido meuItensDoPedido = new ItensdoPedido(pedido, produto, quantidadeDoProduto);
        int idItensDoPedido = itensDoPedidoDao.cadastrarItensDoPedido(meuItensDoPedido);
        meuItensDoPedido.setIdItensDoPedido(idItensDoPedido);
        
        Pedido meuPedido = new Pedido(valorPedido, dataInformada, cliente);
        int idPedido = pedidoDao.cadastrarUmPedido(meuPedido);
        meuPedido.setIdPedido(idPedido);
    }
}
