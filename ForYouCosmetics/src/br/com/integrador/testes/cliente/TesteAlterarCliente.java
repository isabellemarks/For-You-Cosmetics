/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.integrador.testes.cliente;

import br.com.integrador.dao.ClienteDao;
import br.com.integrador.dao.EnderecoDao;
import br.com.integrador.dao.TelefoneDao;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Endereco;
import br.com.integrador.modelos.Telefone;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class TesteAlterarCliente {
    

    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Digite o ID do Cliente a ser alterado:");
        int idClienteA = Integer.parseInt(s.nextLine());
        
        System.out.println("Digite o ID do Endereco a ser alterado:");
        int idEnderecoA = Integer.parseInt(s.nextLine());
        
        System.out.println("Digite o ID do Telefone a ser alterado:");
        int idTelefoneA = Integer.parseInt(s.nextLine());

        System.out.println("Nome do Cliente");
        String nomeDoCliente = s.nextLine();

        System.out.println("Data de Nascimento");
        System.out.println("Informe o dia: ");
        int dia = Integer.parseInt(s.nextLine());
        System.out.println("Informe o mês: ");
        int mes = Integer.parseInt(s.nextLine());
        System.out.println("Informe o ano: ");
        int ano = Integer.parseInt(s.nextLine());
        
        java.sql.Date dataInformada = new java.sql.Date(ano - 1900, mes - 1, dia);
        
        System.out.println("Informe o seu email");
        String emailDoCliente = s.nextLine();  
        
        System.out.println("Informe a rua");
        String ruaDoEndereco = s.nextLine(); 
        
        System.out.println("Informe o número");
        String numeroDoEndereco = s.nextLine();  
        
        System.out.println("Informe o bairro");
        String bairroDoEndereco = s.nextLine();
        
        System.out.println("Informe o cep");
        String cepEmString = s.nextLine();
        int cepDoEndereco = Integer.parseInt(cepEmString);
        
        System.out.println("Informe o complemento");
        String complementoDoEndereco = s.nextLine();  
        
        System.out.println("Informe o telefone");
        String telefoneEmString = s.nextLine();
        int telefoneDoTelefone = Integer.parseInt(telefoneEmString);
        
        int status = 1;
        
        EnderecoDao daoEnd = new EnderecoDao();
        Endereco meuEndereco = new Endereco(ruaDoEndereco, bairroDoEndereco, cepDoEndereco, complementoDoEndereco, numeroDoEndereco);
        meuEndereco.setIdEndereco(idEnderecoA);
        daoEnd.alterarUmEndereco(meuEndereco);
        
        TelefoneDao daoTel = new TelefoneDao();
        Telefone meuTelefone = new Telefone(telefoneDoTelefone);
        meuTelefone.setIdTelefone(idTelefoneA);
        daoTel.alterarUmTelefone(meuTelefone);
       
        ClienteDao daoCli = new ClienteDao();
        Cliente meuCliente = new Cliente(nomeDoCliente, dataInformada, emailDoCliente, status, meuEndereco , Arrays.asList(meuTelefone));        
        meuCliente.setIdCliente(idClienteA);
        daoCli.alterarUmCliente(meuCliente);

        
        
    }
}
