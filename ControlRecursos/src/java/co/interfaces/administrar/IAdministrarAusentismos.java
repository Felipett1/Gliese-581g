/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

/**
 *
 * @author 908036
 */
public interface IAdministrarAusentismos {

    public java.util.List<co.entidades.Ausentismo> obtenerAusentismos();

    public boolean registrarAusentismo(co.entidades.Ausentismo ausentismo);

    public boolean eliminarAusentismo(co.entidades.Ausentismo ausentismo);

    public boolean modificarAusentismo(co.entidades.Ausentismo ausentismo);

    public boolean validarFechasAusentismoRecurso(java.lang.Long identificacionRecurso, java.util.Date fechaInicio, java.util.Date fechaFin);

    public java.util.List<co.entidades.Recurso> lovRecursos();
    
}
