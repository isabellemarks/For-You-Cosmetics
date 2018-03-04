/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.fornecedor;

import br.com.integrador.dao.EnderecoDao;
import br.com.integrador.dao.FornecedorDao;
import br.com.integrador.dao.TelefoneDao;
import br.com.integrador.modelos.Endereco;
import br.com.integrador.modelos.Fornecedor;
import br.com.integrador.modelos.Telefone;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author 20121164010406
 */
public class TesteAdicionarFornecedor {

    static FornecedorDao daoForn = new FornecedorDao();
    static EnderecoDao daoEnd = new EnderecoDao();
    static TelefoneDao daoTel = new TelefoneDao();
    static Fornecedor fornecedor = new Fornecedor();
    static Endereco endereco = new Endereco();
    static Telefone telefone = new Telefone();

    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);

        System.out.println("Nome do Fornecedor");
        String nomeDoFornecedor = s.nextLine();

        System.out.println("Informe o telefone:");
        String telefoneEmString = s.nextLine();
        int telefoneDoTelefone = Integer.parseInt(telefoneEmString);

        System.out.println("Informe o seu e-mail:");
        String emailDoFornecedor = s.nextLine();

        System.out.println("Endereco:\nInforme a rua:");
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
        
        Fornecedor meuFornecedor = new Fornecedor(nomeDoFornecedor, emailDoFornecedor, status, meuEndereco, Arrays.asList(meuTelefone));
        int idFornecedor = daoForn.cadastrarUmFornecedor(meuFornecedor);
        meuFornecedor.setIdFornecedor(idFornecedor);
    }
}
