package requests;

import br.com.integrador.dao.UsuarioClienteDao;
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
@WebServlet(urlPatterns = {"/LoginClienteServlet.do"})
public class LoginClienteServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Método responsável por validar o login e redirecionar o cliente para a
     * sua área. Caso o login não corresponda a algum existente no banco de
     * dados ele irá redirecionará o usuário para uma página onde é informado
     * que ele não tem acesso à area.
     *
     * @param request responsável por solicitar através do servlet
     * @param response responsável por responder através do servlet
     */
    public void login(HttpServletRequest request, HttpServletResponse response) {
        UsuarioClienteDao usuarioClienteDao = new UsuarioClienteDao();
        boolean resposta = false;
        String loginCliente = request.getParameter("loginCliente");
        String senhaCliente = request.getParameter("senhaCliente");
        resposta = usuarioClienteDao.consultarUsuario(loginCliente, senhaCliente);

        if (resposta == true) {
            try {
                response.setContentType("text/html;charset=UTF-8");
                response.sendRedirect(request.getContextPath() + "/areadocliente.html");
            } catch (IOException ex) {
                Logger.getLogger(LoginRevendedoraServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                response.setContentType("text/html;charset=UTF-8");
                response.sendRedirect(request.getContextPath() + "/erro_404.html"); //trocar isso por uma notificação que deu erro no login
            } catch (IOException ex) {
                Logger.getLogger(LoginRevendedoraServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        login(request, response);
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
