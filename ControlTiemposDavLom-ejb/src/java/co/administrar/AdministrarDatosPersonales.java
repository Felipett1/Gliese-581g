package co.administrar;

import co.entidades.Empleados;
import co.interfaces.administrar.IAdministrarDatosPersonales;
import co.interfaces.persistencia.IPersistenciaEmpleados;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author 908036
 */
@Stateful
public class AdministrarDatosPersonales implements IAdministrarDatosPersonales{
    
    @EJB
    private IPersistenciaEmpleados persistenciaEmpleados;
    
    @Override
    public Empleados obtenerEmpleado(BigInteger idEmpleado){
        return persistenciaEmpleados.obtenerEmpleado(idEmpleado);
    }
}
