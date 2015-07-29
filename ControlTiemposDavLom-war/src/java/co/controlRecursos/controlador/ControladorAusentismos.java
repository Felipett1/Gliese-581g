package co.controlRecursos.controlador;

import co.entidades.Ausentismo;
import co.entidades.Recurso;
import co.interfaces.administrar.IAdministrarAusentismos;
import java.io.Serializable;
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
public class ControladorAusentismos implements Serializable {

    @EJB
    private IAdministrarAusentismos administrarAusentismo;
    private List<Ausentismo> listaAusentismo;
    private Ausentismo seleccionAusentismo;
    private Ausentismo nuevoAusentismo;
    private List<Recurso> lovRecursos;
    private List<Recurso> filtradoLovRecursos;
    private Recurso seleccionRecurso;
    private int opcionLovRecurso;

    public ControladorAusentismos() {
        nuevoAusentismo = new Ausentismo();
    }

    @PostConstruct
    public void init() {
        requerirListaAusentismo();
        requerirListaRecursos();
    }

    public void agregarAusentismo() {
        FacesMessage msg;
        RequestContext context = RequestContext.getCurrentInstance();
        if (nuevoAusentismo.getFechaInicio() != null && nuevoAusentismo.getFechaFin() != null
                && nuevoAusentismo.getIdRecurso() != null) {
            if (!administrarAusentismo.validarFechasAusentismoRecurso(nuevoAusentismo.getIdRecurso().getIdentificacion(), nuevoAusentismo.getFechaInicio(), nuevoAusentismo.getFechaFin())) {
                if (administrarAusentismo.registrarAusentismo(nuevoAusentismo)) {
                    nuevoAusentismo = new Ausentismo();
                    requerirListaAusentismo();
                    context.execute("PF('dialogoCrearAusentismo').hide();");
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Ausentismo creado exitosamente.");
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible registrar el ausentismo.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No es posible crear el ausentismo porque las fechas se tralapan con otro ausentismo.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Existen campos obligatorios vacios.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminarAusentismo() {
        FacesMessage msg = null;
        if (seleccionAusentismo != null) {
            if (administrarAusentismo.eliminarAusentismo(seleccionAusentismo)) {
                seleccionAusentismo = null;
                requerirListaAusentismo();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Ausentismo eliminado exitosamente.");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible eliminar el Ausentismo.");
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void editarAusentismo() {
        FacesMessage msg;
        if (seleccionAusentismo != null) {
            if (seleccionAusentismo.getFechaInicio() != null && seleccionAusentismo.getFechaFin() != null
                    && seleccionAusentismo.getIdRecurso() != null) {
                seleccionAusentismo = null;
                requerirListaAusentismo();
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarAusentismo').hide();");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Ausentismo modificado exitosamente.");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible modificar el Ausentismo.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los valores de los campos son invalidos.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void seleccionarRecurso() {
        System.out.println("opcionLovRecurso: " + opcionLovRecurso);
        RequestContext context = RequestContext.getCurrentInstance();
        if (opcionLovRecurso == 0 && seleccionRecurso != null) {
            nuevoAusentismo.setIdRecurso(seleccionRecurso);
            context.update("formularioDialogos:recursoAusentismo");
        } else if (opcionLovRecurso == 1 && seleccionRecurso != null) {
            seleccionAusentismo.setIdRecurso(seleccionRecurso);
            context.update("formularioDialogos:recursoAusentismoEditar");
        }
    }

    public void opcionLovRecursos(int opcion) {
        this.opcionLovRecurso = opcion;
    }

    public void requerirListaAusentismo() {
        listaAusentismo = administrarAusentismo.obtenerAusentismos();
    }

    public void requerirListaRecursos() {
        lovRecursos = administrarAusentismo.lovRecursos();
    }
    //GETTER AND SETTERS

    public List<Ausentismo> getListaAusentismo() {
        return listaAusentismo;
    }

    public void setListaAusentismo(List<Ausentismo> listaAusentismo) {
        this.listaAusentismo = listaAusentismo;
    }

    public Ausentismo getSeleccionAusentismo() {
        return seleccionAusentismo;
    }

    public void setSeleccionAusentismo(Ausentismo seleccionAusentismo) {
        this.seleccionAusentismo = seleccionAusentismo;
    }

    public Ausentismo getNuevoAusentismo() {
        return nuevoAusentismo;
    }

    public void setNuevoAusentismo(Ausentismo nuevoAusentismo) {
        this.nuevoAusentismo = nuevoAusentismo;
    }

    public List<Recurso> getLovRecursos() {
        return lovRecursos;
    }

    public List<Recurso> getFiltradoLovRecursos() {
        return filtradoLovRecursos;
    }

    public void setFiltradoLovRecursos(List<Recurso> filtradoLovRecursos) {
        this.filtradoLovRecursos = filtradoLovRecursos;
    }

    public Recurso getSeleccionRecurso() {
        return seleccionRecurso;
    }

    public void setSeleccionRecurso(Recurso seleccionRecurso) {
        this.seleccionRecurso = seleccionRecurso;
    }
}
