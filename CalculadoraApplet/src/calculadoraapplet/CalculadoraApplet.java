package calculadoraapplet;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Ricardo González Leal
 * Clase CalculadoraApplet
 * La función de esta clase es la creación y la implementación de una calculadora
 * básica, en sitios web por medio de la herramienta JApplet.
 * Esta calculadora cuenta con dos campos de texto, un comboBox con las posibles
 * operaciones: suma, resta, multiplicación y división. Un teclado numérico
 * decimal con botón de borrado y un botón para obtener el resultado.
 * 
 */
public class CalculadoraApplet extends JApplet {

    public JTextField txtValor1 = new JTextField("");
    public JTextField txtValor2 = new JTextField("");
    public final JLabel txtResultado = new JLabel("0");
    public final JLabel txtEqual = new JLabel("=");
    public JComboBox<String> comboOperations = new JComboBox<String>();
    public final JButton calcular = new JButton("=");

    //Se crean los botones del teclado.
    public final JButton buttonOne = new JButton("1");
    public final JButton buttonTwo = new JButton("2");
    public final JButton buttonThree = new JButton("3");
    public final JButton buttonFour = new JButton("4");
    public final JButton buttonFive = new JButton("5");
    public final JButton buttonSix = new JButton("6");
    public final JButton buttonSeven = new JButton("7");
    public final JButton buttonEight = new JButton("8");
    public final JButton buttonNine = new JButton("9");
    public final JButton buttonZero = new JButton("0");
    public final JButton buttonDot = new JButton(".");
    public final JButton buttonCLR = new JButton("CLR");

    //Se crean los paneles que contendrán los TextFields, el ComboBox y los
    //botones.
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();

    //variable estática utilizada para saber cual textfield está seleccionado.
    static int selected = 1;
    
    /**
     * Función init.
     * Esta función se encarga de inicilizar el form de la calculadora,
     * lo cual incluye agregar las opciones de las operaciones al comboBox, 
     * configurar el tamaño y posición de los paneles, agregarle a los paneles
     * sus respectivos elementos (botones, textfields, combobox), configurar
     * los layouts de los paneles, y finalmente, añadir los paneles al form.
     */
    public void init() {
        setSize(340, 400);//dimensiones de la aplicación

        setLayout(null);
        comboOperations.addItem("+");//Se agrega la opcion de suma
        comboOperations.addItem("-");//Se agrega la opcion de resta
        comboOperations.addItem("*");//Se agrega la opcion de multiplicación
        comboOperations.addItem("/");//Se agrega la opcion de división
        panel1.setSize(75, 75);
        panel1.setLocation(10, 70);//posición del panel
        panel1.setLayout(new GridLayout(2, 1));
        //se agregan al panel los textfield de la fracción1.
        panel1.add(txtValor1);
        add(panel1);

        comboOperations.setSize(50, 50);//tamaño del comboBox
        comboOperations.setLocation(92, 60);//posicion del comboBox
        add(comboOperations);

        panel2.setSize(75, 75);//tamaño del panel
        panel2.setLocation(150, 70);//posición del panel
        panel2.setLayout(new GridLayout(2, 1));
        panel2.add(txtValor2);
        add(panel2);

        txtEqual.setLocation(240, 75);//se especifica la localizacion.
        txtEqual.setSize(20, 20);
        add(txtEqual);

        panel3.setSize(150, 20);//tamaño del panel
        panel3.setLocation(250, 80);//posición del panel
        panel3.setLayout(new GridLayout(2, 1));
        panel3.add(txtResultado);
        add(panel3);

        panel4.setSize(200, 200);
        panel4.setLayout(new GridLayout(4, 4));
        panel4.setLocation(25, 150);
        panel4.add(buttonSeven);
        panel4.add(buttonEight);
        panel4.add(buttonNine);
        panel4.add(buttonFour);
        panel4.add(buttonFive);
        panel4.add(buttonSix);
        panel4.add(buttonOne);
        panel4.add(buttonTwo);
        panel4.add(buttonThree);
        panel4.add(buttonZero);
        panel4.add(buttonDot);
        panel4.add(calcular);
        add(panel4);

        //Se implementa la interfaz focusListener, cuya función es detectar cuando
        //se selecciona un textfield, en este caso lo utilizo para detectar
        //cual de los dos textfields de la calculadora está seleccionado.
        //cuando se selecciona, se llama a la función select, y se le manda
        //un 1 o un 2 dependiendo del textfield seleccionado.
        txtValor1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                select(1);
            }  

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
        txtValor2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                select(2);
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
        
