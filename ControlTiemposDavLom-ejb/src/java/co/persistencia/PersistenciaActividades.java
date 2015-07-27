package co.persistencia;

import co.entidades.Actividades;
import co.interfaces.persistencia.IPersistenciaActividades;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 908034
 */
@Stateless
public class PersistenciaActividades implements IPersistenciaActividades {

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;

    @Override
    public List<Actividades> buscarActividades() {
        Query query = em.createQuery("SELECT a FROM Actividades a");
        return query.getResultList();
    }

    @Override
    public boolean modificarActividad(Actividades actividad) {
        if (em.merge(actividad) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Actividades> buscarActividadesHijas() {
        Query query = em.createQuery("SELECT a FROM Actividades a WHERE NOT EXISTS (SELECT at.actividadPadre FROM Actividades at WHERE at.actividadPadre.id = a.id) ORDER BY a.idUnico ASC");
        return query.getResultList();
    }

    @Override
    public BigInteger idActividadPadre() {
        try {
            Query query = em.createQuery("SELECT a.id FROM Actividades a WHERE a.actividadPadre IS NULL");
            return (BigInteger) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error PersistenciaActividades.idActividadPadre: " + e.getCause());
            return null;
        }

    }

    @Override
    public List<Actividades> buscarActividadesParaReAbrir() {
        try {
            Query query = em.createQuery("SELECT a FROM Actividades a WHERE a.porcentajeReal = 100 AND NOT EXISTS (SELECT ra FROM ReabrirActividades ra WHERE ra.actividad.id = a.id AND ra.estado = 'ABIERTO') ORDER BY a.idUnico ASC");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error PersistenciaActividades.buscarActividadesParaReAbrir: " + e);
            return null;
        }
    }
}
