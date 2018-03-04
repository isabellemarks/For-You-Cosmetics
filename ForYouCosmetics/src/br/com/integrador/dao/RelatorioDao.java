/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.ItensdoPedido;
import br.com.integrador.modelos.Pedido;
import br.com.integrador.modelos.Produto;
import br.com.integrador.modelos.Relatorios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estefania
 */
public class RelatorioDao {

    private final String PESQUISAR_DATA_RELATORIO = "SELECT Pedido.valorPedidoTotal, sum(ItensDoPedido.quantidade) AS quantidade, Produto.nomeProduto FROM ItensDoPedido \n"
            + "INNER JOIN Pedido ON ItensDoPedido.idPedido = Pedido.idPedido\n"
            + "INNER JOIN Produto ON ItensDoPedido.idProduto = Produto.idProduto\n"
            + "WHERE YEAR (Pedido.dataPedido) = ? AND MONTH (Pedido.dataPedido) = ? group by Produto.idProduto order by sum(ItensDoPedido.quantidade) DESC;";

    /**
     * Método responsável por gerar o relatório dos produtos mais vendidos.
     *
     * @param mes representa o mês informado.
     * @param ano representa o ano informado.
     * @return uma lista com o resultado obtido, caso não seja encontrado um
     * próximo resultado ele irá retornar nulo.
     */
    public List<Relatorios> gerarRelatorio(int mes, int ano) {
        Connection connection = ConexaoBD.conexao();

        List<Relatorios> resultados = new ArrayList();

        try {

            try (PreparedStatement preparedStatement = connection.prepareStatement(PESQUISAR_DATA_RELATORIO)) {
                preparedStatement.setInt(1, ano);
                preparedStatement.setInt(2, mes);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Relatorios relatorio = new Relatorios();
                    relatorio.setIdPedido(new Pedido());
                    relatorio.getIdPedido().setValorPedidoTotal(resultSet.getFloat(1));
                    relatorio.setItensdopedido(new ItensdoPedido());
                    relatorio.getItensdopedido().setQuantidade(resultSet.getInt(2));
                    relatorio.getItensdopedido().setProduto(new Produto());
                    relatorio.getItensdopedido().getProduto().setNomeProduto(resultSet.getString(3));
                    resultados.add(relatorio);

                    connection.commit();

                }
                return resultados;

            }
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(RelatorioDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null;
    }

}
