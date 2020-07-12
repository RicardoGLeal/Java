package calculadora;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ricardo González Leal Esta clase se encarga de crear el diseño y la
 * programación para la calculadora de fracciones. Este cuenta con 4 TextFields,
 * en los que el usuario ingresa los numeradores y denominadores de las dos
 * fracciones, cuenta con una combobox para que el usuario escoja el tipo de
 * operación (suma, resta, mult o division) cuenta con dos label para mostrar la
 * fracción resultante. cuenta con un botón, en donde al presionarle se ejecuta
 * el evento que hace que calcule el resultado. También hay tres paneles, uno
 * para cada fraccion. El usuario ingresa los datos de las fracciones,
 * selecciona la operacion a realizar y finalmente da click al botón de
 * calcular, en donde se implementa un ActionListener para obtener el valor de
 * la fracción resultante.
 *
 */
public class Form extends JFrame {

    public JTextField txtNumerador1 = new JTextField("0");
    public JTextField txtDenominador1 = new JTextField("0");
    public JTextField txtNumerador2 = new JTextField("0");
    public JTextField txtDenominador2 = new JTextField("0");
    public final JLabel txtNumerador3 = new JLabel("0");
    public final JLabel txtDenominador3 = new JLabel("0");
    public final JLabel txtEqual = new JLabel("=");
    public JComboBox<String> comboOperations = new JComboBox<String>();
    public final JButton calcular = new JButton("Calcular");

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();

    /**
     * Constructor de la forma de la calculadora.
     */
    public Form() {
        initForma();
    }

