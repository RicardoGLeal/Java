
package servletPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ricardo González Leal
 * Clase BaseDeDatos
 * Esta función cuenta con un constructor y con todas las funciones necesarias
 * para poder realizar una conexión hacia la base de datos direccionesbd, 
 * autenticarse y realizar todas las operaciones de un crud para la tabla 
 * Direccion. 
 */
public class DireccionesBD {
    
    private Connection con;
    
    public DireccionesBD() {
        try {
            //se instancia el driver mysql jdbc.
            Class.forName("com.mysql.jdbc.Driver");
            //se realiza la conexión hacia la bd, especificando la ruta, el 
            //usuario y la contaseña.
            con = DriverManager.getConnection("jdbc:mysql://localhost/direccionesbd", "root", "");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error al cargar el driver" + ex.getMessage());
        }
    }
    
    /**
     * Funcion insertarDireccion
     * Esta función se encarga de ejecutar la query que corresponde a la de
     * insertar un registro en la tabla direccion de la bd. 
     * @param direccion Objeto tipo direccion que contiene todos los datos
     * que se le darán al nuevo registro.
     * @return true, si la consulta fue exitosa o false, si la consulta no fue 
     * exitosa
     */
    public boolean insertarDireccion(Direccion direccion) {
        try {
            //se crea la query, dejando con un ? los espacios que cambiarán.
            PreparedStatement stmt = con.prepareStatement("INSERT INTO direccion(calle, numExt, colonia, cp) VALUES (? , ? , ? , ? )");
            
            stmt.setString(1, direccion.calle);
            stmt.setInt(2, direccion.numExt);
            stmt.setString(3, direccion.colonia);
            stmt.setInt(4, direccion.cp);
            
            stmt.executeUpdate();//se ejecuta la query
            System.out.println("Direccion eliminada correctamente");
        } catch (SQLException e) {
            System.out.println("Insert fallido, ya existe el id?");
            return false;
        }
        return true;
    }
    
    /**
     * Función consultarDirecciones
     * Esta función se encarga de seleccionar todos los registros de la tabla
     * direccion de la bd. Estas se almacenan en un ArrayList de tipo Direccion.
     * @return ArrayList tipo Direccion, el cual contiene todos los registros
     * encontrados en la tabla direccion.
     */
    public ArrayList<Direccion> consultarDirecciones() {
        ArrayList<Direccion> direcciones = new ArrayList<>();
        ResultSet result;
        try {
            //Se crea la query.
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM direccion");
            result = stmt.executeQuery();
            while (result.next()) {
                Direccion dir = new Direccion();
                dir.setId(result.getInt("id"));
                dir.setCalle(result.getString("calle"));
                dir.setNumExt(result.getInt("numExt"));
                dir.setCp(result.getInt("cp"));
                dir.setColonia(result.getString("colonia"));
                direcciones.add(dir);
            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta");
            return null;
        }
        return direcciones;
    }
    
    /**
     * Función consultarDirecciones
     * Esta función se encarga de seleccionar todos los registros de la tabla
     * direccion que cumplan con una condicion para uno de sus campos.
     * @param campo el campo al que se le asigna la condición.
     * @param value el valor que se comprobará para el campo.
     * @return ArrayList tipo Direccion, el cual contiene todos los registros
     * encontrados en la tabla direccion que cumplen con la condición.
     */
    public ArrayList<Direccion> consultarDirecciones(String campo, Object value) {
        ArrayList<Direccion> direcciones = new ArrayList<>();
        ResultSet result;
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM direccion WHERE "+campo+" = ? ");
            stmt.setObject(1, value);//se reemplaza el primer ? de stmt por value.
            result = stmt.executeQuery();
            while (result.next()) {
                Direccion dir = new Direccion();//Se crea objeto tipo Direccion.
                dir.setId(result.getInt("id"));
                dir.setCalle(result.getString("calle"));
                dir.setNumExt(result.getInt("numExt"));
                dir.setCp(result.getInt("cp"));
                dir.setColonia(result.getString("colonia"));
                direcciones.add(dir);
            }
        }
        catch(SQLException ex){
            System.out.println("Error en la consulta");
            return null;
        }
        return direcciones;
    }
    
    /**
     * Función eliminarDireccion
     * Esta función se encarga de realizar la consulta que corresponde a eliminar
     * un registro de la tabla direccion de la bd.
     * @param id el ID del registro a eliminar
     * @return true, si la consulta fue exitosa o false, si la consulta no fue 
     * exitosa
     */
    public boolean eliminarDireccion(int id)
    {
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM direccion where id = ? ");
            stmt.setInt(1, id);
            stmt.executeUpdate();//se ejecuta la query
        }
        catch(SQLException ex){
            System.out.println("Error en la consulta");
            return false;
        }
        return true;
    }
        /**
         * Función actualizarDirección
         * Esta función se encarga de realizar la consulta que corresponde a
         * actualizar un registro de la tabla Direccion de la bd
         * @param campo el nombre del campo a actualizar
         * @param value el nuevo valor que se le asignará al campo
         * @param id el id del registro a actualizar
         * @return true, si la consulta fue exitosa o false, si la consulta no fue 
     * exitosa
         */
    public boolean actualizarDireccion(String campo, Object value, int id)
    {
        try{ 
            //se crea la query, dejando con un ? los espacios que cambiarán.
            PreparedStatement stmt = con.prepareStatement("UPDATE direccion SET "+campo+" = ? WHERE id = ?");
            stmt.setObject(1, value);//se reemplaza el primer ? de stmt por value.
            stmt.setInt(2, id);//se reemplaza el seungo ? de stmt por id.
            stmt.executeUpdate();//se ejecuta la query
        }
        catch(SQLException ex){
            System.out.println("Error en la consulta");
            return false;
        }
        return true;
    }
    public void actualizarDireccion(Direccion newDir) {
        actualizarDireccion("calle", newDir.calle, newDir.id);
        actualizarDireccion("colonia", newDir.colonia, newDir.id);
        actualizarDireccion("cp", newDir.cp, newDir.id);
        actualizarDireccion("numExt", newDir.numExt, newDir.id);
    }
}
