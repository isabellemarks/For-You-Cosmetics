/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.cliente;

import br.com.integrador.dao.ClienteDao;
import br.com.integrador.dao.EnderecoDao;
import br.com.integrador.dao.TelefoneDao;
import java.util.Scanner;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Endereco;
import br.com.integrador.modelos.Telefone;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author 20121164010317
 */
public class TesteAdicionarCliente {

    static ClienteDao daoCli = new ClienteDao();
    static TelefoneDao daoTel = new TelefoneDao();
    static EnderecoDao daoEnd = new EnderecoDao();
    static Cliente cliente = new Cliente();
    static Endereco endereco = new Endereco();
    static Telefone telefone = new Telefone();

    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);

        System.out.println("Nome do Cliente");
        String nomeDoCliente = s.nextLine();

        System.out.println("Data de Nascimento");
        System.out.println("Informe o dia: ");
        int dia = s.nextInt();
        System.out.println("Informe o mês: ");
        int mes = s.nextInt();
        System.out.println("Informe o ano: ");
        int ano = s.nextInt();
        s.nextLine();
        java.sql.Date dataInformada = new java.sql.Date(ano - 1900, mes - 1, dia);
        cliente.setDataNascimento(dataInformada);
        
        System.out.println("Email");
        String emailDoCliente = s.nextLine();  
        
        System.out.println("Informe o telefone:");
        String telefoneEmString = s.nextLine();
        int telefoneDoTelefone = Integer.parseInt(telefoneEmString);
        
        System.out.println("Endereço:");
        System.out.println("Informe a rua:");
        String ruaDoEndereco = s.nextLine(); 
        
        System.out.println("Informe o número:");
        String numeroDoEndereco = s.nextLine();  
        
        System.out.println("Informe o complemento:");
        String complementoDoEndereco = s.nextLine(); 
        
        System.out.println("Informe o bairro:");
        String bairroDoEndereco = s.nextLine();
        
        System.out.println("Informe o CEP:");
        String cepEmString = s.nextLine();
        int cepDoEndereco = Integer.parseInt(cepEmString);
         
        int status = 1;
        

        Endereco meuEndereco = new Endereco(ruaDoEndereco, bairroDoEndereco, cepDoEndereco, complementoDoEndereco, numeroDoEndereco);
        int idEndereco = daoEnd.cadastrarUmEndereco(meuEndereco);
        meuEndereco.setIdEndereco(idEndereco);
        
        Telefone meuTelefone = new Telefone(telefoneDoTelefone);
        int idTelefone = daoTel.cadastrarUmTelefone(meuTelefone);
        meuTelefone.setIdTelefone(idTelefone);
        
        Cliente meuCliente = new Cliente(nomeDoCliente, dataInformada, emailDoCliente, status, meuEndereco, Arrays.asList(meuTelefone));
        
                
        int idCliente = daoCli.cadastrarUmCliente(meuCliente);
        meuCliente.setIdCliente(idCliente);
        
        meuTelefone.setTelefone(idTelefone);
        daoTel.cadastrarUmTelefone(meuTelefone);
    }
}