/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 20121164010317
 */
public class UsuarioDao {

    private String status;
    private int idUsuario;

    /**
     * Método responsável por cadastrar um usuário no banco de dados.
     *
     * @param umUsuario representa um objeto do tipo
     * @link Usuário.
     * @return -1 se não tiver um próximo cliente.
     */
    public int cadastrarUmUsuario(Usuario umUsuario) {
        Connection connection = ConexaoBD.conexao();
        final String INSERIR_USUARIO = "INSERT INTO Usuario(login, senha, status) VALUES (?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_USUARIO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, umUsuario.getLogin());
            preparedStatement.setString(2, umUsuario.getSenha());
            preparedStatement.setInt(3, umUsuario.getStatus());

            preparedStatement.executeUpdate();

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
     * Método responsável pela consulta de um usuário.
     *
     * @param login representa o login do cliente
     * @param senha representa a senha do cliente
     * @return
     */
    public boolean consultarUsuario(String login, String senha) {
        boolean autenticado = false;
        Connection connection = ConexaoBD.conexao();
        ResultSet resultSet;

        try {
            final String AUTENTIFICAR_ACESSO = "SELECT idUsuario, login, senha, status FROM Usuario WHERE login LIKE ? and senha LIKE?;";
            PreparedStatement preparedStatement = connection.prepareStatement(AUTENTIFICAR_ACESSO, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);

            resultSet = preparedStatement.executeQuery();
            connection.commit();

            if (resultSet.next()) {
                status = resultSet.getString("status");
                idUsuario = resultSet.getInt("idUsuario");
                autenticado = true;

            } else {
                preparedStatement.close();
                return autenticado;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return autenticado;

    }

    /**
     * Método responsável por alterar um cliente com base em seu id.
     *
     * @param umUsuario representa um objeto da classe Usuário
     * @throws SQLException
     */
    public void alterarUmUsuario(Usuario umUsuario) throws SQLException {
        final String alterar = "UPDATE Usuario SET login=?, senha=? WHERE idUsuario=?;";

        PreparedStatement preparedStatement = ConexaoBD.conexao().prepareStatement(alterar);
        preparedStatement.setString(1, umUsuario.getLogin());
        preparedStatement.setString(2, umUsuario.getSenha());

        preparedStatement.executeUpdate();
    }

    /**
     * Método responsável por inativar um usuário, ou seja, deixar o usuário
     * inútel.
     *
     * @param umUsuario representa um objeto da classe Usuário
     */
    public void inativarUmUsuario(Usuario umUsuario) {
        Connection connection = ConexaoBD.conexao();
        final String INATIVAR_CLIENTE = "UPDATE Usuario SET status = 0 WHERE idUsuario = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INATIVAR_CLIENTE);
            preparedStatement.setInt(1, umUsuario.getIdUsuario());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Método responsável pela ativação de um Usuario.
     *
     * @param umUsuario representa um objeto do tipo Usuario
     */
    public void ativarUmUsuario(Usuario umUsuario) {
        Connection connection = ConexaoBD.conexao();
        final String ATIVAR_CLIENTE = "UPDATE Usuario SET status = 1 WHERE idUsuario = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ATIVAR_CLIENTE);
            preparedStatement.setInt(1, umUsuario.getIdUsuario());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}