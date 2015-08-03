/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

public interface IPersistenciaResultadoDiasHabiles {

    public co.entidades.ResultadoDiasHabiles obtenerDiasHabilesEmpleado(java.util.Date fechaInicial, java.math.BigDecimal horasAsignadas, java.lang.Long idEmpleado);
}
