/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

import co.entidades.*;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author 908034
 */
public interface IAdministrarRegistrosHoras {

    public List<Actividades> consultarActividades();
    public boolean registrarHoras(RegistroHoras registro);
    public List<Asignaciones> actividadesAsignadasEmpleado(BigInteger idEmpleado);
    public boolean modificarActividad(Actividades actividad);
    public boolean actualizarPorcentajeReTrabajo(ReabrirActividades reAbrirActividades);
    public boolean modificarAsignacion(Asignaciones asignacion);
    public boolean modificarHistoricoBloqueo(HistoricoBloqueos historicoBloqueo);
}
