/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

/**
 *
 * @author 908036
 */
public interface IPersistenciaAusentismo {

    public boolean ingresarAusentismo(co.entidades.Ausentismo ausentismo);

    public boolean validarAusentismoRecurso(Long idRecurso);

    public boolean eliminarAusentismo(co.entidades.Ausentismo ausentismo);

    public boolean modificarAusentismo(co.entidades.Ausentismo ausentismo);

    public java.util.List<co.entidades.Ausentismo> obtenerAusentismos();

    public boolean validarFechasAusentismoRecurso(java.lang.Long identificacionRecurso, java.util.Date fechaInicio, java.util.Date fechaFin);
    
}
