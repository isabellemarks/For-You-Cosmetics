package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Fornecedor;
import br.com.integrador.modelos.Telefone;
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
public class TelefoneDao {

    public TelefoneDao() {
    }

    /**
     * Método responsável por cadastrar UmTelefone
     *
     * @param umTelefone que representa um objeto da classe Telefone.
     * @link Telefone
     * @return -1 se não tiver um próximo telefone.
     *
     */
    public int cadastrarUmTelefone(Telefone umTelefone) {
        Connection connection = ConexaoBD.conexao();
        final String INSERIR_TELEFONE = "INSERT INTO Telefone(telefone) VALUES(?);";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_TELEFONE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, umTelefone.getTelefone());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet chaveGerada = preparedStatement.getGeneratedKeys();

            if (chaveGerada.next()) {
                return chaveGerada.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return -1;
    }

    /**
     * Método responsável pela consulta de telefones de cada cliente.
     *
     * @param umCliente representa um objeto da classe
     * @link Cliente
     * @return retorna uma lista com os telefones de cada cliente. Caso não seja
     * encontrado um telefone ele retornará null.
     */
    public List<Telefone> consultarTelefonesdoCliente(Cliente umCliente) {
        Connection connection = ConexaoBD.conexao();
        ResultSet resultSet;
        final String CONSULTAR_TODOS_TELEFONES = "SELECT Telefone.idTelefone,Telefone.Telefone FROM Telefone\n"
                + "INNER JOIN Telefone_Cliente ON Telefone.idTelefone = Telefone_Cliente.idTelefone\n"
                + "INNER JOIN Cliente ON Telefone_Cliente.idCliente = Cliente.idCliente\n"
                + "WHERE Cliente.idCliente = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_TELEFONES);
            preparedStatement.setInt(1, umCliente.getIdCliente());
            resultSet = preparedStatement.executeQuery();
            connection.commit();

            List<Telefone> telefonesDoCliente = new ArrayList<Telefone>();

            while (resultSet.next()) {
                Telefone telefone = new Telefone();
                telefone.setIdTelefone(resultSet.getInt("idTelefone"));
                telefone.setTelefone(resultSet.getInt("telefone"));

                telefonesDoCliente.add(telefone);

            }
            return telefonesDoCliente;
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return null;
    }

    /**
     * Método responsável por consultar os telefones com base em um fornecedor.
     *
     * @param umFornecedor representa um objeto da classe Fornecedor.
     * @return uma lista com todos os telefones. Caso não exista um telefone ele
     * retornará null.
     */
    public List<Telefone> consultarTelefonesdoFornecedor(Fornecedor umFornecedor) {
        Connection connection = ConexaoBD.conexao();
        final String CONSULTAR_TODOS_TELEFONES = "SELECT Telefone.idTelefone,Telefone.Telefone FROM Telefone\n"
                + "INNER JOIN Fornecedor_Telefone ON Telefone.idTelefone = Fornecedor_Telefone.idTelefone\n"
                + "INNER JOIN Fornecedor ON Fornecedor_Telefone.idFornecedor = Fornecedor.idFornecedor\n"
                + "WHERE Fornecedor.idFornecedor = ?;";
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_TELEFONES);
            preparedStatement.setInt(1, umFornecedor.getIdFornecedor());
            resultSet = preparedStatement.executeQuery();
            List<Telefone> telefonesDoFornecedor = new ArrayList<Telefone>();

            while (resultSet.next()) {
                Telefone telefone = new Telefone();
                telefone.setIdTelefone(resultSet.getInt("idTelefone"));
                telefone.setTelefone(resultSet.getInt("telefone"));

                telefonesDoFornecedor.add(telefone);
            }

