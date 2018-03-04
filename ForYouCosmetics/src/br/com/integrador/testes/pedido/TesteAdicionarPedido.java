/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.pedido;

import br.com.integrador.dao.ClienteDao;
import br.com.integrador.dao.PedidoDao;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Pedido;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author 20121164010406
 */
public class TesteAdicionarPedido {

    static PedidoDao daoPed = new PedidoDao();
    static ClienteDao daoCli = new ClienteDao();
    static Pedido pedido = new Pedido();
    static Cliente cliente = new Cliente();

    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);

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

        
        /*Cliente meuCliente = new Cliente(idDoCliente);
        int idCliente = daoCli.cadastrarUmCliente(meuCliente);
        meuCliente.setIdCliente(idCliente);

        Pedido meuPedido = new Pedido(valorPedido, dataInformada, meuCliente);
        int idPedido = daoPed.cadastrarUmPedido(meuPedido);
        meuPedido.setIdPedido(idPedido);*/
        
        Pedido meuPedido = new Pedido(valorPedido, dataInformada, cliente);
        int idPedido = daoPed.cadastrarUmPedido(meuPedido);
        meuPedido.setIdPedido(idPedido);
        
    }
}
