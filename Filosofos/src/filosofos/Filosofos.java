
package filosofos;
/**
 * @author Ricardo González Leal
 * 
 * Clase Filosofos
 * Esta clase se encarga de crear los los objetos y referencias para cada uno
 * de los tenedores que cuentan los filósofos, asímismo se crean los hilos 
 * asignándoles como referencia a cada uno de los filósofos que hay en la mesa.

 */
public class Filosofos {

    public static void main(String[] args) {
                
        Tenedor tenedor1 = new Tenedor(1);
        Tenedor tenedor2 = new Tenedor(2);
        Tenedor tenedor3 = new Tenedor(3);
        Tenedor tenedor4 = new Tenedor(4);
        Tenedor tenedor5 = new Tenedor(5);

        Thread filosofo1 = new Filosofo(1, Filosofo.Estado.pensando, tenedor1, tenedor2);
        Thread filosofo2 = new Filosofo(2, Filosofo.Estado.pensando, tenedor2, tenedor3);
        Thread filosofo3 = new Filosofo(3, Filosofo.Estado.pensando, tenedor3, tenedor4);
        Thread filosofo4 = new Filosofo(4, Filosofo.Estado.pensando, tenedor4, tenedor5);
        Thread filosofo5 = new Filosofo(5, Filosofo.Estado.pensando, tenedor5, tenedor1);

        filosofo1.start();
        filosofo2.start();
        filosofo3.start();
        filosofo4.start();
        filosofo5.start();
    }

}
