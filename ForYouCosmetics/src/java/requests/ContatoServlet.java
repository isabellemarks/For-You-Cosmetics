package requests;

import br.com.integrador.dao.ContatoDao;
import br.com.integrador.modelos.Contato;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/ContatoServlet.do"})
public class ContatoServlet extends HttpServlet {

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
            out.println("<title>Servlet ContatoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContatoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Método responsável por adicionar uma mensagem ao banco de dados
     *
     * @param request responsável por fazer uma solicitação através do servlet
     * @param response responsável por responder através do servlet
     */
    private static void contato(HttpServletRequest request, HttpServletResponse response) {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        int telefone = Integer.parseInt(request.getParameter("telefone"));
        String voceENosso = request.getParameter("voceENosso");
        String assunto = request.getParameter("assunto");
        String mensagem = request.getParameter("mensagem");

        Contato meuContato = new Contato(nome, email, telefone, voceENosso, assunto, mensagem);
        ContatoDao contatoDao = new ContatoDao();
        int idContato = contatoDao.cadastrarUmContato(meuContato);
        meuContato.setIdContato(idContato);
    }

    /**
     * Método responsável por exibir uma página onde diz que o procedimento foi
     * executado com sucesso.
     *
     * @param request representa um objeto do tipo HttpServletRequest
     * @param response representa um objeto do tipo HttpServletResponse
     */
    public void sucesso(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        try {
            response.sendRedirect(request.getContextPath() + "/sucesso.html");
        } catch (IOException ex) {
            Logger.getLogger(AdicionarClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        contato(request, response);
        sucesso(request, response);
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
