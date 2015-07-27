/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

import co.entidades.Actividades;
import co.entidades.Cronograma;
import co.entidades.Empleados;
import co.entidades.RegistroHorasDetallado;
import java.util.List;

/**
 *
 * @author 908036
 */
public interface IAdministrarReportes {
    public List<Cronograma> consultarCronogramaDetallado();
    public List<RegistroHorasDetallado> obtenerDetalleRegistroHoras(String condiciones);
    public List<Empleados> consultarEmpleados();
    public List<Actividades> consultarActividadesHijas();
}
