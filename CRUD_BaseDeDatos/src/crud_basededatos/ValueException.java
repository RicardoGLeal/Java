/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_basededatos;

/**
 *
 *Excepcion ValueExcepcion
 * Esta excepcion es utilizada para todos los valores mal especificados 
 * de los casos para el crud (Insertar, Consultar, Modificar y Eliminar)
 * @author ricar
 */
public class ValueException extends Exception {
    public ValueException(String msg)
    {
        super(msg);
    }
}
