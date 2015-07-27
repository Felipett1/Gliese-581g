package co.persistencia;

import co.entidades.Asignaciones;
import co.interfaces.persistencia.IPersistenciaAsignaciones;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 908036
 */
@Stateless
public class PersistenciaAsignaciones implements IPersistenciaAsignaciones {

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;

    @Override
    public List<Asignaciones> actividadesAsignadasPorEmpleado(BigInteger idUsuario) {
        try {
            Query query = em.createQuery("SELECT A FROM Asignaciones A WHERE A.idEmpleado.employee = :idUsuario AND A.estado = 'A' AND A.idActividad.bloqueante != 'S' ORDER BY A.idActividad.idUnico ASC");
            query.setParameter("idUsuario", idUsuario);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error PersistenciaAsignaciones.actividadesAsignadasPorEmpleado: " + e);
            return null;
        }
    }

    @Override
    public boolean crearAsignacion(Asignaciones asignacion) {
        try {
            if (em.merge(asignacion) != null) {
                em.flush();
                return true;
            } else {
                em.flush();
                return false;
            }
        } catch (Exception ex) {
            System.out.println("1......" + ex);
            return false;
        }
    }

    @Override
    public List<Asignaciones> validarAsignacionActividad(BigInteger idActividad) {
        try {
            Query query = em.createQuery("SELECT A FROM Asignaciones A WHERE A.idActividad.id = :idActividad AND A.idReabrirActividades IS NULL");
            query.setParameter("idActividad", idActividad);
            List<Asignaciones> resultado = query.getResultList();
            if (resultado.isEmpty()) {
                return null;
            }
            return resultado;
        } catch (Exception e) {
            System.out.println("Error PersistenciaAsignaciones.validarAsignacionActividad: " + e);
            return null;
        }
    }
    
    @Override
    public List<Asignaciones> validarAsignacionActividadTerminada(BigInteger idActividad) {
        try {
            Query query = em.createQuery("SELECT A FROM Asignaciones A WHERE A.idActividad.id = :idActividad AND A.estado = 'I' AND A.idReabrirActividades IS NULL");
            query.setParameter("idActividad", idActividad);
            List<Asignaciones> resultado = query.getResultList();
            if (resultado.isEmpty()) {
                return null;
            }
            return resultado;
        } catch (Exception e) {
            System.out.println("Error PersistenciaAsignaciones.validarAsignacionActividad: " + e);
            return null;
        }
    }

    @Override
    public List<Asignaciones> validarAsignacionActividadReAbierta(BigInteger idActividad, BigInteger idReAbierto) {
        try {
            Query query = em.createQuery("SELECT A FROM Asignaciones A WHERE A.idActividad.id = :idActividad AND A.estado = 'A' AND A.idReabrirActividades.id = :idReAbierto");
            query.setParameter("idActividad", idActividad);
            query.setParameter("idReAbierto", idReAbierto);
            List<Asignaciones> resultado = query.getResultList();
            if (resultado.isEmpty()) {
                return null;
            }
            return resultado;
        } catch (Exception e) {
            System.out.println("Error PersistenciaAsignaciones.validarAsignacionActividad: " + e);
            return null;
        }
    }

    @Override
    public boolean modificarAsignacion(Asignaciones asignacion) {
        try {
            if (em.merge(asignacion) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error PersistenciaAsignaciones.mofdificarAsignacion: " + ex);
            return false;
        }
    }
}
