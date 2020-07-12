package pr2_matrices;

import java.util.Scanner;

/**
 * @author Ricardo González Leal
 * Clase PR2_Matrices
 * Esta clase se encarga de solicitarle dos matrices al usuario, en donde él
 * tendrá que indicar el tamaño de éstas y ingresar los valores para todos los
 * elementos, posteriormente él podrá decidir qué función realizar: sumar 
 * matrices, restar matrices, obtener la transpuesta o la determinante.
 */
public class PR2_Matrices {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int filas = 0, columnas = 0;

        while(true)
        {
          System.out.println("Ingresa el numero de filas de la primer matriz");
          filas = in.nextInt();
          if(filas > 0)
          break;
        }
        
        System.out.println("Ingresa el numero de columnas de la primer matriz");
        columnas = in.nextInt();

        double[][] matrizA = new double[filas][columnas];
        AsignarDatosMatriz(filas, columnas, matrizA);

        System.out.println("Ingresa el numero de filas de la segunda matriz");
        filas = in.nextInt();
        System.out.println("Ingresa el numero de columnas de la segunda matriz");
        columnas = in.nextInt();

        double[][] matrizB = new double[filas][columnas];
        AsignarDatosMatriz(filas, columnas, matrizB);

        double[][] matrizC;

        while (true) {
            System.out.println("1: Sumar Matrices");
            System.out.println("2: Restar Matrices");
            System.out.println("3: Multiplicar Matrices");
            System.out.println("4: Transpuesta");
            System.out.println("5: Determinante");

            int option = in.nextInt();
            switch (option) {
                case 1:
                    if (matrizA.length == matrizB.length && matrizA[0].length == matrizB[0].length) {
                        matrizC = SumarMatrices(matrizA, matrizB);
                        PrintMatriz(matrizC);
                    } else {
                        System.out.println("Las matrices tienen distinta dimensión");
                    }
                    break;
                case 2:
                    if (matrizA.length == matrizB.length && matrizA[0].length == matrizB[0].length) {
                        matrizC = RestarMatrices(matrizA, matrizB);
                        PrintMatriz(matrizC);
                    } else {
                        System.out.println("Las matrices tienen distinta dimensión");
                    }
                    break;
                case 3:
                    if (matrizA.length == matrizB[0].length) {
                        matrizC = MultiplicarMatrices(matrizA, matrizB);
                        PrintMatriz(matrizC);
                    } else {
                        System.out.println("No se pueden multiplicar estas matrices");
                    }
                    break;
                case 4:
                    int opt2;
                    System.out.println("Selecciona la matriz");
                    System.out.println("1: Matriz A");
                    System.out.println("2: Matriz B");
                    opt2 = in.nextInt();
                    switch (opt2) {
                        case 1:
                            matrizC = CalcularTranspuesta(matrizA);
                            PrintMatriz(matrizC);

                            break;
                        case 2:
                            matrizC = CalcularTranspuesta(matrizB);
                            PrintMatriz(matrizC);
                            break;
                        default:
                            System.out.println("Opcion invalida");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Selecciona la matriz");
                    System.out.println("1: Matriz A");
                    System.out.println("2: Matriz B");
                    opt2 = in.nextInt();
                    double det = 0;
                    switch (opt2) {
                        case 1:
                            det = Determinante(matrizA);
                            System.out.println("Determinante: " + det);
                            break;
                        case 2:
                            det = Determinante(matrizB);
                            System.out.println("Determinante: " + det);
                            break;
                        default:
                            System.out.println("Opcion invalida");
                            break;
                    }
                    break;

            }
        }

    }

    /**
     * Metodo AsignarDatosMatriz
     * Se rellena la matriz recibida, para esto se le solicita al usuario
     * el valor para cada elemento de la matriz.
     * @param filas Numero de filas que contiene la matriz
     * @param columnas Numero de columnas que contiene la matriz
     * @param matriz Matriz en la que se almacenarán los datos
     */
    private static void AsignarDatosMatriz(int filas, int columnas, double[][] matriz) {
        Scanner in = new Scanner(System.in);
        int dato;
        for (int i = 0; i < filas; i++) {
            System.out.println("Fila " + (i + 1));
            for (int j = 0; j < columnas; j++) {
                System.out.println("Ingresa el valor " + (j + 1));
                dato = in.nextInt();
                matriz[i][j] = dato;
            }
        }
    }

