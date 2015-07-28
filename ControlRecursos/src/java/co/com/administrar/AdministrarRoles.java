package co.com.administrar;

import co.entidades.Rol;
import co.interfaces.administrar.IAdministrarRoles;
import co.interfaces.persistencia.IPersistenciaRol;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class AdministrarRoles implements IAdministrarRoles {

    @EJB
    IPersistenciaRol persistenciaRol;

    @Override
    public List<Rol> obtenerRoles() {
        return persistenciaRol.obtenerRoles();
    }

    @Override
    public boolean registrarRol(Rol rol) {
        return persistenciaRol.registrarRol(rol);
    }

    @Override
    public boolean eliminarRol(Rol rol) {
        return persistenciaRol.eliminarRol(rol);
    }

    @Override
    public boolean modificarRol(Rol rol) {
        return persistenciaRol.modificarRol(rol);
    }
}
