/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.Acta;
import java.math.BigDecimal;

/**
 *
 * @author 908036
 */
public interface IPersistenciaActa {

    public boolean registrarActa(Acta acta);

    public boolean validarNumeroActa(BigDecimal numeroActa);
    
}
