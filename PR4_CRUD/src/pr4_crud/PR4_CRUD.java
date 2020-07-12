package pr4_crud;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ricardo González Leal
 *
 * Clase PR4_CRUD Esta clase se encarga del manejo de un CRUD para alumnos. La
 * clase cuenta con funciones para insertar, buscar, eliminar y actualizar
 * registros. Para el caso de insertar, el usuario tiene que ingresar los cinco
 * campos del alumno (Registro, Nombre, Apellido, Edad, Semestre), para el caso
 * de buscar, el usuario puede buscar registros especificando el valor para
 * cualquiera de los cinco campos, y se imprimira la información de todos los
 * alumnos coincidentes. Para actualizar, el usuario introduce el registro del
 * alumno y escoje el campo que desea modificar. Para eliminar únicamente se
 * pregunta el registro del alumno y se elimina. Para realizar las búsquedas,
 * actualizaciones y eliminaciones se implementan interfaces para realizar la
 * ordenación de los alumnos y para la búsqueda del dato.
 */
public class PR4_CRUD {

    public static Scanner in = new Scanner(System.in);
    public static Alumno[] alumnos;

    public static void main(String[] args) {
        alumnos = null;

        Criterio criterioOrdenacionRegistro = new Criterio() {
            @Override
            public boolean criterio(Object uno, Object dos) {
                Ordenar(alumnos, new Criterio() {
                    @Override
                    public boolean criterio(Object uno, Object dos) {
                        return (((Alumno) uno).getRegistro() > ((Alumno) dos).getRegistro());
                    }
                });
                return false;
            }
        };

        CriterioBusqueda criterioBusquedaRegistro = new CriterioBusqueda() {
            @Override
            public int found(Object dato, Object element) {

                if (((int) dato) == (((Alumno) element).getRegistro())) {
                    return 0;
                } else if (((int) dato) < (((Alumno) element).getRegistro())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        System.out.println("Alumnos");
        int indice = -1;
        while (true) {
            System.out.println("Seleccione una opcion");
            System.out.println("1: Insertar alumno (Altas)");
            System.out.println("2: Consultar alumnos");
            System.out.println("3: Actualizar alumnos");
            System.out.println("4: Eliminar alumno");
            System.out.println("5: Imprimir todo");

            int option = in.nextInt();
            switch (option) {
                case 1:
                    InsertarAlumno();
                    break;
                case 2:
                    if (alumnos == null) {
                        System.out.println("No se encontraron alumnos");
                        break;
                    }
                    Alumno[] alumnos2 = Consultar(criterioOrdenacionRegistro, criterioBusquedaRegistro);
                    if (alumnos2 == null) {
                        System.out.println("No se encontraron registros con ese dato");
                    } else {
                        for (int i = 0; i < alumnos2.length; i++) {
                            PrintRegistro(alumnos2, i);
                        }
                    }
                    break;
                case 3:
                    indice = AskAndFindRegistro(criterioOrdenacionRegistro, criterioBusquedaRegistro);
                    if (indice != -1) {
                        ActualizarAlumno(indice);
                    }
                    PrintRegistro(alumnos, indice);
                    break;
                case 4:
                    if (alumnos == null) {
                        System.out.println("No hay alumnos a eliminar");
                    } else {
                        Delete(criterioOrdenacionRegistro, criterioBusquedaRegistro);
                    }
                    break;
                case 5:
                    if (alumnos != null) {
                        PrintAll();
                    } else {
                        System.out.println("No hay alumnos a imprimir");
                    }
                    break;
                default:
                    System.out.println("Opcion Invalida");
                    break;
            }
        }
    }

   

    /**
     * Función AskAndFindRegistro Esta función se encarga de encontrar un alumno
     * por su registro, para esto se ordenan todos los Alumnos por su campo
     * registro, utilizando el método de Ordenación Busquja y el criterio de
     * Ordenación de Registro. Posteriormente se busca el registro utilizando la
     * búsqueda binaria y el Criterio de Búsqueda por Registro. Finalmente se
     * obtiene el indice.
     *
     * @param criterioOrdenacionRegistro Crtierio de Ordenación por Registro.
     * @param criterioBusquedaRegistro Criterio de Búsqueda por Registro.
     * @return el índice del alumno.
     */
    public static int AskAndFindRegistro(Criterio criterioOrdenacionRegistro, CriterioBusqueda criterioBusquedaRegistro) {
        System.out.println("Introduce el registro del alumno a eliminar");
        int registro = in.nextInt();
        Ordenar(alumnos, criterioOrdenacionRegistro);
        int indice = busquedaBinaria(alumnos, registro, criterioBusquedaRegistro);
        return indice;
    }

    /**
     * Método ActualizarAlumno Éste método se encarga de actualizar uno de los
     * campos de uno de los alumnos que se encuentran en el arreglo. Para esto
     * el usuario selecciona el campo que desea modificar y escribe el nuevo
     * valor a asignar al campo. posteriormente se actualiza por medio de los
     * setters de la clase.
     *
     * @param indice índice en el arreglo del alumno que se actualizará.
     */
    private static void ActualizarAlumno(int indice) {
        Scanner in = new Scanner(System.in);
        System.out.println("Elige el campo");
        System.out.println("1: Nombre");
        System.out.println("2: Apellido");
        System.out.println("3: Edad");
        System.out.println("4: Semestre");

        int option = in.nextInt();
        in.nextLine();
        switch (option) {
            case 1:
                System.out.println("Introduce el nuevo nombre");
                alumnos[indice].setNombre(in.nextLine());
                break;
            case 2:
                System.out.println("Introduce el nuevo apellido");
                alumnos[indice].setApellido(in.nextLine());
                break;
            case 3:
                System.out.println("Introduce la nueva edad");
                alumnos[indice].setEdad(in.nextInt());
                break;
            case 4:
                System.out.println("Introduce el nuevo semestre");
                alumnos[indice].setSemestre(in.nextInt());
                break;
            default:
                System.out.println("Opcion invalida");
                return;
        }
        System.out.println("Alumno actualizado!");
        PrintRegistro(alumnos, indice);
    }

    /**
     * Método InsertarAlumno Este método se encarga de crear un nuevo alumno e
     * insertarlo al arreglo alumnos. Para esto primero se le solicitan los
     * datos al usuario y poste- riormente se crea el objeto alumno mandándole
     * los datos ingresados al constructor. Posteriormente se revisa el tamaño
     * del arreglo de alumnos. Si este es null, únicamente se crea un nuevo
     * arreglo con tamaño 1 y se le asigna a la referencia del arreglo que se
     * creó al inicio. Si el tamaño del arreglo no es nulo, se crea un nuevo
     * arreglo con el mismo tamaño que el arreglo alumnos pero sumándole uno
     * para el nuevo alumno. Posterior- mente se copian todos los registros que
     * tenia el arreglo viejo al nuevo arreglo y finalmente la referencia del
     * viejo arreglo se le asigna al nuevo.
     */
    private static void InsertarAlumno() {
        int registro;
        String nombre;
        int semestre;
        int edad;
        String apellido;

        System.out.println("Introduce el Registro");
        try {
            registro = in.nextInt();
        } catch (Exception e) {
            System.out.println("Valor incorrecto");
            return;
        }

        System.out.println("Introduce el Nombre");
        in.nextLine();
        nombre = in.nextLine();

        System.out.println("Introduce el Apellido");
        apellido = in.nextLine();

        System.out.println("Introduce la Edad");
        edad = in.nextInt();

        System.out.println("Introduce el semestre");
        in.nextLine();
        semestre = in.nextInt();

        if (alumnos != null) {
            Alumno[] alumnos2 = new Alumno[alumnos.length + 1];
            System.arraycopy(alumnos, 0, alumnos2, 0, alumnos.length);
            alumnos = alumnos2;
        } else {
            alumnos = new Alumno[1];
        }

        alumnos[alumnos.length - 1] = new Alumno(registro, nombre, apellido, edad, semestre);
    }

    /**
     * Funcion Consultar Le solicita al usuario el campo por el que desea
     * buscar, posteriormente para cada uno de los campos se implementan dos
     * interfaces: una para rea- lizar una ordenación por dicho campo y otra
     * para realizar la búsqueda binaria en el arreglo por dicho campo.
     * Posteriormente se crea un nuevo arreglo que almacenará los registros de
     * los alumnos que coincidieron con el valor del campo indicado.
     *
     * @param criterioOrdenacionRegistro Crtierio de Ordenación por Registro.
     * @param criterioBusquedaRegistro Criterio de Búsqueda por Registro.
     * @return arreglo que almacena los registros de los alumnos coincidentes.
     */
    private static Alumno[] Consultar(Criterio criterioOrdenacionRegistro, CriterioBusqueda criterioBusquedaRegistro) {
        CriterioBusqueda criterioBusqueda = null;

        System.out.println("Elige el campo");
        System.out.println("1: Registro");
        System.out.println("2: Nombre");
        System.out.println("3: Apellido");
        System.out.println("4: Edad");
        System.out.println("5: Semestre");
        int option = in.nextInt();
        System.out.println("Escribe el valor a buscar");

        String valueString = "";
        int valueInt = 0;

        if (option == 2 || option == 3) {
            in.nextLine();
            valueString = in.nextLine();
        } else {
            valueInt = in.nextInt();
        }

        int indice = -1;

        switch (option) {
            case 1://Registro
                Ordenar(alumnos, criterioOrdenacionRegistro);
                indice = busquedaBinaria(alumnos, valueInt, criterioBusquedaRegistro);
                criterioBusqueda = criterioBusquedaRegistro;
                break;

            case 2: //Nombre
                Ordenar(alumnos, new Criterio() {
                    @Override
                    public boolean criterio(Object uno, Object dos) {
                        return (((Alumno) uno).getNombre().compareTo(((Alumno) dos).getNombre()) > 0);
                    }
                });

                CriterioBusqueda criterioBusquedaNombre = new CriterioBusqueda() {
                    @Override
                    public int found(Object dato, Object element) {
                        return (((String) dato).compareTo(((Alumno) element).getNombre()));
                    }
                };

                criterioBusqueda = criterioBusquedaNombre;
                break;

            case 3://Apellido
                Ordenar(alumnos, new Criterio() {
                    @Override
                    public boolean criterio(Object uno, Object dos) {
                        return (((Alumno) uno).getApellido().compareTo(((Alumno) dos).getApellido()) > 0);
                    }
                });
                CriterioBusqueda criterioBusquedaApellido = new CriterioBusqueda() {
                    @Override
                    public int found(Object dato, Object element) {
                        return (((String) dato).compareTo(((Alumno) element).getApellido()));
                    }
                };
                criterioBusqueda = criterioBusquedaApellido;
                break;

            case 4: //edad
                Ordenar(alumnos, new Criterio() {
                    @Override
                    public boolean criterio(Object uno, Object dos) {
                        return (((Alumno) uno).getEdad() > ((Alumno) dos).getEdad());
                    }
                });
                CriterioBusqueda criterioBusquedaEdad = new CriterioBusqueda() {
                    @Override
                    public int found(Object dato, Object element) {
                        if (((int) dato) == (((Alumno) element).getEdad())) {
                            return 0;
                        } else if (((int) dato) < (((Alumno) element).getEdad())) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                };
                criterioBusqueda = criterioBusquedaEdad;
                break;
            case 5: //semestre
                Ordenar(alumnos, new Criterio() {
                    @Override
                    public boolean criterio(Object uno, Object dos) {
                        return (((Alumno) uno).getSemestre() > ((Alumno) dos).getSemestre());
                    }
                });

                CriterioBusqueda criterioBusquedaSemestre = new CriterioBusqueda() {
                    @Override
                    public int found(Object dato, Object element) {
                        if (((int) dato) == (((Alumno) element).getSemestre())) {
                            return 0;
                        } else if (((int) dato) < (((Alumno) element).getSemestre())) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                };
                criterioBusqueda = criterioBusquedaSemestre;
                break;
        }
        Alumno[] alumnos2 = null;

        if (criterioBusqueda != null) {
            if (option == 2 || option == 3) {
                indice = busquedaBinaria(alumnos, valueString, criterioBusqueda);
            } else {
                indice = busquedaBinaria(alumnos, valueInt, criterioBusqueda);
            }

            if (indice != -1) {
                int endPos = indice;

                if (option == 2 || option == 3) {
                    while (endPos < alumnos.length && criterioBusqueda.found(valueString, alumnos[endPos]) == 0) {
                        endPos++;
                    }
                } else {
                    while (endPos < alumnos.length && criterioBusqueda.found(valueInt, alumnos[endPos]) == 0) {
                        endPos++;
                    }
                }
                alumnos2 = new Alumno[endPos - indice];
                System.arraycopy(alumnos, indice, alumnos2, 0, endPos - indice);
            }

        }
        return alumnos2;
    }

    
     /**
     * Método Delete Este método elimina un registro. Para realizar la
     * eliminación del registro el usuario tiene que ingresar el registro del
     * alumno a eliminar. Una vez que se ingreso el registro, todos los alumnos
     * del arreglo se ordenan por el campo registro, implementando el método
     * burbuja y el citerioOrdenaciónRegistro, que corresponde al criterio de
     * ordenación por registro. Posteriormente se crea un nuevo arreglo alumnos,
     * con el tamaño del arreglo anterior de alumnos menos uno, en este nuevo
     * arreglo se copian los datos que tenía el arreglo anterior de alumnos
     * menos el registro que se va a eliminar.
     *
     * @param criterioOrdenacionRegistro Criterio para la Ordenación para de los
     * alumnos por registro, deriva de la interfaz Criterio.
     * @param criterioBusquedaRegistro Criterio para la Búsqueda de de alumnos
     * por registro, deriva de la interfaz CriterioBúsqueda.
     */
    private static void Delete(Criterio criterioOrdenacionRegistro, CriterioBusqueda criterioBusquedaRegistro) {

        System.out.println("Introduce el registro del alumno a eliminar");
        int registro = in.nextInt();
        Ordenar(alumnos, criterioOrdenacionRegistro);
        int indice = busquedaBinaria(alumnos, registro, criterioBusquedaRegistro);

        if (indice != -1) {
            if (alumnos.length - 1 == 0) {
                alumnos = null;
            } else {
                Alumno[] alumnos2 = new Alumno[alumnos.length - 1];
                System.arraycopy(alumnos, 0, alumnos2, 0, indice);
                try {
                    System.arraycopy(alumnos, indice + 1, alumnos2, indice, alumnos2.length - indice);
                } catch (Exception e) {
                };
                alumnos = alumnos2;

            }
            System.out.println("Alumno Eliminado Correctamente");
        } else {
            System.out.println("No se encontró el registro");
        }
    }
    
    
    /**
     * Metodo de Ordenacion Burbuja, ordena un arreglo dependiendo del criterio
     * que se le mande.
     *
     * @param element Arreglo a ordenar
     * @param c Criterio a considerar para la ordenación
     */
    private static void Ordenar(Object[] element, Criterio c) {
        for (int i = 0; i < element.length; i++) {
            for (int j = i + 1; j < element.length; j++) {
                if (c.criterio(element[i], element[j])) {
                    Object temp = element[j];
                    element[j] = element[i];
                    element[i] = temp;
                }
            }
        }
    }

    /**
     * Método de busquedaBinaria el cual busca en un arreglo el valor
     * especificado por un criterio de busqueda.
     *
     * @param element Arreglo en el que se buscará
     * @param dato Dato a buscar
     * @param criterioBusqueda Criterio a consdierar para la búsqueda.
     * @return el indice en el que se encontró el dato.
     */
    public static int busquedaBinaria(Object[] element, Object dato, CriterioBusqueda criterioBusqueda) {
        int n = element.length;
        int centro, inf = 0, sup = n - 1;
        while (inf <= sup) {
            centro = (sup + inf) / 2;

            if (criterioBusqueda.found(dato, element[centro]) == 0) {
                {
                    while ((centro > 0 && criterioBusqueda.found(dato, element[centro - 1]) == 0)) {
                        centro--;
                    }
                    return centro;

                }
            } else if (criterioBusqueda.found(dato, element[centro]) == -1) {
                sup = centro - 1;
            } else {
                inf = centro + 1;
            }
        }
        return -1;
    }

    /**
     * Metodo PrintRegistro Imprime el registro del arreglo en la posicion
     * recibida por la variable indice
     *
     * @param alumnos Arreglo
     * @param indice Posicion del arreglo a imprimir.
     */
    private static void PrintRegistro(Alumno[] alumnos, int indice) {
        System.out.println("Registro: " + alumnos[indice].getRegistro());
        System.out.println("Nombre: " + alumnos[indice].getNombre());
        System.out.println("Apellido: " + alumnos[indice].getApellido());
        System.out.println("Edad: " + alumnos[indice].getEdad());
        System.out.println("Semestre: " + alumnos[indice].getSemestre());
        System.out.println("");
    }

    /**
     * Método PrintAll Este método imprime todos los registros del arreglo
     * alumnos.
     */
    private static void PrintAll() {
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println("Registro: " + alumnos[i].getRegistro());
            System.out.println("Nombre: " + alumnos[i].getNombre());
            System.out.println("Apellido: " + alumnos[i].getApellido());
            System.out.println("Edad: " + alumnos[i].getApellido());
            System.out.println("Semestre: " + alumnos[i].getSemestre());
            System.out.println("");
        }
    }
}
