package co.interfaces.administrar;

import co.entidades.Login;
import co.excepciones.PasswordIncorrectoExcepcion;
import co.excepciones.UsuarioIncorrectoExcepcion;

/**
 *
 * @author 908036
 */
public interface IAdministrarLogin {

    public Login validarInicioSesion(String nombreUsuario, String password) throws UsuarioIncorrectoExcepcion, PasswordIncorrectoExcepcion;
    public boolean cambiarPassword(Login login);
}