        //Se crean actionsListeners para los botones del teclado numérico,
        //los cuales llaman a la función putNumber y les manda el caractér
        //numérico correspondiente a la tecla.
        buttonOne.addActionListener((ActionEvent e) -> { putNumber('1'); });
        buttonTwo.addActionListener((ActionEvent e) -> { putNumber('2'); });
        buttonThree.addActionListener((ActionEvent e) -> { putNumber('3'); });
        buttonFour.addActionListener((ActionEvent e) -> { putNumber('4'); });
        buttonFive.addActionListener((ActionEvent e) -> { putNumber('5'); });
        buttonSix.addActionListener((ActionEvent e) -> { putNumber('6'); });
        buttonSeven.addActionListener((ActionEvent e) -> { putNumber('7'); });
        buttonEight.addActionListener((ActionEvent e) -> { putNumber('8'); });
        buttonNine.addActionListener((ActionEvent e) -> { putNumber('9'); });
        buttonZero.addActionListener((ActionEvent e) -> { putNumber('0'); });
        buttonDot.addActionListener((ActionEvent e) -> { putNumber('.'); });

        buttonCLR.setLocation(250,150);
        buttonCLR.setSize(70,50);
        add(buttonCLR);
        buttonCLR.addActionListener((ActionEvent e) -> { ClearScreen(); });
        
        
        calcular.addActionListener(
                new ActionListener() {
            //Listener para el botón de calcular el resultado.
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                //guarda el indice seleccionado del combobox.
                int comboPos = comboOperations.getSelectedIndex();
                double resultado;

                switch (comboPos) {
                case 0://suma
                    try { 
                        //se calcula el resultado y se escribe en los labels.
                        resultado = (Double.parseDouble(txtValor1.getText()) + (Double.parseDouble(txtValor2.getText())));
                        txtResultado.setText("" + resultado);
                    } catch (Exception e) { //si no se pudo realizar la
                        //operacion se le muestra una ventana al usuario
                        //que le dice que verifique los datos ingreados.
                        JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                        break;
                    }

                    break;

                case 1://resta
                    try {
                        //se calcula el resultado y se escribe en los labels.
                        resultado = (Double.parseDouble(txtValor1.getText()) - (Double.parseDouble(txtValor2.getText())));
                        txtResultado.setText("" + resultado);
                    } catch (Exception e) {//si no se pudo realizar la
                        //operacion se le muestra una ventana al usuario
                        //que le dice que verifique los datos ingreados.
                        JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                        break;
                    }
                    break;

                case 2://multiplicacion
                    try {                        
                        //se calcula el resultado y se escribe en los labels.
                        resultado = (Double.parseDouble(txtValor1.getText()) * (Double.parseDouble(txtValor2.getText())));
                        txtResultado.setText("" + resultado);
                    } catch (Exception e) {//si no se pudo realizar la
                        //operacion se le muestra una ventana al usuario
                        //que le dice que verifique los datos ingreados.
                        JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                        break;
                    }

                    break;

                case 3://division
                    try {
                        //se calcula el resultado y se escribe en los labels.
                        resultado = (Double.parseDouble(txtValor1.getText()) / (Double.parseDouble(txtValor2.getText())));
                        txtResultado.setText("" + resultado);
                    } catch (Exception e) {//si no se pudo realizar la
                        //operacion se le muestra una ventana al usuario
                        //que le dice que verifique los datos ingreados.
                        JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                        break;
                    }
                    break;
            }

        }
    }

);
    }
    /**
     * Función select
     * Se manda llamar cuando se selecciona uno de los dos textfields de la 
     * calculadora, si se selecciona el primero se recibe un 1, y si se recibe
     * el segundo, se recibe un 2.
     * @param i índice del textfield seleccionado.
     */
    public void select(int i) {
        selected = i;
    }

    /**
     * Se manda llamae cuando se presiona uno de los botones del teclado 
     * numérico. Lo que hace es escribir el número correspondiente al botón
     * en el textfield correspondiente, según la variable selected.
     * @param i caractér de la tecla presionada.
     */
    private void putNumber(char i) {
        switch(selected)
        {
            case 1:
                txtValor1.setText(txtValor1.getText()+i);
                break;
            case 2:
                txtValor2.setText(txtValor2.getText()+i);
                break;
            default:
                break;
        }
    }
    /**
     * Función ClearScreen
     * Se manda llamar cuando se presiona el botón de CLR. 
     * Lo que hace es limpiar los vavlores para los textfields de entrada
     * y para el label de resultado.
     */
    private void ClearScreen() {
        txtValor1.setText("");
        txtValor2.setText("");
        txtResultado.setText("0");
    }
}
