package servletPackage;

/**
 *
 * @author Ricardo González Leal
 * Esta clase cuenta con los cinco atributos que se almacenarán para cada 
 * una de las direcciones, cuenta con getter, setters, y un constructor.
 */
public class Direccion {

    /**
     * Constructor que recibe todos los atributos.
     * @param id id de la direccion
     * @param calle calle de la direccion 
     * @param numExt numero exterior
     * @param colonia colonia
     * @param cp codigo postal
     */
    public Direccion(int id, String calle, int numExt, String colonia, int cp) {
        this.id = id;
        this.calle = calle;
        this.numExt = numExt;
        this.colonia = colonia;
        this.cp = cp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumExt() {
        return numExt;
    }

    public void setNumExt(int numExt) {
        this.numExt = numExt;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public Direccion(String calle, int numExt, String colonia, int cp) {
        this.calle = calle;
        this.numExt = numExt;
        this.colonia = colonia;
        this.cp = cp;
    }

    public int id;
    public String calle;
    public int numExt;
    public String colonia;
    public int cp;

    public Direccion() {
    }
    
    @Override
    public String toString() { 
        return "ID: " + id +" | Calle: "+calle + " | numExt: " + numExt + " | Colonia: " + colonia + " | cp: " + cp;
    }
}
