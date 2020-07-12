package crud_basededatos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Ricardo González Leal Clase CRUD_BaseDeDatos Esta clase cuenta con
 * los métodos y funciones necesarias para solicitarle al usuario la operación
 * que desea realizar: insertar, consultar, actualizar o eliminar, dependiendo
 * del caso se le solicita al usuario la información requerida y manda llamar a
 * la función correspondiente en la clase BaseDeDatos, que se encarga de
 * realizar la consulta.
 */
public class CRUD_BaseDeDatos {

    static String calle, colonia;
    static int numExt, cp, id;
    static Scanner in;

    public static void main(String[] args) throws ValueException {
        while (true) {
            BaseDeDatos bd = new BaseDeDatos();
            System.out.println("");
            System.out.println("Seleccione una opción \n 1: Insertar Direccion \n 2: Consultar Direcciones \n 3: Actualizar Direccion \n 4: Eliminar Direccion \n 5: Salir");
            in = new Scanner(System.in);
            int option = -1;
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException ee) {
                System.out.println("El dato ingresado no es numerico " + ee.getMessage());
            }
            switch (option) {
                case 1://Insertar
                    try {
                        Direccion newDireccion = SolicitarDatos();
                        bd.insertarDireccion(newDireccion);
                    } catch (ValueException ee) {
                        System.out.println(ee.getMessage());
                    }
                    break;
                case 2://Consultar
                    try {
                        Consultar(bd);
                    } catch (ValueException ee) {
                        System.out.println(ee.getMessage());
                    }
                    break;
                case 3://Actualizar
                    try {
                        ActualizarDireccion(bd);
                    } catch (ValueException ee) {
                        System.out.println(ee.getMessage());
                    }
                    break;
                case 4://Eliminar
                    try {
                        EliminarDireccion(bd);
                    } catch (ValueException ee) {
                        System.out.println(ee.getMessage());
                    }
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * Esta función le solicita al usuario los datos para crear una nueva
     * dirección, posteriormente se crea el objeto tipo dirección.
     *
     * @return El Objeto Direccion creado.
     */
    private static Direccion SolicitarDatos() throws ValueException {
        Direccion dir = new Direccion();

        in = new Scanner(System.in);
        System.out.println("ID: ");
        try {
            dir.setId(Integer.parseInt(in.nextLine()));
            System.out.println("Calle: ");
            dir.setCalle(in.nextLine().toString());
            System.out.println("Numero Exterior: ");
            dir.setNumExt(Integer.parseInt(in.nextLine()));
            System.out.println("Colonia: ");
            dir.setColonia(in.nextLine().toString());
            System.out.println("Codigo Postal: ");
            dir.setCp(Integer.parseInt(in.nextLine()));
        } catch (NumberFormatException e) {
            throw new ValueException("El tipo de dato ingresado no es numerico" + e.getMessage());
        }
        return dir;
    }

    /**
     * Función Consultar Esta función se encarga de solicitarle al usuario los
     * datos requeridos para realizar un select especificando un campo y un
     * valor. Posteriormente estos datos se mandan a la función
     * consultarDirecciones de la clase BaseDeDatos, la cual se encarga de
     * ejecutar la query.
     *
     * @param bd La instancia creada de la clase BaseDeDatos.
     */
    private static void Consultar(BaseDeDatos bd) throws ValueException {
        String campo = "";
        ArrayList<Direccion> direcciones = new ArrayList<Direccion>();
        System.out.println("Selecciona una opcion");
        System.out.println("1: Consultar especificando campo");
        System.out.println("2: Consultar todas las direcciones");
        int option;
        try {
            option = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            throw new ValueException("El dato ingresado no es numerico " + e.getMessage());
        }

        switch (option) {
            case 1:
                System.out.println("Selecciona el campo: \n 1: id \n 2: calle \n 3: numExt \n 4: colonia \n 5: cp");
                try {
                    option = Integer.parseInt(in.nextLine());
                    if (option < 1 || option > 5) 
                    throw new ValueException("El campo elegido no existe");
         
                } catch (NumberFormatException e) {
                    throw new ValueException("El dato ingresado no es numerico " + e.getMessage());
                }
                System.out.println("Especifica el valor para el campo");
                String value = in.nextLine().toString();
                if (option < 1 || option > 5) {
                    throw new ValueException("El campo elegido no existe");
                }

                if (option == 1) {
                    campo = "id";
                } else if (option == 2) {
                    campo = "calle";
                } else if (option == 3) {
                    campo = "numExt";
                } else if (option == 4) {
                    campo = "colonia";
                } else if (option == 5) {
                    campo = "cp";
                }

//se obtienen las direcciones.
                direcciones = bd.consultarDirecciones(campo, value);
                break;
            case 2:
                direcciones = bd.consultarDirecciones();
                break;
        }
        for (int i = 0; i < direcciones.size(); i++) {
            System.out.println(direcciones.get(i));
        }
    }

    /**
     * Esta función se encarga de solicitarle al usuario lel ID de la dirección
     * que desea modificar, el campo que desea modificar y el valor que le
     * asignará a ese campo
     *
     * @param bd La instancia creada de la clase BaseDeDatos.
     */
    private static void ActualizarDireccion(BaseDeDatos bd) throws ValueException {
        System.out.println("Inserta el ID de la dirección a actualizar");

        try {
            int id = Integer.parseInt(in.nextLine());
            System.out.println("Selecciona el campo: \n 1: calle \n 2: numExt \n 3: colonia \n 4: cp");
            int option = Integer.parseInt(in.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Introduce la nueva calle");
                    calle = in.nextLine().toString();
                    bd.actualizarDireccion("calle", calle, id);
                    break;
                case 2:
                    System.out.println("Introduce el nuevo numExt");
                    bd.actualizarDireccion("numExt", Integer.parseInt(in.nextLine()), id);
                    break;
                case 3:
                    System.out.println("Introduce la nueva oolonia");
                    bd.actualizarDireccion("colonia", in.nextLine().toString(), id);
                    break;
                case 4:
                    System.out.println("Introduce el nuevo cp");
                    bd.actualizarDireccion("cp", Integer.parseInt(in.nextLine()), id);
                    break;
                default:
                    throw new ValueException("Opcion no reconocida");
            }
        } catch (NumberFormatException ee) {
            throw new ValueException("El dato ingresado no es numerico " + ee.getMessage());
        }
    }

    /**
     * Este método le solicita al usuario el ID de la dirección que desea a
     * eliminar y se manda llamar a la función eliminarDireccion de la clase
     * BaseDeDatos, la cual se encarga de ejecutar la query.
     *
     * @param bd La instancia creada de la clase BaseDeDatos.
     */
    private static void EliminarDireccion(BaseDeDatos bd) throws ValueException {
        System.out.println("Inserta el ID de la dirección a eliminar");
        try {
            int id = Integer.parseInt(in.nextLine());
            bd.eliminarDireccion(id);
        }
        catch(NumberFormatException ee)
        {
            throw new ValueException("El dato ingresado no es numerico "+ee.getMessage());
        }
    }
}