            return telefonesDoFornecedor;
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null;

    }

    /**
     * Método responsável por consultar todos os telefones cadastrados no banco
     * de dados.
     *
     * @return uma lista com todos os telefones existentes.
     */
    public List<Telefone> consultarTodosOsTelefones() {
        final String CONSULTAR_TODOS_OS_TELEFONES = "SELECT * FROM Telefone;";
        Connection connection = ConexaoBD.conexao();
        List<Telefone> resultados = new ArrayList();
        ResultSet resultset;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_OS_TELEFONES);
            resultset = preparedStatement.executeQuery();
            connection.commit();

            while (resultset.next()) {
                Telefone telefone = new Telefone();

                telefone.setIdTelefone(resultset.getInt("idTelefone"));
                telefone.setTelefone(resultset.getInt("telefone"));

                resultados.add(telefone);
            }

        } catch (SQLException e) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return resultados;
    }

    /**
     * Método responsável pelo preenchimento da combobox
     *
     * @param idCliente
     * @return
     */
    public int consultarporIdParaAtualizarTelefone(int idCliente) {
        Connection connection = ConexaoBD.conexao();
        //List<Cliente> id_cliente_resultados = new ArrayList();
        ResultSet ResultSet;

        final String CONSULTAR_CLIENTE_POR_ID_TELEFONE = "SELECT  Cliente.idCliente,Cliente.nome, Telefone.Telefone,Cliente.dataNascimento, Cliente.email, Cliente.status,  Endereco.idEndereco,Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "Endereco.numero , Telefone_Cliente.idTelefone FROM Telefone\n"
                + "INNER JOIN Telefone_Cliente ON Telefone.idTelefone = Telefone_Cliente.idTelefone\n"
                + "INNER JOIN Cliente ON Telefone_Cliente.idCliente = Cliente.idCliente\n"
                + "INNER JOIN Endereco ON Cliente.idEndereco = Endereco.idEndereco\n"
                + "WHERE Cliente.idCliente =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_CLIENTE_POR_ID_TELEFONE);
            preparedStatement.setInt(1, idCliente);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                int idTelefone = ResultSet.getInt(13);
                return idTelefone;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return -1;
    }

    /**
     * Método responsável por consultar o telefone do fornecedor com base no id
     * dele.
     *
     * @param idFornecedor representa o id do fornecedor.
     * @return o id do telefone, caso não seja encontrado um próximo resultado
     * ele irá retornar -1.
     */
    public int consultarporIdParaAtualizarTelefoneDoFornecedor(int idFornecedor) {
        Connection connection = ConexaoBD.conexao();
        ResultSet ResultSet;

        final String CONSULTAR_FORNECEDOR_POR_ID_TELEFONE = "SELECT  Fornecedor.idFornecedor,Fornecedor.nome, Telefone.Telefone, Fornecedor.email, Endereco.idEndereco,Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "    Endereco.numero , Fornecedor_Telefone.idTelefone FROM Telefone\n"
                + "    INNER JOIN Fornecedor_Telefone ON Telefone.idTelefone = Fornecedor_Telefone.idTelefone\n"
                + " INNER JOIN Fornecedor ON Fornecedor_Telefone.idFornecedor = Fornecedor.idFornecedor\n"
                + "              INNER JOIN Endereco ON Fornecedor.idEndereco = Endereco.idEndereco\n"
                + "          WHERE Fornecedor.idFornecedor = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_FORNECEDOR_POR_ID_TELEFONE);
            preparedStatement.setInt(1, idFornecedor);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                int idTelefone = ResultSet.getInt(11);
                System.out.println(idTelefone);
                return idTelefone;

            }

        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return -1;
    }

    /**
     * Método responsável por alterar um telefone
     *
     * @param umTelefone representa um objeto da classe
     * @link Telefone
     */
    public void alterarUmTelefone(Telefone umTelefone) {
        Connection connection = ConexaoBD.conexao();
        final String alterar = "UPDATE Telefone SET telefone=? where idTelefone=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(alterar);
            preparedStatement.setInt(1, umTelefone.getTelefone());
            preparedStatement.setInt(2, umTelefone.getIdTelefone());
            preparedStatement.executeUpdate();

            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);

            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }

    /**
     * Método responsável por remover um telefone
     *
     * @param umTelefone representa um objeto da classe
     * @link Telefone
     *
     */
    public void removerUmTelefone(Telefone umTelefone) {
        try {
            PreparedStatement preparedStatement = ConexaoBD.conexao().prepareStatement("DELETE FROM Telefone WHERE idTelefone=?;");
            preparedStatement.setInt(1, umTelefone.getIdTelefone());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException ex) {

        }

    }

}
