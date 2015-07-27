/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.administrar;

import co.entidades.Actividades;
import co.entidades.Asignaciones;
import co.entidades.Empleados;
import co.entidades.ReabrirActividades;
import co.interfaces.administrar.IAdministrarAsignacionActividades;
import co.interfaces.persistencia.IPersistenciaActividades;
import co.interfaces.persistencia.IPersistenciaAsignaciones;
import co.interfaces.persistencia.IPersistenciaEmpleados;
import co.interfaces.persistencia.IPersistenciaReAbrirActividades;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author 908034
 */
@Stateful
public class AdministrarAsignacionActividades implements IAdministrarAsignacionActividades {

    @EJB
    IPersistenciaEmpleados persistenciaEmpleados;
    @EJB
    IPersistenciaActividades persistenciaActividades;
    @EJB
    IPersistenciaAsignaciones persistenciaAsignaciones;
    @EJB
    IPersistenciaReAbrirActividades persistenciaReAbrirActividades;

    @Override
    public List<Empleados> consultarEmpleados() {
        return persistenciaEmpleados.buscarEmpleados();
    }

    @Override
    public List<Actividades> consultarActividadesHijas() {
        List<Actividades> actividades = persistenciaActividades.buscarActividadesHijas();
        for (int i = 0; i < actividades.size(); i++) {
            Actividades actividad = actividades.get(i);
            List<Asignaciones> asignaciones;

            if (actividad.getPorcentajeReal().compareTo(BigDecimal.valueOf(100)) == 0) {
                ReabrirActividades reAbierta = persistenciaReAbrirActividades.validarActividadReAbierta(actividad.getId());
                if (reAbierta != null) {
                    actividades.get(i).setReAbierto(true);
                    actividades.get(i).setInfoAbierta(reAbierta);

                    asignaciones = persistenciaAsignaciones.validarAsignacionActividadReAbierta(actividad.getId(), reAbierta.getId());
                    if (asignaciones != null) {
                        BigDecimal totalHorasAsignadas = BigDecimal.ZERO;
                        for (int j = 0; j < asignaciones.size(); j++) {
                            totalHorasAsignadas = totalHorasAsignadas.add(asignaciones.get(j).getHorasAsignadas());
                        }
                        if (totalHorasAsignadas.compareTo(actividad.getHorasBaseline()) >= 0) {
                            actividades.remove(i);
                            i -= 1;
                            continue;
                        }
                        actividades.get(i).setAsignadoReAbierto(true);
                        actividades.get(i).setAsignacionReAbierta(asignaciones);
                    }
                    asignaciones = persistenciaAsignaciones.validarAsignacionActividadTerminada(actividad.getId());
                    if (asignaciones != null) {
                        actividades.get(i).setAsignacion(asignaciones);
                        actividades.get(i).setAsignado(true);
                    }
                } else {
                    asignaciones = persistenciaAsignaciones.validarAsignacionActividadTerminada(actividad.getId());
                    if (asignaciones != null) {
                        BigDecimal totalHorasAsignadas = BigDecimal.ZERO;
                        for (int j = 0; j < asignaciones.size(); j++) {
                            totalHorasAsignadas = totalHorasAsignadas.add(asignaciones.get(j).getHorasAsignadas());
                        }
                        if (totalHorasAsignadas.compareTo(actividad.getHorasBaseline()) >= 0) {
                            actividades.remove(i);
                            i -= 1;
                            continue;
                        }
                        actividades.get(i).setAsignado(true);
                        actividades.get(i).setAsignacion(asignaciones);
                    }
                }
            } else {
                asignaciones = persistenciaAsignaciones.validarAsignacionActividad(actividad.getId());
                if (asignaciones != null) {
                    BigDecimal totalHorasAsignadas = BigDecimal.ZERO;
                    for (int j = 0; j < asignaciones.size(); j++) {
                        totalHorasAsignadas = totalHorasAsignadas.add(asignaciones.get(j).getHorasAsignadas());
                    }
                    if (totalHorasAsignadas.compareTo(actividad.getHorasBaseline()) >= 0) {
                        actividades.remove(i);
                        i -= 1;
                        continue;
                    }
                    actividades.get(i).setAsignado(true);
                    actividades.get(i).setAsignacion(asignaciones);
                }
            }
        }
        return actividades;
    }

    @Override
    public boolean crear_Modificar_Asignacion(Asignaciones asignacion) {
        return persistenciaAsignaciones.crearAsignacion(asignacion);
    }
}
