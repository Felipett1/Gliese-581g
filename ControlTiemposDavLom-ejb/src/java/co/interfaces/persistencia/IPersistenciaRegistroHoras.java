/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.RegistroHoras;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author 908036
 */
public interface IPersistenciaRegistroHoras {
    public boolean registrarHoras(RegistroHoras registro);
    public BigDecimal porcentajeActividadEmpleado(BigInteger idActividad, BigInteger idEmpleado);
    public BigDecimal porcentajeActividadReAbiertaEmpleado(BigInteger idActividad, BigInteger idEmpleado, BigInteger idReAbierto);
}
