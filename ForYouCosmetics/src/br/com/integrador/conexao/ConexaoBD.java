package br.com.integrador.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Isabelle
 */
public class ConexaoBD {

    public static Connection connection;

    public ConexaoBD() {
    }

    /**
     * Método responsável por gerar a conexão com o banco de dados.
     *
     * @return a conexão.
     */
    public static Connection conexao() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ConexaoBD.connection == null) {

                //ConexaoBD.connection = DriverManager.getConnection("jdbc:mysql://localhost/integrador_vc", "root", "root");
                ConexaoBD.connection = DriverManager.getConnection("jdbc:mysql://10.225.2.202/INTEGRADOR_VC", "20121164010317", "ester24");
                 //ConexaoBD.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:1024/integrador_vc", "root", "estefanialins2424");
                connection.setAutoCommit(false);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados\n Error:" + e.getMessage());

        }
        return ConexaoBD.connection;
    }

}
