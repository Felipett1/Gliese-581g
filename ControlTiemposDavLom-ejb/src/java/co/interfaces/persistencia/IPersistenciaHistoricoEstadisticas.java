/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.HistoricoEstadisticas;
import java.util.List;

/**
 *
 * @author 908036
 */
public interface IPersistenciaHistoricoEstadisticas {
    public List<HistoricoEstadisticas> buscarHistoricosFecha(Integer cantidadRegistros);
    public boolean registrarHistorico(HistoricoEstadisticas historico);
}
