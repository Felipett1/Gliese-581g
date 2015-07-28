package co.persistencia;

import co.entidades.Recurso;
import co.interfaces.persistencia.IPersistenciaRecurso;
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
            if (em.merge(recurso) != null) {
                return true;
            } else {
                return false;
            }
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
}
