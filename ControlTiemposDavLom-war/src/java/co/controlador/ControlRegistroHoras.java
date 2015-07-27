/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.controlador;

import co.entidades.*;
import co.interfaces.administrar.IAdministrarRegistrosHoras;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.fieldset.Fieldset;
import org.primefaces.component.tree.Tree;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author 908034
 */
@ManagedBean
@SessionScoped
public class ControlRegistroHoras implements Serializable {

    @EJB
    IAdministrarRegistrosHoras administrarRegistrosHoras;
    private List<Asignaciones> listaAsignacionesEmpleado;
    private List<Asignaciones> filtradoListaAsignacionesEmpleado;
    private Asignaciones actividadSeleccionada, actividadRegistroHoras;
    private RegistroHoras registroHoras;
    private Empleados empleado;
    private List<Actividades> estructuraActividad;
    private TreeNode actividadPadre;
    private Tree estructura;
    private Fieldset infoReAbierta;
    private BigDecimal adicionarPorcentaje;

    public ControlRegistroHoras() {
        actividadRegistroHoras = new Asignaciones();
        registroHoras = new RegistroHoras();

    }

    public void usuarioLogeado(Empleados empleado) {
        this.empleado = empleado;
    }

    public void seleccionarActividad() {
        estructuraActividad();
        RequestContext context = RequestContext.getCurrentInstance();
        actividadRegistroHoras = actividadSeleccionada;
        filtradoListaAsignacionesEmpleado = null;
        listaAsignacionesEmpleado = null;
        //cronograma = null;
        FacesContext c = FacesContext.getCurrentInstance();
        infoReAbierta = (Fieldset) c.getViewRoot().findComponent("formularioPrincipal:informacionReAbierta");
        if (actividadSeleccionada.getIdActividad().isReAbierto()) {
            infoReAbierta.setStyle("position: absolute; top: 470px;");
        } else {
            infoReAbierta.setStyle("visibility: hidden");
        }

        context.update("formularioPrincipal:informacionReAbierta");
        context.execute("PF('actividadesDialogo').hide()");
        context.reset("formularioDialogos:tblActividades:globalFilter");
        context.update("formularioDialogos:tblActividades");
        context.update("formularioPrincipal:actividad");
        context.update("formularioPrincipal:progresoActual");
        context.update("formularioPrincipal:fechaI");
        context.update("formularioPrincipal:fechaF");
        context.update("formularioPrincipal:progresoEmpleado");
        context.update("formularioPrincipal:tiempoEstimado");
    }

    public void cancelarSeleccionActividad() {
        filtradoListaAsignacionesEmpleado = null;
        listaAsignacionesEmpleado = null;
        //cronograma = null;
    }

