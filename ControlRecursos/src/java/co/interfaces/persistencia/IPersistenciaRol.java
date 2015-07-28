/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.Rol;
import java.util.List;

/**
 *
 * @author 908036
 */
public interface IPersistenciaRol {

    public boolean registrarRol(co.entidades.Rol rol);

    public List<Rol> obtenerRoles();

    public boolean eliminarRol(co.entidades.Rol rol);

    public boolean modificarRol(co.entidades.Rol rol);

    public co.entidades.Rol obtenerRol(java.math.BigDecimal id);
    
}
