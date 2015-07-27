package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.*;

/**
 *
 * @author 908034
 */
@Entity
@SqlResultSetMapping(name = "RegistroHorasDetallado",
entities = {
    @EntityResult(entityClass = RegistroHorasDetallado.class,
    fields = {
        @FieldResult(name = "id", column = "ID"),
        @FieldResult(name = "fecha", column = "FECHA"),
        @FieldResult(name = "nombreEmpleado", column = "NOMBRE_EMPLEADO"),
        @FieldResult(name = "actividad", column = "ACTIVIDAD"),
        @FieldResult(name = "horaInicial", column = "HORA_INICIAL"),
        @FieldResult(name = "horaFinal", column = "HORA_FINAL"),
        @FieldResult(name = "totalHoras", column = "TOTAL_HORAS"),
        @FieldResult(name = "porcentajeRegistrado", column = "PORCENTAJE_REGISTRADO"),
        @FieldResult(name = "observaciones", column = "OBSERVACIONES")
    })
})
public class RegistroHorasDetallado implements Serializable {

    @Id
    private BigInteger id;
    private String fecha;
    private String nombreEmpleado;
    private String actividad;
    private BigDecimal horaInicial;
    private BigDecimal horaFinal;
    private BigDecimal totalHoras;
    private BigDecimal porcentajeRegistrado;
    private String observaciones;

    public RegistroHorasDetallado() {
    }

    public RegistroHorasDetallado(BigInteger id, String actividad) {
        this.id = id;
        this.actividad = actividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(BigDecimal horaFinal) {
        this.horaFinal = horaFinal;
    }

    public BigDecimal getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(BigDecimal horaInicial) {
        this.horaInicial = horaInicial;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPorcentajeRegistrado() {
        if (porcentajeRegistrado != null) {
            return porcentajeRegistrado.toString() + "%";
        } else {
            return null;
        }
    }

    public void setPorcentajeRegistrado(BigDecimal porcentajeRegistrado) {
        this.porcentajeRegistrado = porcentajeRegistrado;
    }

    public BigDecimal getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(BigDecimal totalHoras) {
        this.totalHoras = totalHoras;
    }
}
