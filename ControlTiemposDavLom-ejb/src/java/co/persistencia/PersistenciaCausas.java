package co.persistencia;

import co.entidades.Causas;
import co.interfaces.persistencia.IPersistenciaCausas;
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
public class PersistenciaCausas implements IPersistenciaCausas {

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;

    @Override
    public List<Causas> buscarCausas() {
        try {
            Query query = em.createQuery("SELECT c FROM Causas c ORDER BY c.nombre");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error PersistenciaCausas.buscarCausas: " + e);
            return null;
        }
    }
}
