package co.administrar;

import co.entidades.Actividades;
import co.entidades.Causas;
import co.entidades.ReabrirActividades;
import co.interfaces.administrar.IAdministrarReAbrirActividad;
import co.interfaces.persistencia.IPersistenciaActividades;
import co.interfaces.persistencia.IPersistenciaCausas;
import co.interfaces.persistencia.IPersistenciaReAbrirActividades;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author 908036
 */
@Stateful
public class AdministrarReAbrirActividad implements IAdministrarReAbrirActividad {

    @EJB
    IPersistenciaActividades persistenciaActividades;
    @EJB
    IPersistenciaReAbrirActividades persistenciaReAbrirActividades;
    @EJB
    IPersistenciaCausas persistenciaCausas;

    @Override
    public List<Actividades> actividadesParaReabrir() {
        return persistenciaActividades.buscarActividadesParaReAbrir();
    }

    @Override
    public boolean registrarActividadReAbierta(ReabrirActividades reAbrirActividad) {
        return persistenciaReAbrirActividades.modificar(reAbrirActividad);
    }

    @Override
    public List<Causas> buscarCausas() {
        return persistenciaCausas.buscarCausas();
    }
}
