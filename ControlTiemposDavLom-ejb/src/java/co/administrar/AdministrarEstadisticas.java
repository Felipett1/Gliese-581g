package co.administrar;

import co.entidades.HistoricoEstadisticas;
import co.interfaces.administrar.IAdministrarEstadisticas;
import co.interfaces.persistencia.IPersistenciaHistoricoEstadisticas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author 908036
 */
@Stateful
public class AdministrarEstadisticas implements IAdministrarEstadisticas{

    @EJB
    IPersistenciaHistoricoEstadisticas persistenciaHistoricoEstadisticas;

    @Override
    public List<HistoricoEstadisticas> buscarHistoricosProyecto(Integer cantidadRegistros) {
        return persistenciaHistoricoEstadisticas.buscarHistoricosFecha(cantidadRegistros);
    }
}
