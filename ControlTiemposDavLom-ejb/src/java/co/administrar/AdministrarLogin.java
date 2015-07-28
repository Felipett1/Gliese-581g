package co.administrar;

import co.entidades.Login;
import co.excepciones.PasswordIncorrectoExcepcion;
import co.excepciones.PermisosModuloExcepcion;
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
    public Login validarInicioSesion(String nombreUsuario, String password, int modulo) throws UsuarioIncorrectoExcepcion, PasswordIncorrectoExcepcion, PermisosModuloExcepcion {
        Login login = persistenciaLogin.validarUsuario(nombreUsuario);
        if (login != null) {
            if (login.getPassword().equals(password)) {
                if (modulo == 1) {
                    if (login.getModuloCH().equals("S")) {
                        return login;
                    } else {
                        throw new PermisosModuloExcepcion("El usuario no posee permisos para ingresar a este modulo.");
                    }
                } else if (modulo == 2) {
                    if (login.getModuloCR().equals("S")) {
                        return login;
                    } else {
                        throw new PermisosModuloExcepcion("El usuario no posee permisos para ingresar a este modulo.");
                    }
                }
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
