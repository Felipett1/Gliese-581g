package co.controHoras.controlador;

import co.entidades.Empleados;
import co.interfaces.administrar.IAdministrarDatosPersonales;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControladorDatosPersonales {
    
    @EJB
    private IAdministrarDatosPersonales administrarDatosPersonales;
   
    private Empleados empleado;
    
    public ControladorDatosPersonales() {
    }
    
    public void iniciarEmpleado(BigInteger idEmpleado){
        empleado = administrarDatosPersonales.obtenerEmpleado(idEmpleado);
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }
}
