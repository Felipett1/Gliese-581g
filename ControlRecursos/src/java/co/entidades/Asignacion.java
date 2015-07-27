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
@Table(name = "ASIGNACION")
@XmlRootElement
public class Asignacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN_ESTIMADA")
    @Temporal(TemporalType.DATE)
    private Date fechaFinEstimada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORAS")
    private BigDecimal horas;
    @JoinColumn(name = "ID_RECURSO", referencedColumnName = "IDENTIFICACION")
    @ManyToOne(optional = false)
    private Recurso idRecurso;
    @JoinColumn(name = "ID_ACTA", referencedColumnName = "NUMERO_ACTA")
    @ManyToOne(optional = false)
    private Acta idActa;

    public Asignacion() {
    }

    public Asignacion(BigDecimal id) {
        this.id = id;
    }

    public Asignacion(BigDecimal id, String estado, Date fechaInicio, BigDecimal horas) {
        this.id = id;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.horas = horas;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public Recurso getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Recurso idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Acta getIdActa() {
        return idActa;
    }

    public void setIdActa(Acta idActa) {
        this.idActa = idActa;
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
        if (!(object instanceof Asignacion)) {
            return false;
        }
        Asignacion other = (Asignacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.Asignacion[ id=" + id + " ]";
    }
    
}
