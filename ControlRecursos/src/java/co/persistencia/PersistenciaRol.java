package co.persistencia;

import co.entidades.Rol;
import co.interfaces.persistencia.IPersistenciaRol;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersistenciaRol implements IPersistenciaRol {

    @PersistenceContext(unitName = "ControlRecursosPU")
    private EntityManager em;

    @Override
    public boolean registrarRol(Rol rol) {
        try {
            em.persist(rol);
            return true;
        } catch (Exception e) {
            System.out.println("Error PersistenciaRol.registrarRol: " + e);
            return false;
        }
    }

    @Override
    public boolean eliminarRol(Rol rol) {
        try {
            em.remove(em.merge(rol));
            return true;
        } catch (Exception e) {
            System.out.println("Error PersistenciaRol.eliminarRol: " + e);
            return false;
        }
    }

    @Override
    public boolean modificarRol(Rol rol) {
        try {
            if (em.merge(rol) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error PersistenciaRol.modificarRol: " + e);
            return false;
        }
    }

    @Override
    public List<Rol> obtenerRoles() {
        try {
            Query query = em.createQuery("SELECT r FROM Rol r");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error PersistenciaRol.obtenerRoles: " + e);
            return null;
        }
    }

    @Override
    public Rol obtenerRol(BigDecimal id) {
        try {
            Query query = em.createQuery("SELECT r FROM Rol r WHERE r.id = :id");
            query.setParameter("id", id);
            return (Rol) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error PersistenciaRol.obtenerRol: " + e);
            return null;
        }
    }
}
