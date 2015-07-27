package co.persistencia;

import co.entidades.HistoricoEstadisticas;
import co.interfaces.persistencia.IPersistenciaHistoricoEstadisticas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersistenciaHistoricoEstadisticas implements IPersistenciaHistoricoEstadisticas{

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;

    @Override
    public List<HistoricoEstadisticas> buscarHistoricosFecha(Integer cantidadRegistros) {
        Query query = em.createQuery("SELECT he FROM HistoricoEstadisticas he ORDER BY he.fecha DESC");
        query.setMaxResults(cantidadRegistros);
        return query.getResultList();
    }
    
    @Override
    public boolean registrarHistorico(HistoricoEstadisticas historico) {
        if (em.merge(historico) != null) {
            return true;
        } else {
            return false;
        }
    }
}
