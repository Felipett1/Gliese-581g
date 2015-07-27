package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "HISTORICO_ESTADISTICAS")
@XmlRootElement
public class HistoricoEstadisticas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "PROYECTO")
    private BigInteger proyecto;
    @Column(name = "CPI")
    private BigDecimal cpi;
    @Column(name = "SPI")
    private BigDecimal spi;
    @Column(name = "PV")
    private BigDecimal pv;
    @Column(name = "EV")
    private BigDecimal ev;
    @Column(name = "AC")
    private BigDecimal ac;

    public HistoricoEstadisticas() {
    }

    public HistoricoEstadisticas(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getProyecto() {
        return proyecto;
    }

    public void setProyecto(BigInteger proyecto) {
        this.proyecto = proyecto;
    }

    public BigDecimal getCpi() {
        return cpi;
    }

    public void setCpi(BigDecimal cpi) {
        this.cpi = cpi;
    }

    public BigDecimal getSpi() {
        return spi;
    }

    public void setSpi(BigDecimal spi) {
        this.spi = spi;
    }

    public BigDecimal getAc() {
        return ac;
    }

    public void setAc(BigDecimal ac) {
        this.ac = ac;
    }

    public BigDecimal getEv() {
        return ev;
    }

    public void setEv(BigDecimal ev) {
        this.ev = ev;
    }

    public BigDecimal getPv() {
        return pv;
    }

    public void setPv(BigDecimal pv) {
        this.pv = pv;
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
        if (!(object instanceof HistoricoEstadisticas)) {
            return false;
        }
        HistoricoEstadisticas other = (HistoricoEstadisticas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.administrar.HistoricoEstadisticas[ id=" + id + " ]";
    }
    
}
