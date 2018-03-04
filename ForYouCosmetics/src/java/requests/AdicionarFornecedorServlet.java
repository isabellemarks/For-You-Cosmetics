package requests;

import br.com.integrador.dao.EnderecoDao;
import br.com.integrador.dao.FornecedorDao;
import br.com.integrador.dao.TelefoneDao;
import br.com.integrador.modelos.Endereco;
import br.com.integrador.modelos.Fornecedor;
import br.com.integrador.modelos.Telefone;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
@WebServlet(urlPatterns = {"/AdicionarFornecedor.do"})
public class AdicionarFornecedorServlet extends HttpServlet {

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
            out.println("<title>Servlet AdicionarFornecedorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdicionarFornecedorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Método responsável pela adição de um fornecedor ao banco de dados atráves
     * do servlet
     *
     * @param request responsável pela solicitação através do servlet
     * @param response responsável pela resposta através do servlet
     */
    private static void adicionarFornecedor(HttpServletRequest request, HttpServletResponse response) {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        int telefone = Integer.parseInt(String.valueOf(request.getParameter("telefone")));
        String rua = request.getParameter("rua");
        String numero = String.valueOf(request.getParameter("numero"));
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        int cep = Integer.parseInt(String.valueOf(request.getParameter("cep")));
        int status = Integer.parseInt(String.valueOf(request.getParameter("status")));

        EnderecoDao daoEnd = new EnderecoDao();
        Endereco meuEndereco = new Endereco(rua, bairro, cep, complemento, numero);
        int idEndereco = daoEnd.cadastrarUmEndereco(meuEndereco);
        meuEndereco.setIdEndereco(idEndereco);

        TelefoneDao daoTel = new TelefoneDao();
        Telefone meuTelefone = new Telefone(telefone);
        int idTelefone = daoTel.cadastrarUmTelefone(meuTelefone);
        meuTelefone.setIdTelefone(idTelefone);

        FornecedorDao fornecedorDao = new FornecedorDao();
        Fornecedor meuFornecedor = new Fornecedor(nome, email, status, meuEndereco, Arrays.asList(meuTelefone));
        int idFornecedor = fornecedorDao.cadastrarUmFornecedor(meuFornecedor);
        meuFornecedor.setIdFornecedor(idFornecedor);

        meuTelefone.setTelefone(idTelefone);
        daoTel.cadastrarUmTelefone(meuTelefone);
    }

    /**
     * Método responsável por exibir uma página onde diz que o procedimento foi
     * executado com sucesso.
     *
     * @param request responsável por solicitar através do servlet
     * @param response responsável por responder através do servlet
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
        adicionarFornecedor(request, response);
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
