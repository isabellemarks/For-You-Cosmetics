/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.conexao;

import br.com.integrador.conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Isabelle
 */
public class TestaConexao {

    public static void main(String[] args) throws SQLException {
        Connection connection = new ConexaoBD().conexao();
        System.out.println("Conexão aberta!");
        connection.close();
    }
}
