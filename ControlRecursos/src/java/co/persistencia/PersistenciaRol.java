package co.persistencia;

import co.entidades.Rol;
import co.interfaces.persistencia.IPersistenciaRol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersistenciaRol implements IPersistenciaRol{

@PersistenceContext(unitName = "ControlRecursosPU")
    private EntityManager em;

    @Override
    public boolean registrarRol(Rol rol) {
        try {
            if (em.merge(rol) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error PersistenciaRol.registrarRol: " + e);
            return false;
        }
    }    
}
