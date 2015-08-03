package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ACTA")
@XmlRootElement
public class Acta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_ACTA")
    private BigDecimal numeroActa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRESUPUESTO")
    private BigDecimal presupuesto;
    @Column(name = "FECHA_FIN_ESTIMADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinEstimada;
    @Column(name = "HORAS")
    private BigDecimal horas;
    @Column(name = "ESTADO")
    private String estado;

    public Acta() {
    }

    public Acta(BigDecimal numeroActa) {
        this.numeroActa = numeroActa;
    }

    public Acta(BigDecimal numeroActa, String nombre, Date fechaInicio, BigDecimal presupuesto) {
        this.numeroActa = numeroActa;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.presupuesto = presupuesto;
    }

    public BigDecimal getNumeroActa() {
        return numeroActa;
    }

    public void setNumeroActa(BigDecimal numeroActa) {
        this.numeroActa = numeroActa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Date getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public void setFechaFinEstimada(Date fechaFinEstimada) {
        this.fechaFinEstimada = fechaFinEstimada;
    }

    public BigDecimal getHoras() {
        return horas;
    }

    public void setHoras(BigDecimal horas) {
        this.horas = horas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroActa != null ? numeroActa.hashCode() : 0);
        return hash;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acta)) {
            return false;
        }
        Acta other = (Acta) object;
        if ((this.numeroActa == null && other.numeroActa != null) || (this.numeroActa != null && !this.numeroActa.equals(other.numeroActa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.Acta[ numeroActa=" + numeroActa + " ]";
    }
}