    public void registrarHoras() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        if (actividadSeleccionada != null && actividadSeleccionada.getIdActividad().isEstadoBloqueante()) {
            if (Integer.parseInt(new SimpleDateFormat("HH").format(new Date())) >= 8) {
                if (registroHoras.getObservaciones().length() >= 20) {
                    msg = null;
                    context.execute("PF('confirmarBloqueo').show();");
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Por favor adicione el motivo por el cual se va a bloquear la actividad. (Minimo 20 caracteres)");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Solo es posible bloquear actividades desde las 5:00 pm hasta las 11:59 pm.");
            }
        } else if (actividadSeleccionada != null && registroHoras.getHoraInicial() != null
                && registroHoras.getHoraFinal() != null && adicionarPorcentaje != null
                && adicionarPorcentaje.compareTo(BigDecimal.ZERO) > 0 && registroHoras.getObservaciones() != null) {
            BigDecimal totalProcentaje;
            totalProcentaje = actividadSeleccionada.getIdActividad().getPorcentajeEmpleado().add(adicionarPorcentaje);
//            if (actividadSeleccionada.getIdActividad().isReAbierto()) {
//                totalProcentaje = actividadSeleccionada.getIdActividad().getInfoAbierta().getPorcentajeRetrabajo().add(adicionarPorcentaje);
//            } else {
//                totalProcentaje = actividadSeleccionada.getIdActividad().getPorcentajeReal().add(adicionarPorcentaje);
//            }
            int comparacion = totalProcentaje.compareTo(BigDecimal.valueOf(100));
            if (comparacion == 0 || comparacion < 0) {
                if (registroHoras.getHoraInicial().compareTo(registroHoras.getHoraFinal()) < 0) {
                    registroHoras.setId(Long.valueOf(0));
                    registroHoras.setFecha(new Date());
                    registroHoras.setPorcentajeRegistrado(adicionarPorcentaje);
                    registroHoras.setAsignacion(actividadSeleccionada);

                    BigDecimal porcentajeRealActividad;
                    if (actividadSeleccionada.getIdActividad().isReAbierto()) {
                        porcentajeRealActividad = calcularPorcentajeReal(actividadSeleccionada.getIdActividad().getInfoAbierta().getPorcentajeRetrabajo(), actividadSeleccionada.getIdActividad().getInfoAbierta().getHorasEstimadas(), actividadSeleccionada.getHorasAsignadas(), adicionarPorcentaje);
                        registroHoras.getAsignacion().getIdActividad().getInfoAbierta().setPorcentajeRetrabajo(porcentajeRealActividad);
                        //registroHoras.setIdReabrirActividades(actividadSeleccionada.getIdActividad().getInfoAbierta());
                    } else {
                        porcentajeRealActividad = calcularPorcentajeReal(actividadSeleccionada.getIdActividad().getPorcentajeReal(), actividadSeleccionada.getIdActividad().getHorasBaseline(), actividadSeleccionada.getHorasAsignadas(), adicionarPorcentaje);
                        registroHoras.getAsignacion().getIdActividad().setPorcentajeReal(porcentajeRealActividad);
                        registroHoras.getAsignacion().setIdReabrirActividades(null);
                    }

                    if (actividadSeleccionada.getIdActividad().isReAbierto() && porcentajeRealActividad != null && porcentajeRealActividad.compareTo(BigDecimal.valueOf(100)) == 0) {
                        registroHoras.getAsignacion().getIdReabrirActividades().setEstado("CERRADO");
                    }

                    //QUITAMOS LA HORA DE ALMUERZO
                    if (registroHoras.getHoraInicial().compareTo(new BigDecimal(13)) >= 0) {
                        registroHoras.setTotalHoras(registroHoras.getHoraFinal().subtract(registroHoras.getHoraInicial()));
                    } else if (registroHoras.getHoraInicial().compareTo(new BigDecimal(12)) < 0 && registroHoras.getHoraFinal().compareTo(new BigDecimal(12)) <= 0) {
                        registroHoras.setTotalHoras(registroHoras.getHoraFinal().subtract(registroHoras.getHoraInicial()));
                    } else if (registroHoras.getHoraInicial().compareTo(new BigDecimal(12)) <= 0 && registroHoras.getHoraFinal().compareTo(new BigDecimal(13)) >= 0) {
                        registroHoras.setTotalHoras(registroHoras.getHoraFinal().subtract(registroHoras.getHoraInicial()).subtract(new BigDecimal(1)));
                    } else if (registroHoras.getHoraInicial().compareTo(new BigDecimal(12)) < 0 && registroHoras.getHoraFinal().compareTo(new BigDecimal(13)) < 0 && registroHoras.getHoraFinal().compareTo(new BigDecimal(12)) > 0) {
                        registroHoras.setTotalHoras(registroHoras.getHoraFinal().subtract(registroHoras.getHoraInicial()).subtract(new BigDecimal(13).subtract(registroHoras.getHoraFinal())));
                    } else if (registroHoras.getHoraInicial().compareTo(new BigDecimal(12)) > 0 && registroHoras.getHoraFinal().compareTo(new BigDecimal(13)) >= 0) {
                        registroHoras.setTotalHoras(registroHoras.getHoraFinal().subtract(registroHoras.getHoraInicial()).subtract(new BigDecimal(13).subtract(registroHoras.getHoraInicial())));
                    } else if (registroHoras.getHoraInicial().compareTo(new BigDecimal(12)) > 0 && registroHoras.getHoraFinal().compareTo(new BigDecimal(13)) < 0) {
                        registroHoras.setTotalHoras(BigDecimal.ZERO);
                    }

                    if (registroHoras.getTotalHoras().compareTo(BigDecimal.ZERO) > 0) {
                        if (!actividadSeleccionada.getIdActividad().isReAbierto() && administrarRegistrosHoras.registrarHoras(registroHoras) && administrarRegistrosHoras.modificarActividad(registroHoras.getAsignacion().getIdActividad())) {
                            msg = new FacesMessage("Información", "Se registraron las horas con éxito.");
                            if (comparacion == 0) {
                                actividadSeleccionada.setEstado("I");
                                administrarRegistrosHoras.modificarAsignacion(actividadSeleccionada);
                            }
                            limpiarCampos();
                        } else if (actividadSeleccionada.getIdActividad().isReAbierto() && administrarRegistrosHoras.registrarHoras(registroHoras) && administrarRegistrosHoras.actualizarPorcentajeReTrabajo(registroHoras.getAsignacion().getIdReabrirActividades())) {
                            msg = new FacesMessage("Información", "Se registraron las horas con éxito.");
                            if (comparacion == 0) {
                                actividadSeleccionada.setEstado("I");
                                administrarRegistrosHoras.modificarAsignacion(actividadSeleccionada);
                            }
                            limpiarCampos();
                        } else {
                            msg = new FacesMessage("Información", "Ocurrio un error al registrar las horas.");
                        }
                    } else {
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El total de horas registradas no puede ser cero.");
                    }
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La hora inicial debe ser menor a la hora final.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El porcentaje no puede sobrepasar el 100%.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hay campos obligatorios vacios.");
        }
        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("formularioPrincipal");
            context.update("formularioPrincipal:growl");
        }
    }

