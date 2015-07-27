package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 908034
 */
@Entity
@Table(name = "ACTIVIDADES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")})
public class Actividades implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigInteger id;
    //@Column(name = "ACTIVIDAD_PADRE")
    @JoinColumn(name = "ACTIVIDAD_PADRE", referencedColumnName = "ID")
    @ManyToOne
    private Actividades actividadPadre;
    @Size(max = 255)
    @Column(name = "ACTIVIDAD")
    private String actividad;
    @Size(max = 20)
    @Column(name = "WBS")
    private String wbs;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HORAS_BASELINE")
    private BigDecimal horasBaseline;
    @Column(name = "PORCENTAJE_REAL")
    private BigDecimal porcentajeReal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UNICO")
    private BigInteger idUnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;
    @Column(name = "SPRINT")
    private BigDecimal sprint;
    @Column(name = "BLOQUEANTE")
    private String bloqueante;
    @Transient
    private boolean estadoBloqueante;
    @Transient
    private boolean asignado;
    @Transient
    private boolean asignadoReAbierto;
    @Transient
    private List<Asignaciones> asignacion;
    @Transient
    private List<Asignaciones> asignacionReAbierta;
    @Transient
    private boolean reAbierto;
    @Transient
    private ReabrirActividades infoAbierta;
    @Transient
    private BigDecimal porcentajeEmpleado;

    public Actividades() {
    }

    public Actividades(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Actividades getActividadPadre() {
        return actividadPadre;
    }

    public void setActividadPadre(Actividades actividadPadre) {
        this.actividadPadre = actividadPadre;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getWbs() {
        return wbs;
    }

    public void setWbs(String wbs) {
        this.wbs = wbs;
    }

    public BigDecimal getHorasBaseline() {
        return horasBaseline;
    }

    public void setHorasBaseline(BigDecimal horasBaseline) {
        this.horasBaseline = horasBaseline;
    }

    public BigDecimal getPorcentajeReal() {
        return porcentajeReal;
    }

    public void setPorcentajeReal(BigDecimal porcentajeReal) {
        this.porcentajeReal = porcentajeReal;
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

    public BigInteger getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(BigInteger idUnico) {
        this.idUnico = idUnico;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

    public List<Asignaciones> getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(List<Asignaciones> asignacion) {
        this.asignacion = asignacion;
    }

    public ReabrirActividades getInfoAbierta() {
        return infoAbierta;
    }

    public void setInfoAbierta(ReabrirActividades infoAbierta) {
        this.infoAbierta = infoAbierta;
    }

    public boolean isReAbierto() {
        return reAbierto;
    }

    public void setReAbierto(boolean reAbierto) {
        this.reAbierto = reAbierto;
    }

    public List<Asignaciones> getAsignacionReAbierta() {
        return asignacionReAbierta;
    }

    public void setAsignacionReAbierta(List<Asignaciones> asignacionReAbierta) {
        this.asignacionReAbierta = asignacionReAbierta;
    }

    public boolean isAsignadoReAbierto() {
        return asignadoReAbierto;
    }

    public void setAsignadoReAbierto(boolean asignadoReAbierto) {
        this.asignadoReAbierto = asignadoReAbierto;
    }

    public BigDecimal getPorcentajeEmpleado() {
        return porcentajeEmpleado;
    }

    public void setPorcentajeEmpleado(BigDecimal porcentajeEmpleado) {
        this.porcentajeEmpleado = porcentajeEmpleado;
    }

    public String getBloqueante() {
        return bloqueante;
    }

    public void setBloqueante(String bloqueante) {
        this.bloqueante = bloqueante;
    }

    public boolean isEstadoBloqueante() {
        if (bloqueante.equals("S")) {
            estadoBloqueante = true;
        } else {
            estadoBloqueante = false;
        }
        return estadoBloqueante;
    }

    public void setEstadoBloqueante(boolean estadoBloqueante) {
        if (estadoBloqueante == true) {
            this.bloqueante = "S";
        } else {
            this.bloqueante = "N";
        }
    }

    public BigDecimal getSprint() {
        return sprint;
    }

    public void setSprint(BigDecimal sprint) {
        this.sprint = sprint;
    }

    @Override
    public String toString() {
        return "co.entidades.Actividades[ id=" + id + " ]";
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
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
