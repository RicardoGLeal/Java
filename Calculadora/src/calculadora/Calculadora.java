package calculadora;
import java.util.Scanner;

/**
 *
 * @author Ricardo González Leal
 * Esta clase se encarga de crear un objeto de la clase Form, y hacerla visible.
 */
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Form form1 = new Form(); //se crea el objeto tipo form.
        form1.setVisible(true);//se hace visible el form de la calculadora
        
    }
}
