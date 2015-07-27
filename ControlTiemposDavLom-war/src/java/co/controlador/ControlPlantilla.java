/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author 908036
 */
@ManagedBean
@SessionScoped
public class ControlPlantilla implements Serializable {

    private Empleados empleado;
    private String verMenu;

    public ControlPlantilla() {
    }

    public void usuarioLogeado(Empleados empleado) {
        this.empleado = empleado;
        if (this.empleado.getAdministrador().equals("S")) {
            verMenu = "";
        } else {
            verMenu = "visibility: hidden; display: none";
        }
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

    public String getVerMenu() {
        return verMenu;
    }
}
