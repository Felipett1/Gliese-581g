package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 908036
 */
@Entity
@Table(name = "ASIGNACIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignaciones.findAll", query = "SELECT a FROM Asignaciones a")})
public class Asignaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "EMPLOYEE")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Actividades idActividad;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "HORAS_ASIGNADAS")
    private BigDecimal horasAsignadas;
    @JoinColumn(name = "ID_REABRIR_ACTIVIDADES", referencedColumnName = "ID")
    @ManyToOne
    private ReabrirActividades idReabrirActividades;
    @Transient
    private String prioridad;

    public Asignaciones() {
    }

    public Asignaciones(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Actividades getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Actividades idActividad) {
        this.idActividad = idActividad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getHorasAsignadas() {
        return horasAsignadas;
    }

    public void setHorasAsignadas(BigDecimal horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }

    public ReabrirActividades getIdReabrirActividades() {
        return idReabrirActividades;
    }

    public void setIdReabrirActividades(ReabrirActividades idReabrirActividades) {
        this.idReabrirActividades = idReabrirActividades;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
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
        if (!(object instanceof Asignaciones)) {
            return false;
        }
        Asignaciones other = (Asignaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.Asignaciones[ id=" + id + " ]";
    }
}