    public BigDecimal calcularPorcentajeReal(BigDecimal actualPorcentaje, BigDecimal horasEstimadas, BigDecimal horasAsignadas, BigDecimal porcentajeRegistrado) {
        BigDecimal porcentajeRealAdicionar;
        porcentajeRealAdicionar = porcentajeRegistrado.multiply(horasAsignadas).divide(horasEstimadas, 2, RoundingMode.HALF_UP);
        actualPorcentaje = actualPorcentaje.add(porcentajeRealAdicionar);
        return actualPorcentaje;
    }

    public void limpiarCampos() {
        registroHoras = new RegistroHoras();
        actividadRegistroHoras = new Asignaciones();
        actividadSeleccionada = null;
        adicionarPorcentaje = null;
        reiniciarActividadesAsignadas();

        FacesContext c = FacesContext.getCurrentInstance();
        estructura = (Tree) c.getViewRoot().findComponent("formularioPrincipal:arbol");
        estructura.setStyle("visibility: hidden");
        infoReAbierta = (Fieldset) c.getViewRoot().findComponent("formularioPrincipal:informacionReAbierta");
        infoReAbierta.setStyle("visibility: hidden");
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formularioPrincipal");
    }

    public void reiniciarActividadesAsignadas() {
        RequestContext context = RequestContext.getCurrentInstance();
        listaAsignacionesEmpleado = null;
        context.update("formularioDialogos:tblActividades");
    }

    public void estructuraActividad() {
        if (actividadSeleccionada != null) {
            estructuraActividad = new ArrayList<Actividades>();
            Actividades actividad = actividadSeleccionada.getIdActividad();
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
            estructura.setStyle("position: relative; left: 515px; top: -390px; width: 550px;");

            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formularioPrincipal:arbol");
        }
    }

    public void bloquearActividad() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        HistoricoBloqueos hb = new HistoricoBloqueos();
        hb.setIdActividad(actividadSeleccionada.getIdActividad());
        hb.setIdEmpleado(empleado);
        hb.setFecha(new Date());
        hb.setObservacion(registroHoras.getObservaciones());
        if (administrarRegistrosHoras.modificarActividad(actividadSeleccionada.getIdActividad()) && administrarRegistrosHoras.modificarHistoricoBloqueo(hb)) {
            msg = new FacesMessage("Información", "Actividad bloqueada exitosamente");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible bloquear la actividad.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.update("formularioPrincipal");
        context.update("formularioPrincipal:growl");
        limpiarCampos();
    }
    //GETTER AND SETTERS

    public List<Asignaciones> getListaAsignacionesEmpleado() {
        if (listaAsignacionesEmpleado == null || listaAsignacionesEmpleado.isEmpty()) {
            listaAsignacionesEmpleado = administrarRegistrosHoras.actividadesAsignadasEmpleado(empleado.getEmployee());
        }
        return listaAsignacionesEmpleado;
    }

    public List<Asignaciones> getFiltradoListaAsignacionesEmpleado() {
        return filtradoListaAsignacionesEmpleado;
    }

    public void setFiltradoListaAsignacionesEmpleado(List<Asignaciones> filtradoListaAsignacionesEmpleado) {
        this.filtradoListaAsignacionesEmpleado = filtradoListaAsignacionesEmpleado;
    }

    public Asignaciones getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Asignaciones actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }

    public Asignaciones getActividadRegistroHoras() {
        return actividadRegistroHoras;
    }

    public RegistroHoras getRegistroHoras() {
        return registroHoras;
    }

    public void setRegistroHoras(RegistroHoras registroHoras) {
        this.registroHoras = registroHoras;
    }

    public TreeNode getActividadPadre() {
        return actividadPadre;
    }

    public BigDecimal getAdicionarPorcentaje() {
        return adicionarPorcentaje;
    }

    public void setAdicionarPorcentaje(BigDecimal adicionarPorcentaje) {
        this.adicionarPorcentaje = adicionarPorcentaje;
    }
}
