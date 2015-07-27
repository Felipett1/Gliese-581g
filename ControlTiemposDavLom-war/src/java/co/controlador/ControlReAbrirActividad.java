package co.controlador;

import co.entidades.Actividades;
import co.entidades.Causas;
import co.entidades.ReabrirActividades;
import co.interfaces.administrar.IAdministrarReAbrirActividad;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.tree.Tree;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author 908036
 */
@ManagedBean
@SessionScoped
public class ControlReAbrirActividad implements Serializable {

    @EJB
    private IAdministrarReAbrirActividad administrarReAbrirActividad;
    private List<Actividades> listaActividades, filtroListaActividades;
    private Actividades actividadSeleccionada;
    private List<Causas> causas;
    private List<String> causasString;
    private String causa;
    private ReabrirActividades reAbrirActividad;
    private List<Actividades> estructuraActividad;
    private TreeNode actividadPadre;
    private Tree estructura;

    public ControlReAbrirActividad() {
        reAbrirActividad = new ReabrirActividades();
    }

    public void reAbrirActividadSeleccionada() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        if (actividadSeleccionada != null) {
            if (reAbrirActividad.getCausa() != null) {
                if (reAbrirActividad.getFechaInicial() != null) {
                    if (reAbrirActividad.getFechaFinal() != null) {
                        if (reAbrirActividad.getFechaInicial().compareTo(reAbrirActividad.getFechaFinal()) <= 0) {
                            if (reAbrirActividad.getObservacion() != null && reAbrirActividad.getObservacion().length() > 25) {
                                if (reAbrirActividad.getHorasEstimadas() != null && reAbrirActividad.getHorasEstimadas().compareTo(BigDecimal.ZERO) == 1) {
                                    reAbrirActividad.setEstado("ABIERTO");
                                    reAbrirActividad.setPorcentajeRetrabajo(BigDecimal.ZERO);
                                    reAbrirActividad.setActividad(actividadSeleccionada);

                                    if (administrarReAbrirActividad.registrarActividadReAbierta(reAbrirActividad)) {
                                        msg = new FacesMessage("Información", "Se reabrio la actividad con éxito.");
                                        limpiarCampos();
                                    } else {
                                        msg = new FacesMessage("Información", "Ocurrio un error al registrar las horas.");
                                    }
                                } else {
                                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las horas estimadas deben ser mayor a cero.");
                                }
                            } else {
                                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La observación debe ser minimo de 25 caracteres.");
                            }
                        } else {
                            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha inicial debe ser menor o igual a la fecha final.");
                        }
                    } else {
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione una fecha final por favor.");
                    }
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione una fecha inicial por favor.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione una causa por favor.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un actividad por favor.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.update("formularioPrincipal");
        context.update("formularioPrincipal:growl");
    }

    public void seleccionarActividad() {
        estructuraActividad();
        RequestContext context = RequestContext.getCurrentInstance();
        filtroListaActividades = null;
        listaActividades = null;
        context.execute("PF('actividadesDialogo').hide()");
        context.reset("formularioDialogos:tblActividades:globalFilter");
        context.update("formularioDialogos:tblActividades");
        context.update("formularioPrincipal:actividad");
    }

    public void cancelarSeleccionActividad() {
        filtroListaActividades = null;
        listaActividades = null;
    }

    public void limpiarCampos() {
        reAbrirActividad = new ReabrirActividades();
        actividadSeleccionada = null;
        causa = "NA";
        FacesContext c = FacesContext.getCurrentInstance();
        estructura = (Tree) c.getViewRoot().findComponent("formularioPrincipal:arbol");
        estructura.setStyle("visibility: hidden");

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formularioPrincipal");
    }

    public void estructuraActividad() {
        estructuraActividad = new ArrayList<Actividades>();
        Actividades actividad = actividadSeleccionada;
        estructuraActividad.add(actividad);
        while (true) {
            actividad = actividad.getActividadPadre();
            if (actividad != null) {
                estructuraActividad.add(actividad);
            } else {
                break;
            }
        }
        actividadPadre = new DefaultTreeNode("ActividadPadre", null);
        ListIterator iter = estructuraActividad.listIterator((estructuraActividad.size() - 1));
        TreeNode nodo = new DefaultTreeNode(estructuraActividad.get(estructuraActividad.size() - 1).getActividad(), actividadPadre);
        nodo.setExpanded(true);
        while (iter.hasPrevious()) {
            Actividades act = (Actividades) iter.previous();
            nodo = new DefaultTreeNode(act.getActividad(), nodo);
            nodo.setExpanded(true);
        }

        FacesContext c = FacesContext.getCurrentInstance();
        estructura = (Tree) c.getViewRoot().findComponent("formularioPrincipal:arbol");
        estructura.setStyle("position: absolute; left: 930px; top: 88px; width: 418px;");

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formularioPrincipal:arbol");
    }

    public Actividades getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Actividades actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }

    public List<Actividades> getFiltroListaActividades() {
        return filtroListaActividades;
    }

    public void setFiltroListaActividades(List<Actividades> filtroListaActividades) {
        this.filtroListaActividades = filtroListaActividades;
    }

    public List<Actividades> getListaActividades() {
        if (listaActividades == null || listaActividades.isEmpty()) {
            listaActividades = administrarReAbrirActividad.actividadesParaReabrir();
        }
        return listaActividades;
    }

    public void setListaActividades(List<Actividades> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public TreeNode getActividadPadre() {
        return actividadPadre;
    }

    public void setActividadPadre(TreeNode actividadPadre) {
        this.actividadPadre = actividadPadre;
    }

    public ReabrirActividades getReAbrirActividad() {
        return reAbrirActividad;
    }

    public void setReAbrirActividad(ReabrirActividades reAbrirActividad) {
        this.reAbrirActividad = reAbrirActividad;
    }

    public List<Causas> getCausas() {
        causas = administrarReAbrirActividad.buscarCausas();
        return causas;
    }

    public void setCausas(List<Causas> causas) {
        this.causas = causas;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        if (causa != null && !causa.equals("NA")) {
            for (int i = 0; i < causas.size(); i++) {
                if (causa.equals(causas.get(i).getNombre())) {
                    reAbrirActividad.setCausa(causas.get(i));
                    break;
                }
            }
        } else {
            reAbrirActividad.setCausa(null);
        }
        this.causa = causa;
    }

    public List<String> getCausasString() {
        causas = administrarReAbrirActividad.buscarCausas();
        if (causas != null && !causas.isEmpty()) {
            causasString = new ArrayList<String>();
            for (int i = 0; i < causas.size(); i++) {
                causasString.add(i, causas.get(i).getNombre());
            }
        }
        return causasString;
    }

    public void setCausasString(List<String> causasString) {
        this.causasString = causasString;
    }
}
