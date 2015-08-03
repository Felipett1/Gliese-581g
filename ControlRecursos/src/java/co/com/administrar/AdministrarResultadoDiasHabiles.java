package co.com.administrar;

import co.entidades.ResultadoDiasHabiles;
import co.interfaces.administrar.IAdministrarResultadoDiasHabiles;
import co.interfaces.persistencia.IPersistenciaResultadoDiasHabiles;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class AdministrarResultadoDiasHabiles implements IAdministrarResultadoDiasHabiles {

    @EJB
    IPersistenciaResultadoDiasHabiles persistenciaResultadoDiasHabiles;

    @Override
    public ResultadoDiasHabiles resultadoDiasHabiles(Date fechaInicial, BigDecimal numeroHoras, Long IdEmpleado) {
        return persistenciaResultadoDiasHabiles.obtenerDiasHabilesEmpleado(fechaInicial, numeroHoras, IdEmpleado);
    }
}
