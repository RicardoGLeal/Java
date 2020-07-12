package pr4_crud;

/**
 * Clase Alumno Esta clase cuenta con todos los cinco atributos que se
 * almacenarán del alumno: registro, nombre, apellido, edad y semestre. Por
 * motivos de buenas prácticas estos son de tipo privado y únicamente se puede
 * acceder a ellos por medio de los getter y setter.
 *
 * @author ricar
 */
public class Alumno {

    /**
     * Constructor que inicializa un nuevo objeto tipo alumno con los datos
     * recibidos.
     *
     * @param registro Registro del alumno
     * @param nombre Nombre del alumno
     * @param apellido Apellido del alumno
     * @param edad Edad del alumno
     * @param semestre Semestre del alumno
     */
    public Alumno(int registro, String nombre, String apellido, int edad, int semestre) {
        this.registro = registro;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.semestre = semestre;
    }

    /**
     * getter registro
     *
     * @return registro del alumno
     */
    public int getRegistro() {
        return registro;
    }

    /**
     * setter registro
     *
     * @param registro Registro del alumno a establecer
     */
    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * setter nombre
     *
     * @param nombre Nombre del alumno a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    /**
     * setter apellido
     *
     * @param apellido Apellido del alumno a establecer
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    private int registro;
    private String nombre;
    private String apellido;
    private int edad;
    private int semestre;
}
