/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.Recurso;

/**
 *
 * @author 908036
 */
public interface IPersistenciaRecurso {

    public boolean registrarRecurso(Recurso recurso);

    public Recurso obtenerRecurso(Long identificacion);

    public boolean eliminarRecurso(co.entidades.Recurso recurso);

    public boolean modificarRecurso(co.entidades.Recurso recurso);

    public java.util.List<co.entidades.Recurso> obtenerRecursos();

    public boolean validarIdentificacionRecurso(java.lang.Long identificacionRecurso);

    public boolean validarRolEnRecurso(java.math.BigDecimal idRol);

    public java.util.List<co.entidades.Recurso> obtenerRecursosDisponible();
}
