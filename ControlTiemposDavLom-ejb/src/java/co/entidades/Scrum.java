package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 908036
 */
@Entity
@Table(name = "SCRUM")
@XmlRootElement
public class Scrum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SPRINT")
    private BigDecimal sprint;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Scrum() {
    }

    public Scrum(BigDecimal sprint) {
        this.sprint = sprint;
    }

    public BigDecimal getSprint() {
        return sprint;
    }

    public void setSprint(BigDecimal sprint) {
        this.sprint = sprint;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sprint != null ? sprint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scrum)) {
            return false;
        }
        Scrum other = (Scrum) object;
        if ((this.sprint == null && other.sprint != null) || (this.sprint != null && !this.sprint.equals(other.sprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.Scrum[ sprint=" + sprint + " ]";
    }
    
}
