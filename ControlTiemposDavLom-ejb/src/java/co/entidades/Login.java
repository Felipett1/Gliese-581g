/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 908036
 */
@Entity
@Table(name = "LOGIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME_USER")
    private String nameUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "EMPLOYEE")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ADMINISTRADOR")
    private String administrado;

    public Login() {
    }

    public Login(BigDecimal id) {
        this.id = id;
    }

    public Login(BigDecimal id, String nameUser, String password) {
        this.id = id;
        this.nameUser = nameUser;
        this.password = password;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.Login[ id=" + id + " ]";
    }

    public String getAdministrador() {
        return administrado;
    }

    public void setAdministrador(String admin) {
        this.administrado = admin;
    }
}
