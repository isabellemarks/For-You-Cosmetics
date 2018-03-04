package Interfaces;

import br.com.integrador.dao.ClienteDao;
import br.com.integrador.modelos.Cliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estefania
 */
public class ConsultarClientes extends javax.swing.JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel();
    ClienteDao clientes = new ClienteDao();

    public ConsultarClientes() {
        initComponents();
        setExtendedState(ConsultarClientes.MAXIMIZED_BOTH);

        modelo.addColumn("IdCliente");
        modelo.addColumn("nome");
        modelo.addColumn("dataNascimento");
        modelo.addColumn("email");

        modelo.addColumn("idEndereco");

        List<Cliente> resultados = new ArrayList<>();

        resultados = clientes.consultarTodosOsClientes();

        if (resultados.isEmpty()) {
            modelo.addRow(new String[]{"Nenhum resultado cadastrado", null, null, null});

        } else {
            ;

            jTable2.setModel(modelo);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(250);
            jTable2.getColumnModel().getColumn(0).setMinWidth(250);
            jTable2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(250);
            jTable2.getTableHeader().getColumnModel().getColumn(0).setMinWidth(250);
            jTable2.setModel(modelo);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(4).setMinWidth(0);
            jTable2.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            jTable2.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);

            jTable2.getColumnModel().getColumn(1).setMinWidth(250);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(250);

            jTable2.getColumnModel().getColumn(2).setMinWidth(250);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(250);

            jTable2.getColumnModel().getColumn(3).setMinWidth(250);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(250);

        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NomePesquisar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        voltaraoMenu = new javax.swing.JButton();
        sair = new javax.swing.JButton();
        excluir = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(NomePesquisar);
        NomePesquisar.setBounds(280, 200, 510, 40);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagensbotoes/OK.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(830, 200, 80, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/titulos/pesquisarclientes.png"))); // NOI18N
        jLabel2.setText("Pesquisar Clientes");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(240, 30, 550, 120);

        voltaraoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagensbotoes/VoltarAoMenuBotao.png"))); // NOI18N
        voltaraoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltaraoMenuActionPerformed(evt);
            }
        });
        getContentPane().add(voltaraoMenu);
        voltaraoMenu.setBounds(810, 550, 210, 40);

        sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagensnomes/Sair.png"))); // NOI18N
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        getContentPane().add(sair);
        sair.setBounds(1030, 550, 90, 40);

        excluir.setText("Excluir");
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });
        getContentPane().add(excluir);
        excluir.setBounds(1080, 410, 90, 30);

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        getContentPane().add(editar);
        editar.setBounds(1080, 330, 90, 30);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable2);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 300, 990, 200);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background2.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-280, 0, 1980, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ClienteDao clientes = new ClienteDao();
        List<Cliente> resultados = new ArrayList<>();
        resultados = clientes.consultarClientePorNome(NomePesquisar.getText());

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        for (Cliente cliente : resultados) {
            modelo.addRow(new String[]{Integer.toString(cliente.getIdCliente()),
                cliente.getNome(), dataFormatada.format(cliente.getDataNascimento()),
                cliente.getEmail(), Integer.toString(cliente.getStatus()), Integer.toString(cliente.getEndereco().getIdEndereco()),});
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void voltaraoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltaraoMenuActionPerformed
        // TODO add your handling code here:
        new PaginaInicialRevendedora().setVisible(true);
        dispose();

    }//GEN-LAST:event_voltaraoMenuActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_sairActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:

        int linha = jTable2.getSelectedRow();
        int idCliente = Integer.parseInt((String) jTable2.getValueAt(linha, 0));
        

        ClienteDao clienteDao = new ClienteDao();
        Cliente clienteSelecionado = clienteDao.consultarClientePorId(idCliente);

        AlterarCliente alterarCliente = new AlterarCliente(clienteSelecionado);
        alterarCliente.setVisible(true);
        dispose();


    }//GEN-LAST:event_editarActionPerformed

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirActionPerformed
        // TODO add your handling code here:
        int linha = jTable2.getSelectedRow();
        int idCliente = Integer.parseInt((String) jTable2.getValueAt(linha, 0));
        ClienteDao clientedao = new ClienteDao();
        int ok = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja desabilitar ?", "Sistema Informa", JOptionPane.YES_NO_OPTION);

        if (ok == JOptionPane.YES_OPTION) {
            clientedao.inativarCliente(idCliente);

        } else {
            ;
        }


    }//GEN-LAST:event_excluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NomePesquisar;
    private javax.swing.JButton editar;
    private javax.swing.JButton excluir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton sair;
    private javax.swing.JButton voltaraoMenu;
    // End of variables declaration//GEN-END:variables
}
