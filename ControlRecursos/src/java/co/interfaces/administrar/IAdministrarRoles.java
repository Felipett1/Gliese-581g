/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

import co.entidades.Rol;
import java.util.List;

/**
 *
 * @author 908036
 */
public interface IAdministrarRoles {

    public List<Rol> obtenerRoles();

    public boolean registrarRol(Rol rol);

    public boolean eliminarRol(co.entidades.Rol rol);

    public boolean modificarRol(co.entidades.Rol rol);
    
}
