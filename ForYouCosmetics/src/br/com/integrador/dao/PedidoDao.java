/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Pedido;
import java.sql.Connection;
import java.sql.Date;
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
 * @author 20121164010406
 */
public class PedidoDao {

    /**
     * Método responsável por cadastrar um pedido no banco de dados.
     *
     * @param umPedido representa um objeto da classe
     * @link Pedido.
     * @return retorna -1 caso não seja encontrado outro pedido.
     */
    public int cadastrarUmPedido(Pedido umPedido) {
        final String INSERIR_PEDIDO = "INSERT INTO Pedido(valorPedidoTotal, dataPedido, idCliente) VALUES (?, ?, ?);";
        Connection connection = ConexaoBD.conexao();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_PEDIDO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, umPedido.getValorPedidoTotal());
            preparedStatement.setDate(2, (Date) umPedido.getDataPedido());
            preparedStatement.setInt(3, umPedido.getCliente().getIdCliente());
            preparedStatement.executeUpdate();
            connection.commit();

            ResultSet chavesGeradas = preparedStatement.getGeneratedKeys();
            if (chavesGeradas.next()) {
                return chavesGeradas.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return -1;
    }

    /**
     * Método responsável pela consulta de todos os pedidos cadastrados no
     * banco.
     *
     * @return uma lista com todos os pedidos cadastrados no banco. Se não
     * houver um próximo pedido ele retorna nulo.
     */
    public List<Pedido> consultarTodosOsPedido() {
        final String CONSULTAR_TODOS_PEDIDOS = "SELECT * FROM Pedido";
        Connection connection = ConexaoBD.conexao();
        List<Pedido> resultados_consulta_pedidos = new ArrayList<>();
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_PEDIDOS);
            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                Cliente cliente = new Cliente();

                pedido.setIdPedido(resultSet.getInt("idPedido"));
                pedido.setValorPedidoTotal(resultSet.getFloat("valorPedidoTotal"));
                pedido.setDataPedido(resultSet.getDate("dataPedido"));
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                pedido.setCliente(cliente);

