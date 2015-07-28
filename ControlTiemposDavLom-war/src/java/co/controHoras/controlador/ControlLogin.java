package co.controHoras.controlador;

import co.autenticacion.Util;
import co.entidades.Login;
import co.excepciones.PasswordIncorrectoExcepcion;
import co.excepciones.PermisosModuloExcepcion;
import co.excepciones.UsuarioIncorrectoExcepcion;
import co.interfaces.administrar.IAdministrarLogin;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author 908036
 */
@ManagedBean
@SessionScoped
public class ControlLogin implements Serializable {

    @EJB
    private IAdministrarLogin administrarLogin;
    private Login login;
    private String nombreUsuario, password;
    private int modulo;

    public ControlLogin() {
    }

    public void opcionLogin(int modulo) {
        this.modulo = modulo;
    }

    public void validarAcceso() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        if (!nombreUsuario.isEmpty() && !password.isEmpty()) {
            try {
                login = administrarLogin.validarInicioSesion(nombreUsuario, password, modulo);
                login.getIdEmpleado().setAdministrador(login.getAdministrador());
                HttpSession session = Util.getSession();
                session.setAttribute("idUsuario", nombreUsuario);
                msg = new FacesMessage("Información", "Acceso correcto.");
                context.execute("iniciarPlantilla()");
                if (modulo == 1) {
                    context.execute("iniciarCambioPwd()");
                    context.execute("iniciarAppControlHoras()");
                } else if (modulo == 2) {
                    context.execute("iniciarAppControlRecursos()");
                }
            } catch (UsuarioIncorrectoExcepcion usuarioE) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", usuarioE.getMessage());
            } catch (PasswordIncorrectoExcepcion passwordE) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", passwordE.getMessage());
            } catch (PermisosModuloExcepcion permisoModuloE) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", permisoModuloE.getMessage());
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Existen campos vacios.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.update("formularioPrincipal");
        context.update("formularioPrincipal:growl");
    }

    //GETTERS AND SETTERS
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login getLogin() {
        return login;
    }

    public int getModulo() {
        return modulo;
    }
}
