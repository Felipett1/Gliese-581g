package co.controlador;

import co.autenticacion.Util;
import co.entidades.Empleados;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author 908036
 */
@ManagedBean
@SessionScoped
public class ControlPlantilla implements Serializable {

    private Empleados empleado;
    private String verMenuCH, verMenuCR, verSubMenuCH, verSubMenuCR;

    public ControlPlantilla() {
    }

    public void usuarioLogeado(Empleados empleado, int modulo) {
        //RequestContext context = RequestContext.getCurrentInstance();
        this.empleado = empleado;
        if (modulo == 1) {
            if (this.empleado.getAdministrador().equals("S")) {
                verSubMenuCH = "";
            } else {
                verSubMenuCH = "visibility: hidden; display: none";
            }
            verMenuCH = "";
            verMenuCR = "visibility: hidden; display: none";
        } else if (modulo == 2) {
            if (this.empleado.getAdministrador().equals("S")) {
                verSubMenuCR = "";
            } else {
                verSubMenuCR = "visibility: hidden; display: none";
            }
            verMenuCR = "";
            verMenuCH = "visibility: hidden; display: none";
        }
        //context.update("formularioPrincipal");
    }

    public void cerrarSession() throws IOException {
        HttpSession session = Util.getSession();
        session.invalidate();

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath());


    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public String getVerMenuCH() {
        return verMenuCH;
    }

    public String getVerMenuCR() {
        return verMenuCR;
    }

    public String getVerSubMenuCH() {
        return verSubMenuCH;
    }

    public String getVerSubMenuCR() {
        return verSubMenuCR;
    }    
    
}
