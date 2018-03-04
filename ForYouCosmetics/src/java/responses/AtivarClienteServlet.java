package responses;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.dao.ClienteDao;
import br.com.integrador.modelos.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Isabelle
 */
@WebServlet(name = "AtivarClienteServlet", urlPatterns = {"/AtivarClienteServlet.do"})
public class AtivarClienteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AtivarClienteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AtivarClienteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Método responsável por pesquisar fazer a ativação de um cliente.
     * Primeiramente ele faz uma consulta pelo seu id e logo após haverá a sua
     * ativação, caso este seja desativado.
     *
     * @param request responsável por fazer uma solicitação através do servlet
     * @param response responsavél por responder através do servlet.
     * @return retorna o id do Cliente.
     */
    public int pesquisarCliente(HttpServletRequest request, HttpServletResponse response) {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        clienteDao.ativarCliente(cliente);

        response.setContentType("text/html");
        try {
            response.sendRedirect(request.getContextPath() + "/sucesso.html");
        } catch (IOException ex) {
            Logger.getLogger(AtivarClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idCliente;
    }

    /**
     * Método responsável por preencher a tabela de resultado da pesquisa de
     * todos os clientes.
     *
     * @param out representa um objeto do tipo PrintWriter.
     * @return ele retornará um contador de itens encontrados.
     * @throws Exception
     */
    private int preencheTabela(java.io.PrintWriter out) throws Exception {
        Connection connection = ConexaoBD.conexao();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from Cliente");

        int contador = 0;
        out.println("<table border = 1>");
        ResultSetMetaData rsmd = rs.getMetaData();
        int colunaContador = rsmd.getColumnCount(); //cabeçalho da tabela
        out.println("<tr>");
        for (int i = 0; i < colunaContador; i++) {
            out.println("<th>" + rsmd.getColumnLabel(i + 1) + "</th>");
        }
        out.println("</tr>");
        while (rs.next()) { //colocar os dados na tabela
            contador++;
            out.println("<tr>");
            for (int i = 0; i < colunaContador; i++) {
                out.println("<td>" + rs.getString(i + 1) + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        return contador;
    }

    /**
     * Método responsável por montar e mostrar a página de resultados pela
     * pesquisa.
     *
     * @param request responsável pela solicitação através de um servlet.
     * @param response responsável pela resposta através de um servlet.
     */
    public void mostrarPesquisa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out;
        try {
            out = response.getWriter();
            try {
                response.setContentType("text/html;charset=UTF-8");
                out.print("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <meta charset=\"utf-8\">\n"
                        + "        <title>Pesquisar Cliente | For You Cosmetics</title>\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilos.css\">\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/areadocliente.css\">\n"
                        + "        <link rel=\"shortcut icon\" href=\"img/icone.ico\" />\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <header class=\"container\">\n"
                        + "            <!--[if lt IE 9]>\n"
                        + "             <script src=\"//html5shiv.googlecode.com/svn/trunk/html5.js\"></script>\n"
                        + "            <![endif]-->\n"
                        + "            <div id=\"imagem-logo\">\n"
                        + "            </div>\n"
                        + "            <nav class=\"menu-topo-opcoes\">\n"
                        + "                <ul>\n"
                        + "                    <li><a href=\"index.html\">Página Inicial</a></li>\n"
                        + "                    <li><a href=\"duvidas.html\">Dúvidas</a></li>\n"
                        + "                    <li><a href=\"contato.html\">Fale Conosco</a></li>\n"
                        + "                </ul>\n"
                        + "            </nav>\n"
                        + "        </header>\n"
                        + "        <p><a href=\"areadorevendedor.html\" title=\"\"><small>Área do Revendedor</small></a> >\n"
                        + "            <strong>Pesquisar clientes</strong></p>\n"
                        + "        <div id=\"form-pesquisar-cliente\">\n"
                        + "            <form action=\"PesquisarClientesServlet.do\" method=\"post\" accept-charset=\"utf-8\">\n"
                        + "                Nome do cliente:\n"
                        + "                <input required type=\"search\" name=\"nome\" value=\"\" placeholder=\"Pesquisar por nome\" size=\"40\">\n"
                        + "                <input type=\"submit\" name=\"submit\" value=\"Pesquisar\">\n"
                        + "            </form>\n"
                        + "        </div>\n"
                        + "        <br />\n"
                        + "        <br />\n"
                        + "        <div class=\"resultado-pesquisa-cliente-tabela\">\n"
                        + "            <table>\n");
                preencheTabela(out);

                out.print("            </table>\n"
                        + "        </div>\n"
                        + "        <br />\n"
                        + "        <br />\n"
                        + "        <footer>\n"
                        + "            <nav class=\"imagensLogosMarcas\">\n"
                        + "                <h5>Nossas marcas:</h5>\n"
                        + "                <img class=\"logos\" src=\"img/logobot.png\" height=\"70\" width=\"70\" alt=\"logo boticario\" /> <!--140 por 133px -->\n"
                        + "                <img class=\"logos\" src=\"img/logo_avon.png\" height=\"70\" width=\"70\" alt=\"logo avon\" />\n"
                        + "                <img class=\"logos\" src=\"img/logo_jequiti.jpg\" height=\"70\" width=\"70\" alt=\"logo jequiti\" />\n"
                        + "                <img class=\"logos\" src=\"img/logo_natura.jpg\" height=\"70\" width=\"70\" alt=\"logo natura\" />\n"
                        + "                <img class=\"logos\" src=\"img/logo_yes.jpg\" height=\"70\" width=\"70\" alt=\"logo yes cosmetics\" />\n"
                        + "                <img class=\"logos\" src=\"img/logo_mac.jpg\" height=\"70\" width=\"70\" alt=\"logo mac\" />\n"
                        + "                <img class=\"logos\" src=\"img/logo_nyx.jpg\" height=\"70\" width=\"70\" alt=\"logo nyx\" />\n"
                        + "                <img class=\"logos\" src=\"img/logo_qdb.jpg\" height=\"70\" width=\"70\" alt=\"logo qdb\" />\n"
                        + "                <img class=\"logos\" src=\"img/logo_vult.jpg\" height=\"70\" width=\"70\" alt=\"logo vult\" />\n"
                        + "            </nav>\n"
                        + "        </footer>\n"
                        + "    </body>"
                        + " </html>");
            } catch (Exception ex) {
                Logger.getLogger(PesquisarClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(PesquisarClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        pesquisarCliente(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
