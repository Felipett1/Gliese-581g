package co.persistencia;

import co.entidades.Asignacion;
import co.interfaces.persistencia.IPersistenciaAsignacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersistenciaAsignacion implements IPersistenciaAsignacion{

    @PersistenceContext(unitName = "ControlRecursosPU")
    private EntityManager em;

    @Override
    public boolean registrarAsignacion(Asignacion asignacion) {
        try {
            if (em.merge(asignacion) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error PersistenciaAsignacion.registrarAsignacion: " + e);
            return false;
        }
    }
    
    @Override
    public boolean validarAsignacionRecurso(Long identificacionRecurso){
        try {
            Query query = em.createQuery("SELECT COUNT(a) FROM Asignacion a WHERE a.idRecurso.identificacion = :identificacionRecurso");
            query.setParameter("identificacionRecurso", identificacionRecurso);
            return (Long) query.getSingleResult() > 0;
        } catch (Exception e) {
            System.out.println("Error PersistenciaAsignacion.validarAsignacionRecurso: " + e);
            return true;
        }
    }
}
