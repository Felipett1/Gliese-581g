package co.administrar;

import co.entidades.Login;
import co.excepciones.PasswordIncorrectoExcepcion;
import co.excepciones.UsuarioIncorrectoExcepcion;
import co.interfaces.administrar.IAdministrarLogin;
import co.interfaces.persistencia.IPersistenciaLogin;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author 908036
 */
@Stateful
public class AdministrarLogin implements IAdministrarLogin {

    @EJB
    IPersistenciaLogin persistenciaLogin;

    @Override
    public Login validarInicioSesion(String nombreUsuario, String password) throws UsuarioIncorrectoExcepcion, PasswordIncorrectoExcepcion {
        Login login = persistenciaLogin.validarUsuario(nombreUsuario);
        if (login != null) {
            if (login.getPassword().equals(password)) {
                return login;
            } else {
                throw new PasswordIncorrectoExcepcion("La contraseña no es correcta.");
            }
        } else {
            throw new UsuarioIncorrectoExcepcion("El usuario no existe.");
        }
    }

    @Override
    public boolean cambiarPassword(Login login) {
        return persistenciaLogin.actualizarLogin(login);
    }
}
