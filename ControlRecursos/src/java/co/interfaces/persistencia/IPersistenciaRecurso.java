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
}
