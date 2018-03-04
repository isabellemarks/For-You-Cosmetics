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
public class TesteAlterarFornecedor {

    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite o ID do fornecedor a ser alterado:");
        int idFornecedorA = Integer.parseInt(s.nextLine());
        
        System.out.println("Digite o ID do Endereco a ser alterado:");
        int idEnderecoA = Integer.parseInt(s.nextLine());

        System.out.println("Digite o ID do Telefone a ser alterado:");
        int idTelefoneA = Integer.parseInt(s.nextLine());

        System.out.println("Nome do fornecedor");
        String nomeDoFornecedor = s.nextLine();

        System.out.println("informe o seu email");
        String emailDoFornecedor = s.nextLine();

        System.out.println("informe a rua");
        String ruaDoEndereco = s.nextLine();

        System.out.println("informe o número");
        String numeroDoEndereco = s.nextLine();
        int telefoneDoTelefone = Integer.parseInt(numeroDoEndereco);

        System.out.println("informe a bairro");
        String bairroDoEndereco = s.nextLine();

        System.out.println("informe o cep");
        String cepEmString = s.nextLine();
        int cepDoEndereco = Integer.parseInt(cepEmString);

        System.out.println("informe o complemento");
        String complementoDoEndereco = s.nextLine();
        
        int status = 1;

        EnderecoDao daoEnd = new EnderecoDao();
        Endereco meuEndereco = new Endereco(ruaDoEndereco, bairroDoEndereco, cepDoEndereco, complementoDoEndereco, numeroDoEndereco);
        meuEndereco.setIdEndereco(idEnderecoA);
        daoEnd.alterarUmEndereco(meuEndereco);

        TelefoneDao daoTel = new TelefoneDao();
        Telefone meuTelefone = new Telefone(telefoneDoTelefone);
        meuTelefone.setIdTelefone(idTelefoneA);
        daoTel.alterarUmTelefone(meuTelefone);

        FornecedorDao daoForn = new FornecedorDao();
        Fornecedor meuFornecedor = new Fornecedor(nomeDoFornecedor, emailDoFornecedor, status, meuEndereco, Arrays.asList(meuTelefone));

        int idFornecedor = daoForn.cadastrarUmFornecedor(meuFornecedor);
        meuFornecedor.setIdFornecedor(idFornecedor);
        meuFornecedor.setIdFornecedor(idFornecedorA);
        daoForn.alterarUmFornecedor(meuFornecedor);

    }
}
