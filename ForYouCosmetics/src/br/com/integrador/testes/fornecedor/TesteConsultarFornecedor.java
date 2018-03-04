/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.integrador.testes.fornecedor;

import br.com.integrador.dao.FornecedorDao;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author 20121164010406
 */
public class TesteConsultarFornecedor {
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);
        
        FornecedorDao fornecedor = new FornecedorDao();
                       
        System.out.println(" ");
        
        System.out.println("Digite o nome do fornecedor para a ser consultado:");
        String consultafornecedor = s.nextLine();
        fornecedor.consultarFornecedorPorNome(consultafornecedor);   
        
        System.out.println(" ");
        
        System.out.println("Informe o ID do fornecedor a ser pesquisado: ");
        int idDoFornecedor = s.nextInt();
      
       fornecedor.consultarFornecedorPorId(idDoFornecedor);
    }
    
    
        
}

