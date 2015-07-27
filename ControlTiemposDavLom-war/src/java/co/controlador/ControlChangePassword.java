package co.controlador;

import co.entidades.Login;
import co.interfaces.administrar.IAdministrarLogin;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author 908036
 */
@ManagedBean
@SessionScoped
public class ControlChangePassword implements Serializable {

    @EJB
    private IAdministrarLogin administrarLogin;
    private Login loginActual;
    private String actualPwd, nuevoPwd, confirmaPwd;

    public ControlChangePassword() {
    }

    public void recibirLogin(Login login) {
        loginActual = login;
    }

    public void cambiarPassword() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        if (!actualPwd.isEmpty() && !nuevoPwd.isEmpty() && !confirmaPwd.isEmpty()) {
            if (loginActual != null && loginActual.getPassword().equals(actualPwd)) {
                if (nuevoPwd.equals(confirmaPwd)) {
                    loginActual.setPassword(nuevoPwd);
                    if (administrarLogin.cambiarPassword(loginActual)) {
                        msg = new FacesMessage("Información", "Cambio de contraseña correcto.");
                        actualPwd = null;
                        nuevoPwd = null;
                        confirmaPwd = null;
                    } else {
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al cambiar la contraseña.");
                    }
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña actual no es correcta.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hay campos obligatorios vacios.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.update("formularioPrincipal");
        context.update("formularioPrincipal:growl");
    }

    public String getActualPwd() {
        return actualPwd;
    }

    public void setActualPwd(String actualPwd) {
        this.actualPwd = actualPwd;
    }

    public String getConfirmaPwd() {
        return confirmaPwd;
    }

    public void setConfirmaPwd(String confirmaPwd) {
        this.confirmaPwd = confirmaPwd;
    }

    public String getNuevoPwd() {
        return nuevoPwd;
    }

    public void setNuevoPwd(String nuevoPwd) {
        this.nuevoPwd = nuevoPwd;
    }
}
