/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

import co.entidades.Actividades;
import co.entidades.Causas;
import co.entidades.ReabrirActividades;
import java.util.List;

/**
 *
 * @author 908036
 */
public interface IAdministrarReAbrirActividad {

    public List<Actividades> actividadesParaReabrir();
    public boolean registrarActividadReAbierta(ReabrirActividades reAbrirActividad);
    public List<Causas> buscarCausas();
}
