package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20121164010317
 */
public class EnderecoDao {

    public EnderecoDao() {
    }

    /**
     * Método responsável pelo cadastro de um endereço.
     *
     * @param umEndereco representa um endereço.
     * @return -1 se não houver um próximo endereço.
     */
    public int cadastrarUmEndereco(Endereco umEndereco) {
        final String INSERIR_ENDERECO = "INSERT INTO Endereco(rua, bairro, cep, complemento, numero) VALUES(?, ?, ?, ?, ?);";
        Connection connection = ConexaoBD.conexao();

        try {
            if (ConexaoBD.conexao() != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_ENDERECO, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, umEndereco.getRua());
                preparedStatement.setString(2, umEndereco.getBairro());
                preparedStatement.setInt(3, umEndereco.getCep());
                preparedStatement.setString(4, umEndereco.getComplemento());
                preparedStatement.setString(5, umEndereco.getNumero());
                preparedStatement.executeUpdate();
                connection.commit();

                ResultSet chaveGerada = preparedStatement.getGeneratedKeys();

                if (chaveGerada.next()) {
                    return chaveGerada.getInt(1);
                }
                System.out.println("Cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possivel conectar com o banco de dados.");

            }

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return -1;
    }

    /**
     * Método responsável pela consulta de todos os endereços cadastrados no
     * banco de dados.
     *
     * @return uma lista com todos os endereços que foram cadastrados.
     */
    public List<Endereco> consultarTodosOsEndereco() {
        Connection connection = ConexaoBD.conexao();
        List<Endereco> endereco_resultados = new ArrayList();
        ResultSet ResultSet;

        final String CONSULTAR_TODOS_ENDERECOS = "SELECT * FROM Endereco";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_ENDERECOS);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {
                Endereco endereco = new Endereco();

                endereco.setIdEndereco(ResultSet.getInt("idEndereco"));
                endereco.setRua(ResultSet.getString("rua"));
                endereco.setNumero(ResultSet.getString("numero"));
                endereco.setBairro(ResultSet.getString("bairro"));
                endereco.setCep(ResultSet.getInt("cep"));
                endereco.setComplemento(ResultSet.getString("complemento"));

                endereco_resultados.add(endereco);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return endereco_resultados;

    }

    /**
     * Método responsávle por consultar um endereço com base em seu ID.
     *
     * @param idEndereco um inteiro que representa o identificador do endereço
     * desejado.
     * @return Uma lista com todos os endereços cadastrados no banco de dados.
     */
    public List<Endereco> consultarEnderecoPorId(int idEndereco) {
        Connection connection = ConexaoBD.conexao();
        List<Endereco> id_endereco_resultados = new ArrayList();
        ResultSet resultSet;

        final String CONSULTAR_ENDERECO_POR_ID = "SELECT * FROM Endereco WHERE idEndereco = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_ENDERECO_POR_ID);
            preparedStatement.setInt(1, idEndereco);
            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {

                Endereco endereco = new Endereco();
                endereco.setIdEndereco(resultSet.getInt("idCliente"));
                endereco.setRua(resultSet.getString("rua"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCep(resultSet.getInt("cep"));
                endereco.setComplemento(resultSet.getString("complemento"));
                endereco.setNumero(resultSet.getString("numero"));

                id_endereco_resultados.add(endereco);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return id_endereco_resultados;
    }

    /**
     * Método responsável pela alteração do endereço de um cliente.
     *
     * @param umCliente representa um cliente
     */
    public void alterarEnderecoDeUmCliente(Cliente umCliente) {
        Connection connection = ConexaoBD.conexao();
        final String sql = "?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, umCliente.getIdCliente());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    /**
     * Método responsável pela alteração de um endereco.
     *
     * @param umEndereco que representa o id do endereço.
     */
    public void alterarUmEndereco(Endereco umEndereco) {
        Connection connection = ConexaoBD.conexao();
        String ALTERAR_ENDERECO = "UPDATE Endereco SET rua=?, bairro=?, cep=?, complemento=?, numero=? WHERE idEndereco=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ALTERAR_ENDERECO);
            preparedStatement.setString(1, umEndereco.getRua());
            preparedStatement.setString(2, umEndereco.getBairro());
            preparedStatement.setInt(3, umEndereco.getCep());
            preparedStatement.setString(4, umEndereco.getComplemento());
            preparedStatement.setString(5, umEndereco.getNumero());
            preparedStatement.setInt(6, umEndereco.getIdEndereco());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    /**
     * Método responsável remoção de um endereço.
     *
     * @param umEndereco que representa um objeto da classe Endereco.
     * @link Endereco.
     *
     */
    public void removerEndereco(Endereco umEndereco) {
        final String REMOVER_ENDERECO = "DELETE FROM Endereco WHERE idEndereco=?;";
        Connection connection = ConexaoBD.conexao();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVER_ENDERECO);
            preparedStatement.setInt(1, umEndereco.getIdEndereco());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

}
