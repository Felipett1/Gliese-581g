/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.Empleados;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author 908034
 */
public interface IPersistenciaEmpleados {

    public List<Empleados> buscarEmpleados();
    public Empleados obtenerEmpleado(BigInteger idEmpleado);
}
