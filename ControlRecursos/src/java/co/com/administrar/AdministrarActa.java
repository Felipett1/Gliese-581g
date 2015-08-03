package co.com.administrar;

import co.interfaces.administrar.IAdministrarActa;
import co.interfaces.persistencia.IPersistenciaActa;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class AdministrarActa  implements IAdministrarActa{

    @EJB
    IPersistenciaActa persistenciaActa;

    @Override
    public boolean validarNumeroActa(BigDecimal numeroActa) {
        return persistenciaActa.validarNumeroActa(numeroActa);
    }
}
