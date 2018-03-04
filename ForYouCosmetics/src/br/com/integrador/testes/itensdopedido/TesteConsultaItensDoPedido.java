/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.itensdopedido;

import br.com.integrador.dao.ItensDoPedidoDao;
import br.com.integrador.dao.RelatorioDao;
import br.com.integrador.modelos.Relatorios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteConsultaItensDoPedido {
    
    public static void main(String[] args) throws SQLException {
        List<Relatorios> relatorio = new ArrayList();
        RelatorioDao relatorioDao = new RelatorioDao();
        Scanner s = new Scanner(System.in);
        /*
        ItensDoPedidoDao itensDoPedido = new ItensDoPedidoDao();
        
        
        System.out.println("Todos os itens do pedido:");
        itensDoPedido.consultarItensDoPedido();
        
        System.out.println("Informe o ID do iten do pedido:");
        int id = s.nextInt();
        itensDoPedido.consultarItensDoPedidoPorId(id);
                */
        
        System.out.println("Mes");
        int mes = s.nextInt();
        System.out.println("Ano");
        int ano = s.nextInt();
        
        relatorio = relatorioDao.gerarRelatorio(mes, ano);
        for (Relatorios relatorios : relatorio) {
            
            System.out.println(relatorios.getIdPedido().getValorPedidoTotal());
            System.out.println(relatorios.getItensdopedido().getQuantidade());
            System.out.println(relatorios.getItensdopedido().getProduto().getNomeProduto());
        
            
        }
                
                
        
    }
    
}
