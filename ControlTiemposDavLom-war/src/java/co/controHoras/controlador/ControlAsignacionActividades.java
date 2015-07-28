package co.controHoras.controlador;

import co.entidades.Actividades;
import co.entidades.Asignaciones;
import co.entidades.Empleados;
import co.interfaces.administrar.IAdministrarAsignacionActividades;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author 908034
 */
@ManagedBean
@SessionScoped
public class ControlAsignacionActividades implements Serializable {

    @EJB
    IAdministrarAsignacionActividades administrarAsignacionActividades;
    private List<Empleados> listaEmpleados, filtroListaEmpleados;
    private List<Actividades> listaActividades, filtroListaActividades;
    private Actividades actividadSeleccionada, actividad;
    private Empleados empleadosSeleccionado;
    private Asignaciones asignacion;
    private List<Actividades> estructuraActividad;
    private TreeNode actividadPadre;
    private Tree estructura;
    private BigDecimal horasAsignadas;

    public ControlAsignacionActividades() {
        listaEmpleados = new ArrayList<Empleados>();
        listaActividades = new ArrayList<Actividades>();
    }

    public void asignarActividad() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        if (actividad != null) {
            if (empleadosSeleccionado != null) {
                if (horasAsignadas != null && horasAsignadas.compareTo(BigDecimal.ZERO) == 1) {
                    if (sobrepasaHorasEstimadas()) {
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La suma de las horas asignadas supera las horas estimadas.");
                    } else {
                        if (!validarEmpleadoAsignado()) {
                            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El empleado seleccionado ya tiene la actividad asignada, si desea reasignar la actividad por favor cambie el empleado.");
                        } else {
                            asignacion = new Asignaciones();
                            asignacion.setIdEmpleado(empleadosSeleccionado);
                            asignacion.setIdActividad(actividad);
                            asignacion.setEstado("A");
                            asignacion.setHorasAsignadas(horasAsignadas);
                            if (actividad.isReAbierto()) {
                                asignacion.setIdReabrirActividades(actividad.getInfoAbierta());
                            }
                            boolean estado;
                            //if (actividad.isAsignado()) {
                            // actividad.getAsignacion().setEstado("I");
                            // estado = administrarAsignacionActividades.crear_Modificar_Asignacion(actividad.getAsignacion()) && administrarAsignacionActividades.crear_Modificar_Asignacion(asignacion);
                            // } else {
                            estado = administrarAsignacionActividades.crear_Modificar_Asignacion(asignacion);
                            // }

                            if (estado) {
                                msg = new FacesMessage("Información", "Se asigno la actividad con éxito.");
                                limpiarCampos();
                            } else {
                                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error asignando la actividad.");
                            }
                        }
                    }
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las horas asignadas deben ser mayores a 0.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un empleado por favor.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un actividad por favor.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.update("formularioPrincipal");
        context.update("formularioPrincipal:growl");
    }

    public boolean validarEmpleadoAsignado() {
        boolean resultado = true;
        if (actividad.isReAbierto()) {
            if (actividad.isAsignadoReAbierto()) {
                for (int i = 0; i < actividad.getAsignacionReAbierta().size(); i++) {
                    if (actividad.getAsignacionReAbierta().get(i).getIdEmpleado().getEmployee().compareTo(empleadosSeleccionado.getEmployee()) == 0) {
                        resultado = false;
                        break;
                    }
                }
            }
        } else {
            if (actividad.isAsignado()) {
                for (int i = 0; i < actividad.getAsignacion().size(); i++) {
                    if (actividad.getAsignacion().get(i).getIdEmpleado().getEmployee().compareTo(empleadosSeleccionado.getEmployee()) == 0) {
                        resultado = false;
                        break;
                    }
                }
            }
        }
        return resultado;
    }

    public boolean sobrepasaHorasEstimadas() {
        BigDecimal horasAsignadasActividad = BigDecimal.ZERO;
        BigDecimal horasEstimadas;

        if (actividad.isReAbierto()) {
            horasEstimadas = actividad.getInfoAbierta().getHorasEstimadas();
            if (actividad.isAsignadoReAbierto()) {
                for (int i = 0; i < actividad.getAsignacionReAbierta().size(); i++) {
                    horasAsignadasActividad = horasAsignadasActividad.add(actividad.getAsignacionReAbierta().get(i).getHorasAsignadas());
                }
                horasAsignadasActividad = horasAsignadasActividad.add(horasAsignadas);
                if (horasAsignadasActividad.compareTo(horasEstimadas) < 1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (horasAsignadas.compareTo(horasEstimadas) < 1) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            horasEstimadas = actividad.getHorasBaseline();
            if (actividad.isAsignado()) {
                for (int i = 0; i < actividad.getAsignacion().size(); i++) {
                    horasAsignadasActividad = horasAsignadasActividad.add(actividad.getAsignacion().get(i).getHorasAsignadas());
                }
                horasAsignadasActividad = horasAsignadasActividad.add(horasAsignadas);
                if (horasAsignadasActividad.compareTo(horasEstimadas) < 1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (horasAsignadas.compareTo(horasEstimadas) < 1) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public Empleados buscarEmpleado(BigInteger idEmpleado) {
        Empleados retorno = null;
        for (Empleados empleado : listaEmpleados) {
            if (empleado.getEmployee().equals(idEmpleado)) {
                retorno = empleado;
                break;
            }
        }
        return retorno;
    }

    public void seleccionarActividad() {
        estructuraActividad();
        RequestContext context = RequestContext.getCurrentInstance();
        actividad = actividadSeleccionada;
        filtroListaActividades = null;
        listaActividades = null;
        /*
         * if (actividad.isAsignado()) { empleadosSeleccionado =
         * actividad.getAsignacion().getIdEmpleado(); } else {
         * empleadosSeleccionado = null; }
         */
        context.execute("PF('actividadesDialogo').hide()");
        context.reset("formularioDialogos:tblActividades:globalFilter");
        context.update("formularioDialogos:tblActividades");
        context.update("formularioPrincipal");
    }

    //GETTER AND SETTER
    public void cancelarSeleccionActividad() {
        filtroListaActividades = null;
        listaActividades = null;
    }

    public void seleccionarEmpleado() {
        RequestContext context = RequestContext.getCurrentInstance();
        filtroListaEmpleados = null;
        listaEmpleados = null;
        context.execute("PF('empleadosDialogo').hide()");
        context.reset("formularioDialogos:tblEmpleados:globalFilter");
        context.update("formularioDialogos:tblEmpleados");
        context.update("formularioPrincipal:empleado");
    }

    public void cancelarSeleccionEmpleado() {
        filtroListaEmpleados = null;
        listaEmpleados = null;
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
        estructura.setStyle("position: absolute; left: 798px; top: 88px; width: 530px;");

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formularioPrincipal:arbol");
    }

    public void limpiarCampos() {
        actividad = null;
        empleadosSeleccionado = null;
        actividadSeleccionada = null;
        horasAsignadas = null;
        cancelarSeleccionActividad();
        cancelarSeleccionEmpleado();
        FacesContext c = FacesContext.getCurrentInstance();
        estructura = (Tree) c.getViewRoot().findComponent("formularioPrincipal:arbol");
        estructura.setStyle("visibility: hidden");
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formularioPrincipal");
        context.update("formularioDialogos");
    }

    /**
     * @return the listaEmpleados
     */
    public List<Empleados> getListaEmpleados() {
        listaEmpleados = administrarAsignacionActividades.consultarEmpleados();
        return listaEmpleados;
    }

    /**
     * @param listaEmpleados the listaEmpleados to set
     */
    public void setListaEmpleados(List<Empleados> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Empleados getEmpleadoSeleccionado() {
        return empleadosSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleados empleadosSeleccionado) {
        this.empleadosSeleccionado = empleadosSeleccionado;
    }

    /**
     * @return the listaActividades
     */
    public List<Actividades> getListaActividades() {
        if (listaActividades == null || listaActividades.isEmpty()) {
            listaActividades = administrarAsignacionActividades.consultarActividadesHijas();
        }
        return listaActividades;
    }

    /**
     * @param listaActividades the listaActividades to set
     */
    public void setListaActividades(List<Actividades> listaActividades) {
        this.listaActividades = listaActividades;
    }

    /**
     * @return the actividades
     */
    public Actividades getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Actividades actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }

    /**
     * @return the filtroListaActividades
     */
    public List<Actividades> getFiltroListaActividades() {
        return filtroListaActividades;
    }

    /**
     * @param filtroListaActividades the filtroListaActividades to set
     */
    public void setFiltroListaActividades(List<Actividades> filtroListaActividades) {
        this.filtroListaActividades = filtroListaActividades;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public TreeNode getActividadPadre() {
        return actividadPadre;
    }

    public List<Empleados> getFiltroListaEmpleados() {
        return filtroListaEmpleados;
    }

    public void setFiltroListaEmpleados(List<Empleados> filtroListaEmpleados) {
        this.filtroListaEmpleados = filtroListaEmpleados;
    }

    public BigDecimal getHorasAsignadas() {
        return horasAsignadas;
    }

    public void setHorasAsignadas(BigDecimal horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }
}