    /**
     * Metodo initForma Este metodo se encarga de configurar la interfaz gráfica
     * y el funcionamiento de la calculadora de fracciones. Se configura el
     * tamaño de la ventana, de los paneles, del botón de calcular, del comboBox
     * y de los TextFields, asímismo se especifica la posición para cada uno de
     * estos. Para poder realizar operaciones en la calculadora, se tienen que
     * ingresar los valores de los numeradores y denominadores, posteriormente
     * elegir la operación a realizar por medio del combobox y presionar el
     * botón de calcular, finalmente se mostrará el resultado de la fracción
     * resultante.
     */
    private void initForma() {
        setTitle("Calculadora de Fracciones");
        setSize(340, 400);//dimensiones de la aplicación
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        comboOperations.addItem("+");//Se agrega la opcion de suma
        comboOperations.addItem("-");//Se agrega la opcion de resta
        comboOperations.addItem("*");//Se agrega la opcion de multiplicación
        comboOperations.addItem("/");//Se agrega la opcion de división
        panel1.setSize(75, 150);
        panel1.setLocation(10, 70);//posición del panel
        panel1.setLayout(new GridLayout(2, 1));
        //se agregan al panel los textfield de la fracción1.
        panel1.add(txtNumerador1);
        panel1.add(txtDenominador1);
        add(panel1);

        comboOperations.setSize(50, 50);//tamaño del comboBox
        comboOperations.setLocation(92, 120);//posicion del comboBox
        add(comboOperations);

        panel2.setSize(75, 150);//tamaño del panel
        panel2.setLocation(150, 70);//posición del panel
        panel2.setLayout(new GridLayout(2, 1));
        //se agregan al panel los textfield de la fracción2.
        panel2.add(txtNumerador2);
        panel2.add(txtDenominador2);
        add(panel2);

        txtEqual.setLocation(240, 41);//se especifica la localizacion.
        txtEqual.setSize(200, 200);
        add(txtEqual);

        panel3.setSize(75, 150);//tamaño del panel
        panel3.setLocation(280, 70);//posición del panel
        panel3.setLayout(new GridLayout(2, 1));
        panel3.add(txtNumerador3); 
        panel3.add(txtDenominador3);
        add(panel3);

        calcular.setSize(150, 50);//tamaño del panel
        calcular.setLocation(50, 250);//posición del panel
        add(calcular);

        calcular.addActionListener(new ActionListener() {
            //Listener para el botón de calcular el resultado.
            @Override
            public void actionPerformed(ActionEvent ae) {
                //guarda el indice seleccionado del combobox.
                int comboPos = comboOperations.getSelectedIndex();
                double numerador = 0, denominador = 0;

                try { //se verifica que los denominadores no sean cero
                    if (Double.parseDouble(txtDenominador1.getText()) == 0 || Double.parseDouble(txtDenominador2.getText()) == 0) {
                        JOptionPane.showMessageDialog(null, "Los denominadores no pueden ser 0");
                        return;
                    }
                } catch (Exception e) { //se muestra una nueva ventana que le verifique al usuario los
                    //datos ingresados.
                    JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                    return;
                }
                switch (comboPos) {
                    case 0://suma
                        try { //se calcula el numerador y denominador resultante,
                            //y se escribe en los labels.
                            numerador = (Double.parseDouble(txtNumerador1.getText()) * Double.parseDouble(txtDenominador2.getText())) + (Double.parseDouble(txtDenominador1.getText()) * Double.parseDouble(txtNumerador2.getText()));
                            txtNumerador3.setText("" + numerador);
                            denominador = (Double.parseDouble(txtDenominador1.getText()) * Double.parseDouble(txtDenominador2.getText()));
                            txtDenominador3.setText("" + denominador);
                        } catch (Exception e) { //si no se pudo realizar la
                            //operacion se le muestra una ventana al usuario
                            //que le dice que verifique los datos ingreados.
                            JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                            break;
                        }

                        break;

                    case 1://resta
                        try {//se calcula el numerador y denominador resultante,
                            //y se escribe en los labels.
                            numerador = (Double.parseDouble(txtNumerador1.getText()) * Double.parseDouble(txtDenominador2.getText())) - (Double.parseDouble(txtDenominador1.getText()) * Double.parseDouble(txtNumerador2.getText()));
                            txtNumerador3.setText("" + numerador);
                            denominador = (Double.parseDouble(txtDenominador1.getText()) * Double.parseDouble(txtDenominador2.getText()));
                            txtDenominador3.setText("" + denominador);
                        } catch (Exception e) {//si no se pudo realizar la
                            //operacion se le muestra una ventana al usuario
                            //que le dice que verifique los datos ingreados.
                            JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                            break;
                        }
                        break;

                    case 2://multiplicacion
                        try {//se calcula el numerador y denominador resultante,
                            //y se escribe en los labels.
                            numerador = (Double.parseDouble(txtNumerador1.getText()) * Double.parseDouble(txtNumerador2.getText()));
                            txtNumerador3.setText("" + numerador);
                            denominador = (Double.parseDouble(txtDenominador1.getText()) * Double.parseDouble(txtDenominador2.getText()));
                            txtDenominador3.setText("" + denominador);
                        } catch (Exception e) {//si no se pudo realizar la
                            //operacion se le muestra una ventana al usuario
                            //que le dice que verifique los datos ingreados.
                            JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                            break;
                        }

                        break;

                    case 3://division
                        try {//se calcula el numerador y denominador resultante,
                            //y se escribe en los labels.
                            numerador = (Double.parseDouble(txtNumerador1.getText()) * Double.parseDouble(txtDenominador2.getText()));
                            txtNumerador3.setText("" + numerador);
                            denominador = (Double.parseDouble(txtDenominador1.getText()) * Double.parseDouble(txtNumerador2.getText()));
                            txtDenominador3.setText("" + denominador);
                        } catch (Exception e) {//si no se pudo realizar la
                            //operacion se le muestra una ventana al usuario
                            //que le dice que verifique los datos ingreados.
                            JOptionPane.showMessageDialog(null, "Verifica que los datos ingresados sean numericos");
                            break;
                        }
                        break;
                }

            }
        });
    }
}
