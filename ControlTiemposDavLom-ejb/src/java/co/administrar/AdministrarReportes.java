package co.administrar;

import co.entidades.Actividades;
import co.entidades.Cronograma;
import co.entidades.Empleados;
import co.entidades.RegistroHorasDetallado;
import co.interfaces.administrar.IAdministrarReportes;
import co.interfaces.persistencia.IPersistenciaActividades;
import co.interfaces.persistencia.IPersistenciaCronograma;
import co.interfaces.persistencia.IPersistenciaEmpleados;
import co.interfaces.persistencia.IPersistenciaRegistroHorasDetallado;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author 908036
 */
@Stateful
public class AdministrarReportes implements IAdministrarReportes {

    @EJB
    IPersistenciaRegistroHorasDetallado persistenciaRegistroHorasDetallado;
    @EJB
    IPersistenciaCronograma persistenciaCronograma;
    @EJB
    IPersistenciaEmpleados persistenciaEmpleados;
    @EJB
    IPersistenciaActividades persistenciaActividades;

    @Override
    public List<Cronograma> consultarCronogramaDetallado() {
        //BigInteger idActividadPadre = persistenciaActividades.idActividadPadre();
        //return persistenciaCronograma.estadoCronograma(idActividadPadre);
        return persistenciaCronograma.cronogramaGeneral();
    }

    @Override
    public List<RegistroHorasDetallado> obtenerDetalleRegistroHoras(String condiciones) {
        return persistenciaRegistroHorasDetallado.consultarDetalleRegistroHoras(condiciones);
    }

    @Override
    public List<Empleados> consultarEmpleados() {
        return persistenciaEmpleados.buscarEmpleados();
    }

    @Override
    public List<Actividades> consultarActividadesHijas() {
        return persistenciaActividades.buscarActividadesHijas();
    }
}
