/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

/**
 *
 * @author 908036
 */
public interface IAdministrarRecursos {

    public java.util.List<co.entidades.Recurso> obtenerRecursos();
    public boolean eliminarRecurso(co.entidades.Recurso Recurso);
    public boolean modificarRecurso(co.entidades.Recurso Recurso);
    public boolean registrarRecurso(co.entidades.Recurso recurso);
    public boolean validarIdentificacionRecurso(java.lang.Long identificacionRecurso);
    public java.util.List<co.entidades.Rol> obtenerListadoRoles();
    public co.entidades.Rol obtenerRol(java.math.BigDecimal id);
    public boolean validarAsignacionRecurso(java.lang.Long identificacionRecurso);
    public boolean validarAusentismoRecurso(java.lang.Long identificacionRecurso);
    
}
