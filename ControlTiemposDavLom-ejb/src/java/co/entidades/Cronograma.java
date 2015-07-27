package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author 908034
 */
@Entity
@SqlResultSetMapping(name = "CronogramaDetallado",
entities = {
    @EntityResult(entityClass = Cronograma.class,
    fields = {
        @FieldResult(name = "id", column = "ID"),
        @FieldResult(name = "idUnico", column = "ID_UNICO"),
        @FieldResult(name = "actividad_Padre", column = "ACTIVIDAD_PADRE"),
        @FieldResult(name = "wbs", column = "WBS"),
        @FieldResult(name = "actividad", column = "ACTIVIDAD"),
        @FieldResult(name = "fechaInicio", column = "FECHA_INICIO"),
        @FieldResult(name = "fechaFinal", column = "FECHA_FINAL"),
        @FieldResult(name = "horas_baseline", column = "HORAS_BASELINE"),
        @FieldResult(name = "horas_trabajadas", column = "HORAS_TRABAJADAS"),
        @FieldResult(name = "horas_re_trabajadas", column = "HORAS_RE_TRABAJADAS"),
        @FieldResult(name = "porcentaje_Real", column = "PORCENTAJE_REAL"),
        @FieldResult(name = "duracion", column = "DURACION"),
        @FieldResult(name = "dias_corte", column = "DIAS_CORTE"),
        @FieldResult(name = "ac", column = "AC"),
        @FieldResult(name = "duracion", column = "PV"),
        @FieldResult(name = "duracion", column = "EV"),
        @FieldResult(name = "duracion", column = "CV"),
        @FieldResult(name = "duracion", column = "SV"),
        @FieldResult(name = "duracion", column = "CPI"),
        @FieldResult(name = "duracion", column = "SPI"),
        @FieldResult(name = "duracion", column = "EAC"),
        @FieldResult(name = "duracion", column = "ETC"),
        @FieldResult(name = "duracion", column = "VAC")
    })
})
public class Cronograma implements Serializable {

    @Id
    private BigInteger id;
    private BigInteger idUnico;
    private String actividad_Padre;
    private String wbs;
    private String actividad;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinal;
    private BigDecimal horas_baseline;
    private BigDecimal horas_trabajadas;
    private BigDecimal horas_re_trabajadas;
    private BigDecimal porcentaje_Real;
    private BigInteger duracion;
    private BigInteger dias_corte;
    private BigDecimal ac;
    private BigDecimal pv;
    private BigDecimal ev;
    private BigDecimal cv;
    private BigDecimal sv;
    private BigDecimal cpi;
    private BigDecimal spi;
    private BigDecimal eac;
    private BigDecimal etc;
    private BigDecimal vac;

    public Cronograma() {
    }

    public BigDecimal longitudDecimales(BigDecimal value) {
        if (value != null && value.scale() > 0) {
            return value.setScale(2, RoundingMode.CEILING);
        } else {
            return value;
        }
    }

    public Cronograma(BigInteger id, String actividad) {
        this.id = id;
        this.actividad = actividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getActividad_Padre() {
        return actividad_Padre;
    }

    public void setActividad_Padre(String actividad_Padre) {
        this.actividad_Padre = actividad_Padre;
    }

    public BigDecimal getHoras_baseline() {
        return horas_baseline;
    }

    public void setHoras_baseline(BigDecimal horas_baseline) {
        this.horas_baseline = horas_baseline;
    }

    public BigDecimal getHoras_trabajadas() {
        return horas_trabajadas;
    }

    public void setHoras_trabajadas(BigDecimal horas_trabajadas) {
        this.horas_trabajadas = horas_trabajadas;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getWbs() {
        return wbs;
    }

    public void setWbs(String wbs) {
        this.wbs = wbs;
    }

    public String getPorcentaje_Real() {
        if (porcentaje_Real != null) {
            if (porcentaje_Real.scale() > 0) {
                return porcentaje_Real.setScale(2, RoundingMode.CEILING).toString() + "%";
            } else {
                return porcentaje_Real.toString() + "%";
            }
        } else {
            return null;
        }
    }

    public void setPorcentaje_Real(BigDecimal porcentaje_Real) {
        this.porcentaje_Real = porcentaje_Real;
    }

    public BigInteger getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(BigInteger idUnico) {
        this.idUnico = idUnico;
    }

    public BigDecimal getHoras_re_trabajadas() {
        return horas_re_trabajadas;
    }

    public void setHoras_re_trabajadas(BigDecimal horas_re_trabajadas) {
        this.horas_re_trabajadas = horas_re_trabajadas;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getCpi() {
        return longitudDecimales(cpi);
    }

    public void setCpi(BigDecimal cpi) {
        this.cpi = cpi;
    }

    public BigDecimal getCv() {
        return longitudDecimales(cv);
    }

    public void setCv(BigDecimal cv) {
        this.cv = cv;
    }

    public BigInteger getDias_corte() {
        return dias_corte;
    }

    public void setDias_corte(BigInteger dias_corte) {
        this.dias_corte = dias_corte;
    }

    public BigInteger getDuracion() {
        return duracion;
    }

    public void setDuracion(BigInteger duracion) {
        this.duracion = duracion;
    }

    public BigDecimal getEac() {
        return longitudDecimales(eac);
    }

    public void setEac(BigDecimal eac) {
        this.eac = eac;
    }

    public BigDecimal getEtc() {
        return longitudDecimales(etc);
    }

    public void setEtc(BigDecimal etc) {
        this.etc = etc;
    }

    public BigDecimal getEv() {
        return longitudDecimales(ev);
    }

    public void setEv(BigDecimal ev) {
        this.ev = ev;
    }

    public BigDecimal getPv() {
        return longitudDecimales(pv);
    }

    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }

    public BigDecimal getSpi() {
        return longitudDecimales(spi);
    }

    public void setSpi(BigDecimal spi) {
        this.spi = spi;
    }

    public BigDecimal getSv() {
        return longitudDecimales(sv);
    }

    public void setSv(BigDecimal sv) {
        this.sv = sv;
    }

    public BigDecimal getVac() {
        return longitudDecimales(vac);
    }

    public void setVac(BigDecimal vac) {
        this.vac = vac;
    }

    public BigDecimal getAc() {
        return ac;
    }

    public void setAc(BigDecimal ac) {
        this.ac = ac;
    }
}
