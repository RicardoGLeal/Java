package pr3_ecuacion;

import java.util.Scanner;

/**
 *
 * @author Ricardo González Leal
 * 
 * Clase PR3_Ecuación
 * Esta clase cuenta con las funciones y los métodos necesarios para solicitarle
 * al usuario que ingrese una ecuación exponencial, posteriormente por medio del
 * algoritmo de Newton se busca recursivamente una solución para la ecuación.
 * Se le solicita al usuario el valor de inicio, el cual significa el valor
 * inicial con el que se comenzará a realizar las verificaciones para encontrar
 * uno de los valores de la solución de la ecuación. Asimismo se le solicita
 * el valor de la precisión o tolerancia de error, el cuál se tomará en cuenta
 * para encontrar la solución.
 */
public class PR3_Ecuacion {

    static double[] ecuacion; //arreglo double que almacena la ecuacion
    static double[] ecuacionDer; //areglo double que almacena la ecuacion deriv.
    public static void main(String[] args) {
        float start=0;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduce el exponente mayor de la ecuacion");
        int maxExp = in.nextInt();
        
        ecuacion = IngresarCoeficientes(maxExp);
        ecuacionDer = CalcularDerivada(ecuacion);
        float precision = 0;
        System.out.println("Introduce el Inicio");
        start = in.nextFloat();
        System.out.println("Introduce la precision");
        precision = in.nextFloat();
        CalcularResultado(start, precision);    
    }
    
    /**
     * Función Sustituir
     * Sustituye la ecuación ingresada por el usuario por un valor determinado.
     * Cada posición del arreglo equivale al exponente al que está elevado ese 
     * elemento, el cual se eleva utilizado la instrucción Math.pow.
     * @param start valor deseado a sustituir.
     * @param ecuacion ecuacion de la que se sustituirá.
     * @return el resultado obtenido de la sustitución.
     */
    
    
    public static float Sustituir(double start, double[] ecuacion)
    {
       float valor=0;
        for (int i = 0; i < ecuacion.length; i++) {
            valor += ecuacion[i] * Math.pow(start, i);
        }
       return valor;
    }
    
    /**
     * Función IngresarCoeficientes
     * Esta función le solicita el coeficiente para cada uno de los términos.
     * Se crea un arreglo del tamaño del del exponente mayor de la ecuación + 1
     * y posteriormente por medio de un for se va recorriendo descendentemente
     * para realizar la asignación de los valores. Cada posición del arreglo 
     * equivale al exponente al que está elevado ese elemento. 
     * 
     * @param maxExp el valor del máximo exponente.
     * @return un arreglo double con cada uno de los valores ingresados.
     */
    private static double[] IngresarCoeficientes(int maxExp) {
        Scanner in = new Scanner(System.in);
        double[] newArray = new double[maxExp+1];
        for (int i = maxExp; i >= 0; i--) {
            System.out.println("Introduce el coeficiente para el exponente "+i);
            newArray[i]=in.nextDouble();
        }
        return newArray;
    }
    
    /**
     * Calcula la derivada de una ecuacion y la devuelve. Para esto únicamente
     * se multiplica el valor que se encuentra en cada elemento del arreglo por
     * la posición en la que se encuentran, la cual equivale al exponente. Ya
     * que se le tiene que restar 1 al exponente, la asignación del valor se la
     * hace al elemento actual -1. 
     * @param ecuacion Ecuacion de la que se obtendrá su derivada
     * @return La ecuación derivada
     */
    public static double[] CalcularDerivada(double[] ecuacion)
    {
        double[] ecuacionDer = new double[ecuacion.length];
        
        for (int i = ecuacion.length-1; i > 0; i--) {
            ecuacionDer[i-1] = i*ecuacion[i];
        }
        return ecuacionDer;
    }

    /**
     * Realiza los cálculos para A B y C recursivamente hasta que valorB
     * (ecuación sustituida) sea menor que el valor de la precisión. 
     * Cada vez que se ejecuta esta función se calcula el nuevo valorB y valorC,
     * los cuales corresponden al resultado de la ecuación sustituida y al
     * resultado de la ecuación derivada sustituida.
     * @param resultado valor con el que se realizarán los cálculos.
     * @param precision el error de preicisión para la obtención del resultado
     * final.
     */
    public static float lastResult;
    public static int times=0;
    
    private static void CalcularResultado(float resultado, float precision) 
    {
        float valorB = Sustituir(resultado, ecuacion); //se calcula el nuevo
        //valorB (ecuación sustituida) para el nuevo resultado.
        float valorC = Sustituir(resultado, ecuacionDer);//se calcula el nuevo
        //valorC (ecuación derivada sustituida) para el nuevo resultado.

        System.out.print(" res: "+resultado);   
        System.out.print(" valorB: "+valorB);
        System.out.print(" valorC: "+valorC);
        System.out.println("");
        
        resultado = resultado - valorB/valorC;
        
       //mientras el valor de la ecuación sustituida sea mayor al de la precision
       //esparada..
        if ((valorB <= precision && valorB >= precision * -1) || times>2){
            System.out.println("Resultado: " + resultado);
        } else {
            if(lastResult == resultado)
                times++;
            lastResult = resultado; //Para evitar el stackoverflow, se guarda el
            //valor del resultado anterior y se verifica si es igual al nuevo
            //resultado de las siguientes ocasiones. Si es igual
            
            CalcularResultado(resultado, precision);//se vuelve a rellamar a la
            //función con el nuevo resultado y la precision.

        }
    }
}
