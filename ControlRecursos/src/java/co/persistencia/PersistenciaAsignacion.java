package co.persistencia;

import co.entidades.Asignacion;
import co.interfaces.persistencia.IPersistenciaAsignacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
