/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.controlRecursos.controlador;

import co.entidades.Acta;
import co.entidades.Asignacion;
import co.entidades.Recurso;
import co.interfaces.administrar.IAdministrarActa;
import co.interfaces.administrar.IAdministrarRecursos;
import co.interfaces.administrar.IAdministrarResultadoDiasHabiles;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author 908034
 */
@ManagedBean
@SessionScoped
public class ControladorCrearActa implements Serializable {

    @EJB
    private IAdministrarActa administrarActa;
    @EJB
    private IAdministrarRecursos administrarRecursos;
    @EJB
    private IAdministrarResultadoDiasHabiles administrarResultadoDiasHabiles;
    private Acta acta;
    private Asignacion nuevaAsignacion;
    private Asignacion seleccionAsignacion;
    private List<Asignacion> asignaciones;
    private List<Recurso> lovRecursos, filtradoLovRecursos;
    private Recurso seleccionRecurso;
    private int opcionLovRecurso;

    public ControladorCrearActa() {
        acta = new Acta();
        nuevaAsignacion = new Asignacion();
        asignaciones = new ArrayList<Asignacion>();
    }

    @PostConstruct
    public void init() {
        requerirLovRecursos();
    }

    public boolean validarActa() {
        boolean retorno = false;
        FacesMessage msg = null;
        RequestContext context = RequestContext.getCurrentInstance();
        if (acta.getNumeroActa() != null && acta.getNombre() != null && !acta.getNombre().isEmpty() && acta.getHoras() != null
                && acta.getPresupuesto() != null && acta.getPresupuesto().compareTo(BigDecimal.ZERO) > 0 && acta.getFechaInicio() != null
                && acta.getFechaFin() != null) {
            if (acta.getFechaInicio().compareTo(acta.getFechaFin()) <= 0) {
                if (!administrarActa.validarNumeroActa(acta.getNumeroActa())) {
                    retorno = true;
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Validación de acta exitosa.");
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El número del acta ya esxiste.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha inicial debe ser menor a la fecha final.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Existen campos obligatorios vacios.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.update("formActa:growl");
        return retorno;
    }

    public String onFlowProcess(FlowEvent event) {
        if (validarActa()) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
    }

    public void agregarAsignacion() {
        FacesMessage msg = null;
        if (nuevaAsignacion.getFechaInicio() != null && nuevaAsignacion.getHoras() != null
                && nuevaAsignacion.getHoras().compareTo(BigDecimal.ZERO) > 0 && nuevaAsignacion.getIdRecurso() != null) {
            if (validarHorasAsignacion(nuevaAsignacion.getHoras())) {
                if (validarCostoAsignacion(nuevaAsignacion.getCostoRecurso())) {
                    if (!validarRecursoAsignacion(nuevaAsignacion.getIdRecurso().getIdentificacion())) {
                        asignaciones.add(nuevaAsignacion);
                        nuevaAsignacion = new Asignacion();
                        RequestContext.getCurrentInstance().execute("PF('dialogoCrearAsignacion').hide();");
                        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Asignación agregada con exito.");
                    } else {
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El recurso ya fue asignado a esta acta.");
                    }
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El costo de los recursos asignados supera el presupuesto del acta.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El número de horas asignadas supera las horas del acta.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Existen campos obligatorios vacios.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        RequestContext.getCurrentInstance().update("formActa:growl");
    }

    public void crearActa() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
//        if(){
//            
//        }
    }

    public void eliminarAsignacion() {
        if (seleccionAsignacion != null) {
            for (int i = 0; i < asignaciones.size(); i++) {
                if (seleccionAsignacion.getIdRecurso().getIdentificacion().compareTo(asignaciones.get(i).getIdRecurso().getIdentificacion()) == 0) {
                    asignaciones.remove(i);
                    break;
                }
            }
        }
    }

    public void modificarAsignacion() {
        FacesMessage msg = null;
        if (seleccionAsignacion != null) {
            if (seleccionAsignacion.getFechaInicio() != null && seleccionAsignacion.getHoras() != null
                    && seleccionAsignacion.getHoras().compareTo(BigDecimal.ZERO) > 0 && seleccionAsignacion.getIdRecurso() != null) {
                if (validarHorasAsignacion(seleccionAsignacion.getHoras())) {
                    if (validarCostoAsignacion(seleccionAsignacion.getCostoRecurso())) {
                        if (!validarRecursoAsignacionModificar(seleccionAsignacion.getIdRecurso().getIdentificacion())) {
                            seleccionRecurso = null;
                            RequestContext.getCurrentInstance().execute("PF('dialogoEditarAsignacion').hide();");
                            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Asignación modificada con exito.");
                        } else {
                            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El recurso ya fue asignado a esta acta.");
                        }
                    } else {
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El costo de los recursos asignados supera el presupuesto del acta.");
                    }
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El número de horas asignadas supera las horas del acta.");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Existen campos obligatorios vacios.");
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        RequestContext.getCurrentInstance().update("formActa:growl");
    }

    public void seleccionarRecurso() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (opcionLovRecurso == 0 && seleccionRecurso != null) {
            nuevaAsignacion.setIdRecurso(seleccionRecurso);
            context.update("formularioDialogos:recursoAsignacion");
            //obtenerResultadoDiasHabiles();
        } else if (opcionLovRecurso == 1 && seleccionRecurso != null) {
//            seleccionAusentismo.setIdRecurso(seleccionRecurso);
//            context.update("formularioDialogos:recursoAusentismoEditar");
        }
        seleccionRecurso = null;
    }

    public boolean validarHorasAsignacion(BigDecimal horas) {
        if (horas != null) {
            if (asignaciones != null && !asignaciones.isEmpty()) {
                BigDecimal contador = BigDecimal.ZERO;
                for (Asignacion asignacion : asignaciones) {
                    contador = contador.add(asignacion.getHoras());
                }
                contador = contador.add(horas);
                if (contador.compareTo(acta.getHoras()) <= 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (horas.compareTo(acta.getHoras()) <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validarCostoAsignacion(BigDecimal costoNuevoRecurso) {
        if (costoNuevoRecurso != null) {

            if (asignaciones != null && !asignaciones.isEmpty()) {
                BigDecimal costo = BigDecimal.ZERO;
                for (Asignacion asignacion : asignaciones) {
                    costo = costo.add(asignacion.getCostoRecurso());
                }
                costo = costo.add(costoNuevoRecurso);
                if (costo.compareTo(acta.getPresupuesto()) <= 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (costoNuevoRecurso.compareTo(acta.getPresupuesto()) <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validarRecursoAsignacion(Long identificacion) {
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getIdRecurso().getIdentificacion().compareTo(identificacion) == 0) {
                return true;
            }
        }

        return false;
    }

    public boolean validarRecursoAsignacionModificar(Long identificacion) {
        int contador = 0;
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getIdRecurso().getIdentificacion().compareTo(identificacion) == 0) {
                contador++;
            }
        }
        if (contador >= 2) {
            return true;
        } else if (contador == 1) {
            return false;
        }
        return true;
    }

    public void editarAsignacion() {
        FacesMessage msg;
        if (nuevaAsignacion != null) {
            if (nuevaAsignacion.getIdRecurso() != null && nuevaAsignacion.getFechaInicio() != null) {
                seleccionRecurso = null;
//                requerirLista();
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarAsignacion').hide();");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Asignación modificada exitosamente.");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No fue posible modificar la Asignación.");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los valores de los campos son invalidos.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void obtenerResultadoDiasHabiles(int opcion) {
        if (opcion == 0) {
            if (nuevaAsignacion.getFechaInicio() != null && nuevaAsignacion.getHoras() != null && nuevaAsignacion.getHoras().compareTo(BigDecimal.ZERO) >= 0) {
                nuevaAsignacion.setFechaFinEstimada(administrarResultadoDiasHabiles.resultadoDiasHabiles(nuevaAsignacion.getFechaInicio(), nuevaAsignacion.getHoras(), nuevaAsignacion.getIdRecurso().getIdentificacion()).getFechaFinalEstimada());
            }
        } else if (opcion == 1 && seleccionAsignacion != null) {
            if (seleccionAsignacion.getFechaInicio() != null && seleccionAsignacion.getHoras() != null && seleccionAsignacion.getHoras().compareTo(BigDecimal.ZERO) >= 0) {
                seleccionAsignacion.setFechaFinEstimada(administrarResultadoDiasHabiles.resultadoDiasHabiles(seleccionAsignacion.getFechaInicio(), seleccionAsignacion.getHoras(), seleccionAsignacion.getIdRecurso().getIdentificacion()).getFechaFinalEstimada());
            }
        }
    }

    public void obtenerCostoRecurso(int opcion) {
        if (opcion == 0) {
            if (nuevaAsignacion.getIdRecurso() != null && nuevaAsignacion.getHoras().compareTo(BigDecimal.ONE) >= 0) {
                nuevaAsignacion.setCostoRecurso(nuevaAsignacion.getIdRecurso().getRol().getCosto().multiply(nuevaAsignacion.getHoras()));
            }
        } else if (opcion == 1 && seleccionAsignacion != null) {
            if (seleccionAsignacion.getIdRecurso() != null && seleccionAsignacion.getHoras().compareTo(BigDecimal.ONE) >= 0) {
                seleccionAsignacion.setCostoRecurso(seleccionAsignacion.getIdRecurso().getRol().getCosto().multiply(seleccionAsignacion.getHoras()));
            }
        }
    }

    public void requerirLovRecursos() {
        lovRecursos = administrarRecursos.obtenerRecursosDisponible();
    }

    public void opcionLovRecursos(int opcion) {
        this.opcionLovRecurso = opcion;
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignacion) {
        this.asignaciones = asignacion;
    }

    public List<Recurso> getFiltradoLovRecursos() {
        return filtradoLovRecursos;
    }

    public void setFiltradoLovRecursos(List<Recurso> filtradoLovRecursos) {
        this.filtradoLovRecursos = filtradoLovRecursos;
    }

    public List<Recurso> getLovRecursos() {
        return lovRecursos;
    }

    public void setLovRecursos(List<Recurso> lovRecursos) {
        this.lovRecursos = lovRecursos;
    }

    public Recurso getSeleccionRecurso() {
        return seleccionRecurso;
    }

    public void setSeleccionRecurso(Recurso seleccionRecurso) {
        this.seleccionRecurso = seleccionRecurso;
    }

    public Asignacion getNuevaAsignacion() {
        return nuevaAsignacion;
    }

    public void setNuevaAsignacion(Asignacion nuevaAsignacion) {
        this.nuevaAsignacion = nuevaAsignacion;
    }

    public int getOpcionLovRecurso() {
        return opcionLovRecurso;
    }

    public void setOpcionLovRecurso(int opcionLovRecurso) {
        this.opcionLovRecurso = opcionLovRecurso;
    }

    public Asignacion getSeleccionAsignacion() {
        return seleccionAsignacion;
    }

    public void setSeleccionAsignacion(Asignacion seleccionAsignacion) {
        this.seleccionAsignacion = seleccionAsignacion;
    }
}
