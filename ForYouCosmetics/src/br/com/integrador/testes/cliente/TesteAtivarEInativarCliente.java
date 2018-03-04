/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.cliente;

import br.com.integrador.dao.ClienteDao;
import br.com.integrador.modelos.Cliente;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteAtivarEInativarCliente {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        ClienteDao clienteDao = new ClienteDao();

        System.out.println("Você deseja ativar ou desativar um cliente? 1- ativar 2-inativar");
        int opCliente = s.nextInt();

        if (opCliente == 1) {
            
            System.out.println("Digite o ID do cliente que deseja ativar.");
            int idCliente = s.nextInt();

            int status = 0;

            Cliente meuCliente = new Cliente(idCliente, status);
            clienteDao.ativarCliente(meuCliente);

            System.out.println("Cliente ativado com sucesso!");
            
        } else if (opCliente == 2) {
            
            System.out.println("Digite o ID do cliente que deseja inativar.");
            int idCliente = s.nextInt();

            int status = 0;

            Cliente meuCliente = new Cliente(idCliente, status);
            //clienteDao.inativarCliente(meuCliente);

            System.out.println("Cliente inativado com sucesso!");
            
        }

    }
}
