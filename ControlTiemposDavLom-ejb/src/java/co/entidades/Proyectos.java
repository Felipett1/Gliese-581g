/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 908034
 */
@Entity
@Table(name = "PROYECTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p")})
public class Proyectos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "PROYECTO")
    private String proyecto;
    @Size(max = 255)
    @Column(name = "TAREA_PADRE")
    private String tareaPadre;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    public Proyectos() {
    }

    public Proyectos(Long id) {
        this.id = id;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getTareaPadre() {
        return tareaPadre;
    }

    public void setTareaPadre(String tareaPadre) {
        this.tareaPadre = tareaPadre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.Proyectos[ id=" + id + " ]";
    }
    
}
