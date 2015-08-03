/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.Asignacion;

/**
 *
 * @author 908036
 */
public interface IPersistenciaAsignacion {

    public boolean registrarAsignacion(Asignacion asignacion);

    public boolean validarAsignacionRecurso(Long identificacionRecurso);
    
}
