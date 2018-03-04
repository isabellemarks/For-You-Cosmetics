/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.Endereco;
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
public class FornecedorDao {

    public FornecedorDao() {
    }

    /**
     * Método responsável pelo cadastro de um fornecedor no banco de dados.
     *
     * @param umFornecedor representa um fornecedor
     * @return -1 se não houver um próximo fornecedor.
     */
    public int cadastrarUmFornecedor(Fornecedor umFornecedor) {
        Connection connection = ConexaoBD.conexao();
        final String INSERIR_FORNECEDOR = "INSERT INTO Fornecedor(nome, email, status, idEndereco) VALUES (?, ?, ?, ?);";
        final String INSERIR_FORNECEDOR_TELEFONE = "INSERT INTO Fornecedor_Telefone(idFornecedor, idTelefone) VALUES (?,?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_FORNECEDOR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, umFornecedor.getNome());
            preparedStatement.setString(2, umFornecedor.getEmail());
            preparedStatement.setInt(3, umFornecedor.getStatus());
            preparedStatement.setInt(4, umFornecedor.getEndereco().getIdEndereco());

            preparedStatement.executeUpdate();

            ResultSet chavesGeradas = preparedStatement.getGeneratedKeys();
            if (chavesGeradas.next()) {
                int idGerado = chavesGeradas.getInt(1);
                List<Telefone> telefonesDoFornecedor = umFornecedor.getTelefones();

                if (!telefonesDoFornecedor.isEmpty()) {
                    PreparedStatement preparedStatementRelacionamento = ConexaoBD.conexao().prepareStatement(INSERIR_FORNECEDOR_TELEFONE);
                    for (Telefone fone : telefonesDoFornecedor) {
                        preparedStatementRelacionamento.setInt(1, idGerado);
                        preparedStatementRelacionamento.setInt(2, fone.getIdTelefone());

                        preparedStatementRelacionamento.executeUpdate();
                        connection.commit();
                    }
                }
                return idGerado;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return -1;

    }

    /**
     * Método responsável pela alteração dos dados de um fornecedor.
     *
     * @param umFornecedor representa um objeto da classe fornecedor
     */
    public void alterarUmFornecedor(Fornecedor umFornecedor) {

        final String ALTERAR_FORNECEDOR = "UPDATE Fornecedor SET nome=?,email=? WHERE idFornecedor=?;";
        Connection connection = ConexaoBD.conexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ALTERAR_FORNECEDOR);
            preparedStatement.setString(1, umFornecedor.getNome());
            preparedStatement.setString(2, umFornecedor.getEmail());
            preparedStatement.setInt(3, umFornecedor.getIdFornecedor());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }

    /**
     * Método responsável pela consulta de todos os fornecedores cadastrados no
     * banco de dados.
     *
     * @return uma lista com todas as informações relacionadas aos fornecedores
     * cadastrados.
     */
    public List<Fornecedor> consultarTodosOsFornecedores() {
        final String CONSULTAR_TODOS_OS_FORNECEDORES = "SELECT * FROM Fornecedor;";
        Connection connection = ConexaoBD.conexao();
        List<Fornecedor> resultados = new ArrayList();
        ResultSet resultset;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_OS_FORNECEDORES);
            resultset = preparedStatement.executeQuery();
            connection.commit();

