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
 * @author 908034
 */
@Entity
@Table(name = "REGISTRO_HORAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroHoras.findAll", query = "SELECT r FROM RegistroHoras r")})
public class RegistroHoras implements Serializable {
    
    private static final long serialVersionUID = 1L;    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "HORA_INICIAL")
    private BigDecimal horaInicial;
    @Basic(optional = false)
    @Column(name = "HORA_FINAL")
    private BigDecimal horaFinal;
    @Column(name = "TOTAL_HORAS")
    private BigDecimal totalHoras;
    @Size(max = 255)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "PORCENTAJE_REGISTRADO")
    private BigDecimal porcentajeRegistrado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "ASIGNACION", referencedColumnName = "ID")
    @ManyToOne
    private Asignaciones asignacion;

    public RegistroHoras() {
    }

    public RegistroHoras(Long id) {
        this.id = id;
    }

    public RegistroHoras(Long id, Date fecha, BigDecimal horaInicial, BigDecimal horaFinal) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(BigDecimal horaInicial) {
        this.horaInicial = horaInicial;
    }

    public BigDecimal getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(BigDecimal horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getPorcentajeRegistrado() {
        return porcentajeRegistrado;
    }

    public void setPorcentajeRegistrado(BigDecimal porcentajeRegistrado) {
        this.porcentajeRegistrado = porcentajeRegistrado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Asignaciones getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignaciones asignacion) {
        this.asignacion = asignacion;
    }

    public BigDecimal getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(BigDecimal totalHoras) {
        this.totalHoras = totalHoras;
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
        if (!(object instanceof RegistroHoras)) {
            return false;
        }
        RegistroHoras other = (RegistroHoras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.RegistroHoras[ id=" + id + " ]";
    }
}
