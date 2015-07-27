package co.administrar;

import co.entidades.*;
import co.interfaces.administrar.IAdministrarRegistrosHoras;
import co.interfaces.persistencia.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author 908034
 */
@Stateful
public class AdministrarRegistroHoras implements IAdministrarRegistrosHoras {

    @EJB
    IPersistenciaActividades persistenciaActividades;
    @EJB
    IPersistenciaRegistroHoras persistenciaRegistroHoras;
    @EJB
    IPersistenciaAsignaciones persistenciaAsignaciones;
    @EJB
    IPersistenciaReAbrirActividades persistenciaReAbrirActividades;
    @EJB
    IPersistenciaHistoricoBloqueos persistenciaHistoricoBloqueos;

    @Override
    public List<Actividades> consultarActividades() {
        return persistenciaActividades.buscarActividades();
    }

    @Override
    public boolean registrarHoras(RegistroHoras registro) {
        return persistenciaRegistroHoras.registrarHoras(registro);
    }

    @Override
    public List<Asignaciones> actividadesAsignadasEmpleado(BigInteger idEmpleado) {
        List<Asignaciones> asignaciones = persistenciaAsignaciones.actividadesAsignadasPorEmpleado(idEmpleado);
        if (asignaciones != null && asignaciones.size() > 0) {
            for (int i = 0; i < asignaciones.size(); i++) {
                Asignaciones asignacion = asignaciones.get(i);
                BigDecimal porcentajeEmpleado;
                if (asignacion.getIdActividad().getPorcentajeReal().compareTo(BigDecimal.valueOf(100)) == 0) {
                    ReabrirActividades reAbierta = persistenciaReAbrirActividades.validarActividadReAbierta(asignacion.getIdActividad().getId());
                    if (reAbierta != null) {
                        porcentajeEmpleado = persistenciaRegistroHoras.porcentajeActividadReAbiertaEmpleado(asignacion.getIdActividad().getId(), idEmpleado, reAbierta.getId());
                        asignaciones.get(i).getIdActividad().setReAbierto(true);
                        asignaciones.get(i).getIdActividad().setInfoAbierta(reAbierta);
                        asignaciones.get(i).getIdActividad().setPorcentajeEmpleado(porcentajeEmpleado);
                    } else {
                        asignaciones.remove(i);
                        i -= i;
                    }
                } else {
                    porcentajeEmpleado = persistenciaRegistroHoras.porcentajeActividadEmpleado(asignacion.getIdActividad().getId(), idEmpleado);
                    asignaciones.get(i).getIdActividad().setPorcentajeEmpleado(porcentajeEmpleado);
                }

                //PRIORIDAD POR FECHA
                if (asignacion.getIdActividad().getFechaInicio().compareTo(new Date()) > 0) {
                    asignacion.setPrioridad("BAJA");
                } else if (asignacion.getIdActividad().getFechaInicio().compareTo(new Date()) == 0) {
                    asignacion.setPrioridad("MEDIA");
                } else if (asignacion.getIdActividad().getFechaInicio().compareTo(new Date()) < 0 && asignacion.getIdActividad().getFechaFinal().compareTo(new Date()) > 0) {
                    asignacion.setPrioridad("MEDIA");
                } else if (asignacion.getIdActividad().getFechaInicio().compareTo(new Date()) < 0 && asignacion.getIdActividad().getFechaFinal().compareTo(new Date()) < 0) {
                    asignacion.setPrioridad("ALTA");
                }
            }
        }
        return asignaciones;
    }

    @Override
    public boolean modificarActividad(Actividades actividad) {
        return persistenciaActividades.modificarActividad(actividad);
    }

    @Override
    public boolean actualizarPorcentajeReTrabajo(ReabrirActividades reAbrirActividades) {
        return persistenciaReAbrirActividades.modificar(reAbrirActividades);
    }

    @Override
    public boolean modificarAsignacion(Asignaciones asignacion) {
        return persistenciaAsignaciones.modificarAsignacion(asignacion);
    }

    @Override
    public boolean modificarHistoricoBloqueo(HistoricoBloqueos historicoBloqueo) {
        return persistenciaHistoricoBloqueos.modificarHistoricoBloqueo(historicoBloqueo);
    }
}
