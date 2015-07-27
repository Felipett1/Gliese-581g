package co.controlador;

import co.entidades.HistoricoEstadisticas;
import co.interfaces.administrar.IAdministrarEstadisticas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@SessionScoped
public class ControlEstadisticas implements Serializable {

    @EJB
    private IAdministrarEstadisticas administrarEstadisticas;
    private List<HistoricoEstadisticas> listaHistoricoPrincipal;
    private LineChartModel estadoProyecto;
    private LineChartModel estadoEsfuerzo;
    private SimpleDateFormat formato;
    private Integer cantidadRegistros;

    public ControlEstadisticas() {
        formato = new SimpleDateFormat("yyyy/MM/dd");
        cantidadRegistros = 5;
    }

    @PostConstruct
    public void init() {
        consultarHistoricosProyecto();
    }

    public void consultarHistoricosProyecto() {
        listaHistoricoPrincipal = administrarEstadisticas.buscarHistoricosProyecto(cantidadRegistros);

        estadoProyecto = initLinearModel();
        estadoProyecto.setTitle("Evolución CPI & SPI");
        estadoProyecto.setAnimate(true);
        estadoProyecto.setLegendPosition("ne");
        estadoProyecto.getAxis(AxisType.Y).setLabel("Estado");
        estadoProyecto.getAxis(AxisType.Y).setTickFormat("%.2f");
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickCount(listaHistoricoPrincipal.size());
        axis.setTickFormat("%d/%m/%y");
        estadoProyecto.getAxes().put(AxisType.X, axis);

        estadoEsfuerzo = iniciarEstadoEsfuerzo();
        estadoEsfuerzo.setTitle("Evolución esfuerzo");
        estadoEsfuerzo.setAnimate(true);
        estadoEsfuerzo.setLegendPosition("ne");
        estadoEsfuerzo.getAxis(AxisType.Y).setLabel("Estado");
        estadoEsfuerzo.getAxis(AxisType.Y).setTickFormat("%.2f");
        DateAxis axis2 = new DateAxis("Fecha");
        axis2.setTickCount(listaHistoricoPrincipal.size());
        axis2.setTickFormat("%d/%m/%y");
        estadoEsfuerzo.getAxes().put(AxisType.X, axis2);
    }

    public LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries cpi = new LineChartSeries();
        cpi.setLabel("CPI");
        for (HistoricoEstadisticas historicoEstadisticas : listaHistoricoPrincipal) {
            cpi.set(formato.format(historicoEstadisticas.getFecha()), historicoEstadisticas.getCpi());
        }

        LineChartSeries spi = new LineChartSeries();
        spi.setLabel("SPI");
        for (HistoricoEstadisticas historicoEstadisticas : listaHistoricoPrincipal) {
            spi.set(formato.format(historicoEstadisticas.getFecha()), historicoEstadisticas.getSpi());
        }

        LineChartSeries std = new LineChartSeries();
        std.setLabel("STD");
        for (HistoricoEstadisticas historicoEstadisticas : listaHistoricoPrincipal) {
            std.set(formato.format(historicoEstadisticas.getFecha()), 1);
        }

        model.addSeries(cpi);
        model.addSeries(spi);
        model.addSeries(std);

        return model;
    }

    public LineChartModel iniciarEstadoEsfuerzo() {
        LineChartModel model = new LineChartModel();

        LineChartSeries pv = new LineChartSeries();
        pv.setLabel("Valor Planeado (PV)");
        for (HistoricoEstadisticas historicoEstadisticas : listaHistoricoPrincipal) {
            pv.set(formato.format(historicoEstadisticas.getFecha()), historicoEstadisticas.getPv());
        }

        LineChartSeries ev = new LineChartSeries();
        ev.setLabel("Valor ganado (EV)");
        for (HistoricoEstadisticas historicoEstadisticas : listaHistoricoPrincipal) {
            ev.set(formato.format(historicoEstadisticas.getFecha()), historicoEstadisticas.getEv());
        }

        LineChartSeries ac = new LineChartSeries();
        ac.setLabel("Valor real (AC)");
        for (HistoricoEstadisticas historicoEstadisticas : listaHistoricoPrincipal) {
            ac.set(formato.format(historicoEstadisticas.getFecha()), historicoEstadisticas.getAc());
        }

        model.addSeries(pv);
        model.addSeries(ev);
        model.addSeries(ac);

        return model;
    }

    public LineChartModel getEstadoProyecto() {
        return estadoProyecto;
    }

    public LineChartModel getEstadoEsfuerzo() {
        return estadoEsfuerzo;
    }

    public Integer getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }
}
