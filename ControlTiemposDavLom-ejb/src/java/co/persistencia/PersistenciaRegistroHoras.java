package co.persistencia;

import co.entidades.RegistroHoras;
import co.interfaces.persistencia.IPersistenciaRegistroHoras;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 908034
 */
@Stateless
public class PersistenciaRegistroHoras implements IPersistenciaRegistroHoras {

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;

    @Override
    public boolean registrarHoras(RegistroHoras registro) {
        if (em.merge(registro) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BigDecimal porcentajeActividadEmpleado(BigInteger idActividad, BigInteger idEmpleado) {
        try {
            Query query = em.createQuery("SELECT SUM(RH.porcentajeRegistrado) FROM RegistroHoras RH WHERE RH.asignacion.idActividad.id = :idActividad AND RH.asignacion.idEmpleado.employee = :idEmpleado AND RH.asignacion.idReabrirActividades IS NULL");
            query.setParameter("idActividad", idActividad);
            query.setParameter("idEmpleado", idEmpleado);
            BigDecimal resultado = (BigDecimal) query.getSingleResult();
            if (resultado == null) {
                return BigDecimal.ZERO;
            }
            return resultado;
        } catch (Exception e) {
            System.out.println("Error PersistenciaRegistroHoras.porcentajeActividadEmpleado: " + e);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal porcentajeActividadReAbiertaEmpleado(BigInteger idActividad, BigInteger idEmpleado, BigInteger idReAbierto) {
        try {
            Query query = em.createQuery("SELECT SUM(RH.porcentajeRegistrado) FROM RegistroHoras RH WHERE RH.asignacion.idActividad.id = :idActividad AND RH.asignacion.idEmpleado.employee = :idEmpleado AND RH.asignacion.idReabrirActividades.id = :idReAbierto");
            query.setParameter("idActividad", idActividad);
            query.setParameter("idEmpleado", idEmpleado);
            query.setParameter("idReAbierto", idReAbierto);
            BigDecimal resultado = (BigDecimal) query.getSingleResult();
            if (resultado == null) {
                return BigDecimal.ZERO;
            }
            return resultado;
        } catch (Exception e) {
            System.out.println("Error PersistenciaRegistroHoras.porcentajeActividadEmpleado: " + e);
            return BigDecimal.ZERO;
        }
    }
}