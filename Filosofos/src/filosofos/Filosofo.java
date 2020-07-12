package filosofos;

/**
 * @author Ricardo González Leal
 *
 * Clase Filosofos Esta clase sirve para controlar el comportamiento de cada uno
 * de los filó- sofos, acorde al problema planteado, en donde cinco filósofos
 * tienen que poder comer, contemplando que cada uno de ellos requiere dos
 * tenedores y unicamente hay uno por lugar. Esta clase esta diseñada para ser
 * trabajada por medio de hilos, en donde cada hilo controla a un único
 * filósofo. La clase cuenta con un id, para ubicar al filósofo, Dos objetos
 * tipo tenedor, los cuales corresponden a los que el filósofo puede tomar
 * (izquierda y derecha), y el tiempo que les toma cambiar de estado.
 * 
 * Para las acciones de esperar, tomar tenedores y comer se utiliza un tiempo
 * de espera de 3 segundos.
 *
 */
public class Filosofo extends Thread {

    //Posibles estados del filósofo:
    enum Estado {
        pensando,
        esperando,
        comiendo,
        intentandoPensar;
    }

    int numeroComidas = 0;
    public int id;
    public Estado estado;
    public Tenedor tenedorIzq;
    public Tenedor tenedorDer;
    float esperandoTime = 3000;

    //Constructor
    public Filosofo(int id, Estado estado, Tenedor tenedorIzq, Tenedor tenedorDer) {
        this.id = id;
        this.estado = estado;
        this.tenedorIzq = tenedorIzq;
        this.tenedorDer = tenedorDer;
    }

    @Override
    public void run() {
        while (true) {
            if (estado == estado.pensando) {
                try {
                    System.out.println("(FILOSOFO " + id + ") Pensando");
                    Thread.sleep((long) esperandoTime);
                } catch (Exception e) {
                }

                if (tenedorIzq.lock()) {
                    if (tenedorDer.lock()) {
                        if (!tenedorIzq.getOcupado() && !tenedorDer.getOcupado()) {
                            Comer();
                        } else {
                            Esperar();
                        }
                        tenedorDer.unlock();
                    } else {
                        Esperar();
                    }
                    tenedorIzq.unlock();
                } else {
                    Esperar();
                }
            }

            //si el filósofo está comiendo...
            if (estado == estado.comiendo) {
                try {
                    Thread.sleep((long) esperandoTime);
                } catch (Exception e) {
                }

                if (tenedorIzq.lock()) {
                    if (tenedorDer.lock()) {
                        Pensar();

                        tenedorDer.unlock();
                    } else {
                        estado = Estado.intentandoPensar;
                    }
                    tenedorIzq.unlock();
                } else {
                    estado = Estado.intentandoPensar;
                }
            }
            
            //si el filósofo está esperando...
            if (estado == estado.esperando) {
                try {
                    Thread.sleep((long) esperandoTime);
                } catch (Exception e) {
                }
                System.out.println("(FILOSOFO " + id + ") Esperando");
                if (tenedorIzq.lock()) {
                    if (tenedorDer.lock()) {
                        if (!tenedorIzq.getOcupado() && !tenedorDer.getOcupado()) {
                            Comer();
                        }
                        tenedorDer.unlock();
                    }
                    tenedorIzq.unlock();
                }

            }

            //si el filósofo está intentando pensar...
            if (estado == estado.intentandoPensar) {
                if (tenedorIzq.lock()) {
                    if (tenedorDer.lock()) {
                        Pensar();
                        tenedorDer.unlock();
                    }
                    tenedorIzq.unlock();
                }
            }
        }
    }

    /**
     * Esta función se encarga asignarle true a las variables 'ocupado' de los
     * tenedores del filósofo, asimismo imprime un mensaje indicando el que el
     * filosofo acaba de tomar el tenedor respectivo.
     */
    private void OcuparTenedores() {

        try {
            Thread.sleep((long) esperandoTime);
            System.out.println("(FILOSOFO " + id + ") ha tomado el tenedor " + tenedorIzq.id);
            tenedorIzq.setOcupado(true);

            Thread.sleep((long) esperandoTime);
            System.out.println("(FILOSOFO " + id + ") ha tomado el tenedor " + tenedorDer.id);
            tenedorDer.setOcupado(true);

        } catch (Exception e) {
        }
    }

    /**
     * Esta función se encarga asignarle false a las variables 'ocupado' de los
     * tenedores del filósofo, indicando que el filósofo ya termino de utilizar
     * sus tenedores.
     */
    private void DescuparTenedores() {
        tenedorIzq.setOcupado(false);
        tenedorDer.setOcupado(false);
    }

    /**
     * Esta función se manda llamar cuando ya terminó de comer y volverá a
     * pensar, lo que hace es desocupar los tenedores que estaba usando para
     * comer y cambiar su estado a pensar.
     */
    private void Pensar() {
        DescuparTenedores();
        estado = estado.pensando;
    }

    /**
     * Esta funcion cambia el estado del filósofo a esperando.
     */
    private void Esperar() {

        estado = estado.esperando;
    }

    /**
     * Función Comer Se manda llamar cuando el filósofo tiene hambre, se
     * encuentra en estado de espera y los tenedores que tiene a su lado están
     * desocupados. Lo que hace es mostrar un mensaje indicando que esta
     * comiendo, marcar como ocupados los tenedores para que otro filósofo no
     * los intente tomar y cambiar el estado del filósofo a comiendo.
     */
    private void Comer() {
        OcuparTenedores();
        numeroComidas++;
        System.out.println("(FILOSOFO " + id + ") Comiendo, lleva " + numeroComidas + " comidas");
        estado = estado.comiendo;

    }
}
