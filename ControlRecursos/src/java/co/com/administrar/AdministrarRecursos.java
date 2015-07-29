package co.com.administrar;

import co.entidades.Recurso;
import co.entidades.Rol;
import co.interfaces.administrar.IAdministrarRecursos;
import co.interfaces.persistencia.IPersistenciaAsignacion;
import co.interfaces.persistencia.IPersistenciaAusentismo;
import co.interfaces.persistencia.IPersistenciaRecurso;
import co.interfaces.persistencia.IPersistenciaRol;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class AdministrarRecursos implements IAdministrarRecursos {

    @EJB
    IPersistenciaRecurso persistenciaRecurso;
    @EJB
    IPersistenciaRol persistenciaRol;
    @EJB
    IPersistenciaAsignacion persistenciaAsignacion;
    @EJB
    IPersistenciaAusentismo persistenciaAusentismo;

    @Override
    public boolean registrarRecurso(Recurso recurso) {
        return persistenciaRecurso.registrarRecurso(recurso);
    }

    @Override
    public List<Recurso> obtenerRecursos() {
        return persistenciaRecurso.obtenerRecursos();
    }

    @Override
    public boolean eliminarRecurso(Recurso Recurso) {
        return persistenciaRecurso.eliminarRecurso(Recurso);
    }

    @Override
    public boolean modificarRecurso(Recurso Recurso) {
        return persistenciaRecurso.modificarRecurso(Recurso);
    }

    @Override
    public boolean validarIdentificacionRecurso(Long identificacionRecurso) {
        return persistenciaRecurso.validarIdentificacionRecurso(identificacionRecurso);
    }

    @Override
    public List<Rol> obtenerListadoRoles() {
        return persistenciaRol.obtenerRoles();
    }
    
    @Override
    public Rol obtenerRol(BigDecimal id){
        return persistenciaRol.obtenerRol(id);
    }
    
    @Override
    public boolean validarAsignacionRecurso(Long identificacionRecurso){
        return persistenciaAsignacion.validarAsignacionRecurso(identificacionRecurso);
    }
    
    @Override
    public boolean validarAusentismoRecurso(Long identificacionRecurso){
        return persistenciaAusentismo.validarAusentismoRecurso(identificacionRecurso);
    }
}
