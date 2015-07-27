package co.persistencia;

import co.entidades.Ausentismo;
import co.interfaces.persistencia.IPersistenciaAusentismo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersistenciaAusentismo implements IPersistenciaAusentismo{

    @PersistenceContext(unitName = "ControlRecursosPU")
    private EntityManager em;
    
    @Override
    public boolean ingresarAusentismo(Ausentismo ausentismo) {
        try {
            if (em.merge(ausentismo) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error PersistenciaAusentismo.ingresarAusentismo: " + e);
            return false;
        }
    }

}
