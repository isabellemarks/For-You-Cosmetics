/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.Fornecedor;
import br.com.integrador.modelos.Produto;
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
public class ProdutoDao {

    /**
     * Método responsável pelo cadastro de um produto no banco de dados.
     *
     * @param umProduto representa um objeto da classe Produto
     * @return -1 caso não seja encontrado um próximo produto a ser cadastrado.
     */
    public int cadastrarUmProduto(Produto umProduto) {
        final String INSERIR_PEDIDO = "INSERT INTO Produto(nomeProduto, descricao, precoUnitario, status, idFornecedor) VALUES (?, ?, ?, ?, ?);";
        Connection connection = ConexaoBD.conexao();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_PEDIDO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, umProduto.getNomeProduto());
            preparedStatement.setString(2, umProduto.getDescricao());
            preparedStatement.setFloat(3, umProduto.getPrecoUnitario());
            preparedStatement.setInt(4, umProduto.getStatus());
            preparedStatement.setInt(5, umProduto.getFornecedor().getIdFornecedor());
            preparedStatement.executeUpdate();
            connection.commit();

            ResultSet chavesGeradas = preparedStatement.getGeneratedKeys();
            if (chavesGeradas.next()) {
                return chavesGeradas.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return -1;
    }

    /**
     * Método responsável pela consulta de todos os produtos cadastrados no
     * banco de dados.
     *
     * @return uma lista com todos os produtos e suas respectivas informações.
     */
    public List<Produto> consultarTodosOsProdutos() {
        final String CONSULTAR_TODOS_PRODUTOS = "SELECT * FROM Produto";
        Connection connection = ConexaoBD.conexao();
        List<Produto> produtos_resultados = new ArrayList();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_PRODUTOS);
            ResultSet ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {
                Produto produto = new Produto();
                Fornecedor fornecedor = new Fornecedor();

                produto.setIdProduto(ResultSet.getInt("idProduto"));
                produto.setNomeProduto(ResultSet.getString("nomeProduto"));
                produto.setDescricao(ResultSet.getString("descricao"));
                produto.setPrecoUnitario(ResultSet.getLong("precoUnitario"));
                produto.setStatus(ResultSet.getInt("status"));
                fornecedor.setIdFornecedor(ResultSet.getInt("idFornecedor"));
                produto.setFornecedor(fornecedor);

                produtos_resultados.add(produto);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return produtos_resultados;

    }

