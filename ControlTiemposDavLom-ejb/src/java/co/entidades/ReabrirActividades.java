/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 908036
 */
@Entity
@Table(name = "REABRIR_ACTIVIDADES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReabrirActividades.findAll", query = "SELECT r FROM ReabrirActividades r")})
public class ReabrirActividades implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigInteger id;
    //@Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE_RETRABAJO")
    private BigDecimal porcentajeRetrabajo;
    @JoinColumn(name = "CAUSA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Causas causa;
    @JoinColumn(name = "ACTIVIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Actividades actividad;
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "FECHA_INICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicial;
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;
    @Column(name = "HORAS_ESTIMADAS")
    private BigDecimal horasEstimadas;

    public ReabrirActividades() {
        /*
         * fechaInicial = new Date(); fechaFinal = new Date(); observacion = "";
         */
    }

    public ReabrirActividades(BigInteger id) {
        this.id = id;
    }

    public ReabrirActividades(BigInteger id, String observacion, String estado, Date fechaInicial, Date fechaFinal) {
        this.id = id;
        this.observacion = observacion;
        this.estado = estado;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Causas getCausa() {
        return causa;
    }

    public void setCausa(Causas causa) {
        this.causa = causa;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public void setActividad(Actividades actividad) {
        this.actividad = actividad;
    }

    public BigDecimal getPorcentajeRetrabajo() {
        return porcentajeRetrabajo;
    }

    public void setPorcentajeRetrabajo(BigDecimal porcentajeRetrabajo) {
        this.porcentajeRetrabajo = porcentajeRetrabajo;
    }

    public BigDecimal getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(BigDecimal horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReabrirActividades)) {
            return false;
        }
        ReabrirActividades other = (ReabrirActividades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.ReabrirActividades[ id=" + id + " ]";
    }
}
