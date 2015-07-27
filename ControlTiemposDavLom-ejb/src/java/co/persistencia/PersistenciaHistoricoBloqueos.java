package co.persistencia;

import co.entidades.HistoricoBloqueos;
import co.interfaces.persistencia.IPersistenciaHistoricoBloqueos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersistenciaHistoricoBloqueos implements IPersistenciaHistoricoBloqueos{

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager entityManager;

    @Override
    public boolean modificarHistoricoBloqueo(HistoricoBloqueos historicoBloqueo) {
        if (entityManager.merge(historicoBloqueo) != null) {
            return true;
        } else {
            return false;
        }
    }
}
