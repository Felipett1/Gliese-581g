package co.interfaces.administrar;

import co.entidades.HistoricoEstadisticas;
import java.util.List;

public interface IAdministrarEstadisticas {
    public List<HistoricoEstadisticas> buscarHistoricosProyecto(Integer cantidadRegistros);
}
