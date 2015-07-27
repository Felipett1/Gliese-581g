/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.ReabrirActividades;
import java.math.BigInteger;

/**
 *
 * @author 908036
 */
public interface IPersistenciaReAbrirActividades {
    public ReabrirActividades validarActividadReAbierta(BigInteger idActividad);
    public boolean modificar(ReabrirActividades reAbrirActividad);
}
