package co.controlRecursos.controlador;

import co.entidades.Rol;
import co.interfaces.administrar.IAdministrarRoles;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ControladorRoles implements Serializable {

    @EJB
    private IAdministrarRoles administrarRoles;
    private List<Rol> listaRoles;
    private Rol seleccionRol;
    private Rol nuevoRol;

    public ControladorRoles() {
        nuevoRol = new Rol();
    }

    @PostConstruct
    public void init() {
        requerirListaRoles();
    }

    public void agregarRol() {
        FacesMessage msg = null;
        RequestContext context = RequestContext.getCurrentInstance();
        if (nuevoRol.getNombre() != null && !nuevoRol.getNombre().isEmpty() && nuevoRol.getCosto() != null && nuevoRol.getCosto().compareTo(BigDecimal.ZERO) > 0) {
            nuevoRol.setId(BigDecimal.ZERO);
            if (administrarRoles.registrarRol(nuevoRol)) {
                nuevoRol = new Rol();
                requerirListaRoles();
                context.execute("PF('dialogoCrearRol').hide();");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Rol creado exitosamente.");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible registrar el rol.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Existen campos obligatorios vacios.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminarRol() {
        FacesMessage msg = null;
        if (seleccionRol != null) {
            if (!administrarRoles.validarRolEnRecurso(seleccionRol.getId())) {
                if (administrarRoles.eliminarRol(seleccionRol)) {
                    seleccionRol = null;
                    requerirListaRoles();
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Rol eliminado exitosamente.");
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible eliminar el rol.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No es posible eliminar el rol porque esta asignado a un recurso.");
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void editarRol() {
        FacesMessage msg = null;
        if (seleccionRol != null && seleccionRol.getNombre() != null && !seleccionRol.getNombre().isEmpty() && seleccionRol.getCosto() != null && seleccionRol.getCosto().compareTo(BigDecimal.ZERO) > 0) {
            if (administrarRoles.modificarRol(seleccionRol)) {
                seleccionRol = null;
                requerirListaRoles();
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarRol').hide();");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Rol modificado exitosamente.");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible modificar el rol.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los valores de los campos son invalidos.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void requerirListaRoles() {
        listaRoles = administrarRoles.obtenerRoles();
    }
    //GETTER AND SETTERS

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public Rol getSeleccionRol() {
        return seleccionRol;
    }

    public void setSeleccionRol(Rol seleccionRol) {
        this.seleccionRol = seleccionRol;
    }

    public Rol getNuevoRol() {
        return nuevoRol;
    }

    public void setNuevoRol(Rol nuevoRol) {
        this.nuevoRol = nuevoRol;
    }
}
