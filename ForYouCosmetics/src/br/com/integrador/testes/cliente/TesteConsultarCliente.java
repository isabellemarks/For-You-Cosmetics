
package br.com.integrador.testes.cliente;

import br.com.integrador.dao.ClienteDao;
import java.sql.SQLException;
import java.util.Scanner;

public class TesteConsultarCliente {
   
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);
        ClienteDao cliente = new ClienteDao();
        
        
        System.out.println(" ");
        
        System.out.println("Informe o nome do cliente a ser pesquisado:");
        String nomeDoCliente = s.nextLine();
        cliente.consultarClientePorNome(nomeDoCliente);
        
        System.out.println(" ");
        
        System.out.println("Informe o ID do cliente a ser pesquisado: ");
        int idDoCliente = s.nextInt();
        cliente.consultarClientePorId(idDoCliente);
        
    }

}
