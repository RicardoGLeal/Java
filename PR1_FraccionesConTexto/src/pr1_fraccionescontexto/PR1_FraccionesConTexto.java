/*

 */
package pr1_fraccionescontexto;

import java.util.*;

/**
 *
 * @author Ricardo González Leal
 * 
 * Clase PR1_FraccionesConTezto 
 * Esta clase le solicita al usuario que ingrese de manera textual una operación 
 * matemática básica (suma, resta, multiplicación o divison) involucrando dos
 * fracciones.
 * La clase contiene las funciones y los métodos necesarios para obtener los 
 * valores numérios de ambas fracciones y realizar la operación. Finalmente la
 * fracción resultante se convierte a texto y se le muestra al usuario.
 */
public class PR1_FraccionesConTexto {

    static String[] parts;
    static int denominadorPos;

    public static void main(String[] args) {

        System.out.println("Introduce la operacion de fracciones ");
        IngresarOperacion();
    }

    /**
     * Función IngresarOperacion
     * Se utiliza para recibir de manera textual la operación de fracciones 
     * ingresada por el usuario. Esta función guarda en un arreglo de string
     * cada una de las palabras que contiene la operación, posteriormente se 
     * analiza el inicio y el fin de cada fracción y en dónde se encuentra la
     * palabra que representa el tipo de operación a realizar (suma, resta, etc)
     * Se crean dos objetos tipo Numbers, las cuales almacenarán los datos de
     * cada una de las fracciones.
     */
    private static void IngresarOperacion() {
        Scanner in = new Scanner(System.in);
        String texto;
        texto = in.nextLine();
        parts = texto.split(" ");

        int signoPos = 0; //signoPos guarda la posicion del arreglo parts donde
        //se encuentra la palabra que indica el tipo de operacion (suma, etc).
        
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("mas") || parts[i].equals("menos") || parts[i].equals("por") || parts[i].equals("entre")) {
                signoPos = i;
            }
        }

        Fraccion fraccion1 = new Fraccion(); //Se crea la primer fraccion
        fraccion1 = convertToNumber(0, signoPos); //Se obtienen los valores
        //numericos de la fraccion.
        
        if (!fraccion1.typedNumerador) {
            System.out.println("Error al ingresar el numerador de la primer fraccion");
            return;
        }
        if (!fraccion1.typedDenominador || fraccion1.denominador == 0) {
            System.out.println("Error al ingresar el denominador de la primer fraccion");
            return;
        }

        Fraccion fraccion2 = new Fraccion();
        fraccion2 = convertToNumber(signoPos, parts.length);
        if (!fraccion2.typedNumerador) {
            System.out.println("Error al ingresar el numerador de la segunda fraccion");
            return;
        }
        if (!fraccion2.typedDenominador || fraccion2.denominador == 0) {
            System.out.println("Error al ingresar el denominador de la segunda fraccion");
            return;
        }

        PrintOperation(fraccion1, fraccion2, signoPos);

        Fraccion fraccion3 = new Fraccion();
        switch (parts[signoPos]) {
            case "mas":
                fraccion3 = SumarFracciones(fraccion1, fraccion2);
                break;

            case "menos":
                fraccion3 = RestarFracciones(fraccion1, fraccion2);
                break;

            case "por":
                fraccion3 = MultiplicarFracciones(fraccion1, fraccion2);
                break;

            case "entre":
                fraccion3 = DividirFracciones(fraccion1, fraccion2);
                break;
        }

        System.out.print(fraccion3.numerador);
        System.out.print("/");
        System.out.print(fraccion3.denominador);
        System.out.println("");
        if (fraccion3.denominador == 0) {
            System.out.println("Operacion invalida, ceroavos");
        } else {
            TraducirNumerador(fraccion3);
            TraducirDenominador(fraccion3);
        }

    }

    /**
     * Se utiliza para convertir una fraccion expresada en texto en una fraccion
     * numérica. Para esto se utilizan dos diccionarios que implementa la clase
     * Numbers, los cuales contienen las palabras utilizadas para la definición
     * del numerador y denominador.
     * @param startPos Indice del arreglo en el que iniciará la conversión.
     * @param endPos Indice del arreglo en el que termina la conversión.
     * @return Fracción resultante.
     */
    private static Fraccion convertToNumber(int startPos, int endPos) {
        Fraccion fraccion = new Fraccion(); //Se crea una nueva fracción
        StringBuilder str = new StringBuilder(); //Se crea un StringBuilder
        Boolean isDenominador = false;
        Boolean decenas = false;

        for (int i = startPos; i < endPos; i++) {
            str = new StringBuilder(); //Se referencia a un nuevo StringBuilder

            if (parts[i].length() > 3) {
                for (int j = parts[i].length() - 4; j < parts[i].length(); j++) {
                    str.append(parts[i].charAt(j));
                    if (str.toString().equals("avos")) {
                        isDenominador = true;
                        break;
                    }
                }
            }
            str = new StringBuilder();

            for (int j = 0; j < parts[i].length(); j++) {
                str.append(parts[i].charAt(j));

                if (fraccion.numeradores.containsKey(str.toString())) {
                    if (!isDenominador) {

                        if (fraccion.numeradores.get(str.toString()) < 10)//Si son unidades..
                        {
                            fraccion.numerador += fraccion.numeradores.get(str.toString());//Se le suma al numerador el valor obtenido del diccionario
                            isDenominador = true;//Al haber recibido unidades se da por hecho que ya se termino de ingresar el numerador.
                            fraccion.typedNumerador = true;
                        } else if (fraccion.numeradores.get(str.toString()) >= 10)//Si son decenas
                        {
                            if (decenas)//si ya se habia escrito una decena
                            {
                                isDenominador = true; //Se da por hecho que ya se termino de ingresar el numerador.
                                fraccion.denominador += fraccion.denominadores.get(str.toString());//Se le suma al denominador el valor obtenido del diccionario.
                                fraccion.typedDenominador = true;
                            } else {
                                fraccion.numerador += fraccion.numeradores.get(str.toString());//Se le suma al numerador el valor obtenido del diccionario.
                                fraccion.typedNumerador = true; //la propiedad typedNumerador se hace true, que indica que si se ingreso un numerador.
                                decenas = true;
                            }
                        }
                        str = new StringBuilder();

                    } else {
                        fraccion.denominador += fraccion.denominadores.get(str.toString());
                        fraccion.typedDenominador = true;
                        str = new StringBuilder();
                    }
                } else {
                    if (fraccion.denominadores.containsKey(str.toString())) {
                        isDenominador = true;
                        fraccion.denominador += fraccion.denominadores.get(str.toString());
                        fraccion.typedDenominador = true;
                    } else if (fraccion.specials.containsKey(str.toString())) {
                        isDenominador = true;
                        fraccion.denominador += fraccion.specials.get(str.toString());
                        fraccion.typedDenominador = true;
                    }
                }

            }
        }
        return fraccion;
    }

    /**
     * Funcion SumarFracciones
     * Esta funcion recibe dos fracciones y las suma, en donde el numerador de
     * la fracción resultante se calcula sumando la multiplicación
     * del numerador de la primer fraccion por el denominador de la sugunda más
     * el denominador de la primer fraccion por el numerador de la segunda.
     * El denominador se obtiene multiplicando el denominador de ambas.
     * @param fraccion1 Primer fraccion
     * @param fraccion2 Segunda fraccion
     * @return Fraccion Nueva Resultante
     */
    private static Fraccion SumarFracciones(Fraccion fraccion1, Fraccion fraccion2) {
        Fraccion newFraccion = new Fraccion();
        newFraccion.numerador = (fraccion1.numerador * fraccion2.denominador) + (fraccion1.denominador * fraccion2.numerador);
        newFraccion.denominador = fraccion1.denominador * fraccion2.denominador;
        return newFraccion;
    }

    /**
     * Funcion RestarFracciones
     * Esta funcion recibe dos fracciones y las resta, en donde el numerador de
     * la fracción resultante se calcula restando la multiplicación
     * del numerador de la primer fraccion por el denominador de la sugunda menos
     * el denominador de la primer fraccion por el numerador de la segunda.
     * El denominador se obtiene multiplicando el denominador de ambas.
     *
     * @param fraccion1 Primer fraccion
     * @param fraccion2 Segunda fraccion
     * @return Fraccion Nueva Resultante
     */
    private static Fraccion RestarFracciones(Fraccion fraccion1, Fraccion fraccion2) {
        Fraccion newFraccion = new Fraccion();
        newFraccion.numerador = (fraccion1.numerador * fraccion2.denominador) - (fraccion1.denominador * fraccion2.numerador);
        newFraccion.denominador = fraccion1.denominador * fraccion2.denominador;
        return newFraccion;
    }

    /**
     * Funcion MultiplicarFracciones
     * Multiplica dos fracciones, en donde el numerador de la fracción resultante
     * se calcula multiplicando los numeradores de ambas y el denominador se
     * calcula multiplicando los denominadores de ambas. 
     * 
     * @param fraccion1 Primer fraccion
     * @param fraccion2 Segunda fraccion
     * @return Fraccion Nueva Resultante
     */
    private static Fraccion MultiplicarFracciones(Fraccion fraccion1, Fraccion fraccion2) {
        Fraccion newFraccion = new Fraccion();
        newFraccion.numerador = fraccion1.numerador * fraccion2.numerador;
        newFraccion.denominador = fraccion1.denominador * fraccion2.denominador;
        return newFraccion;
    }

   /**
     * Funcion DividirFracciones
     * Divide dos fracciones, en donde el numerador de la fracción resultante
     * se calcula multiplicando el numerador de la primer fracción por el
     * denominador de la segunda. El denominador de la fracción
     * resultante se calcula multiplicando el denominador de la primer fracción
     * por el numerador de la segunda.
     * 
     * @param fraccion1 Primer fraccion
     * @param fraccion2 Segunda fraccion
     * @return Fraccion Nueva Resultante
     */
    private static Fraccion DividirFracciones(Fraccion fraccion1, Fraccion fraccion2) {
        Fraccion newFraccion = new Fraccion();
        newFraccion.numerador = fraccion1.numerador * fraccion2.denominador;
        newFraccion.denominador = fraccion1.denominador * fraccion2.numerador;
        return newFraccion;
    }

    /**
     * Método TraducirNumerador
     * Esta función traduce a texto el numerador de la fracción resultante.
     * Lo que hace es obtener cuantos miles, centenas, decenas y enteros hay
     * en el numerador, posteriormente para cada uno de ellos se manda llamar
     * a su respectiva función que se encarga de traducir a texto la parte
     * correspondiente (miles, centenas, decenas y enteros).
     * @param fraccion Fraccion en la que se encuentra el numerador a convertir.
     */
    private static void TraducirNumerador(Fraccion fraccion) {
        String key = "";
        int miles = fraccion.numerador / 1000;
        int centenas = (fraccion.numerador - miles * 1000) / 100;
        int decenas = (fraccion.numerador - miles * 1000 - centenas * 100) / 10 * 10;
        int enteros = fraccion.numerador - miles * 1000 - centenas * 100 - decenas;

        if (miles > 0) {
            PrintMiles(miles, Fraccion.numeradores);
        }

        if (centenas > 0) {
            PrintCentenas(centenas, Fraccion.numeradores);
        }

        if (decenas > 0) {
            if (decenas == 10 && enteros < 6) {
                for (Map.Entry entry : Fraccion.numeradores.entrySet()) {
                    if (entry.getValue().equals(decenas + enteros)) {
                        key = (String) entry.getKey();
                        System.out.print(" " + key);
                        return;
                    }
                }
            } else {
                PrintDecenas(decenas, enteros, Fraccion.numeradores);
            }
        }

        for (Map.Entry entry : Fraccion.numeradores.entrySet()) {
            if (entry.getValue().equals(enteros)) {
                key = (String) entry.getKey();
                if (decenas > 20) {
                    System.out.print(" y " + key);
                } else {
                    System.out.print(key);
                }
                break; //breaking because its one to one map
            }
        }
    }
    
    /**
     * Método TraducirDenominador
     * Esta función traduce a texto el denominador de la fracción resultante.
     * Lo que hace es obtener cuantos miles, centenas, decenas y enteros hay
     * en el denominador, posteriormente para cada uno de ellos se manda llamar
     * a su respectiva función que se encarga de traducir a texto la parte
     * correspondiente (miles, centenas, decenas y enteros).
     * @param fraccion Fraccion en la que se encuentra el denominador a convertir.
     */
    private static void TraducirDenominador(Fraccion fraccion) {
        String key = "";
        int miles = fraccion.denominador / 1000;
        int centenas = (fraccion.denominador - miles * 1000) / 100;
        int decenas = (fraccion.denominador - miles * 1000 - centenas * 100) / 10 * 10;
        int enteros = fraccion.denominador - miles * 1000 - centenas * 100 - decenas;

        if (fraccion.denominador > 0 && fraccion.denominador < 10) {
            for (Map.Entry entry : Fraccion.specials.entrySet()) {
                if (entry.getValue().equals(fraccion.denominador)) {

                    key = (String) entry.getKey();
                    System.out.print(" " + key);
                    if (fraccion.numerador > 1) {
                        System.out.print("s");
                    }
                    return;
                }
            }
        }

        if (miles > 0) {
            PrintMiles(miles, Fraccion.denominadores);
        }
        if (centenas > 0) {
            PrintCentenas(centenas, Fraccion.denominadores);
        }
        if (decenas > 0) {
            PrintDecenas(decenas, enteros, Fraccion.denominadores);
        }

        for (Map.Entry entry : Fraccion.denominadores.entrySet()) {
            if (entry.getValue().equals(enteros)) {
                key = (String) entry.getKey();
                if (key.equals("entero") && decenas > 0) {
                    key = "un";
                }
                if (decenas > 20) {
                    System.out.print(" y " + key);
                } else {
                    System.out.print(key);
                }
                break;
            }
        }
        System.out.print("avos");
    }

    /**
     * Función PrintMiles
     * Convierte a texto la cantidad de miles expresada numéricamente. Para
     * esto se realiza una busqueda por valor en el diccionario recibido, 
     * al encontrarse una coincidencia se almacena en la variable key el key 
     * correspondiente a dicho valor, y se imprime.
     * @param miles numero de miles
     * @param diccionary diccionario de donde buscará los valores
     */
    private static void PrintMiles(int miles, Hashtable<String, Integer> diccionary) {
        String key = "";
        for (Map.Entry entry : diccionary.entrySet()) {
            if (entry.getValue().equals(miles)) {
                key = (String) entry.getKey();
                if (key.equals("un")) {
                    System.out.print(" mil");
                } else {
                    System.out.print(" " + key + " mil");
                }
                break; //breaking because its one to one map
            }
        }
    }

    /**
     * Método PrintCentenas
     * Convierte a texto la cantidad de centenas recibidas. Para
     * esto se realiza una busqueda por valor en el diccionario recibido, 
     * al encontrarse una coincidencia se almacena en la variable key el key 
     * correspondiente a dicho valor, y se imprime.
     * Existen algunas restricciones para algunos valores.
     * @param centenas Numero de centenas
     * @param diccionary Diccionario de donde consultará
     */
    private static void PrintCentenas(int centenas, Hashtable<String, Integer> diccionary) {
        String key = "";
        for (Map.Entry entry : diccionary.entrySet()) {
            if (entry.getValue().equals(centenas)) {
                key = (String) entry.getKey();
                if (key.equals("un")) {
                    System.out.print(" ciento");
                    break;
                } else if (key.equals("cinco")) {
                    key = "quini";
                } else if (key.equals("siete")) {
                    key = "sete";
                } else if (key.equals("nueve")) {
                    key = "nove";
                }
                System.out.print(" " + key + "cientos");
                break; //breaking because its one to one map
            }
        }
    }

   /**
     * Método PrintDecenas
     * Convierte a texto la cantidad de decenas recibidas. Para
     * esto se realiza una busqueda por valor en el diccionario recibido, 
     * al encontrarse una coincidencia se almacena en la variable key el key 
     * correspondiente a dicho valor, y se imprime.
     * Existen algunas restricciones para algunos valores.
     * @param centenas Numero de centenas
     * @param diccionary Diccionario de donde consultará
     */
    private static void PrintDecenas(int decenas, int enteros, Hashtable<String, Integer> diccionary) {
        String key = "";
        for (Map.Entry entry : diccionary.entrySet()) {
            if (entry.getValue().equals(decenas)) {
                key = (String) entry.getKey();
                if (key.equals("diez") && enteros > 5) {
                    System.out.print(" " + "dieci");
                } else {
                    System.out.print(" " + key);
                }
                break; //breaking because its one to one map
            }
        }
    }

    /**
     * Esta función imprime la operación de fracciones ingresada por el usuario
     * pero en forma numérica
     * @param fraccion1 Primer fraccion
     * @param fraccion2 Segunda Fraccion
     * @param signoPos Posición del arreglo en dónde se especifica el tipo de 
     * operación a realizar (suma, resta, etc).
     */
    private static void PrintOperation(Fraccion fraccion1, Fraccion fraccion2, int signoPos) {
        System.out.print(fraccion1.numerador); //se imprime el numerador de la fraccion 1.
        System.out.print("/");
        System.out.print(fraccion1.denominador); //se imprime el denominador de la fraccion 1.

        switch (parts[signoPos]) {
            case "mas":
                System.out.print(" + ");
                break;

            case "menos":
                System.out.print(" - ");
                break;

            case "por":
                System.out.print(" * ");
                break;

            case "entre":
                System.out.print(" / ");
                break;
        }

        System.out.print(fraccion2.numerador); //se imprime el numerador de la fraccion 2.
        System.out.print("/");
        System.out.print(fraccion2.denominador);//se imprime el denominador de la fraccion 2.
        System.out.println("");
    }
}
