package co.persistencia;

import co.entidades.Login;
import co.interfaces.persistencia.IPersistenciaLogin;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 908036
 */
@Singleton
public class PersistenciaLogin implements IPersistenciaLogin {

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;

    @Override
    public Login validarUsuario(String nombreUsuario) {
        Login login = null;
        try {
            Query query = em.createQuery("SELECT L FROM Login L WHERE L.nameUser = :nombreUsuario");
            query.setParameter("nombreUsuario", nombreUsuario);
            login = (Login) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No existe ningún usuario con ese nombre.\n" + e.getMessage());
        }
        return login;
    }

    @Override
    public boolean actualizarLogin(Login login) {
        if (em.merge(login) != null) {
            return true;
        } else {
            return false;
        }
    }
}
