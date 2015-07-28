package co.com.administrar;

import co.entidades.Recurso;
import co.interfaces.persistencia.IPersistenciaRecurso;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class AdministrarRecursos {

    @EJB
    IPersistenciaRecurso persistenciaRecurso;
    
    public boolean registrarRecurso(Recurso recurso){
        return persistenciaRecurso.registrarRecurso(recurso);
    }
    
}
