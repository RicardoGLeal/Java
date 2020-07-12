
package filosofos;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ricardo González Leal
 * 
 * Clase Tenedor
 * Esta clase cuenta con las variables necesarias para cada uno de los tenedores,
 * id sirve para poder ubicar a cada uno de los tenedores, ocupado sirve
 * para saber si el tenedor esta siendo utilizado por alguno de los filosofos,
 * asímismo se utiliza un Lock, esto para poder proteger que si uno de los 
 * filosofos está accediendo al tenedor y sus recursos, los demás filósofos no
 * puedan acceder a el.
 */
public class Tenedor {

    public Tenedor(int id) {
        this.id = id;
        this.ocupado = false;
    }
    
    int id;
    Boolean ocupado;

    public Boolean getOcupado() {
        return ocupado;
    }
    ReentrantLock lock = new ReentrantLock();

    public boolean lock(){
        return (lock.tryLock());
    }
    
    public void unlock(){
        lock.unlock();
    }
    
    //Set que cambia el valor de la variable ocupado.
    public void setOcupado(Boolean ocupado)
    {
        this.ocupado = ocupado;
    }
}
