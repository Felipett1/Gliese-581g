/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.Actividades;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author 908034
 */
public interface IPersistenciaActividades {
    
    public List<Actividades> buscarActividades(); 
    public boolean modificarActividad(Actividades actividad);
    public List<Actividades> buscarActividadesHijas();
    public BigInteger idActividadPadre();
    public List<Actividades> buscarActividadesParaReAbrir();
}
