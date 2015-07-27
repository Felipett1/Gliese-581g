/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

import co.entidades.Empleados;
import java.math.BigInteger;

/**
 *
 * @author 908036
 */
public interface IAdministrarDatosPersonales {

    public Empleados obtenerEmpleado(BigInteger idEmpleado);
    
}
