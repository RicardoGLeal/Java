
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ricardo González Leal Clase Controller Esta clase se encarga de
 * funcionar como controlador para el inicio de sesión en la página web. Cuando
 * el usuario da click en el botón de iniciar sesión la función processRequest
 * almacena las credenciales ingresadas, hace un select por medio del
 * controlador de la base de datos, y dependiendo si se encontró el usuario o
 * no, se asignan atributos que se comunican con el jsp del login.
 *
 */
@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    DBController dbc;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public Controller() {
        dbc = new DBController();
    }

    /**
     * Función processRequest Esta función se encarga del funcionamiento del
     * inicio de sesión. Cuando el usuario presiona el botón de iniciar sesión,
     * esta función recibe las credenciales por medio de un parámetro,
     * posetriormente se llama a la función selectUser del controlador de la
     * base de datos, para comprobar si las credenciales ingresadas corresponden
     * o no a las de un registro. si son correctas, al atributo "logged" se le
     * almacena el nombre del usuario, y si es incorrecto, al atributo
     * "failedLoggin" se le almacena un true. De esta manera el jsp imprime html
     * distinto dependiendo del resultado.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String link = request.getParameter("link");
        if (link.equals("Login")) {
            String user = request.getParameter("User");
            String password = request.getParameter("Password");
            if (dbc.selectUser(user, password)) {
                request.setAttribute("logged", user);
            } else {
                request.setAttribute("failedLogging", true);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
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
        processRequest(request, response);
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
