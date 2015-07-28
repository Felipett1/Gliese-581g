package co.controHoras.controlador;

import co.entidades.Actividades;
import co.entidades.Cronograma;
import co.entidades.Empleados;
import co.entidades.RegistroHorasDetallado;
import co.interfaces.administrar.IAdministrarReportes;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.ExcelExporter;
import org.primefaces.component.export.Exporter;
import org.primefaces.component.export.XMLExporter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author 908036
 */
@ManagedBean
@SessionScoped
public class ControlReportes implements Serializable {

    @EJB
    IAdministrarReportes administrarReportes;
    private List<Cronograma> cronogramaTotal;
    private List<Actividades> listaActividades, filtroListaActividades, listaSeleccionActividades;
    private List<RegistroHorasDetallado> detalleHorasRegistradas;
    private List<Empleados> listaEmpleados;
    private String[] listaEmpleadosSeleccionados;
    private Date fechaInicio, fechaFin;
    private SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
    private String formatoReporte, nombreCronogramaXLS, nombreCronogramaXML;

    public ControlReportes() {
        listaEmpleados = new ArrayList<Empleados>();
        detalleHorasRegistradas = new ArrayList<RegistroHorasDetallado>();
        formatoReporte = "xls";
        nombreCronogramaXLS = "Cronograma_XLS_(" + formato.format(new Date()) + ")";
        nombreCronogramaXML = "Cronograma_XML_(" + formato.format(new Date()) + ")";       
    }

    public void generarReporteDetalleRegistroHoras() throws IOException {
        String condicionConsultaHoras = "";
        condicionConsultaHoras = verificarFechas(condicionConsultaHoras);
        condicionConsultaHoras = verificarEmpleados(condicionConsultaHoras);
        condicionConsultaHoras = verificarActividades(condicionConsultaHoras);
        //System.out.println("condicionConsultaHoras: " + condicionConsultaHoras);
        detalleHorasRegistradas = administrarReportes.obtenerDetalleRegistroHoras(condicionConsultaHoras);
        if (formatoReporte.equals("xls")) {
            generarReporteXLS();
        } else if (formatoReporte.equals("xml")) {
            generarReporteXML();
        }
    }

    public String verificarFechas(String condicionConsultaHoras) {
        if (fechaInicio != null && fechaFin != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            condicionConsultaHoras = "AND RH.FECHA BETWEEN TO_DATE('" + formato.format(fechaInicio) + "','dd/mm/yyyy') AND (TO_DATE('" + formato.format(fechaFin) + "','dd/mm/yyyy') + 1) ";
        }
        return condicionConsultaHoras;
    }

    public String verificarEmpleados(String condicionConsultaHoras) {
        if (listaEmpleadosSeleccionados != null && listaEmpleadosSeleccionados.length > 0) {
            String codigosEmpleados = "(";
            for (int i = 0; i < listaEmpleadosSeleccionados.length; i++) {
                String cadena = listaEmpleadosSeleccionados[i];
                String idEmpleado = cadena.substring(0, (cadena.lastIndexOf(":") - 1)).trim();
                if (i != (listaEmpleadosSeleccionados.length - 1)) {
                    codigosEmpleados = codigosEmpleados + idEmpleado + ", ";
                } else {
                    codigosEmpleados = codigosEmpleados + idEmpleado + ") ";
                }
            }
            condicionConsultaHoras += "AND ASI.ID_EMPLEADO IN " + codigosEmpleados;
        }
        return condicionConsultaHoras;
    }

    public String verificarActividades(String condicionConsultaHoras) {
        if (listaSeleccionActividades != null && listaSeleccionActividades.size() > 0) {
            String codigosActividades = "(";
            for (int i = 0; i < listaSeleccionActividades.size(); i++) {
                if (i != (listaSeleccionActividades.size() - 1)) {
                    codigosActividades = codigosActividades + listaSeleccionActividades.get(i).getId().toString() + ", ";
                } else {
                    codigosActividades = codigosActividades + listaSeleccionActividades.get(i).getId().toString() + ") ";
                }
            }
            condicionConsultaHoras += "AND ASI.ID_ACTIVIDAD IN " + codigosActividades;
        }
        return condicionConsultaHoras;
    }

    public void generarReporteXML() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        DataTable tabla = (DataTable) context.getViewRoot().findComponent("formExportar:detalleRegistroHoras");
        Exporter exporter = new XMLExporter();
        exporter.export(context, tabla, "DetalleRegistroHoras_XML", false, false, "UTF-8", null, null);
        context.responseComplete();
    }

    public void generarReporteXLS() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        DataTable tabla = (DataTable) context.getViewRoot().findComponent("formExportar:detalleRegistroHoras");
        Exporter exporter = new ExcelExporter();
        exporter.export(context, tabla, "DetalleRegistroHoras_XLS", false, false, "UTF-8", null, null);
        context.responseComplete();
    }

    public void limpiarParametros() {
        RequestContext context = RequestContext.getCurrentInstance();
        fechaInicio = null;
        fechaFin = null;
        listaEmpleadosSeleccionados = null;
        listaSeleccionActividades = null;
        context.update("formularioPrincipal");
        context.update("formularioDialogos:tblActividades");
    }

    public void seleccionarActividad() {
        RequestContext context = RequestContext.getCurrentInstance();
        filtroListaActividades = null;
        context.execute("PF('actividadesDialogo').hide()");
        context.reset("formularioDialogos:tblActividades:globalFilter");
        context.update("formularioDialogos:tblActividades");
        context.update("formularioPrincipal:panel:actividades");
        listaActividades = null;
    }

    public void cancelarSeleccionActividad() {
        filtroListaActividades = null;
        listaActividades = null;
    }
    //GETTERS AND SETTERS

    public List<Cronograma> getCronogramaTotal() {
        cronogramaTotal = administrarReportes.consultarCronogramaDetallado();
        return cronogramaTotal;
    }

    public List<RegistroHorasDetallado> getDetalleHorasRegistradas() {
        //detalleHorasRegistradas = administrarReportes.obtenerDetalleRegistroHoras();
        return detalleHorasRegistradas;
    }

    public List<Empleados> getListaEmpleados() {
        listaEmpleados = administrarReportes.consultarEmpleados();
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleados> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public String[] getListaEmpleadosSeleccionados() {
        return listaEmpleadosSeleccionados;
    }

    public void setListaEmpleadosSeleccionados(String[] listaEmpleadosSeleccionados) {
        this.listaEmpleadosSeleccionados = listaEmpleadosSeleccionados;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<Actividades> getFiltroListaActividades() {
        return filtroListaActividades;
    }

    public void setFiltroListaActividades(List<Actividades> filtroListaActividades) {
        this.filtroListaActividades = filtroListaActividades;
    }

    public List<Actividades> getListaActividades() {
        if (listaActividades == null) {
            listaActividades = administrarReportes.consultarActividadesHijas();
        }
        return listaActividades;
    }

    public void setListaActividades(List<Actividades> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public List<Actividades> getListaSeleccionActividades() {
        return listaSeleccionActividades;
    }

    public void setListaSeleccionActividades(List<Actividades> listaSeleccionActividades) {
        this.listaSeleccionActividades = listaSeleccionActividades;
    }

    public String getFormatoReporte() {
        return formatoReporte;
    }

    public void setFormatoReporte(String formatoReporte) {
        this.formatoReporte = formatoReporte;
    }

    public String getNombreCronogramaXLS() {
        return nombreCronogramaXLS;
    }

    public String getNombreCronogramaXML() {
        return nombreCronogramaXML;
    }
}
