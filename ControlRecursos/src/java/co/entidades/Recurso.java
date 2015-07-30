package co.entidades;

import java.io.Serializable;
import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDENTIFICACION")
    private Long identificacion;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    @JoinColumn(name = "ROL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rol rol;
    @Transient
    private String nombreCompleto;

    public Recurso() {
    }

    public Recurso(Long identificacion) {
        this.identificacion = identificacion;
    }

    public Recurso(String nombre, String primerApellido, Long identificacion) {
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

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombreCompleto() {
        if (nombre != null && primerApellido != null) {
            this.nombreCompleto = nombre + " " + primerApellido;
            if (segundoApellido != null) {
                this.nombreCompleto = this.nombreCompleto + " " + segundoApellido;
            }
        }
        return nombreCompleto;
    }

    @Override
    public String toString() {
        return "co.entidades.Recurso[ id=" + identificacion + " ]";
    }
}
