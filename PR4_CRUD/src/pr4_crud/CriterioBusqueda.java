package pr4_crud;

/**
 * @author Ricardo González Leal
 * Interfaz CriterioBusqueda
 * Esta interfaz cuenta la función int fuound, utilizada en el algotitmo de
 * búsqueda binaria.
 */
public interface CriterioBusqueda { 
    /**
     * Función found
     * Esta función se utiliza para comprobar si el elemento dato
     * ya se encontró en el arreglo. Esta función es sobreescrita para cada uno
     * de los campos que la utiliza la clase Alumno.
     * @param dato Dato a verificar
     * @param element Arreglo de Objetos
     * @return 
     */
    public int found(Object dato, Object element);
}
