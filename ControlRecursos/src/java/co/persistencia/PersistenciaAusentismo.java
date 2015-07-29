package co.persistencia;

import co.entidades.Ausentismo;
import co.interfaces.persistencia.IPersistenciaAusentismo;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersistenciaAusentismo implements IPersistenciaAusentismo {

    @PersistenceContext(unitName = "ControlRecursosPU")
    private EntityManager em;

    @Override
    public boolean ingresarAusentismo(Ausentismo ausentismo) {
        try {
            em.persist(ausentismo);
            return true;
        } catch (Exception e) {
            System.out.println("Error PersistenciaAusentismo.ingresarAusentismo: " + e);
            return false;
        }
    }

    @Override
    public boolean eliminarAusentismo(Ausentismo ausentismo) {
        try {
            em.remove(em.merge(ausentismo));
            return true;
        } catch (Exception e) {
            System.out.println("Error PersistenciaAusentismo.eliminarAusentismo: " + e);
            return false;
        }
    }

    @Override
    public boolean modificarAusentismo(Ausentismo ausentismo) {
        try {
            if (em.merge(ausentismo) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error PersistenciaAusentismo.modificarAusentismo: " + e);
            return false;
        }
    }

    @Override
    public List<Ausentismo> obtenerAusentismos() {
        try {
            Query query = em.createQuery("SELECT a FROM Ausentismo a");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error PersistenciaAusentismo.obtenerAusentismos: " + e);
            return null;
        }
    }

    @Override
    public boolean validarAusentismoRecurso(Long identificacionRecurso) {
        try {
            Query query = em.createQuery("SELECT COUNT(a) FROM Ausentismo a WHERE a.idRecurso.identificacion = :identificacionRecurso");
            query.setParameter("identificacionRecurso", identificacionRecurso);
            return (Long) query.getSingleResult() > 0;
        } catch (Exception e) {
            System.out.println("Error PersistenciaAusentismo.validarAusentismoRecurso: " + e);
            return true;
        }
    }
    
    @Override
    public boolean validarFechasAusentismoRecurso(Long identificacionRecurso, Date fechaInicio, Date fechaFin) {
        try {
            Query query = em.createQuery("SELECT COUNT(a) FROM Ausentismo a WHERE a.idRecurso.identificacion = :identificacionRecurso "
                    + "AND (:fechaInicio BETWEEN a.fechaInicio AND a.fechaFin OR :fechaFin BETWEEN a.fechaInicio AND a.fechaFin "
                    + "OR (:fechaInicio <= a.fechaInicio AND :fechaFin >= a.fechaFin))");
            query.setParameter("identificacionRecurso", identificacionRecurso);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return (Long) query.getSingleResult() > 0;
        } catch (Exception e) {
            System.out.println("Error PersistenciaAusentismo.validarFechasAusentismoRecurso: " + e);
            return true;
        }
    }
}
