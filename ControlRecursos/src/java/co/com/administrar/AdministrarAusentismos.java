package co.com.administrar;

import co.entidades.Ausentismo;
import co.entidades.Recurso;
import co.interfaces.administrar.IAdministrarAusentismos;
import co.interfaces.persistencia.IPersistenciaAusentismo;
import co.interfaces.persistencia.IPersistenciaRecurso;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class AdministrarAusentismos implements IAdministrarAusentismos {

    @EJB
    IPersistenciaAusentismo persistenciaAusentismo;
    @EJB
    IPersistenciaRecurso persistenciaRecurso;

    @Override
    public List<Ausentismo> obtenerAusentismos() {
        return persistenciaAusentismo.obtenerAusentismos();
    }

    @Override
    public boolean registrarAusentismo(Ausentismo ausentismo) {
        return persistenciaAusentismo.ingresarAusentismo(ausentismo);
    }

    @Override
    public boolean eliminarAusentismo(Ausentismo ausentismo) {
        return persistenciaAusentismo.eliminarAusentismo(ausentismo);
    }

    @Override
    public boolean modificarAusentismo(Ausentismo ausentismo) {
        return persistenciaAusentismo.modificarAusentismo(ausentismo);
    }

    @Override
    public boolean validarFechasAusentismoRecurso(Long identificacionRecurso, Date fechaInicio, Date fechaFin) {
        return persistenciaAusentismo.validarFechasAusentismoRecurso(identificacionRecurso, fechaInicio, fechaFin);
    }
    
    public List<Recurso> lovRecursos() {
        return persistenciaRecurso.obtenerRecursos();
    }
}
