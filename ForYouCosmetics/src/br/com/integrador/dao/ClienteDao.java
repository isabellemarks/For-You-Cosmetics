package br.com.integrador.dao;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Endereco;
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
public class ClienteDao {

    public ClienteDao() {
    }

    /**
     * Método responsável por cadastrar um cliente no banco de dados.
     *
     * @param umCliente representa um objeto do tipo
     * @link Cliente.
     * @return -1 se não tiver um próximo cliente.
     */
    public int cadastrarUmCliente(Cliente umCliente) {
        if (umCliente == null || umCliente.getEndereco() == null) {
            throw new IllegalArgumentException("Ou você está tentando persistir NULL ou então está faltando alguma dependência.");
        }

        Connection connection = ConexaoBD.conexao();
        final String INSERIR_CLIENTE = "INSERT INTO Cliente(nome, dataNascimento, email, status, idEndereco) VALUES (?, ?, ?, ?, ?);";
        final String INSERIR_CLIENTE_TELEFONE = "INSERT INTO Telefone_Cliente(idCliente, idTelefone) VALUES (?,?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_CLIENTE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, umCliente.getNome());
            preparedStatement.setDate(2, umCliente.getDataNascimento());
            preparedStatement.setString(3, umCliente.getEmail());
            preparedStatement.setInt(4, umCliente.getStatus());
            preparedStatement.setInt(5, umCliente.getEndereco().getIdEndereco());

            preparedStatement.executeUpdate();

            ResultSet chavesGeradas = preparedStatement.getGeneratedKeys();

            if (chavesGeradas.next()) {
                int idGerado = chavesGeradas.getInt(1);
                List<Telefone> telefonesDoCliente = umCliente.getTelefones();
                if (!telefonesDoCliente.isEmpty()) {
                    PreparedStatement preparedStatementRelacionamento = ConexaoBD.conexao().prepareStatement(INSERIR_CLIENTE_TELEFONE);
                    for (Telefone fone : telefonesDoCliente) {
                        preparedStatementRelacionamento.setInt(1, idGerado);
                        preparedStatementRelacionamento.setInt(2, fone.getIdTelefone());
                        preparedStatementRelacionamento.executeUpdate();
                    }

                }

                connection.commit();

                return idGerado;
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
     * Método responsável por consultar todos os clientes cadastrados no banco
     * de dados.
     *
     * @param umCliente que representa um objeto do tipo Cliente.
     * @return uma lista com o resultado da consulta, todas as informações de
     * todos os clientes.
     */
    public List<Cliente> consultarTodosOsClientes(Cliente umCliente) {
        Connection connection = ConexaoBD.conexao();
        List<Cliente> cliente_resultados = new ArrayList();
        ResultSet ResultSet;
        final String CONSULTAR_TODOS_CLIENTES = "SELECT  Cliente.nome, Telefone.Telefone,Cliente.dataNascimento, Cliente.email, Cliente.status,  Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "Endereco.numero FROM Telefone\n"
                + "INNER JOIN Telefone_Cliente ON Telefone.idTelefone = Telefone_Cliente.idTelefone\n"
                + "INNER JOIN Cliente ON Telefone_Cliente.idCliente = Cliente.idCliente\n"
                + "INNER JOIN Endereco ON Cliente.idEndereco = Endereco.idEndereco\n"
                + "WHERE Cliente.idCliente = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_CLIENTES);
            preparedStatement.setInt(1, umCliente.getIdCliente());
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();
                Telefone telefone = new Telefone();
                List<Telefone> telefones = new ArrayList();

                cliente.setNome(ResultSet.getString(1));
                telefone.setTelefone(ResultSet.getInt(2));
                cliente.setDataNascimento(ResultSet.getDate(3));
                cliente.setEmail(ResultSet.getString(4));
                cliente.setStatus(ResultSet.getInt(5));
                endereco.setRua(ResultSet.getString(6));
                endereco.setBairro(ResultSet.getString(7));
                endereco.setCep(ResultSet.getInt(8));
                endereco.setComplemento(ResultSet.getString(9));
                endereco.setNumero(ResultSet.getString(10));

                telefones.add(telefone);

                cliente.setEndereco(endereco);
                cliente.setTelefones(telefones);
                cliente_resultados.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return cliente_resultados;
    }

    /**
     * Método responsável por consultar os clientes cadastrados no banco de
     * dados pelo seu nome.
     *
     * @param umCliente representa o nome do cliente requerido.
     * @return um arraylist com todos os clientes correspondentes ao nome
     * informado.
     */
    public ArrayList<Cliente> consultarClientePorNome(String umCliente) {
        final String CONSULTAR_CLIENTE = "SELECT * FROM Cliente WHERE nome LIKE ?;";
        Connection connection = ConexaoBD.conexao();
        List<Cliente> nome_cliente_resultados = new ArrayList();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_CLIENTE);
            preparedStatement.setString(1, umCliente + "%");
            ResultSet ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();

                cliente.setIdCliente(ResultSet.getInt("idCliente"));
                cliente.setNome(ResultSet.getString("nome"));
                cliente.setDataNascimento(ResultSet.getDate("dataNascimento"));
                cliente.setEmail(ResultSet.getString("email"));
                endereco.setIdEndereco(ResultSet.getInt("idEndereco"));
                cliente.setEndereco(endereco);

                nome_cliente_resultados.add(cliente);
                System.out.println(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return (ArrayList<Cliente>) nome_cliente_resultados;

    }

    /**
     * Método responsável pela consulta de um cliente cadastrado no banco pelo
     * ID informado.
     *
     * @param idCliente representa o ID do cliente.
     * @return retorna uma lista com todas as informações do cliente que
     * corresponde ao ID.
     */
    public Cliente consultarClientePorId(int idCliente) {
        Connection connection = ConexaoBD.conexao();
        ResultSet ResultSet;

        final String CONSULTAR_CLIENTE_POR_ID = "SELECT Cliente.idCliente, Cliente.nome, Telefone.Telefone,Cliente.dataNascimento, Cliente.email, Cliente.status,  Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "Endereco.numero FROM Telefone\n"
                + "INNER JOIN Telefone_Cliente ON Telefone.idTelefone = Telefone_Cliente.idTelefone\n"
                + "INNER JOIN Cliente ON Telefone_Cliente.idCliente = Cliente.idCliente\n"
                + "INNER JOIN Endereco ON Cliente.idEndereco = Endereco.idEndereco\n"
                + "WHERE Cliente.idCliente = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_CLIENTE_POR_ID);
            preparedStatement.setInt(1, idCliente);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();
                Telefone telefone = new Telefone();
                List<Telefone> telefones = new ArrayList();

                cliente.setIdCliente(ResultSet.getInt(1));
                cliente.setNome(ResultSet.getString(2));
                telefone.setTelefone(ResultSet.getInt(3));
                cliente.setDataNascimento(ResultSet.getDate(4));
                cliente.setEmail(ResultSet.getString(5));
                cliente.setStatus(ResultSet.getInt(6));
                endereco.setRua(ResultSet.getString(7));
                endereco.setBairro(ResultSet.getString(8));
                endereco.setCep(ResultSet.getInt(9));
                endereco.setComplemento(ResultSet.getString(10));
                endereco.setNumero(ResultSet.getString(11));

                telefones.add(telefone);
                cliente.setEndereco(endereco);
                cliente.setTelefones(telefones);

                return cliente;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return null;

    }

    /**
     * Método responsável pela alteração dos dados de um cliente.
     *
     * @param umCliente representa um objeto da classe Cliente.
     */
    public void alterarUmCliente(Cliente umCliente) {
        Connection connection = ConexaoBD.conexao();
        final String ALTERAR_CLIENTE = "UPDATE Cliente SET nome=?, dataNascimento=?, email=?, status = ? WHERE idCliente=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ALTERAR_CLIENTE);
            preparedStatement.setString(1, umCliente.getNome());
            preparedStatement.setDate(2, umCliente.getDataNascimento());
            preparedStatement.setString(3, umCliente.getEmail());
            preparedStatement.setInt(4, umCliente.getStatus());
            preparedStatement.setInt(5, umCliente.getIdCliente());
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
     * Método responsável pela remoção de um cliente.
     *
     * @param umCliente que representa um objeto do tipo
     * @link Cliente.
     */
    public void removerUmCliente(Cliente umCliente) {
        try {
            try (PreparedStatement preparedStatement = ConexaoBD.conexao().prepareStatement("DELETE FROM Cliente WHERE idCliente=?;")) {
                preparedStatement.setInt(1, umCliente.getIdCliente());
                preparedStatement.execute();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método responsável pelo preenchimento da Combobox.
     *
     * @param umCliente representa um cliente.
     * @return
     */
    public int preencheComboBox(Cliente umCliente) {
        final String CONSULTAR_CLIENTES_PARA_COMBOBOX = "SELECT idCliente FROM Cliente WHERE nome='" + umCliente.getNome() + "';";
        Connection connection = ConexaoBD.conexao();
        ResultSet resultSet;
        int id = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_CLIENTES_PARA_COMBOBOX);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;

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
     * Método responsável por inativar um cliente, ou seja, deixar o cliente
     * inutilizável.
     *
     * @param umCliente representa um objeto da classe Cliente
     */
    public void inativarCliente(int umCliente) {
        Connection connection = ConexaoBD.conexao();
        final String INATIVAR_CLIENTE = "UPDATE Cliente SET status = 0 WHERE idCliente = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INATIVAR_CLIENTE);
            preparedStatement.setInt(1, umCliente);
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
     * Método responsável pela ativação de um cliente.
     *
     * @param umCliente representa um objeto do tipo cliente
     */
    public void ativarCliente(Cliente umCliente) {
        Connection connection = ConexaoBD.conexao();
        final String ATIVAR_CLIENTE = "UPDATE Cliente SET status = 1 WHERE idCliente = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ATIVAR_CLIENTE);
            preparedStatement.setInt(1, umCliente.getIdCliente());
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
     * Método responsável por consultar todos os clientes existentes no banco de
     * dados.
     *
     * @return uma lista com todos os dados de todos os clientes existentes.
     */
    public List<Cliente> consultarTodosOsClientes() {
        Connection connection = ConexaoBD.conexao();
        List<Cliente> cliente_resultados = new ArrayList();
        ResultSet ResultSet;
        final String CONSULTAR_TODOS_CLIENTES = "SELECT * FROM Cliente";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_TODOS_CLIENTES);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {
                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();

                cliente.setIdCliente(ResultSet.getInt("idCliente"));
                cliente.setNome(ResultSet.getString("nome"));
                cliente.setDataNascimento(ResultSet.getDate("dataNascimento"));
                cliente.setEmail(ResultSet.getString("email"));
                endereco.setIdEndereco(ResultSet.getInt("idEndereco"));
                cliente.setEndereco(endereco);
                //cliente.setAcesso(ResultSet.getBoolean("acesso"));

                cliente_resultados.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return cliente_resultados;
    }

    /**
     * Método responsável por consultar os endereços pelo ID do cliente para a
     * atualização de um endereço.
     *
     * @param idCliente representa o ID informado.
     * @return -1 caso não seja encontrado um próximo endereço.
     */
    public int consultarporIdParaAtualizarEndereco(int idCliente) {
        Connection connection = ConexaoBD.conexao();
        ResultSet ResultSet;

        final String CONSULTAR_CLIENTE_POR_ID = "SELECT  Cliente.idCliente,Cliente.nome, Telefone.Telefone,Cliente.dataNascimento, Cliente.email, Cliente.status,  Endereco.idEndereco,Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "Endereco.numero FROM Telefone\n"
                + "INNER JOIN Telefone_Cliente ON Telefone.idTelefone = Telefone_Cliente.idTelefone\n"
                + "INNER JOIN Cliente ON Telefone_Cliente.idCliente = Cliente.idCliente\n"
                + "INNER JOIN Endereco ON Cliente.idEndereco = Endereco.idEndereco\n"
                + "WHERE Cliente.idCliente =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_CLIENTE_POR_ID);
            preparedStatement.setInt(1, idCliente);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                int idEndereco = ResultSet.getInt(7);
                return idEndereco;
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
     * Método responsável por consultar os clientes pelo seu ID para a
     * atualização de um cliente.
     *
     * @param idCliente representa o id do cliente informado.
     * @return Ele retorna o id do cliente, caso não exista um próximo dado ele
     * irá retornar -1.
     */
    public int consultarporIdParaAtualizarCliente(int idCliente) {
        Connection connection = ConexaoBD.conexao();
        ResultSet ResultSet;

        final String CONSULTAR_CLIENTE_POR_ID = "SELECT  Cliente.idCliente,Cliente.nome, Telefone.Telefone,Cliente.dataNascimento, Cliente.email, Cliente.status,  Endereco.idEndereco,Endereco.rua, Endereco.bairro, Endereco.cep, Endereco.complemento,\n"
                + "Endereco.numero FROM Telefone\n"
                + "INNER JOIN Telefone_Cliente ON Telefone.idTelefone = Telefone_Cliente.idTelefone\n"
                + "INNER JOIN Cliente ON Telefone_Cliente.idCliente = Cliente.idCliente\n"
                + "INNER JOIN Endereco ON Cliente.idEndereco = Endereco.idEndereco\n"
                + "WHERE Cliente.idCliente =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_CLIENTE_POR_ID);
            preparedStatement.setInt(1, idCliente);
            ResultSet = preparedStatement.executeQuery();
            connection.commit();

            while (ResultSet.next()) {

                int idClientes = ResultSet.getInt(1);
                return idClientes;
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
}
