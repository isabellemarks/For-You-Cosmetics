package responses;

import br.com.integrador.conexao.ConexaoBD;
import br.com.integrador.dao.FornecedorDao;
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
@WebServlet(name = "PesquisarFornecedorServlet", urlPatterns = {"/PesquisarFornecedorServlet.do"})
public class PesquisarFornecedorServlet extends HttpServlet {

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
            out.println("<title>Servlet PesquisarFornecedorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PesquisarFornecedorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Método responsável por pesquisar os fornecedores através do nome informado.
     *
     * @param request responsável por solicitar através do servlet.
     * @param response responsável por responder através do servlet.
     * @return ele retornará o nome informado.
     */
    public String pesquisarFornecedor(HttpServletRequest request, HttpServletResponse response) {
        String nome = request.getParameter("nome");

        FornecedorDao fornecedorDao = new FornecedorDao();
        fornecedorDao.consultarFornecedorPorNome(nome);

        return nome;
    }

    /**
     * Método responsável por preencher a tabela de resultado da pesquisa dos
     * fornecedores existentes no banco com o nome solicitado.
     *
     * @param out representa um objeto do tipo PrintWriter.
     * @return ele retornará um contador de itens encontrados.
     * @throws Exception
     */
    private int preencheTabela(java.io.PrintWriter out, String nome) throws Exception {
        Connection connection = ConexaoBD.conexao();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Fornecedor WHERE nome LIKE '" + nome + "%';");

        int contador = 0;
        out.println("<table border = 1>");
        ResultSetMetaData rsmd = rs.getMetaData();
        int colunaContador = rsmd.getColumnCount();  // cabeçalho da tabela
        out.println("<tr>");
        for (int i = 0; i < colunaContador; i++) {
            out.println("<th>" + rsmd.getColumnLabel(i + 1) + "</th>");
        }
        out.println("</tr>");   // colocando os dados na tabela
        while (rs.next()) {
            contador++;
            out.println("<tr>"); // class=\"resultado-pesquisa-cliente\"
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
        String nome = pesquisarFornecedor(request, response);
        try {
            out = response.getWriter();
            try {
                response.setContentType("text/html;charset=UTF-8");
                out.print("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <meta charset=\"utf-8\">\n"
                        + "        <title>Pesquisar Fornecedor | For You Cosmetics</title>\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilos.css\">\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/areadocliente.css\">\n"
                        + "        <link rel=\"shortcut icon\" href=\"img/icone.ico\" />\n"
                        + "        <!--[if lt IE 9]>\n"
                        + "         <script src=\"//html5shiv.googlecode.com/svn/trunk/html5.js\"></script>\n"
                        + "        <![endif]-->\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <header class=\"container\">\n"
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
                        + "            <strong>Pesquisar Fornecedor</strong></p>\n"
                        + "        <div id=\"form-pesquisar-fornecedor\">\n"
                        + "            <form action=\"PesquisarFornecedorServlet.do\" method=\"POST\" accept-charset=\"utf-8\">\n"
                        + "                Nome do fornecedor:\n"
                        + "                <input required type=\"search\" name=\"nome\" value=\"\" placeholder=\"Pesquisar por nome\" size=\"40\">\n"
                        + "                <input type=\"submit\" name=\"submit\" value=\"Pesquisar\">\n"
                        + "            </form>\n"
                        + "        </div>\n"
                        + "        <br />\n"
                        + "        <br />\n"
                        + "        <div class=\"resultado-pesquisa-fornecedor-tabela\">\n"
                        + "            <table>\n");
                preencheTabela(out, nome);
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
                        + "    </body>\n"
                        + "</html>");
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
        pesquisarFornecedor(request, response);
        mostrarPesquisa(request, response);
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
