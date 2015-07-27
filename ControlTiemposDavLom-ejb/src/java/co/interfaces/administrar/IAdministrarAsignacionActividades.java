/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

import co.entidades.Actividades;
import co.entidades.Asignaciones;
import co.entidades.Empleados;
import java.util.List;

/**
 *
 * @author 908034
 */
public interface IAdministrarAsignacionActividades {

    public List<Empleados> consultarEmpleados();
    public List<Actividades> consultarActividadesHijas();
    public boolean crear_Modificar_Asignacion(Asignaciones asignacion);
}
