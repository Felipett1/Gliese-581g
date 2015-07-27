package co.persistencia;

import co.entidades.Recurso;
import co.interfaces.persistencia.IPersistenciaRecurso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersistenciaRecurso implements IPersistenciaRecurso{

    
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
    
}