    /**
     * Funcion SumarMatrices
     * Esta funcion realiza la suma de dos matrices recibidas. 
     * Los valores resultantes se almacenan en una nueva matriz resultante.
     * @param matrizA Primer matriz a sumar
     * @param matrizB Segunda matriz a sumar
     * @return newMatriz: matriz resultante de la suma entre matrizA y matrizB
     */
    private static double[][] SumarMatrices(double[][] matrizA, double[][] matrizB) {
        int filas = matrizA.length;
        int columnas = matrizA[0].length;

        double[][] newMatriz = new double[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                newMatriz[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
        return newMatriz;
    }

    /**
     * Metodo PrintMatriz
     * Este método imprime los valores de una matriz recibida.
     * @param matriz Matriz a Imprimir
     */
    private static void PrintMatriz(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * Funcion RestarMatrices
     * Esta funcion realiza la resta de dos matrices recibidas. 
     * Los valores resultantes de la resta se almacenan en una nueva matriz 
     * resultante.
     * @param matrizA Primer matriz a rsetar
     * @param matrizB Segunda matriz a restar
     * @return newMatriz: matriz resultante de la resta entre matrizA y matrizB
     */
     
    private static double[][] RestarMatrices(double[][] matrizA, double[][] matrizB) {
        int filas = matrizA.length;
        int columnas = matrizA[0].length;

        double[][] newMatriz = new double[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                newMatriz[i][j] = matrizA[i][j] - matrizB[i][j];
            }
        }
        return newMatriz;
    }

    /**
     * Funcion MultiplicarMatrices
     * Esta funcion realiza la multiplicación de dos matrices recibidas. 
     * Los valores resultantes de la multiplicación se almacenan en una nueva 
     * matriz resultante.
     * @param matrizA Primer matriz a multiplicar.
     * @param matrizB Segunda matriz a multiplicar.
     * @return newMatriz: matriz resultante de la multiplicación entre matrizA y 
     * matrizB.
     */
    private static double[][] MultiplicarMatrices(double[][] matrizA, double[][] matrizB) {
        int filasA = matrizA.length;
        int columnasA = matrizA[0].length;

        int filasB = matrizB.length;
        int columnasB = matrizB[0].length;

        double[][] newMatriz = new double[filasA][columnasB];

        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizB[0].length; j++) {
                for (int k = 0; k < matrizA[0].length; k++) {
                    newMatriz[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
        return newMatriz;
    }

    /**
     * Funcion CalcularTranspuesta
     * Esta funcion se encarga de recibir una matriz y calcular su transpuesta
     * (invertirla) y guardarla en una nueva matriz la cual es retornada.
     * @param matrizA
     * @return newMatriz matriz transpuesta.
     */
    private static double[][] CalcularTranspuesta(double[][] matrizA) {
        double[][] newMatriz = new double[matrizA[0].length][matrizA.length];

        for (int x = 0; x < matrizA.length; x++) {//filas
            for (int y = 0; y < matrizA[x].length; y++) {//columnas
                newMatriz[y][x] = matrizA[x][y];
            }
        }
        return newMatriz;
    }

    /**
     * Funcion CalcularDeterminante
     * Esta funcion se encarga de recibir una calcular el determinante de una
     * matriz recibida. Para esto se implementa el método de ley general,
     * en donde inicialmente se toma una fila como pibote y la parte resultante
     * de la matriz se divide en submatrices de 2x2, las cuales se multiplican
     * por los valores de la fila pibote. 
     * @param matriz Matriz de la que se calculará la determinante.
     * @return el valor de la determinante
     */
    private static double Determinante(double[][] matriz) {
        double determinante;
        if (matriz.length == 2) {
            determinante = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
            return determinante;
        }
        double suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            //se crea una nueva matriz eliminando una fila y una columna
            double[][] newMatriz = new double[matriz.length - 1][matriz.length - 1];
            for (int j = 0; j < matriz.length; j++) {
                if (j != i) {
                    for (int k = 1; k < matriz.length; k++) {
                        int indice = -1;
                        if (j < i) {
                            indice = j;
                        } else if (j > i) {
                            indice = j - 1;
                        }
                        newMatriz[indice][k - 1] = matriz[j][k];
                    }
                }
            }
            if (i % 2 == 0) {
                suma += matriz[i][0] * Determinante(newMatriz);
            } else {
                suma -= matriz[i][0] * Determinante(newMatriz);
            }
        }
        return suma;
    }

}
