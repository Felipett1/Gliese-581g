package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "HISTORICO_BLOQUEOS")
@XmlRootElement
public class HistoricoBloqueos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Size(max = 20)
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID")
    @ManyToOne
    private Actividades idActividad;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "EMPLOYEE")
    @ManyToOne
    private Empleados idEmpleado;

    public HistoricoBloqueos() {
    }

    public HistoricoBloqueos(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Actividades getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Actividades idActividad) {
        this.idActividad = idActividad;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
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
        if (!(object instanceof HistoricoBloqueos)) {
            return false;
        }
        HistoricoBloqueos other = (HistoricoBloqueos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.HistoricoBloqueos[ id=" + id + " ]";
    }
    
}
