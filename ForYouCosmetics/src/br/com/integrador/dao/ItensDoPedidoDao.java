package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.ItensdoPedido;
import br.com.integrador.modelos.Pedido;
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
 * @author 20121164010406
 */
public class ItensDoPedidoDao {

    /**
     * Método responsável pelo cadastro dos itens do pedido de um determinado
     * pedido.
     *
     * @param umItensDoPedido representa um objeto da classe ItensDoPedido.
     * @return -1 caso não for encontrado um próximo iten do pedido.
     */
    public int cadastrarItensDoPedido(ItensdoPedido umItensDoPedido) {
        Connection connection = ConexaoBD.conexao();
        final String INSERIR_ITENSDOPEDIDO = "INSERT INTO ItensDoPedido(quantidade, idProduto, idPedido) VALUES(?, ?, ?);";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_ITENSDOPEDIDO, Statement.RETURN_GENERATED_KEYS);
            System.out.println(" aqui: " + umItensDoPedido.getQuantidade() + " | " + umItensDoPedido.getProduto().getIdProduto() + " | " + umItensDoPedido.getPedido().getIdPedido());
            preparedStatement.setInt(1, umItensDoPedido.getQuantidade());
            preparedStatement.setInt(2, umItensDoPedido.getProduto().getIdProduto());
            preparedStatement.setInt(3, umItensDoPedido.getPedido().getIdPedido());
            preparedStatement.executeUpdate();
            connection.commit();

            ResultSet chaveGerada = preparedStatement.getGeneratedKeys();
            if (chaveGerada.next()) {
                return chaveGerada.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItensDoPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ItensDoPedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return -1;
    }

    /**
     * Método responsável pela consulta dos itens dos pedidos cadastrados no
     * banco de dados.
     *
     * @return uma lista com todos itens cadastrados. Caso não seja encontrado
     * um próximo pedido ele retornará null.
     */
    public List<ItensdoPedido> consultarItensDoPedido() {

        Connection connection = ConexaoBD.conexao();
        List<ItensdoPedido> resultados_todos_itens = new ArrayList();
        final String CONSULTAR_TODOS_ITENS_DO_PEDIDO = "SELECT * FROM ItensDoPedido";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_ITENS_DO_PEDIDO);
            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                ItensdoPedido itensdoPedido = new ItensdoPedido();
                Produto produto = new Produto();
                Pedido pedido = new Pedido();

                itensdoPedido.setIdItensDoPedido(resultSet.getInt("idItensDoPedido"));
                itensdoPedido.setQuantidade(resultSet.getInt("quantidade"));
                produto.setIdProduto(resultSet.getInt("idProduto"));
                itensdoPedido.setProduto(produto);
                pedido.setIdPedido(resultSet.getInt("idPedido"));
                itensdoPedido.setPedido(pedido);

                resultados_todos_itens.add(itensdoPedido);

            }
            return resultados_todos_itens;

        } catch (SQLException ex) {
            Logger.getLogger(ItensDoPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ItensDoPedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return null;

    }

    /**
     * Método responsável por consultar os itens do pedido com base no id
     * informado.
     *
     * @param idDoPedido um inteiro que representa o id de um pedido
     * @return Retorna um objeto pedido. Caso não seja encontrado um próximo
     * pedido ele retornará null.
     */
    public List<ItensdoPedido> consultarItensDoPedidoPorId(int idDoPedido) {
        Connection connection = ConexaoBD.conexao();
        List<ItensdoPedido> resultados_itens_por_id = new ArrayList();
        final String CONSULTAR_PEDIDO = "SELECT * FROM ItensDoPedido WHERE idItensDoPedido = ?;";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_PEDIDO);
            preparedStatement.setInt(1, idDoPedido);
            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                ItensdoPedido itensdoPedido = new ItensdoPedido();
                Produto produto = new Produto();
                Pedido pedido = new Pedido();

                itensdoPedido.setIdItensDoPedido(resultSet.getInt("idItensDoPedido"));
                itensdoPedido.setQuantidade(resultSet.getInt("quantidade"));
                produto.setIdProduto(resultSet.getInt("idProduto"));
                itensdoPedido.setProduto(produto);
                pedido.setIdPedido(resultSet.getInt("idPedido"));
                itensdoPedido.setPedido(pedido);

                resultados_itens_por_id.add(itensdoPedido);
            }
            return resultados_itens_por_id;

        } catch (SQLException ex) {
            Logger.getLogger(ItensDoPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ItensDoPedidoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return null;
    }

    /**
     * Método responsável por remover/cancelar um determinado pedido
     *
     * @param umPedido representa um objeto da classo
     * @link Pedido
     * @throws SQLException
     */
    public void removerItensDoPedido(int umPedido) throws SQLException {
        PreparedStatement preparedStatement = ConexaoBD.conexao().prepareStatement("DELETE FROM ItensDoPedido WHERE idItensDoPedido=?;");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
