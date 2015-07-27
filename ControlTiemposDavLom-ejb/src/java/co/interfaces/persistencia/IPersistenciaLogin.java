package co.interfaces.persistencia;

import co.entidades.Login;

/**
 *
 * @author 908036
 */
public interface IPersistenciaLogin {
    public Login validarUsuario(String nombreUsuario);
    public boolean actualizarLogin(Login login);
}
