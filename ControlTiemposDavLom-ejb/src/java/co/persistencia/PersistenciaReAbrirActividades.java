/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.persistencia;

import co.entidades.ReabrirActividades;
import co.interfaces.persistencia.IPersistenciaReAbrirActividades;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 908036
 */
@Stateless
public class PersistenciaReAbrirActividades implements IPersistenciaReAbrirActividades {

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;

    @Override
    public ReabrirActividades validarActividadReAbierta(BigInteger idActividad) {
        try {
            Query query = em.createQuery("SELECT RA FROM ReabrirActividades RA WHERE RA.actividad.id = :idActividad AND RA.estado = 'ABIERTO'");
            query.setParameter("idActividad", idActividad);
            ReabrirActividades resultado = (ReabrirActividades) query.getSingleResult();
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean modificar(ReabrirActividades reAbrirActividad) {
        if (em.merge(reAbrirActividad) != null) {
            return true;
        } else {
            return false;
        }
    }
}
