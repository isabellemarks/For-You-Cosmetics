package br.com.integrador.negocio;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.dao.ClienteDao;
import br.com.integrador.dao.EnderecoDao;
import br.com.integrador.dao.TelefoneDao;
import br.com.integrador.modelos.Cliente;
import br.com.integrador.modelos.Telefone;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20121164010406
 */
public class GerenciadorCliente {
    
    private ClienteDao clienteDao = new ClienteDao();
    private EnderecoDao enderecoDao = new EnderecoDao();
    private TelefoneDao telefoneDao = new TelefoneDao();
    
    public void excluirCliente(Cliente umCliente) {
        Connection conexao = ConexaoBD.conexao();
        try {
            conexao.setAutoCommit(false);
            List<Telefone> telefones = this.telefoneDao.consultarTelefonesdoCliente(umCliente);
            for (Telefone telefone : telefones) {
                this.telefoneDao.removerUmTelefone(telefone);
            }
            this.clienteDao.removerUmCliente(umCliente);
            this.enderecoDao.removerEndereco(umCliente.getEndereco());
            conexao.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorCliente.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conexao.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GerenciadorCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    public void cadastrarCliente(Cliente umCliente) {
        
    }
    
}
