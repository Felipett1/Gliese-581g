package co.controlRecursos.controlador;

import co.entidades.Recurso;
import co.entidades.Rol;
import co.interfaces.administrar.IAdministrarRecursos;
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
public class ControladorRecursos implements Serializable {

    @EJB
    private IAdministrarRecursos administrarRecursos;
    private List<Recurso> listaRecursos;
    private List<Rol> listaRoles;
    private Recurso seleccionRecurso;
    private Recurso nuevoRecurso;

    public ControladorRecursos() {
        nuevoRecurso = new Recurso();
    }

    @PostConstruct
    public void init() {
        requerirListaRecursos();
        requerirListaRoles();
    }

    public void agregarRecurso() {
        FacesMessage msg;
        RequestContext context = RequestContext.getCurrentInstance();
        if (nuevoRecurso.getNombre() != null && !nuevoRecurso.getNombre().isEmpty()
                && nuevoRecurso.getPrimerApellido() != null && !nuevoRecurso.getSegundoApellido().isEmpty()
                && nuevoRecurso.getIdentificacion() != null && nuevoRecurso.getRol() != null) {
            if (!administrarRecursos.validarIdentificacionRecurso(nuevoRecurso.getIdentificacion())) {
                nuevoRecurso.setId(BigDecimal.ZERO);
                if (administrarRecursos.registrarRecurso(nuevoRecurso)) {
                    nuevoRecurso = new Recurso();
                    requerirListaRecursos();
                    context.execute("PF('dialogoCrearRecurso').hide();");
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Recurso creado exitosamente.");
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible registrar el recurso.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ya existe un recurso con la identificación: " + nuevoRecurso.getIdentificacion());
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Existen campos obligatorios vacios.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminarRecurso() {
        FacesMessage msg = null;
        if (seleccionRecurso != null) {
            if (!administrarRecursos.validarAsignacionRecurso(seleccionRecurso.getIdentificacion())) {
                if (!administrarRecursos.validarAusentismoRecurso(seleccionRecurso.getIdentificacion())) {
                    if (administrarRecursos.eliminarRecurso(seleccionRecurso)) {
                        seleccionRecurso = null;
                        requerirListaRecursos();
                        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Recurso eliminado exitosamente.");
                    } else {
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible eliminar el Recurso.");
                    }
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El recurso no puede ser eliminado porque tiene registrado uno o varios ausentismos.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El recurso no puede ser eliminado porque esta asignado a una acta.");
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void editarRecurso() {
        FacesMessage msg;
        if (seleccionRecurso.getNombre() != null && !seleccionRecurso.getNombre().isEmpty()
                && seleccionRecurso.getPrimerApellido() != null && !seleccionRecurso.getSegundoApellido().isEmpty()
                && seleccionRecurso.getIdentificacion() != null && seleccionRecurso.getRol() != null) {
            if (administrarRecursos.modificarRecurso(seleccionRecurso)) {
                seleccionRecurso = null;
                requerirListaRecursos();
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarRecurso').hide();");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Recurso modificado exitosamente.");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible modificar el Recurso.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los valores de los campos son invalidos.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void requerirListaRecursos() {
        listaRecursos = administrarRecursos.obtenerRecursos();
    }

    public void requerirListaRoles() {
        listaRoles = administrarRecursos.obtenerListadoRoles();
    }
    //GETTER AND SETTERS

    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public Recurso getSeleccionRecurso() {
        return seleccionRecurso;
    }

    public void setSeleccionRecurso(Recurso seleccionRecurso) {
        this.seleccionRecurso = seleccionRecurso;
    }

    public Recurso getNuevoRecurso() {
        return nuevoRecurso;
    }

    public void setNuevoRecurso(Recurso nuevoRecurso) {
        this.nuevoRecurso = nuevoRecurso;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }
}