    /**
     * Método responsável pela consulta de um produto com base no id informado.
     *
     * @param idProduto representa um objeto da classe Produto
     * @return uma lista com as informações do produto consultado.
     */
    public Produto consultaProdutoPorId(int idProduto) {
        final String CONSULTAR_PEDIDO = "SELECT * FROM Produto WHERE idProduto = ?;";
        Connection connection = ConexaoBD.conexao();
        // List<Produto> resultados_consulta_id = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PEDIDO);
            preparedStatement.setInt(1, idProduto);
            ResultSet ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {
                Produto produto = new Produto();
                Fornecedor fornecedor = new Fornecedor();

                produto.setIdProduto(ResultSet.getInt("idProduto"));
                produto.setNomeProduto(ResultSet.getString("nomeProduto"));
                produto.setDescricao(ResultSet.getString("descricao"));
                produto.setPrecoUnitario(ResultSet.getLong("precoUnitario"));
                fornecedor.setIdFornecedor(ResultSet.getInt("idFornecedor"));
                produto.setFornecedor(fornecedor);

                //resultados_consulta_id.add(produto);
                return produto;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return null;
    }

    /**
     * Método responsável pela consulta de um produto com base em seu nome.
     *
     * @param umProduto representa o nome do produto.
     * @return uma lista com todas as informações. Caso não seja encontrado ele
     * retorna null.
     */
    public List<Produto> consultarProdutosPorNome(String umProduto) {
        final String CONSULTAR_POR_NOME = "SELECT Produto.nomeProduto,Produto.idProduto, Produto.descricao, Produto.precoUnitario, Fornecedor.idFornecedor ,Fornecedor.nome  FROM Produto\n"
                + "INNER JOIN Fornecedor ON Produto.idFornecedor = Fornecedor.idFornecedor\n"
                + "WHERE Produto.nomeProduto LIKE ?;";
        Connection connection = ConexaoBD.conexao();
        List<Produto> resultados_produtos_nome = new ArrayList<>();

        ResultSet ResultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_POR_NOME);
            preparedStatement.setString(1, umProduto + "%");
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {
                Produto produto = new Produto();
                Fornecedor fornecedor = new Fornecedor();

                produto.setNomeProduto(ResultSet.getString("nomeProduto"));
                produto.setIdProduto(ResultSet.getInt("idProduto"));
                produto.setDescricao(ResultSet.getString("descricao"));
                produto.setPrecoUnitario(ResultSet.getLong("precoUnitario"));
                fornecedor.setIdFornecedor(ResultSet.getInt("idFornecedor"));
                fornecedor.setNome(ResultSet.getString("nome"));
                produto.setFornecedor(fornecedor);

                resultados_produtos_nome.add(produto);
                System.out.println(produto);

            }
            return resultados_produtos_nome;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

            return null;

        }

    }

    /**
     * Método responsável pela consulta dos produtos com base em seu fornecedor.
     *
     * @param idDoFornecedor representa um objeto da classe Fornecedor, o seu
     * id.
     * @return uma lista com todos os produtos de cada fornecedor. Caso não seja
     * encontrada ele retornará nulo.
     */
    public List<Produto> consultarProdutosPorFornecedor(int idDoFornecedor) {
        final String CONSULTAR_TODOS_PRODUTOS_FORNECEDOR = "SELECT  Produto.idProduto, Produto.nomeProduto, Produto.descricao, Produto.precoUnitario, Fornecedor.idFornecedor ,Fornecedor.nome  FROM Produto\n"
                + "INNER JOIN Fornecedor ON Produto.idFornecedor = Fornecedor.idFornecedor\n"
                + "WHERE Produto.idProduto = ? ;";

        Connection connection = ConexaoBD.conexao();
        List<Produto> resultados_produtos_fornecedor = new ArrayList<>();
        ResultSet resultset;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_PRODUTOS_FORNECEDOR);
            preparedStatement.setInt(1, idDoFornecedor);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                Produto produto = new Produto();
                Fornecedor fornecedor = new Fornecedor();

                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setNomeProduto(resultSet.getString("nomeProduto"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPrecoUnitario(resultSet.getLong("precoUnitario"));
                fornecedor.setIdFornecedor(resultSet.getInt("idFornecedor"));
                fornecedor.setNome(resultSet.getString("nome"));

                produto.setFornecedor(fornecedor);

            }
            return resultados_produtos_fornecedor;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return null;

    }

    /**
     * Método responsável por inativar um produto, ou seja, deixar o produto
     * inútel.
     *
     * @param umProduto representa um objeto da classe Cliente
     */
    public void inativarUmProduto(int umProduto) {
        Connection connection = ConexaoBD.conexao();
        final String INATIVAR_CLIENTE = "UPDATE Produto SET status = 0 WHERE idProduto = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INATIVAR_CLIENTE);
            preparedStatement.setInt(1, umProduto);
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
     * Método responsável pela ativação de um produto.
     *
     * @param umProduto representa um objeto do tipo produto
     */
    public void ativarUmProduto(Produto umProduto) {
        Connection connection = ConexaoBD.conexao();
        final String ATIVAR_CLIENTE = "UPDATE Produto SET status = 1 WHERE idCliente = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ATIVAR_CLIENTE);
            preparedStatement.setInt(1, umProduto.getIdProduto());
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

    public void removerUmProduto(Produto umProduto) throws SQLException {
        PreparedStatement preparedStatement = ConexaoBD.conexao().prepareStatement("DELETE FROM Produto WHERE idProduto=?;");
        preparedStatement.setInt(1, umProduto.getIdProduto());
        preparedStatement.execute();
        preparedStatement.close();
    }

    /**
     * Método responsável por preencher a combobox
     *
     * @param umProduto representa um objeto do tipo produto.
     * @return
     */
    public int preencheComboBox(Produto umProduto) {
        final String CONSULTAR_PRODUTOS_PARA_COMBOBOX = "SELECT idProduto FROM Produto WHERE nomeProduto='" + umProduto.getNomeProduto() + "';";
        Connection connection = ConexaoBD.conexao();
        ResultSet resultSet;
        int id = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PRODUTOS_PARA_COMBOBOX);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return -1;

    }

}
