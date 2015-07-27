package co.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 908036
 */
@Entity
@Table(name = "RECURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r")})
public class Recurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDENTIFICACION")
    private long identificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Size(max = 100)
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    @JoinColumn(name = "ROL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rol rol;

    public Recurso() {
    }

    public Recurso(long identificacion) {
        this.identificacion = identificacion;
    }

    public Recurso(String nombre, String primerApellido, long identificacion) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "co.entidades.Recurso[ id=" + identificacion + " ]";
    }
    
}
