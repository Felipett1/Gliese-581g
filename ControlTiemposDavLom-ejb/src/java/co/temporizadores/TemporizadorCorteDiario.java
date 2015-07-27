package co.temporizadores;

import co.entidades.Cronograma;
import co.entidades.HistoricoEstadisticas;
import co.interfaces.persistencia.IPersistenciaCronograma;
import co.interfaces.persistencia.IPersistenciaHistoricoEstadisticas;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;

@Singleton
public class TemporizadorCorteDiario {
    @EJB
    private IPersistenciaHistoricoEstadisticas persistenciaHistoricoEstadisticas;
    @EJB
    private IPersistenciaCronograma persistenciaCronograma;
    
  
    
    //@Schedule(hour = "23", minute = "59", second = "00")
    @Schedule(hour = "23", minute = "59", second = "00")
    public void ejecutarCorteDiario(Timer timer) {
        HistoricoEstadisticas historico = new HistoricoEstadisticas();
        Cronograma estadoCronograma = persistenciaCronograma.estadisticasCronogramaGeneral();
        if(estadoCronograma != null){
            historico.setFecha(new Date());
            historico.setCpi(estadoCronograma.getCpi());
            historico.setSpi(estadoCronograma.getSpi());
            historico.setPv(estadoCronograma.getPv());
            historico.setEv(estadoCronograma.getEv());
            historico.setAc(estadoCronograma.getAc());
            if(persistenciaHistoricoEstadisticas.registrarHistorico(historico)){
                System.out.println("REGISTRO DE ESTADISTICAS EXITOSO: " + new Date());
            }else{
                System.out.println("REGISTRO DE ESTADISTICAS NO EXITOSO: " + new Date());
            }
        }
    }
}
