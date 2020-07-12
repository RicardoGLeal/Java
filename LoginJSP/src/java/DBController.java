
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase DBController
 * @author Ricardo González Leal
 * Esta clase se encarga de la comunicación hacia la base de datos usuarios.
 * Cuenta con un constructor que se encarga de conectarse con la base de datos
 * y asímismo cuenta con una función selectUser, la cual es utilizada para 
 * buscar un usuario con las credenciales ingresadas en el jsp.
 */
public class DBController {
     private Connection con;
    
    public DBController() {
        try {
            //se instancia el driver mysql jdbc.
            Class.forName("com.mysql.jdbc.Driver");
            //se realiza la conexión hacia la bd, especificando la ruta, el 
            //usuario y la contaseña.
            con = DriverManager.getConnection("jdbc:mysql://localhost/usuarios", "root", "");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error al cargar el driver" + ex.getMessage());
        }
    }

    /**
     * Función selectUser
     * Esta función se encarga de realizar un select a la base de datos de 
     * usuarios, buscando un registro que concuerde con el nombre de usuario
     * y contraseña recibidos.
     * @param user nombre de usuario a buscar
     * @param password contraseña a buscar
     * @return true si se encontró y false si no se encontró.
     */
    public boolean selectUser(String user, String password) {
        ResultSet result;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT userid FROM usuario WHERE usuario = ? and password = ?");
            stmt.setString(1, user);
            stmt.setString(2, password);
            result = stmt.executeQuery();
            if(!result.first())
                return false;
                
        } catch (SQLException e) {
            System.out.println("No se encontraron resultados");
            return false;
        }
        return true;
    }
}