            while (resultset.next()) {
                Fornecedor fornecedor = new Fornecedor();
                Endereco endereco = new Endereco();

                fornecedor.setIdFornecedor(resultset.getInt("idFornecedor"));
                fornecedor.setNome(resultset.getString("nome"));
                fornecedor.setEmail(resultset.getString("email"));
                endereco.setIdEndereco(resultset.getInt("idEndereco"));
                fornecedor.setEndereco(endereco);
                resultados.add(fornecedor);
            }

        } catch (SQLException e) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return resultados;
    }

    /**
     * Método responsável pela consulta de um fornecedor com base em um ID
     * informado.
     *
     * @param idFornecedor representa um objeto da classe Fornecedor
     * @return uma lista com as informações referentes ao fornecedor pesquisado.
     */
    public Fornecedor consultarFornecedorPorId(int idFornecedor) {
        final String CONSULTAR_FORNECEDOR = "SELECT Fornecedor.idFornecedor ,Fornecedor.nome, Telefone.Telefone,Fornecedor.email,  Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "                Endereco.numero FROM Telefone\n"
                + "                INNER JOIN Fornecedor_Telefone ON Telefone.idTelefone = Fornecedor_Telefone.idTelefone\n"
                + "			INNER JOIN Fornecedor ON Fornecedor_Telefone.idFornecedor = Fornecedor.idFornecedor\n"
                + "                INNER JOIN Endereco ON Fornecedor.idEndereco = Endereco.idEndereco\n"
                + "                WHERE Fornecedor.idFornecedor = ? ;";
        Connection connection = ConexaoBD.conexao();
        List<Fornecedor> resultados_fornecedor_id = new ArrayList();
        ResultSet resultset;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_FORNECEDOR);
            preparedStatement.setInt(1, idFornecedor);
            resultset = preparedStatement.executeQuery();
            connection.commit();

            while (resultset.next()) {

                Fornecedor fornecedor = new Fornecedor();
                Endereco endereco = new Endereco();
                Telefone telefone = new Telefone();
                List<Telefone> telefones = new ArrayList();

                fornecedor.setIdFornecedor(resultset.getInt(1));
                fornecedor.setNome(resultset.getString(2));
                telefone.setTelefone(resultset.getInt(3));

                fornecedor.setEmail(resultset.getString(4));
                //fornecedor.setStatus(resultset.getInt(5));

                //endereco.setIdEndereco(resultset.getInt("idEndereco"));
                endereco.setRua(resultset.getString(5));
                endereco.setBairro(resultset.getString(6));
                endereco.setCep(resultset.getInt(7));
                endereco.setComplemento(resultset.getString(8));
                endereco.setNumero(resultset.getString(9));
                fornecedor.setEndereco(endereco);
                telefones.add(telefone);

                fornecedor.setEndereco(endereco);

                fornecedor.setTelefones(telefones);

                return fornecedor;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return null;

    }

    /**
     * Método responsável pela consulta de um fornecedor com base em seu nome.
     *
     * @param umFornecedor representa um objeto da classe fornecedor, ou seja, o
     * fornecedor.
     * @return retorna uma lista com as informações referidas.
     */
    public List<Fornecedor> consultarFornecedorPorNome(String umFornecedor) {
        Connection connection = ConexaoBD.conexao();
        final String CONSULTAR_FORNECEDORES = "SELECT Fornecedor.idFornecedor, Fornecedor.nome, Fornecedor.email, Fornecedor.status, Endereco.idEndereco, Endereco.rua, Endereco.bairro, Endereco.cep,\n"
                + "Endereco.complemento, Endereco.numero , Telefone.telefone FROM INTEGRADOR_VC.Fornecedor\n"
                + "INNER JOIN Endereco ON Fornecedor.idEndereco = Endereco.idEndereco\n"
                + "INNER JOIN Fornecedor_Telefone ON Fornecedor.idFornecedor = Fornecedor_Telefone.idFornecedor\n"
                + "INNER JOIN Telefone ON Fornecedor_Telefone.idTelefone = Telefone.idTelefone\n"
                + " WHERE nome LIKE ?;";
        List<Fornecedor> resultados_fornecedores_nome = new ArrayList();
        ResultSet resultset;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_FORNECEDORES);
            preparedStatement.setString(1, umFornecedor + "%");
            resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(resultset.getInt(1));

                fornecedor.setNome(resultset.getString(2));
                fornecedor.setEmail(resultset.getString(3));
                fornecedor.setStatus(resultset.getInt(4));

                Endereco endereco = new Endereco();
                endereco.setIdEndereco(resultset.getInt(5));
                endereco.setRua(resultset.getString(6));
                endereco.setBairro(resultset.getString(7));
                endereco.setCep(resultset.getInt(8));
                endereco.setComplemento(resultset.getString(9));
                endereco.setNumero(resultset.getString(10));
                Telefone telefone = new Telefone();
                List<Telefone> resultados = new ArrayList();
                telefone.setTelefone(resultset.getInt(11));

                fornecedor.setEndereco(endereco);
                resultados.add(telefone);
                fornecedor.setTelefones(resultados);

                resultados_fornecedores_nome.add(fornecedor);
                System.out.println(fornecedor);
            }
            return resultados_fornecedores_nome;

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return null;
    }

    /**
     * Método responsável por consultar o endereço do fornecedor com base no id
     * fornecido.
     *
     * @param idFornecedor representa o id fornecido.
     * @return o id do fornecedor, caso não exista um próximo ele irá retornar
     * -1.
     */
    public int consultarporIdParaAtualizarEnderecoDoFornecedor(int idFornecedor) {
        Connection connection = ConexaoBD.conexao();
        //List<Cliente> id_cliente_resultados = new ArrayList();
        ResultSet ResultSet;

        final String CONSULTAR_FORNECEDOR_POR_ID = "SELECT  Fornecedor.idFornecedor,Fornecedor.nome, Telefone.Telefone, Fornecedor.email, Endereco.idEndereco,Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "    Endereco.numero , Fornecedor_Telefone.idTelefone FROM Telefone\n"
                + "    INNER JOIN Fornecedor_Telefone ON Telefone.idTelefone = Fornecedor_Telefone.idTelefone\n"
                + " INNER JOIN Fornecedor ON Fornecedor_Telefone.idFornecedor = Fornecedor.idFornecedor\n"
                + "              INNER JOIN Endereco ON Fornecedor.idEndereco = Endereco.idEndereco\n"
                + "          WHERE Fornecedor.idFornecedor = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_FORNECEDOR_POR_ID);
            preparedStatement.setInt(1, idFornecedor);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                int idEndereco = ResultSet.getInt(5);
                return idEndereco;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return -1;
    }

    /**
     * Método responsável por consultar o fornecedor com base no id
     * informado.
     *
     * @param idFornecedores
     * @return
     */
    public int consultarporIdParaAtualizarFornecedor(int idFornecedores) {
        Connection connection = ConexaoBD.conexao();
        ResultSet ResultSet;

        final String CONSULTAR_FORNECEDOR_POR_ID = "SELECT  Fornecedor.idFornecedor,Fornecedor.nome, Telefone.Telefone, Fornecedor.email, Endereco.idEndereco,Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "    Endereco.numero , Fornecedor_Telefone.idTelefone FROM Telefone\n"
                + "    INNER JOIN Fornecedor_Telefone ON Telefone.idTelefone = Fornecedor_Telefone.idTelefone\n"
                + " INNER JOIN Fornecedor ON Fornecedor_Telefone.idFornecedor = Fornecedor.idFornecedor\n"
                + "              INNER JOIN Endereco ON Fornecedor.idEndereco = Endereco.idEndereco\n"
                + "          WHERE Fornecedor.idFornecedor = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_FORNECEDOR_POR_ID);
            preparedStatement.setInt(1, idFornecedores);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                int idFornecedor = ResultSet.getInt(1);
                return idFornecedor;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return -1;

    }

    /**
     * Método responsável por inativar um fornecedor, ou seja, deixar o
     * fornecedor inútel.
     *
     * @param umFornecedor representa um objeto da classe Fornecedor
     */
    public void inativarFornecedor(int umFornecedor) {
        Connection connection = ConexaoBD.conexao();
        final String INATIVAR_CLIENTE = "UPDATE Fornecedor SET status = 0 WHERE idFornecedor = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INATIVAR_CLIENTE);
            preparedStatement.setInt(1, umFornecedor);
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
     * Método responsável pela ativação de um fornecedor.
     *
     * @param umFornecedor representa um objeto do tipo Fornecedor
     */
    public void ativarFornecedor(int umFornecedor) {
        Connection connection = ConexaoBD.conexao();
        final String INATIVAR_CLIENTE = "UPDATE Fornecedor SET status = 1 WHERE idFornecedor = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INATIVAR_CLIENTE);
            preparedStatement.setInt(1, umFornecedor);
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
     * Método responsável pela remoção de um Fornecedor.
     *
     * @param umFornecedor que representa um objeto do tipo
     * @link Fornecedor.
     */
    public void removerUmCliente(Fornecedor umFornecedor) {
        Connection connection = ConexaoBD.conexao();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Fornecedor WHERE idFornecedor=?;");
            preparedStatement.setString(1, umFornecedor.getNome());
            preparedStatement.execute();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }

    /**
     * Método responsável pelo preenchimento da combobox
     *
     * @param meuFornecedor representa um fornecedor.
     * @return 
     */
    public int preencheComboBox(Fornecedor meuFornecedor) {
        final String CONSULTAR_FORNECEDORES_PARA_COMBOBOX = "SELECT idFornecedor FROM Fornecedor WHERE nome='" + meuFornecedor.getNome() + "';";
        Connection conn = ConexaoBD.conexao();
        ResultSet resultSet;
        int id = 0;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(CONSULTAR_FORNECEDORES_PARA_COMBOBOX);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

}
