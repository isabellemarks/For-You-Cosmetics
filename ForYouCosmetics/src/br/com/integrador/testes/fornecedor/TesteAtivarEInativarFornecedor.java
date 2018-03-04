/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.fornecedor;

import br.com.integrador.testes.cliente.*;
import br.com.integrador.dao.ClienteDao;
import br.com.integrador.dao.FornecedorDao;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Fornecedor;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteAtivarEInativarFornecedor {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        FornecedorDao clienteDao = new FornecedorDao();
        
        System.out.println("Você deseja ativar ou desativar um fornecedor? 1- ativar 2-inativar");
        int opcao = s.nextInt();

        if (opcao == 1) {
            
            System.out.println("Digite o ID do fornecedor que deseja ativar.");
            int idFornecedor = s.nextInt();

            int status = 0;

            Fornecedor meuFornecedor = new Fornecedor(idFornecedor, status);
            //clienteDao.ativarFornecedor(meuFornecedor);

            System.out.println("Fornecedor ativado com sucesso!");
            
        } else if (opcao == 2) {
            
            System.out.println("Digite o ID do fornecedor que deseja inativar.");
            int idFornecedor = s.nextInt();

            int status = 0;

            Fornecedor meuFornecedor = new Fornecedor(idFornecedor, status);
           // clienteDao.inativarFornecedor(meuFornecedor);

            System.out.println("Fornecedor inativado com sucesso!");
            
        }
    }
}
