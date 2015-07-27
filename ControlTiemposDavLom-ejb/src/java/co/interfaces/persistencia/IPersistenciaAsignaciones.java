package co.interfaces.persistencia;

import co.entidades.Asignaciones;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author 908036
 */
public interface IPersistenciaAsignaciones {

    public List<Asignaciones> actividadesAsignadasPorEmpleado(BigInteger idUsuario);
    public boolean crearAsignacion(Asignaciones asignacion);
    public List<Asignaciones> validarAsignacionActividad(BigInteger idActividad);
    public List<Asignaciones> validarAsignacionActividadTerminada(BigInteger idActividad);
    public List<Asignaciones> validarAsignacionActividadReAbierta(BigInteger idActividad, BigInteger idReAbierto);
    public boolean modificarAsignacion(Asignaciones asignacion);
}
