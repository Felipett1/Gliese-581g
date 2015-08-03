package co.persistencia;

import co.entidades.Recurso;
import co.interfaces.persistencia.IPersistenciaRecurso;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersistenciaRecurso implements IPersistenciaRecurso {

    @PersistenceContext(unitName = "ControlRecursosPU")
    private EntityManager em;

    @Override
    public boolean registrarRecurso(Recurso recurso) {
        try {
            em.persist(recurso);
            return true;
        } catch (Exception e) {
            System.out.println("Error PersistenciaRecurso.registrarRecurso: " + e);
            return false;
        }
    }

    @Override
    public Recurso obtenerRecurso(Long identificacion) {
        try {
            Query query = em.createQuery("SELECT r FROM Recurso r WHERE r.identificacion = :identificacion");
            query.setParameter("identificacion", identificacion);
            return (Recurso) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error PersistenciaRecurso.obtenerRecurso");
            return null;
        }
    }

    @Override
    public boolean eliminarRecurso(Recurso recurso) {
        try {
            em.remove(em.merge(recurso));
            return true;
        } catch (Exception e) {
            System.out.println("Error PersistenciaRecurso.eliminarRecurso: " + e);
            return false;
        }
    }

    @Override
    public boolean modificarRecurso(Recurso recurso) {
        try {
            if (em.merge(recurso) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error PersistenciaRecurso.modificarRecurso: " + e);
            return false;
        }
    }

    @Override
    public List<Recurso> obtenerRecursos() {
        try {
            Query query = em.createQuery("SELECT r FROM Recurso r");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error PersistenciaRecurso.obtenerRecusos: " + e);
            return null;
        }
    }

    @Override
    public boolean validarIdentificacionRecurso(Long identificacionRecurso) {
        try {
            Query query = em.createQuery("SELECT COUNT(r) FROM Recurso r WHERE r.identificacion = :identificacionRecurso");
            query.setParameter("identificacionRecurso", identificacionRecurso);
            return (Long) query.getSingleResult() > 0;
        } catch (Exception e) {
            System.out.println("Error PersistenciaRecurso.validarIdentificacionRecurso: " + e);
            return false;
        }
    }

    @Override
    public boolean validarRolEnRecurso(BigDecimal idRol) {
        try {
            Query query = em.createQuery("SELECT COUNT(r) FROM Recurso r WHERE r.rol.id = :idRol");
            query.setParameter("idRol", idRol);
            return (Long) query.getSingleResult() > 0;
        } catch (Exception e) {
            System.out.println("Error PersistenciaRecursos.validarRolEnRecurso: " + e);
            return true;
        }
    }

    @Override
    public List<Recurso> obtenerRecursosDisponible() {
        try {

            Query query = em.createQuery("SELECT r FROM Recurso r WHERE EXISTS (SELECT a1 FROM Asignacion a1 WHERE a1.idRecurso.identificacion = r.identificacion AND a1.estado = 'I') OR NOT EXISTS (SELECT a2 FROM Asignacion a2 WHERE a2.idRecurso.identificacion = r.identificacion)");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error PersistenciaRecursos.obtenerRecursosDisponible: " + e);
            return null;
        }
    }
}