                resultados_consulta_pedidos.add(pedido);
            }
            return resultados_consulta_pedidos;

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return null;
    }

    /**
     * Método responsável pela consulta de um pedido com base no id informado.
     *
     * @param idPedido representa um objeto da classe Pedido.
     * @return uma lista com os parâmetros de pedido. Caso não seja encontrado
     * um próximo pedido ele retornará null.
     */
    public List<Pedido> consultarPedidoPorId(int idPedido) {
        final String CONSULTAR_PEDIDO = "SELECT Pedido.idPedido, Cliente.idCliente, Cliente.nome , Pedido.valorPedidoTotal, Pedido.dataPedido FROM Pedido\n"
                + "INNER JOIN Cliente ON Pedido.idCliente = Cliente.idCliente \n"
                + "WHERE Pedido.idPedido = ?;";
        Connection connection = ConexaoBD.conexao();
        List<Pedido> resultados_consultar_id = new ArrayList<>();
        ResultSet ResultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PEDIDO);
            preparedStatement.setInt(1, idPedido);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {
                Pedido pedido = new Pedido();
                Cliente cliente = new Cliente();

                pedido.setIdPedido(ResultSet.getInt("idPedido"));
                pedido.setValorPedidoTotal(ResultSet.getFloat("valorPedidoTotal"));
                pedido.setDataPedido(ResultSet.getDate("dataPedido"));
                cliente.setIdCliente(ResultSet.getInt("idCliente"));
                cliente.setNome(ResultSet.getString("nome"));
                pedido.setCliente(cliente);

                resultados_consultar_id.add(pedido);

            }
            return resultados_consultar_id;

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return null;

    }

    /**
     * Método responsável pela consulta de todos os pedidos de um cliente com
     * base em seu ID.
     *
     * @param umCliente representa um objeto da classe Cliente.
     * @return retorna uma lista com todos os pedidos de um cliente. Caso não
     * seja encontrado um próximo pedido ele retornará null.
     */
    public List<Pedido> consultarTodosOsPedidosDeUmCliente(Cliente umCliente) {
        final String CONSULTAR_PEDIDOS_DO_CLIENTE = "SELECT * FROM Pedido WHERE idCliente = ?;";
        Connection connection = ConexaoBD.conexao();
        List<Pedido> resultados_pedidos_cliente = new ArrayList<>();
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PEDIDOS_DO_CLIENTE);
            preparedStatement.setInt(1, umCliente.getIdCliente());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                Cliente cliente = new Cliente();

                pedido.setIdPedido(resultSet.getInt("idPedido"));
                pedido.setValorPedidoTotal(resultSet.getFloat("valorPedidoTotal"));
                pedido.setDataPedido(resultSet.getDate("dataPedido"));
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                pedido.setCliente(cliente);

                resultados_pedidos_cliente.add(pedido);

            }
            return resultados_pedidos_cliente;

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return null;

    }

    /**
     * Método responsável pela consulta de todos os pedidos de um cliente com
     * base em seu ID.
     *
     * @param idDoCliente representa um objeto da classe Cliente.
     * @return retorna uma lista com todos os pedidos de um cliente. Caso não
     * seja encontrado um próximo pedido ele retornará null.
     */
    public List<Pedido> consultarPedidosPorCliente(int idDoCliente) {
        final String CONSULTAR_PEDIDOS_CLIENTE = "SELECT * FROM Pedido WHERE idCliente = ?;";
        Connection connection = ConexaoBD.conexao();
        List<Pedido> resultados_consultar_pedidos_clientes = new ArrayList<>();
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PEDIDOS_CLIENTE);
            preparedStatement.setInt(1, idDoCliente);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                Cliente cliente = new Cliente();

                pedido.setIdPedido(resultSet.getInt("idPedido"));
                pedido.setValorPedidoTotal(resultSet.getFloat("valorPedidoTotal"));
                pedido.setDataPedido(resultSet.getDate("dataPedido"));
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                pedido.setCliente(cliente);
                resultados_consultar_pedidos_clientes.add(pedido);

            }
            return resultados_consultar_pedidos_clientes;

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return null;

    }

    /**
     * Método responsável pela remoção de um pedido.
     *
     * @param umPedido objeto da classe
     * @link Pedido.
     * @throws SQLException
     */
    public void removerUmPedido(int umPedido) throws SQLException {
        PreparedStatement preparedStatement = ConexaoBD.conexao().prepareStatement("DELETE FROM Pedido WHERE idPedido=?;");
        preparedStatement.setInt(1, umPedido);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public List<Pedido> consultarPedidoPorNomeCliente(String nomeCliente) {
        final String CONSULTAR_PEDIDO_PELO_NOME = "SELECT Pedido.idPedido, Cliente.idCliente, Cliente.nome , Pedido.valorPedidoTotal, Pedido.dataPedido FROM Pedido\n"
                + "INNER JOIN Cliente ON Pedido.idCliente = Cliente.idCliente \n"
                + "WHERE Cliente.nome LIKE ?;";
        Connection connection = ConexaoBD.conexao();
        List<Pedido> resultados_consultar_por_nome = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PEDIDO_PELO_NOME);
            preparedStatement.setString(1, nomeCliente + "%");
            ResultSet ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                Pedido pedido = new Pedido();
                Cliente cliente = new Cliente();

                pedido.setIdPedido(ResultSet.getInt("idPedido"));
                cliente.setIdCliente(ResultSet.getInt("idCliente"));
                cliente.setNome(ResultSet.getString("nome"));
                pedido.setValorPedidoTotal(ResultSet.getFloat("valorPedidoTotal"));
                pedido.setDataPedido(ResultSet.getDate("dataPedido"));

                pedido.setCliente(cliente);

                resultados_consultar_por_nome.add(pedido);

            }
            return resultados_consultar_por_nome;

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return null;

    }

}
