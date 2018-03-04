/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isabelle
 */
public class ContatoDao {

    /**
     * Método responsável por cadastrar um contato no banco de dados, ou seja,
     * uma mensagem mandada.
     *
     * @param umContato representa um objeto do tipo contato.
     * @return o id do contato que foi gerado, caso não exista um próximo
     * cliente ele retornará -1.
     */
    public int cadastrarUmContato(Contato umContato) {
        final String INSERIR_CONTATO = "INSERT INTO Contato(nome, email, telefone, voceENosso, assunto, mensagem) VALUES (?, ?, ?, ?, ?, ?);";
        Connection connection = ConexaoBD.conexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_CONTATO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, umContato.getNome());
            preparedStatement.setString(2, umContato.getEmail());
            preparedStatement.setInt(3, umContato.getTelefone());
            preparedStatement.setString(4, umContato.getVoceENosso());
            preparedStatement.setString(5, umContato.getAssunto());
            preparedStatement.setString(6, umContato.getMensagem());
            preparedStatement.executeUpdate();
            connection.commit();

            ResultSet chavesGeradas = preparedStatement.getGeneratedKeys();
            if (chavesGeradas.next()) {
                return chavesGeradas.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return -1;
    }

    /**
     * Método responsável por consultar todos os contatos.
     */
    public void consultarTodosOsContatos() {
        ResultSet ResultSet;
        Connection connection = ConexaoBD.conexao();
        final String TODOS_CONTATOS = "SELECT * FROM Contato;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(TODOS_CONTATOS);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {
                Contato contato = new Contato();

                contato.setIdContato(ResultSet.getInt("idContato"));
                contato.setNome(ResultSet.getString("nome"));
                contato.setEmail(ResultSet.getString("email"));
                contato.setTelefone(ResultSet.getInt("telefone"));
                contato.setVoceENosso(ResultSet.getString("voceENosso"));
                contato.setAssunto(ResultSet.getString("assunto"));
                contato.setMensagem(ResultSet.getString("mensagem"));

                System.out.println(contato);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    /**
     * Método responsável por consultar todos os contatos com base no nome
     * informado.
     *
     * @param nome representa o nome informado.
     */
    public void consultarContatosPorNome(String nome) {
        ResultSet ResultSet;
        Connection connection = ConexaoBD.conexao();
        final String CONTATOS_POR_NOME = "SELECT * FROM Contato WHERE nome LIKE ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONTATOS_POR_NOME);
            preparedStatement.setString(1, nome + "%");
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {
                Contato contato = new Contato();

                contato.setIdContato(ResultSet.getInt("idContato"));
                contato.setNome(ResultSet.getString("nome"));
                contato.setEmail(ResultSet.getString("email"));
                contato.setTelefone(ResultSet.getInt("telefone"));
                contato.setVoceENosso(ResultSet.getString("voceENosso"));
                contato.setAssunto(ResultSet.getString("assunto"));
                contato.setMensagem(ResultSet.getString("mensagem"));

                System.out.println(contato);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
