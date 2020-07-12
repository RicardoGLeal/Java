
package pr1_fraccionescontexto;
import java.util.*; 

/**
 * @author Ricardo González Leal
 * 
 * Clase Fraccion
 * Esta clase cuenta con dos variables enteras que almacenan el valor del
 * numerador y del denominador, asímismo cuentan con tres diccionarios para 
 * realizar las conversiones numéricas y textuales. A estos tres diccionarios
 * se le agregan los valores en el constructor de la clase.
 * También cuentan con dos variables de verificación (typedNumerador y
 * typedDenominador) para saber si se escribió el numerador o el denominador 
 * correctamente.
 
 */
public class Fraccion {
    
    //Diccionario utilizado para los numeradores
    public static Hashtable<String, Integer> numeradores = new Hashtable<String, Integer>();
    //Diccionario utilizado para los denominadores
    public static Hashtable<String, Integer> denominadores = new Hashtable<String, Integer>();
    //Dicionario utilizado para los denominadores diferentes: medio, cuarto, etc.
    public static Hashtable<String, Integer> specials = new Hashtable<String, Integer>();

    public int numerador;//Almacena el valor del numerador
    public int denominador;//Almacena el valor del denominador
    public boolean typedNumerador=false;//Sirve para verificara si se ingresó el numerador
    public boolean typedDenominador=false;//Sirve para verificara si se ingresó el denominador
     
    /**
     * Constructor, se insertan los elementos a los diccionarios
     */
    public Fraccion() {
        numeradores.put("cero", 0);
        numeradores.put("un", 1);
        numeradores.put("dos", 2);
        numeradores.put("tres", 3);
        numeradores.put("cuatro", 4);
        numeradores.put("cinco", 5);
        numeradores.put("seis", 6);
        numeradores.put("siete", 7);
        numeradores.put("ocho", 8);
        numeradores.put("nueve", 9);
        numeradores.put("diez", 10);
        
        numeradores.put("once", 11);
        numeradores.put("doce", 12);
        numeradores.put("trece", 13);
        numeradores.put("catorce", 14);
        numeradores.put("quince", 15);
        numeradores.put("dieciseis", 16);
        numeradores.put("diecisiete", 17);
        numeradores.put("dieciocho", 18);
        numeradores.put("diecinueve", 19);

        numeradores.put("veinti", 20);
        numeradores.put("veinte", 20);
        numeradores.put("treinta", 30);
        numeradores.put("cuarenta", 40);
        numeradores.put("cincuenta", 50);
        numeradores.put("sesenta", 60);
        numeradores.put("setenta", 70);
        numeradores.put("ochenta", 80);
        numeradores.put("noventa", 90);
        numeradores.put("cien", 100);
        
        numeradores.put("mil", 1000);

        //*************************************************************//
        denominadores.put("cero", 0);
        denominadores.put("un", 1);
        denominadores.put("dos", 2);
        denominadores.put("tres", 3);
        denominadores.put("cuatro", 4);
        denominadores.put("cinco", 5);
        denominadores.put("seis", 6);
        denominadores.put("siete", 7);
        denominadores.put("ocho", 8);
        denominadores.put("nueve", 9);
        denominadores.put("diez", 10);
        
        denominadores.put("once", 11);
        denominadores.put("doce", 12);
        denominadores.put("trece", 13);
        denominadores.put("catorce", 14);
        denominadores.put("quince", 15);
        denominadores.put("dieciseis", 16);
        denominadores.put("diecisiete", 17);
        denominadores.put("dieciocho", 18);
        denominadores.put("diecinueve", 19);
        
        denominadores.put("veinte", 20);
        denominadores.put("veinti", 20);
        denominadores.put("treinta", 30);
        denominadores.put("cuarenta", 40);
        denominadores.put("cincuenta", 50);
        denominadores.put("sesenta", 60);
        denominadores.put("setenta", 70);
        denominadores.put("ochenta", 80);
        denominadores.put("noventa", 90);
        denominadores.put("cien", 100);
        /*--------------------------------------------*/
        
        specials.put("entero", 1);
        specials.put("medio", 2);
        specials.put("tercio", 3);
        specials.put("cuarto", 4);
        specials.put("quinto", 5);
        specials.put("sexto", 6);
        specials.put("septimo", 7);
        specials.put("octavo", 8);
        specials.put("noveno", 9);
        specials.put("decimo", 10);
        specials.put("centesimo", 100);
    }
}
